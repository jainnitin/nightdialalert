package com.nightdialalert.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.nightdialalert.data.InternationalTimeZones;
import com.nightdialalert.data.InternationalTimeZones.CountryTimeZone;
import com.nightdialalert.data.USTimeZones;
import com.nightdialalert.data.USTimeZones.USStateTimeZone;
import com.nightdialalert.view.TimePreference;
import com.nightdialalert.view.UserSettingActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;

@SuppressLint("SimpleDateFormat")
public class DestinationTimeCalculator {

    private static final String TAG = DestinationTimeCalculator.class.getSimpleName();

    /**
     * Returns Country/State Name and LocalTime(s) for a given phoneNumber or null if not
     * found/error/toll-free
     *
     * @param context
     * @param phoneNumber
     * @return
     */
    public static Pair<String, List<String>> getDestinationTime(Context context, String phoneNumber) {
        // init variables
        String currentStartTimeAtDestination = null;
        String currentLastTimeAtDestination = null;

        // Get an instance of DateFormat
        DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);

        // Get the CountryCode from the PhoneNumber
        PhoneNumber phoneNumberProto = null;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();
        try {
            // Parse PhoneNumber
            phoneNumberProto = phoneUtil.parse(phoneNumber, Locale.getDefault().getCountry());
        }
        catch (NumberParseException e) {
            Log.d(TAG, "Unable to parse phoneNumber " + e.toString());
            return null;
        }

        // Check the Number Validity
        if (!phoneUtil.isValidNumber(phoneNumberProto)) {
            Log.d(TAG, "Could not determine if the Phone Number " + phoneNumber + " is valid! FREE PASS!");
            return null;
        }

        // Check for Toll-Free Number
        PhoneNumberUtil.PhoneNumberType type = phoneUtil.getNumberType(phoneNumberProto);
        if (type == PhoneNumberUtil.PhoneNumberType.TOLL_FREE) {
            // Return right-away as we do not want to do anything for TOLL_FREE numbers
            Log.d(TAG, "TOLL_FREE numbers get a FreePass");
            return null;
        }
        // Determine GeoLocation
        String countryOrStateName = geocoder.getDescriptionForValidNumber(phoneNumberProto, Locale.getDefault(), Locale.getDefault().getCountry());
        if (countryOrStateName == null || countryOrStateName.trim().isEmpty()) {
            Log.d(TAG, "Failed to determine the GeoLocation. Free PASS!");
            return null;
        }

        // Lookup CountryName and GMT-Data from CountryCode
        int countryCode = phoneNumberProto.getCountryCode();
        Log.d(TAG, "Dialed Number is in " + countryOrStateName + " with CountryCode=" + countryCode);
        // Check if the CountryCode matches?
        if (!InternationalTimeZones.mapInternationalTimeZones.containsKey(countryCode)) {
            Log.d(TAG, "Bummer! No TZ data found for countryCode (" + countryCode + ")");
            return null;
        }

        // Lookup the TimeZones for USA
        if (countryCode == 1) {
            String stateName = USTimeZones.getStateName(countryOrStateName);
            // Determine TimeZone for the US state
            if (USTimeZones.mapUSStatesTimeZones.containsKey(stateName)) {
                USStateTimeZone stateTimeZones = USTimeZones.mapUSStatesTimeZones.get(stateName);
                boolean stateHasMultipleTimeZones = (!stateTimeZones.mStateGMTEnd.isEmpty());
                Log.d(TAG, "TimeZone(s) in " + countryOrStateName + " [" + stateTimeZones.mStateGMTStart + ((stateHasMultipleTimeZones) ? " to " + stateTimeZones.mStateGMTEnd + "]" : "]"));

                // Compute destination time
                df.setTimeZone(TimeZone.getTimeZone(stateTimeZones.mStateGMTStart));
                currentStartTimeAtDestination = df.format(new Date());
                if (stateHasMultipleTimeZones) {
                    df.setTimeZone(TimeZone.getTimeZone(stateTimeZones.mStateGMTEnd));
                    currentLastTimeAtDestination = df.format(new Date());
                    Log.d(TAG, "Current Time in " + stateName + " is " + currentStartTimeAtDestination + " or " + currentLastTimeAtDestination);
                    return new Pair<String, List<String>>(stateName, Arrays.asList(currentStartTimeAtDestination, currentLastTimeAtDestination));
                }
                else {
                    Log.d(TAG, "Current Time in " + stateName + " is " + currentStartTimeAtDestination);
                    return new Pair<String, List<String>>(stateName, Arrays.asList(currentStartTimeAtDestination));
                }
            }
            else {
                Log.d(TAG, "Bummer! USStatesMap does not contain stateName=" + stateName);
                return null;
            }
        }

