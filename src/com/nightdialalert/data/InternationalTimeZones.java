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

import android.annotation.SuppressLint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InternationalTimeZones {

    public static class CountryTimeZone {
        public String mCountryName;
        public int mCountryAreaCode;
        public String mCountryGMTStart;
        public String mCountryGMTEnd;

        public CountryTimeZone(String name, int areaCode, String gmtStart, String gmtEnd) {
            mCountryName = name;
            mCountryAreaCode = areaCode;
            mCountryGMTStart = gmtStart;
            mCountryGMTEnd = gmtEnd;
        }
    }

    @SuppressLint("UseSparseArrays")
    public static final Map<Integer, CountryTimeZone> mapInternationalTimeZones =
            Collections.unmodifiableMap(new HashMap<Integer, CountryTimeZone>() {
                private static final long serialVersionUID = 6463345356015699067L;
                {
                    put(355, new CountryTimeZone("Albania", 355, "GMT+01:00", "GMT+02:00"));
                    put(213, new CountryTimeZone("Algeria", 213, "GMT", ""));
                    put(376, new CountryTimeZone("Andorra", 376, "GMT+01:00", "GMT+02:00"));
                    put(244, new CountryTimeZone("Angola", 244, "GMT+01:00", ""));
                    put(264, new CountryTimeZone("Anguilla", 264, "GMT-04:00", ""));
                    put(268, new CountryTimeZone("Antigua and Barbuda", 268, "GMT-04:00", ""));
                    put(54, new CountryTimeZone("Argentina", 54, "GMT-03:00", ""));
                    put(374, new CountryTimeZone("Armenia", 374, "GMT+04:00", ""));
                    put(297, new CountryTimeZone("Aruba", 297, "GMT-04:00", ""));
                    put(247, new CountryTimeZone("Ascension Island", 247, "GMT", ""));
                    put(61, new CountryTimeZone("Australia", 61, "GMT+10:00", "GMT+07:00"));
                    put(43, new CountryTimeZone("Austria", 43, "GMT+01:00", "GMT+02:00"));
                    put(994, new CountryTimeZone("Azerbaijan", 994, "GMT+04:00", ""));
                    put(242, new CountryTimeZone("Bahamas", 242, "GMT-04:00", "GMT-05:00"));
                    put(973, new CountryTimeZone("Bahrain", 973, "GMT+03:00", ""));
                    put(880, new CountryTimeZone("Bangladesh", 880, "GMT+06:00", ""));
                    put(246, new CountryTimeZone("Barbados", 246, "GMT-04:00", ""));
                    put(375, new CountryTimeZone("Belarus", 375, "GMT+03:00", ""));
                    put(32, new CountryTimeZone("Belgium", 32, "GMT+01:00", "GMT+02:00"));
                    put(501, new CountryTimeZone("Belize", 501, "GMT-06:00", ""));
                    put(229, new CountryTimeZone("Benin", 229, "GMT+01:00", ""));
                    put(441, new CountryTimeZone("Bermuda", 441, "GMT-04:00", ""));
                    put(975, new CountryTimeZone("Bhutan", 975, "GMT+05:30", ""));
                    put(591, new CountryTimeZone("Bolivia", 591, "GMT-04:00", ""));
                    put(387, new CountryTimeZone("Bosnia", 387, "GMT+01:00", "GMT+02:00"));
                    put(267, new CountryTimeZone("Botswana", 267, "GMT+02:00", ""));
                    put(55, new CountryTimeZone("Brazil", 55, "GMT-03:00", "GMT-05:00"));
                    put(673, new CountryTimeZone("Brunei", 673, "GMT+08:00", ""));
                    put(359, new CountryTimeZone("Bulgaria", 359, "GMT+02:00", "GMT+03:00"));
                    put(226, new CountryTimeZone("Burkina Faso", 226, "GMT", ""));
                    put(257, new CountryTimeZone("Burundi", 257, "GMT+02:00", ""));
                    put(855, new CountryTimeZone("Cambodia", 855, "GMT+07:00", ""));
                    put(237, new CountryTimeZone("Cameroon", 237, "GMT+01:00", ""));
                    put(1, new CountryTimeZone("Canada", 1, "GMT-04:00", "GMT-08:00"));
                    put(238, new CountryTimeZone("Cape Verde Islands", 238, "GMT-01:00", ""));
                    put(345, new CountryTimeZone("Cayman Islands", 345, "GMT-05:00", ""));
                    put(236, new CountryTimeZone("Central Africa Republic", 236, "GMT+01:00", ""));
                    put(235, new CountryTimeZone("Chad", 235, "GMT+01:00", ""));
                    put(56, new CountryTimeZone("Chile", 56, "GMT-04:00", "GMT-03:00"));
                    put(86, new CountryTimeZone("China", 86, "GMT+08:00", ""));
                    put(57, new CountryTimeZone("Columbia", 57, "GMT-05:00", ""));
                    put(269, new CountryTimeZone("Comoros Island", 269, "GMT+03:00", ""));
                    put(242, new CountryTimeZone("Congo", 242, "GMT+01:00", ""));
                    put(682, new CountryTimeZone("Cook Islands", 682, "GMT-10:00", ""));
                    put(506, new CountryTimeZone("Costa Rica", 506, "GMT-06:00", ""));
                    put(385, new CountryTimeZone("Croatia", 385, "GMT+01:00", "GMT+02:00"));
                    put(53, new CountryTimeZone("Cuba", 53, "GMT-04:00", "GMT-05:00"));
                    put(357, new CountryTimeZone("Cyprus", 357, "GMT+02:00", "GMT+03:00"));
                    put(420, new CountryTimeZone("Czech Republic", 420, "GMT+01:00", "GMT+02:00"));
                    put(243, new CountryTimeZone("Democratic Republic of Congo (Zaire)", 243, "GMT+02:00", "GMT+01:00"));
                    put(45, new CountryTimeZone("Denmark", 45, "GMT+01:00", "GMT+02:00"));
                    put(246, new CountryTimeZone("Diego Garcia", 246, "GMT+05:00", ""));
                    put(253, new CountryTimeZone("Djibouti", 253, "GMT+03:00", ""));
                    put(767, new CountryTimeZone("Dominica Islands", 767, "GMT-04:00", ""));
                    put(809, new CountryTimeZone("Dominican Republic", 809, "GMT-04:00", ""));
                    put(593, new CountryTimeZone("Ecuador", 593, "GMT-05:00", ""));
                    put(20, new CountryTimeZone("Egypt", 20, "GMT+02:00", ""));
                    put(503, new CountryTimeZone("El Salvador", 503, "GMT-06:00", ""));
                    put(240, new CountryTimeZone("Equatorial Guinea", 240, "GMT+01:00", ""));
                    put(291, new CountryTimeZone("Eritrea", 291, "GMT+03:00", ""));
                    put(372, new CountryTimeZone("Estonia", 372, "GMT+02:00", "GMT+03:00"));
                    put(251, new CountryTimeZone("Ethiopia", 251, "GMT+03:00", ""));
                    put(298, new CountryTimeZone("Faeroe Islands", 298, "GMT", "GMT+01:00"));
                    put(500, new CountryTimeZone("Falkland Islands", 500, "GMT-04:00", ""));
                    put(679, new CountryTimeZone("Fiji Islands", 679, "GMT+12:00", "GMT+13:00"));
                    put(358, new CountryTimeZone("Finland", 358, "GMT+02:00", "GMT+03:00"));
                    put(33, new CountryTimeZone("France", 33, "GMT+01:00", "GMT+02:00"));
                    put(594, new CountryTimeZone("French Guiana", 594, "GMT-04:00", ""));
                    put(689, new CountryTimeZone("French Polynesia", 689, "GMT-10:00", ""));
                    put(241, new CountryTimeZone("Gabon", 241, "GMT+01:00", ""));
                    put(995, new CountryTimeZone("Georgia", 995, "GMT+04:00", ""));
                    put(49, new CountryTimeZone("Germany", 49, "GMT+01:00", "GMT+02:00"));
                    put(233, new CountryTimeZone("Ghana", 233, "GMT", ""));
                    put(350, new CountryTimeZone("Gibraltar", 350, "GMT+01:00", "GMT+02:00"));
                    put(30, new CountryTimeZone("Greece", 30, "GMT+02:00", "GMT+03:00"));
                    put(299, new CountryTimeZone("Greenland", 299, "GMT-03:00", "GMT-02:00"));
                    put(473, new CountryTimeZone("Grenada", 473, "GMT-04:00", ""));
                    put(590, new CountryTimeZone("Guadeloupe", 590, "GMT-04:00", ""));
                    put(671, new CountryTimeZone("Guam", 671, "GMT+10:00", ""));
                    put(502, new CountryTimeZone("Guatemala", 502, "GMT-06:00", ""));
                    put(245, new CountryTimeZone("Guinea Bissau", 245, "GMT-01:00", ""));
                    put(224, new CountryTimeZone("Guinea Republic", 224, "GMT", ""));
                    put(592, new CountryTimeZone("Guyana", 592, "GMT-03:00", ""));
                    put(509, new CountryTimeZone("Haiti", 509, "GMT-05:00", "GMT-04:00"));
                    put(503, new CountryTimeZone("Honduras", 503, "GMT-06:00", ""));
                    put(852, new CountryTimeZone("Hong Kong", 852, "GMT+08:00", ""));
                    put(36, new CountryTimeZone("Hungary", 36, "GMT+01:00", "GMT+02:00"));
                    put(354, new CountryTimeZone("Iceland", 354, "GMT", ""));
                    put(91, new CountryTimeZone("India", 91, "GMT+05:30", ""));
                    put(62, new CountryTimeZone("Indonesia", 62, "GMT+09:00", "GMT+07:00"));
                    put(98, new CountryTimeZone("Iran", 98, "GMT+03:30", ""));
                    put(964, new CountryTimeZone("Iraq", 964, "GMT+03:00", ""));
                    put(353, new CountryTimeZone("Ireland", 353, "GMT", "GMT+01:00"));
                    put(972, new CountryTimeZone("Israel", 972, "GMT+02:00", "GMT+03:00"));
                    put(39, new CountryTimeZone("Italy", 39, "GMT+01:00", "GMT+02:00"));
                    put(225, new CountryTimeZone("Ivory Coast", 225, "GMT", ""));
                    put(876, new CountryTimeZone("Jamaica", 876, "GMT-05:00", ""));
                    put(81, new CountryTimeZone("Japan", 81, "GMT+09:00", ""));
                    put(962, new CountryTimeZone("Jordan", 962, "GMT+02:00", "GMT+03:00"));
                    put(7, new CountryTimeZone("Kazakhstan", 7, "GMT+06:00", ""));
                    put(254, new CountryTimeZone("Kenya", 254, "GMT+03:00", ""));
                    put(686, new CountryTimeZone("Kiribati", 686, "GMT+12:00", ""));
                    put(850, new CountryTimeZone("North Korea", 850, "GMT+09:00", ""));
                    put(82, new CountryTimeZone("South Korea", 82, "GMT+09:00", ""));
                    put(965, new CountryTimeZone("Kuwait", 965, "GMT+03:00", ""));
                    put(996, new CountryTimeZone("Kyrgyzstan", 996, "GMT+06:00", ""));
                    put(856, new CountryTimeZone("Laos", 856, "GMT+07:00", ""));
                    put(371, new CountryTimeZone("Latvia", 371, "GMT+02:00", "GMT+03:00"));
                    put(961, new CountryTimeZone("Lebanon", 961, "GMT+02:00", "GMT+03:00"));
                    put(266, new CountryTimeZone("Lesotho", 266, "GMT+02:00", ""));
                    put(231, new CountryTimeZone("Liberia", 231, "GMT", ""));
                    put(218, new CountryTimeZone("Libya", 218, "GMT+02:00", ""));
                    put(423, new CountryTimeZone("Liechtenstein", 423, "GMT+01:00", "GMT+02:00"));
                    put(370, new CountryTimeZone("Lithuania", 370, "GMT+02:00", "GMT+03:00"));
                    put(352, new CountryTimeZone("Luxembourg", 352, "GMT+01:00", "GMT+02:00"));
                    put(853, new CountryTimeZone("Macau", 853, "GMT+08:00", ""));
                    put(389, new CountryTimeZone("Macedonia (Fyrom)", 389, "GMT+01:00", "GMT+02:00"));
                    put(261, new CountryTimeZone("Madagascar", 261, "GMT+03:00", ""));
                    put(265, new CountryTimeZone("Malawi", 265, "GMT+02:00", ""));
                    put(60, new CountryTimeZone("Malaysia", 60, "GMT+08:00", ""));
                    put(960, new CountryTimeZone("Maldives Republic", 960, "GMT+05:00", ""));
                    put(223, new CountryTimeZone("Mali", 223, "GMT", ""));
                    put(356, new CountryTimeZone("Malta", 356, "GMT+01:00", "GMT+02:00"));
                    put(670, new CountryTimeZone("Mariana Islands", 670, "GMT+10:00", ""));
                    put(692, new CountryTimeZone("Marshall Islands", 692, "GMT+10:00", ""));
                    put(596, new CountryTimeZone("Martinique", 596, "GMT-04:00", ""));
                    put(230, new CountryTimeZone("Mauritius", 230, "GMT+04:00", ""));
                    put(269, new CountryTimeZone("Mayotte Islands", 269, "GMT+03:00", ""));
                    put(52, new CountryTimeZone("Mexico", 52, "GMT-06:00", "GMT-08:00"));
                    put(691, new CountryTimeZone("Micronesia", 691, "GMT+10:00", ""));
                    put(373, new CountryTimeZone("Moldova", 373, "GMT+02:00", "GMT+03:00"));
                    put(377, new CountryTimeZone("Monaco", 377, "GMT+01:00", "GMT+02:00"));
                    put(976, new CountryTimeZone("Mongolia", 976, "GMT+08:00", ""));
                    put(664, new CountryTimeZone("Montserrat", 664, "GMT-04:00", ""));
                    put(212, new CountryTimeZone("Morocco", 212, "GMT", "GMT+01:00"));
                    put(258, new CountryTimeZone("Mozambique", 258, "GMT+02:00", ""));
                    put(95, new CountryTimeZone("Myanmar (Burma)", 95, "GMT+06:30", ""));
                    put(264, new CountryTimeZone("Namibia", 264, "GMT+02:00", "GMT+03:00"));
                    put(674, new CountryTimeZone("Nauru", 674, "GMT+12:00", ""));
                    put(977, new CountryTimeZone("Nepal", 977, "GMT+05:30", ""));
                    put(31, new CountryTimeZone("Netherlands", 31, "GMT+01:00", "GMT+02:00"));
                    put(599, new CountryTimeZone("Netherlands Antilles", 599, "GMT-04:00", ""));
                    put(687, new CountryTimeZone("New Caledonia", 687, "GMT+11:00", ""));
                    put(64, new CountryTimeZone("New Zealand", 64, "GMT+12:00", "GMT+13:00"));
                    put(505, new CountryTimeZone("Nicaragua", 505, "GMT-06:00", ""));
                    put(227, new CountryTimeZone("Niger", 227, "GMT+01:00", ""));
                    put(234, new CountryTimeZone("Nigeria", 234, "GMT+01:00", ""));
                    put(683, new CountryTimeZone("Niue Island", 683, "GMT-11:00", ""));
                    put(672, new CountryTimeZone("Norfolk Island", 672, "GMT+11:30", ""));
                    put(47, new CountryTimeZone("Norway", 47, "GMT+01:00", "GMT+02:00"));
                    put(968, new CountryTimeZone("Oman", 968, "GMT+04:00", ""));
                    put(92, new CountryTimeZone("Pakistan", 92, "GMT+05:00", ""));
                    put(680, new CountryTimeZone("Palau", 680, "GMT+09:00", ""));
                    put(970, new CountryTimeZone("Palestine", 970, "GMT+02:00", ""));
                    put(507, new CountryTimeZone("Panama", 507, "GMT-05:00", ""));
                    put(675, new CountryTimeZone("Papua New Guinea", 675, "GMT+10:00", ""));
                    put(595, new CountryTimeZone("Paraguay", 595, "GMT-03:00", "GMT-04:00"));
                    put(51, new CountryTimeZone("Peru", 51, "GMT-05:00", ""));
                    put(63, new CountryTimeZone("Philippines", 63, "GMT+08:00", ""));
                    put(48, new CountryTimeZone("Poland", 48, "GMT+01:00", "GMT+02:00"));
                    put(351, new CountryTimeZone("Portugal", 351, "GMT+01:00", "GMT+02:00"));
                    put(787, new CountryTimeZone("Puerto Rico", 787, "GMT-04:00", ""));
                    put(974, new CountryTimeZone("Qatar", 974, "GMT+03:00", ""));
                    put(262, new CountryTimeZone("Reunion Island", 262, "GMT+04:00", ""));
                    put(40, new CountryTimeZone("Romania", 40, "GMT+02:00", "GMT+03:00"));
                    put(7, new CountryTimeZone("Russia", 7, "GMT+03:00", ""));
                    put(250, new CountryTimeZone("Rwanda", 250, "GMT+02:00", ""));
                    put(684, new CountryTimeZone("American Samoa", 684, "GMT-11:00", ""));
                    put(685, new CountryTimeZone("Western Samoa", 685, "GMT+13:00", "GMT+14:00"));
                    put(378, new CountryTimeZone("San Marino", 378, "GMT+01:00", "GMT+02:00"));
                    put(239, new CountryTimeZone("Sao Tome & Principe", 239, "GMT", ""));
                    put(966, new CountryTimeZone("Saudi Arabia", 966, "GMT+03:00", ""));
                    put(221, new CountryTimeZone("Senegal", 221, "GMT", ""));
                    put(381, new CountryTimeZone("Serbia", 381, "GMT+01:00", "GMT+02:00"));
                    put(248, new CountryTimeZone("Seychelles", 248, "GMT+04:00", ""));
                    put(232, new CountryTimeZone("Sierra Leone", 232, "GMT", ""));
                    put(65, new CountryTimeZone("Singapore", 65, "GMT+08:00", ""));
                    put(421, new CountryTimeZone("Slovak Republic", 421, "GMT+01:00", "GMT+02:00"));
                    put(386, new CountryTimeZone("Slovenia", 386, "GMT+01:00", "GMT+02:00"));
                    put(677, new CountryTimeZone("Solomon Islands", 677, "GMT+11:00", ""));
                    put(252, new CountryTimeZone("Somalia", 252, "GMT+03:00", ""));
                    put(27, new CountryTimeZone("South Africa", 27, "GMT+02:00", ""));
                    put(34, new CountryTimeZone("Spain", 34, "GMT+01:00", "GMT+02:00"));
                    put(94, new CountryTimeZone("Sri Lanka", 94, "GMT+05:30", ""));
                    put(290, new CountryTimeZone("St Helena", 290, "GMT", ""));
                    put(869, new CountryTimeZone("St Kitts & Nevia", 869, "GMT-04:00", ""));
                    put(758, new CountryTimeZone("St Lucia", 758, "GMT-04:00", ""));
                    put(249, new CountryTimeZone("Sudan", 249, "GMT+02:00", ""));
                    put(597, new CountryTimeZone("Surinam", 597, "GMT-03:30", ""));
                    put(268, new CountryTimeZone("Swaziland", 268, "GMT+02:00", ""));
                    put(46, new CountryTimeZone("Sweden", 46, "GMT+01:00", "GMT+02:00"));
                    put(41, new CountryTimeZone("Switzerland", 41, "GMT+01:00", "GMT+02:00"));
                    put(963, new CountryTimeZone("Syria", 963, "GMT+02:00", "GMT+03:00"));
                    put(886, new CountryTimeZone("Taiwan", 886, "GMT+08:00", ""));
                    put(992, new CountryTimeZone("Tajikistan", 992, "GMT+06:00", ""));
                    put(255, new CountryTimeZone("Tanzania", 255, "GMT+03:00", ""));
                    put(66, new CountryTimeZone("Thailand", 66, "GMT+07:00", ""));
                    put(220, new CountryTimeZone("The Gambia", 220, "GMT", ""));
                    put(228, new CountryTimeZone("Togo", 228, "GMT", ""));
                    put(676, new CountryTimeZone("Tonga", 676, "GMT+13:00", ""));
                    put(868, new CountryTimeZone("Trinidad & Tobago", 868, "GMT-04:00", ""));
                    put(216, new CountryTimeZone("Tunisia", 216, "GMT+01:00", ""));
                    put(90, new CountryTimeZone("Turkey", 90, "GMT+02:00", "GMT+03:00"));
                    put(993, new CountryTimeZone("Turkmenistan", 993, "GMT+05:00", ""));
                    put(649, new CountryTimeZone("Turks & Caicos Islands", 649, "GMT-05:00", ""));
                    put(688, new CountryTimeZone("Tuvalu", 688, "GMT+12:00", ""));
                    put(256, new CountryTimeZone("Uganda", 256, "GMT+03:00", ""));
                    put(380, new CountryTimeZone("Ukraine", 380, "GMT+02:00", "GMT+03:00"));
                    put(971, new CountryTimeZone("United Arab Emirates", 971, "GMT+04:00", ""));
                    put(44, new CountryTimeZone("United Kingdom", 44, "GMT", "GMT+01:00"));
                    put(598, new CountryTimeZone("Uruguay", 598, "GMT-03:00", "GMT-02:00"));
                    put(1, new CountryTimeZone("USA", 1, "GMT-05:00", "GMT-11:00"));
                    put(998, new CountryTimeZone("Uzbekistan", 998, "GMT+06:00", ""));
                    put(678, new CountryTimeZone("Vanuatu", 678, "GMT+11:00", ""));
                    put(58, new CountryTimeZone("Venezuela", 58, "GMT-04:00", ""));
                    put(84, new CountryTimeZone("Vietnam", 84, "GMT+07:00", ""));
                    put(681, new CountryTimeZone("Wallis & Futuna Islands", 681, "GMT+12:00", ""));
                    put(967, new CountryTimeZone("Yemen Arab Republic", 967, "GMT+03:00", ""));
                    put(260, new CountryTimeZone("Zambia", 260, "GMT+02:00", ""));
                    put(263, new CountryTimeZone("Zimbabwe", 263, "GMT+02:00", ""));
                }
            });
}
