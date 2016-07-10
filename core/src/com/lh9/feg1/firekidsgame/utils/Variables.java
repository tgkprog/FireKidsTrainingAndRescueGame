package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.math.Vector2;

public class Variables {

	static final String meetTheTrucks = "Meet The Trucks";
	static final double maxLogoScale = 0.5f;
	static final double delayChangingScreens = 3f;
	static final Vector2 logoPosition = new Vector2(130, -150);

	static final boolean debugMode = false;

	public boolean getDebugMode() {
		return debugMode;
	}

	public double getMaxLogoScale() {
		return maxLogoScale;
	}

	public double getDelayChangingScreens() {
		return delayChangingScreens;
	}

	public Vector2 getLogoPosition() {
		return logoPosition;
	}
	public String getMeetTheTrucks(){
		return meetTheTrucks;
	}
}