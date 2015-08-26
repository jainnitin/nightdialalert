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

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nightdialalert.NightDialAlertApplication;
import com.nightdialalert.receivers.BroadcastReceiverManager;
import com.nightdialalert.receivers.NewOutgoingCallBroadcastReceiver;
import com.nightdialalert.R;

public class UserSettingActivity extends PreferenceActivity {

    protected static final String TAG = UserSettingActivity.class.getSimpleName();

    public static final String DEFAULT_WAKE_TIME = "07:00";
    public static final String DEFAULT_SLEEP_TIME = "22:00";

    private SharedPreferences mPrefs;
    private ComponentName mReceiverComponent;
    private Context mContext;
    private PreferenceCategory mSleepPref;
    private SwitchPreference mSwitchPref;
    private CheckBoxPreference mCheckboxPref;
    private PreferenceScreen mDisclaimerPref;
    private PreferenceScreen mPrivacyPref;
    private Preference mVersion;
    private Tracker mTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BuildVersion: " + Build.VERSION.SDK_INT);
        mContext = this;

        // Obtain the shared Tracker instance.
        NightDialAlertApplication application = (NightDialAlertApplication) getApplication();
        mTracker = application.getDefaultTracker();

        addPreferencesFromResource(R.xml.settings);

        mReceiverComponent = new ComponentName(mContext, NewOutgoingCallBroadcastReceiver.class);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        mSleepPref = (PreferenceCategory) findPreference("prefSettings");
        initSleepPreferences();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mSwitchPref = (SwitchPreference) findPreference("prefEnabled");
            initSwitchState();
        }
        else {
            mCheckboxPref = (CheckBoxPreference) findPreference("prefEnabled");
            initCheckboxState();
        }

        mDisclaimerPref = (PreferenceScreen) findPreference("prefDisclaimer");
        initDisclaimerPreferences();

        mPrivacyPref = (PreferenceScreen) findPreference("prefPrivacy");
        initPrivacyPreferences();

        mVersion = findPreference("prefVersion");
        try {
            String versionString = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            mVersion.setSummary(versionString);
        } catch (NameNotFoundException e) {}

    }

    @Override
    public void onStart() {
        super.onStart();
        mTracker.setScreenName("SettingsActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        //EasyTracker.getInstance(mContext).activityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        //EasyTracker.getInstance(mContext).activityStop(this);
    }

    private void initPrivacyPreferences() {
        mPrivacyPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.privacy_policy_title);
                final SpannableString privacy_text = new SpannableString(mContext.getText(R.string.privacy_policy));
                Linkify.addLinks(privacy_text, Linkify.ALL);
                builder.setMessage(privacy_text);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // action on dialog close
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void initDisclaimerPreferences() {
        mDisclaimerPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.legal_disclaimer_title);
                builder.setMessage(R.string.legal_disclaimer);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // action on dialog close
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void initSleepPreferences() {
        String sleepTimeStr = mPrefs.getString("prefSleepTime", DEFAULT_SLEEP_TIME);
        String wakeTimeStr = mPrefs.getString("prefWakeTime", DEFAULT_WAKE_TIME);

        final TimePreference sleepTime = (TimePreference) findPreference("prefSleepTime");
        sleepTime.setSummary(TimePreference.getStandardTime(sleepTimeStr));
        sleepTime.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d(TAG, "Updated SleepTime: " + newValue);
                sleepTime.setSummary(TimePreference.getStandardTime((String) newValue));
                return true;
            }
        });

        final TimePreference wakeTime = (TimePreference) findPreference("prefWakeTime");
        wakeTime.setSummary(TimePreference.getStandardTime(wakeTimeStr));
        wakeTime.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d(TAG, "Updated WakeTime: " + newValue);
                wakeTime.setSummary(TimePreference.getStandardTime((String) newValue));
                return true;
            }
        });
    }

    private void initSwitchState() {
        Log.d(TAG, "Receiver Init State: " + mSwitchPref.isChecked());
        if (mSwitchPref.isChecked()) {
            // ON-STATE
            mSleepPref.setEnabled(true);
            BroadcastReceiverManager.enableBroadcastReceiver(mContext, mReceiverComponent);
        }
        else {
            // OFF-STATE
            mSleepPref.setEnabled(false);
            BroadcastReceiverManager.disableBroadcastReceiver(mContext, mReceiverComponent);
        }
        mSwitchPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean monitorTurnOn = (Boolean) newValue;
                if (monitorTurnOn) {
                    // TURN-ON
                    Log.d(TAG, "Monitor State=ON");
                    mSleepPref.setEnabled(true);
                    BroadcastReceiverManager.enableBroadcastReceiver(mContext, mReceiverComponent);
                }
                else {
                    // TURN-OFF
                    Log.d(TAG, "Monitor State=OFF");
                    mSleepPref.setEnabled(false);
                    BroadcastReceiverManager.disableBroadcastReceiver(mContext, mReceiverComponent);
                }
                return true;
            }
        });
    }

    private void initCheckboxState() {
        Log.d(TAG, "Initial Receiver State: " + mCheckboxPref.isChecked());
        if (mCheckboxPref.isChecked()) {
            // ON-STATE
            mSleepPref.setEnabled(true);
            BroadcastReceiverManager.enableBroadcastReceiver(mContext, mReceiverComponent);
        }
        else {
            // OFF-STATE
            mSleepPref.setEnabled(false);
            BroadcastReceiverManager.disableBroadcastReceiver(mContext, mReceiverComponent);
        }
        mCheckboxPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean monitorTurnOn = (Boolean) newValue;
                if (monitorTurnOn) {
                    // TURN-ON
                    Log.d(TAG, "Monitor State=ON");
                    mSleepPref.setEnabled(true);
                    BroadcastReceiverManager.enableBroadcastReceiver(mContext, mReceiverComponent);
                }
                else {
                    // TURN-OFF
                    Log.d(TAG, "Monitor State=OFF");
                    mSleepPref.setEnabled(false);
                    BroadcastReceiverManager.disableBroadcastReceiver(mContext, mReceiverComponent);
                }
                return true;
            }
        });
    }
}
