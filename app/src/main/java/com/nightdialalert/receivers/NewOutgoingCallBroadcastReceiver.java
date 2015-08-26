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

import java.util.List;

import com.nightdialalert.utils.DestinationTimeCalculator;
import com.nightdialalert.view.AlertDialogActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;

public class NewOutgoingCallBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NewOutgoingCallBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        // intercept call
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

            // get phone number from bundle
            String phoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);

            // compute the destination time
            Pair<String, List<String>> destinationNameTimePair = DestinationTimeCalculator.getDestinationTime(context, phoneNumber);
            if (destinationNameTimePair != null) {

                // compute if its night-time (in any of the time-zones)
                for (String currentTime : destinationNameTimePair.second) {

                    if (DestinationTimeCalculator.isBetweenGraveYardHours(context, currentTime)) {
                        Log.i(TAG, "Intercepting Call and Launching Alert Dialog");

                        // Abort Broadcast
                        setResultData(null);
                        abortBroadcast();

                        // Launch Dialog Intent
                        Intent dialogIntent = new Intent(context, AlertDialogActivity.class);
                        dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        dialogIntent.putExtra("PHONE_NUMBER", phoneNumber);
                        dialogIntent.putExtra("DESTINATION_COUNTRY", destinationNameTimePair.first);
                        dialogIntent.putExtra("DESTINATION_TIMES", destinationNameTimePair.second.toArray());
                        context.startActivity(dialogIntent);

                        break; // break-out of for-loop
                    }
                }
            }
        }
    }
}