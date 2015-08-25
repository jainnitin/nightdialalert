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
package com.nightdialalert.receivers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class BroadcastReceiverManager {
    private static final String TAG = BroadcastReceiverManager.class.getSimpleName();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void enableBroadcastReceiverAfterDelay(final Context mContext, final ComponentName mReceiverComponent, int seconds) {
        scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    enableBroadcastReceiver(mContext, mReceiverComponent);
               }
            },
            seconds, java.util.concurrent.TimeUnit.SECONDS);
    }

    public static void enableBroadcastReceiver(Context mContext, ComponentName mReceiverComponent) {
        if (!isReceiverEnabled(mContext, mReceiverComponent)) {
            Log.d(TAG, "Turning ON Receiver");
            mContext.getPackageManager().setComponentEnabledSetting(mReceiverComponent, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }

    public static void disableBroadcastReceiver(Context mContext, ComponentName mReceiverComponent) {
        if (isReceiverEnabled(mContext, mReceiverComponent)) {
            Log.d(TAG, "Turning OFF Receiver");
            mContext.getPackageManager().setComponentEnabledSetting(mReceiverComponent, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
    }

    public static boolean isReceiverEnabled(Context mContext, ComponentName mReceiverComponent) {
        int status = mContext.getPackageManager().getComponentEnabledSetting(mReceiverComponent);
        if (status == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            Log.v(TAG, "Receiver is Active");
            return true;
        }
        else if (status == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            Log.v(TAG, "Receiver is Inactive");
            return false;
        }
        else if (status == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT) {
            Log.v(TAG, "Receiver is Active");
            return true;
        }
        return false;
    }
}