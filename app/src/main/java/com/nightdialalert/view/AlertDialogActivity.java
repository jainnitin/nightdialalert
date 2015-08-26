/*
 * Copyright 2015, NightDialAlert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nightdialalert.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.nightdialalert.NightDialAlertApplication;
import com.nightdialalert.R;
import com.nightdialalert.receivers.BroadcastReceiverManager;
import com.nightdialalert.receivers.NewOutgoingCallBroadcastReceiver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class AlertDialogActivity extends Activity {

    private static final String TAG = AlertDialogActivity.class.getSimpleName();
    private Context mContext;
    private ComponentName mReceiverComponent;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mReceiverComponent = new ComponentName(mContext, NewOutgoingCallBroadcastReceiver.class);

        // Obtain the shared Tracker instance.
        NightDialAlertApplication application = (NightDialAlertApplication) getApplication();
        mTracker = application.getDefaultTracker();

        // Parse Arguments
        String phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
        String[] destinationTimes = getIntent().getStringArrayExtra("DESTINATION_TIMES");

        if (destinationTimes.length > 1) {

          // Sort destination time(s) in ascending-order
            Arrays.sort(destinationTimes, new Comparator<String>() {

                @Override
                public int compare(String t1, String t2) {
                    SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                    try {
                        Date dt1 = df.parse(t1);
                        Date dt2 = df.parse(t2);
                        return dt1.compareTo(dt2);
                    }
                    catch (ParseException e) {
                        Log.d(TAG, "Unable to sort destination times(s) " + e);
                    }
                    return 0;
                }
            });
        }

        String destinationCountry = getIntent().getStringExtra("DESTINATION_COUNTRY");
        if (phoneNumber != null) {
            showAlertDialog(phoneNumber, destinationCountry, destinationTimes);
        } else {
            Log.e(TAG, "Invalid arguments passed");
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mTracker.setScreenName("SettingsActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadcastReceiverManager.enableBroadcastReceiverAfterDelay(mContext, mReceiverComponent, 10);
    }

    private void finishSuccess() {
      setResult(RESULT_OK);
      finish();
    }

    private void showAlertDialog(final String phoneNumber, final String destinationCountry, final String[] destinationTimes) {
        // Make some noise!
        try {
            final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
            tg.startTone(ToneGenerator.TONE_PROP_ACK);
        } catch (Exception e) {
            Log.e(TAG, "Failed to play beep sound " + e);
        }

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.confirm_dialog, null);

        // Update Name
        TextView name = (TextView) v.findViewById(R.id.contact_name);
        String contactName = getContactDisplayNameByNumber(phoneNumber);
        if (contactName == null || contactName.trim().isEmpty()) {
            contactName = formatPhoneNumber(phoneNumber);
        }
        name.setText(getString(R.string.contact_name, contactName));

        // Update Country
        TextView country = (TextView) v.findViewById(R.id.local_time_text);
        country.setText(getString(R.string.local_time_text, destinationCountry));

        // Update Destination LocalTime
        TextView destTime = (TextView) v.findViewById(R.id.destination_time_text);
        String destinationTimesDisplayStr = destinationTimes[0];
        if (destinationTimes.length > 1) {
            destinationTimesDisplayStr += " - " + destinationTimes[1];
        }
        destTime.setText(getString(R.string.destination_time, destinationTimesDisplayStr));

        // AlertBuilder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Dialog))
                .setView(v)
                .setInverseBackgroundForced(true)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "User aborted call");
                        finishSuccess();
                    }
                })
                .setPositiveButton(R.string.call, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "User selected to call");
                        BroadcastReceiverManager.disableBroadcastReceiver(mContext, mReceiverComponent);
                        String number = "tel:" + phoneNumber;
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                        startActivity(callIntent);
                        finishSuccess();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Parse dialed PhoneNumber using Google's PhoneNumber library
     * @param phoneNumber
     * @return parsedPhoneNumber
     */
    private String formatPhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, Locale.getDefault().getCountry());
            if (phoneNumberProto.getCountryCode() == 1) {
                return PhoneNumberUtil.getInstance().format(phoneNumberProto, PhoneNumberFormat.NATIONAL);
            } else {
                return PhoneNumberUtil.getInstance().format(phoneNumberProto, PhoneNumberFormat.INTERNATIONAL);
            }
        } catch (NumberParseException e) {
            Log.e(TAG, "Failed to parse phoneNumber " + phoneNumber + " Exception: " + e.toString());
        }
        return phoneNumber;
    }

    /**
     * Get the ContactName from the PhoneNumber (if available)
     *
     * @param number
     * @return contact name
     */
    public String getContactDisplayNameByNumber(String number) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "";

        ContentResolver contentResolver = getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] { BaseColumns._ID, ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            }
        }
        finally {
            if (contactLookup != null) {
                contactLookup.close();
            }
        }

        return name;
    }
}