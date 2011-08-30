/*
 * [The "BSD licence"]
 * Copyright (c) 2011 Ben Gruver
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.Penroser;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class PenroserOptions extends Activity {
    private HalfRhombusButton leftSkinny = null;
    private HalfRhombusButton rightSkinny = null;
    private HalfRhombusButton leftFat = null;
    private HalfRhombusButton rightFat = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.options);

        leftSkinny = (HalfRhombusButton)findViewById(R.id.left_skinny);
        rightSkinny = (HalfRhombusButton)findViewById(R.id.right_skinny);
        leftFat = (HalfRhombusButton)findViewById(R.id.left_fat);
        rightFat = (HalfRhombusButton)findViewById(R.id.right_fat);


        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        leftSkinny.setColor(getColor(preferences, HalfRhombusType.LEFT_SKINNY));
        rightSkinny.setColor(getColor(preferences, HalfRhombusType.RIGHT_SKINNY));
        leftFat.setColor(getColor(preferences, HalfRhombusType.LEFT_FAT));
        rightFat.setColor(getColor(preferences, HalfRhombusType.RIGHT_FAT));

        preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("left_skinny_color")) {
                    leftSkinny.setColor(getColor(preferences, HalfRhombusType.LEFT_SKINNY));
                } else if (key.equals("right_skinny_color")) {
                    rightSkinny.setColor(getColor(preferences, HalfRhombusType.RIGHT_SKINNY));
                } else if (key.equals("left_fat_color")) {
                    leftFat.setColor(getColor(preferences, HalfRhombusType.LEFT_FAT));
                } else if (key.equals("right_fat_color")) {
                    rightFat.setColor(getColor(preferences, HalfRhombusType.RIGHT_FAT));
                }
            }
        });
    }

    private int getColor(SharedPreferences preferences, HalfRhombusType rhombusType) {
        return preferences.getInt(rhombusType.colorKey, rhombusType.defaultColor);
    }
}