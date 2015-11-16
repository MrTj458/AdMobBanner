/*
 * Copyright (C) 2013 Google, Inc.
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
package com.google.android.gms.example.bannerexample;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Main class that holds all of the Java for the BannerTextActivity activity.
 */
public class BannerTextActivity extends ActionBarActivity
{

    private AdView mAdView;
    private Button changeColorButton;
    private RelativeLayout background;

    /**
     * Sets up all of the variables and starts the helper methods.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_text);

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        changeColorButton = (Button) findViewById(R.id.changeColorButton);
        background = (RelativeLayout) findViewById(R.id.background);

        changeColors();
        setupListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause()
    {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume()
    {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy()
    {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    /**
     * Sets up the button to change the colors of the button, button text, and background.
     */
    private void setupListeners()
    {
        changeColorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeColors();
            }
        });
    }

    private void changeColors()
    {
        int backgroundRed = (int) (Math.random() * 256);
        int backgroundBlue = (int) (Math.random() * 256);
        int backgroundGreen = (int) (Math.random() * 256);
        background.setBackgroundColor(Color.rgb(backgroundRed, backgroundBlue, backgroundGreen));

        int buttonRed = (int) (Math.random() * 256);
        int buttonBlue = (int) (Math.random() * 256);
        int buttonGreen = (int) (Math.random() * 256);
        changeColorButton.setBackgroundColor(Color.rgb(buttonRed, buttonBlue, buttonGreen));

        int textRed = (int) (Math.random() * 256);
        int textBlue = (int) (Math.random() * 256);
        int textGreen = (int) (Math.random() * 256);
        changeColorButton.setTextColor(Color.rgb(textRed, textBlue, textGreen));
    }
}
