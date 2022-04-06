package com.lh9.feg1.firekidsgame;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.lh9.feg1.firekidsgame.gdxpay.AndroidResolver;

import org.jetbrains.annotations.NotNull;

public class AndroidLauncher extends AndroidApplication implements AdsController {

    private static final String AD_UNIT_ID = "ca-app-pub-9854936984598191/3067088838";
    private static final String TAG = "MyActivity";
    static boolean runEvery50sec = false;
    private Handler addHandler;
    private InterstitialAd interstitialAd;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Starter feg = new Starter(this);

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(feg, config);

        //===== detect operating system and Configure platform dependent code ==========================
        if (Starter.ISAPPSTORE == Starter.APPSTORE_GOOGLE) {
            Starter.setPlatformResolver(new AndroidResolver(feg, this));
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull @org.jetbrains.annotations.NotNull InitializationStatus initializationStatus) {
            }
        });

        addHandler = new Handler();
        loadAdd();

    }

    private void loadAdd() {
        if (interstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(this, AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull @NotNull InterstitialAd interstitialAd) {
                    AndroidLauncher.this.interstitialAd = interstitialAd;
                    Log.i(TAG, "onAddLoaded");

                    interstitialAd.setFullScreenContentCallback(
                            new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    AndroidLauncher.this.interstitialAd = null;
                                    Log.d(TAG, "The ad was dismissed.");
                                    loadAdd();
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(@NotNull AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    AndroidLauncher.this.interstitialAd = null;
                                    Log.d(TAG, "The ad failed to show.");
                                    loadAdd();
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when fullscreen content is shown.
                                    //AndroidLauncher.this.interstitialAd = null;
                                    Log.d(TAG, "The ad was shown.");
                                    //loadAdd();
                                }
                            });

                    if (runEvery50sec) {
                        addHandler.postDelayed(r = new Runnable() {
                            @Override
                            public void run() {
                                showInterstitial();
                            }
                        }, 47000);
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull @NotNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.e(TAG, "onAdFailedToLoad = " + loadAdError.getMessage());
                    interstitialAd = null;
                }
            });
        }
    }

    @Override
    public void showInterstitial() {
        // TODO Auto-generated method stub
        if (interstitialAd != null) {
            //Log.i(TAG, "Showing add");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    interstitialAd.show(AndroidLauncher.this);
                }
            });
        } else {
            Log.e(TAG, "Add didn't load");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AndroidLauncher.this, "Add is not loaded", Toast.LENGTH_LONG).show();
                    loadAdd();
                }
            });
        }
    }

    @Override
    public void setRunEvery50sec(boolean runEvery50sec) {
        AndroidLauncher.runEvery50sec = runEvery50sec;
        if (runEvery50sec)
            showInterstitial();
        else {
            interstitialAd = null;
            addHandler.removeCallbacks(r);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadAdd();
                }
            });
        }

    }

    @Override
    public void toastMessage(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AndroidLauncher.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
