package com.lh9.feg1.firekidsgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lh9.feg1.firekidsgame.Starter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Starter(), config);
		config.title = "Fire Kids Game";
		config.width = 800;
		config.height = 480;
		config.samples = 8;
	}
}
