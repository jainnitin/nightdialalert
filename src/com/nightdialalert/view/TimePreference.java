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
import java.util.Date;

import com.nightdialalert.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

// Adapted from the following articles/blogs/source code:
// 1. https://github.com/commonsguy/cw-lunchlist/blob/master/19-Alarm/LunchList/src/apt/tutorial/TimePreference.java
// 2. http://stackoverflow.com/questions/5614221/custom-attributes-for-a-dialogpreference
public class TimePreference extends DialogPreference {
    private static final String TAG = TimePreference.class.getSimpleName();
    private int _lastHour=0;
    private int _lastMinute=0;
    private TimePicker _picker=null;

    public static int getHour(String time) {
        String[] pieces=time.split(":");
        return(Integer.parseInt(pieces[0]));
    }

    public static int getMinute(String time) {
        String[] pieces=time.split(":");
        return(Integer.parseInt(pieces[1]));
    }

    @SuppressLint("SimpleDateFormat")
    public static String getStandardTime(String milTime) {
        String stdTime = milTime;
        SimpleDateFormat fmtMil = new SimpleDateFormat("HH:mm");
        SimpleDateFormat fmtStd = new SimpleDateFormat("hh:mm a");
        try {
            Date inTime = fmtMil.parse(milTime);
            stdTime = fmtStd.format(inTime);
        } catch (ParseException e) {
            Log.i(TAG, "Error converting MilTime " + milTime + " to StandardTime " + e);
        }
        return stdTime;
    }

    public TimePreference(Context ctxt) {
        this(ctxt, null);
    }

    public TimePreference(Context ctxt, AttributeSet attrs) {
        this(ctxt, attrs, 0);
        for (int i=0;i<attrs.getAttributeCount();i++) {
            String attr = attrs.getAttributeName(i);
            String val  = attrs.getAttributeValue(i);
            if (attr.equalsIgnoreCase("defaultValue")) {
            	_lastHour = TimePreference.getHour(val);
            	_lastMinute = TimePreference.getMinute(val);
            }
        }
    }

    public TimePreference(Context ctxt, AttributeSet attrs, int defStyle) {
        super(ctxt, attrs, defStyle);

        setPositiveButtonText(R.string.set);
        setNegativeButtonText(R.string.cancel);
    }

    @Override
    protected View onCreateDialogView() {
    	_picker=new TimePicker(getContext());
        return(_picker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        _picker.setCurrentHour(_lastHour);
        _picker.setCurrentMinute(_lastMinute);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
        	_picker.clearFocus();
        	_lastHour=_picker.getCurrentHour();
        	_lastMinute=_picker.getCurrentMinute();

            String time=String.valueOf(_lastHour)+":"+String.valueOf(_lastMinute);

            if (callChangeListener(time)) {
                persistString(time);
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return(a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        String time=null;

        if (restoreValue) {
            if (defaultValue==null) {
                time=getPersistedString("00:00");
            } else {
                time=getPersistedString(defaultValue.toString());
            }
        } else {
            time=defaultValue.toString();
        }

        _lastHour=getHour(time);
        _lastMinute=getMinute(time);
    }
}
