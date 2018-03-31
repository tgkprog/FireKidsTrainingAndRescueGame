package com.lh9.feg1.firekidsgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.desktop.gdxpay.DesktopResolver;

public class DesktopLauncher {
    public static void main(String[] arg) {

        Starter feg = new Starter();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        LwjglApplication lwjglApplication = new LwjglApplication(feg, config);
        config.title = "Fire Kids Game";
        config.width = 800;
        config.height = 480;
        config.samples = 8;

        Starter.setPlatformResolver(new DesktopResolver(feg, lwjglApplication));
    }
}
