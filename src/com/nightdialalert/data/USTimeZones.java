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
package com.nightdialalert.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class USTimeZones {

    public static class USStateTimeZone {
        public String mStateName;
        public String mStateCode;
        public String mStateGMTStart;
        public String mStateGMTEnd;

        public USStateTimeZone(String name, String code, String gmtStart, String gmtEnd) {
            mStateName = name;
            mStateCode = code;
            mStateGMTStart = gmtStart;
            mStateGMTEnd = gmtEnd;
        }
    }

    public static final Map<String, USStateTimeZone> mapUSStatesTimeZones =
            Collections.unmodifiableMap(new HashMap<String, USStateTimeZone>() {
                private static final long serialVersionUID = 5734801166326537919L;

                {
                    put("Alabama", new USStateTimeZone("Alabama", "AL", "US/Central", ""));
                    put("Alaska", new USStateTimeZone("Alaska", "AK", "US/Alaska", "US/Aleutian")); // Multiple TimeZones
                    put("Arizona", new USStateTimeZone("Arizona", "AZ", "US/Arizona", ""));
                    put("Arkansas", new USStateTimeZone("Arkansas", "AR", "US/Central", ""));
                    put("California", new USStateTimeZone("California", "CA", "US/Pacific", ""));
                    put("Colorado", new USStateTimeZone("Colorado", "CO", "US/Mountain", ""));
                    put("Connecticut", new USStateTimeZone("Connecticut", "CT", "US/Eastern", ""));
                    put("District of Columbia", new USStateTimeZone("District of Columbia", "DC", "US/Eastern", ""));
                    put("Delaware", new USStateTimeZone("Delaware", "DE", "US/Eastern", ""));
                    put("Florida", new USStateTimeZone("Florida", "FL", "US/Eastern", "US/Central")); // Multiple TimeZones
                    put("Georgia", new USStateTimeZone("Georgia", "GA", "US/Eastern", ""));
                    put("Hawaii", new USStateTimeZone("Hawaii", "HI", "US/Hawaii", ""));
                    put("Idaho", new USStateTimeZone("Idaho", "ID", "US/Mountain", "US/Pacific")); // Multiple TimeZones
                    put("Illinois", new USStateTimeZone("Illinois", "IL", "US/Central", ""));
                    put("Indiana", new USStateTimeZone("Indiana", "IN", "US/Eastern", "US/Central")); // Multiple TimeZones
                    put("Iowa", new USStateTimeZone("Iowa", "IA", "US/Central", ""));
                    put("Kansas", new USStateTimeZone("Kansas", "KS", "US/Central", "US/Mountain")); // Multiple TimeZones
                    put("Kentucky", new USStateTimeZone("Kentucky", "KY", "US/Eastern", "US/Central")); // Multiple TimeZones
                    put("Louisiana", new USStateTimeZone("Louisiana", "LA", "US/Central", ""));
                    put("Maine", new USStateTimeZone("Maine", "ME", "US/Eastern", ""));
                    put("Maryland", new USStateTimeZone("Maryland", "MD", "US/Eastern", ""));
                    put("Massachusetts", new USStateTimeZone("Massachusetts", "MA", "US/Eastern", ""));
                    put("Michigan", new USStateTimeZone("Michigan", "MI", "US/Michigan", "US/Central")); // Multiple TimeZones
                    put("Minnesota", new USStateTimeZone("Minnesota", "MN", "US/Central", ""));
                    put("Mississippi", new USStateTimeZone("Mississippi", "MS", "US/Central", ""));
                    put("Missouri", new USStateTimeZone("Missouri", "MO", "US/Central", ""));
                    put("Montana", new USStateTimeZone("Montana", "MT", "US/Mountain", ""));
                    put("Nebraska", new USStateTimeZone("Nebraska", "NE", "US/Central", "US/Mountain")); // Multiple TimeZones
                    put("Nevada", new USStateTimeZone("Nevada", "NV", "US/Pacific", ""));
                    put("New Hampshire", new USStateTimeZone("New Hampshire", "NH", "US/Eastern", ""));
                    put("New Jersey", new USStateTimeZone("New Jersey", "NJ", "US/Eastern", ""));
                    put("New Mexico", new USStateTimeZone("New Mexico", "NM", "US/Mountain", ""));
                    put("New York", new USStateTimeZone("New York", "NY", "US/Eastern", ""));
                    put("North Carolina", new USStateTimeZone("North Carolina", "NC", "US/Eastern", ""));
                    put("North Dakota", new USStateTimeZone("North Dakota", "ND", "US/Central", "US/Mountain")); // Multiple TimeZones
                    put("Ohio", new USStateTimeZone("Ohio", "OH", "US/Eastern", ""));
                    put("Oklahoma", new USStateTimeZone("Oklahoma", "OK", "US/Central", ""));
                    put("Oregon", new USStateTimeZone("Oregon", "OR", "US/Mountain", "US/Pacific"));
                    put("Pennsylvania", new USStateTimeZone("Pennsylvania", "PA", "US/Eastern", ""));
                    put("Rhode Island", new USStateTimeZone("Rhode Island", "RI", "US/Eastern", ""));
                    put("South Carolina", new USStateTimeZone("South Carolina", "SC", "US/Eastern", ""));
                    put("South Dakota", new USStateTimeZone("South Dakota", "SD", "US/Central", "US/Mountain")); // Multiple TimeZones
                    put("Tennessee", new USStateTimeZone("Tennessee", "TN", "US/Eastern", "US/Central")); // Multiple TimeZones
                    put("Texas", new USStateTimeZone("Texas", "TX", "US/Central", "US/Mountain")); // Multiple TimeZones
                    put("Utah", new USStateTimeZone("Utah", "UT", "US/Mountain", ""));
                    put("Vermont", new USStateTimeZone("Vermont", "VT", "US/Eastern", ""));
                    put("Virginia", new USStateTimeZone("Virginia", "VA", "US/Eastern", ""));
                    put("Washington", new USStateTimeZone("Washington", "WA", "US/Pacific", ""));
                    put("West Virginia", new USStateTimeZone("West Virginia", "WV", "US/Eastern", ""));
                    put("Wisconsin", new USStateTimeZone("Wisconsin", "WI", "US/Central", ""));
                    put("Wyoming", new USStateTimeZone("Wyoming", "WY", "US/Mountain", ""));
                }
            });

    public static Map<String, String> mapUSStates = new HashMap<String, String>();
    static {
        // Build the Map
        Map<String, String> map_states = new HashMap<String, String>();
        for (USStateTimeZone entry : mapUSStatesTimeZones.values()) {
            map_states.put(entry.mStateCode, entry.mStateName);
        }
        mapUSStates = Collections.unmodifiableMap(map_states);
    }

    /**
     * Returns the StateName for location strings e.g. 'Portland, OR' will return 'Oregon'
     */
    public static String getStateName(String locationStr) {
        String stateName = locationStr; // default return value is input
        if (locationStr.contains(",")) {
            String tokens[] = locationStr.split(",");
            String stateCode = tokens[1].trim();
            if (mapUSStates.containsKey(stateCode)) {
                stateName = mapUSStates.get(stateCode);
            }
        }
        return stateName;
    }
}