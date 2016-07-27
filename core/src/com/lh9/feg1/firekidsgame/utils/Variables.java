package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.math.Vector2;

public class Variables {

	static final String menuScreen = "Menu Screen";
	static final String meetTheTrucks = "Meet The Trucks";
	static final String fitnessScreenOne = "Fitness Screen One";
	static final String trainingScreen = "Training Screen";
	static final String trainingScreenTwo = "Training Screen Two";
	static final String rescueMetroScreen = "Rescue Metro Screen";
	static final String bigRoadRescueScreen = "Big Road Rescue Screen";
	static final String settingsScreen = "Settings Screen";
	static final String authorsScreen = "Authors Screen";
	static final String catRescueScreen = "Cat Rescue Screen";
	static final String fitnessScreenTwo = "Fitness Screen Two";
	static final String fitnessScreenThree = "Fitness Screen Three";
	
	static final double maxLogoScale = 0.5f;
	static final double delayChangingScreens = 3f;
	
	static final Vector2 logoPosition = new Vector2(130, -150);
	static final Vector2 runButtonPosition = new Vector2(650, 220);
	static final Vector2 pauseButtonPosition = new Vector2(710, 400);

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

	public Vector2 getRunButtonPosition() {
		return runButtonPosition;
	}

	public Vector2 getPauseButtonPosition() {
		return pauseButtonPosition;
	}

	public String getMeetTheTrucks() {
		return meetTheTrucks;
	}

	public String getFitnessScreenOne() {
		return fitnessScreenOne;
	}

	public String getTrainingScreen() {
		return trainingScreen;
	}

	public String getTrainingScreenTwo() {
		return trainingScreenTwo;
	}

	public String getRescueMetroScreen() {
		return rescueMetroScreen;
	}

	public String getBigRoadRescueScreen() {
		return bigRoadRescueScreen;
	}

	public String getSettingsScreen() {
		return settingsScreen;
	}

	public String getMenuScreen() {
		return menuScreen;
	}

	public String getAuthorsScreen() {
		return authorsScreen;
	}
	
	public String getCatRescueScreen() {
		return catRescueScreen;
	}
	
	public String getFitnessScreenTwo() {
		return fitnessScreenTwo;
	}
	public String getFitnessScreenThree() {
		return fitnessScreenThree;
	}
}