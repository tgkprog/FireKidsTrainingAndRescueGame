package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.math.Vector2;

public class Variables {

	static final String MENU_SCREEN = "Menu Screen";
	static final String MEET_THE_TRUCKS = "Meet The Trucks";
	static final String FITNESS_SCREEN_ONE = "Fitness Screen One";
	static final String TRAINING_SCREEN_ONE = "Training Screen One";
	static final String TRAINING_SCREEN_TWO = "Training Screen Two";
	static final String RESCUE_METRO_SCREEN = "Rescue Metro Screen";
	static final String BIG_ROAD_RESCUE_SCREEN = "Big Road Rescue Screen";
	static final String SETTINGS_SCREEN = "Settings Screen";
	static final String AUTHORS_SCREEN = "Authors Screen";
	static final String CAT_RESCUE_SCREEN = "Cat Rescue Screen";
	static final String FITNESS_SCREEN_TWO = "Fitness Screen Two";
	static final String FITNESS_SCREEN_THREE = "Fitness Screen Three";
	static final String ELEVATOR_SCREEN = "Elevator Screen";
	static final String FOODS_SCREEN = "Foods Screen";

	static final double MAX_LOGO_SCALE = 0.5f;
	static final double DELAY_CHANGING_SCREENS = 0f;

	static final Vector2 LOGO_POSITION = new Vector2(130, -150);
	static final Vector2 RUN_BUTTON_POSITION = new Vector2(650, 220);
	static final Vector2 PAUSE_BUTTON_POSITION = new Vector2(710, 400);

	static final boolean DEBUG_MODE = false;

	public boolean getDEBUG_MODE() {
		return DEBUG_MODE;
	}

	public double getMAX_LOGO_SCALE() {
		return MAX_LOGO_SCALE;
	}

	public double getDELAY_CHANGING_SCREENS() {
		return DELAY_CHANGING_SCREENS;
	}

	public Vector2 getLOGO_POSITION() {
		return LOGO_POSITION;
	}

	public Vector2 getRUN_BUTTON_POSITION() {
		return RUN_BUTTON_POSITION;
	}

	public Vector2 getPAUSE_BUTTON_POSITION() {
		return PAUSE_BUTTON_POSITION;
	}

	public String getMEET_THE_TRUCKS() {
		return MEET_THE_TRUCKS;
	}

	public String getFITNESS_SCREEN_ONE() {
		return FITNESS_SCREEN_ONE;
	}

	public String getTRAINING_SCREEN_ONE() {
		return TRAINING_SCREEN_ONE;
	}

	public String getTRAINING_SCREEN_TWO() {
		return TRAINING_SCREEN_TWO;
	}

	public String getRESCUE_METRO_SCREEN() {
		return RESCUE_METRO_SCREEN;
	}

	public String getBIG_ROAD_RESCUE_SCREEN() {
		return BIG_ROAD_RESCUE_SCREEN;
	}

	public String getSETTINGS_SCREEN() {
		return SETTINGS_SCREEN;
	}

	public String getMENU_SCREEN() {
		return MENU_SCREEN;
	}

	public String getAUTHORS_SCREEN() {
		return AUTHORS_SCREEN;
	}

	public String getCAT_RESCUE_SCREEN() {
		return CAT_RESCUE_SCREEN;
	}

	public String getFITNESS_SCREEN_TWO() {
		return FITNESS_SCREEN_TWO;
	}

	public String getFITNESS_SCREEN_THREE() {
		return FITNESS_SCREEN_THREE;
	}

	public String getELEVATOR_SCREEN() {
		return ELEVATOR_SCREEN;
	}

	public String getFOODS_SCREEN() {
		return FOODS_SCREEN;
	}
}