        // Lookup International TimeZones for countries
        CountryTimeZone countryTimeZones = InternationalTimeZones.mapInternationalTimeZones.get(countryCode);
        boolean countryHasMultipleTimeZones = (!countryTimeZones.mCountryGMTEnd.isEmpty());
        Log.d(TAG, "TimeZone(s) in " + countryOrStateName + " [" + countryTimeZones.mCountryGMTStart + ((countryHasMultipleTimeZones) ? " to " + countryTimeZones.mCountryGMTEnd + "]" : "]"));

        // Compute destination time
        df.setTimeZone(TimeZone.getTimeZone(countryTimeZones.mCountryGMTStart));
        currentStartTimeAtDestination = df.format(new Date());
        if (countryHasMultipleTimeZones) {
            df.setTimeZone(TimeZone.getTimeZone(countryTimeZones.mCountryGMTEnd));
            currentLastTimeAtDestination = df.format(new Date());
            Log.d(TAG, "Current Time in " + countryOrStateName + " is between " + currentStartTimeAtDestination + " to " + currentLastTimeAtDestination);
            return new Pair<String, List<String>>(countryOrStateName, Arrays.asList(currentStartTimeAtDestination, currentLastTimeAtDestination));
        }
        else {
            Log.d(TAG, "Current Time in " + countryOrStateName + " is " + currentStartTimeAtDestination);
            return new Pair<String, List<String>>(countryOrStateName, Arrays.asList(currentStartTimeAtDestination));
        }
    }

    /**
     * Determines if a given time is within the configured Sleep/Wakeup time-periods
     *
     * @param context
     * @param destinationTime
     * @return
     */
    public static boolean isBetweenGraveYardHours(Context context, String destinationTime) {
        boolean isSplit = false;
        boolean isWithin = false;

        Date sleepTime = null;
        Date wakeTime = null;
        Date currentTime = null;

        SimpleDateFormat mdf = new SimpleDateFormat("HH:mm");   // Military Date
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // Date.SHORT
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            String sleepTimeStr = prefs.getString("prefSleepTime", UserSettingActivity.DEFAULT_SLEEP_TIME);
            String wakeTimeStr = prefs.getString("prefWakeTime", UserSettingActivity.DEFAULT_WAKE_TIME);
            Log.d(TAG, "SleepTime: " + TimePreference.getStandardTime(sleepTimeStr) + " and WakeTime: " + TimePreference.getStandardTime(wakeTimeStr));

            sleepTime = mdf.parse(sleepTimeStr);
            wakeTime = mdf.parse(wakeTimeStr);
            currentTime = sdf.parse(destinationTime);

            isSplit = (wakeTime.compareTo(sleepTime) < 0);
            if (isSplit)
            {
                isWithin = (currentTime.after(sleepTime) || currentTime.before(wakeTime));
            }
            else
            {
                isWithin = (currentTime.after(sleepTime) && currentTime.before(wakeTime));
            }
        }
        catch (ParseException e) {
            Log.e(TAG, "Error Parsing Date " + e);
            e.printStackTrace();
        }
        Log.d(TAG, "IsSleeping? " + isWithin);
        return isWithin;
    }
}