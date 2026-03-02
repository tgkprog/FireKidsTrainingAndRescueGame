package com.lh9.feg1.firekidsgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.lh9.feg1.firekidsgame.gdxpay.AndroidResolver;

public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Starter feg = new Starter();

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(feg, config);

        //===== detect operating system and Configure platform dependent code ==========================
        if (Starter.ISAPPSTORE == Starter.APPSTORE_GOOGLE) {
            Starter.setPlatformResolver(new AndroidResolver(feg, this));
        }
    }
}
