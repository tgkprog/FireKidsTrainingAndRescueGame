package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.math.Vector2;

public class Variables {

	static final public String MR_TUSHAR_WEBSITE = "www.google.com";
	
	static final public String[] MEET_THE_TRUCKS_POP_UP_1 = {"Run through the","training ground and", "complete training!"};
	static final public String[] FOODS_POP_UP_1 = {"Pick only healthy","food to become a", "strong firefighter!"};
	static final public String[] FITNESS_SCREEN_ONE_POP_UP_1 = {"Race through the","park faster than","your opponent!"};
	static final public String[] FITNESS_SCREEN_TWO_POP_UP_1 = {"Finish excercises","faster than your", "opponent!"};
	static final public String[] FITNESS_SCREEN_THREE_POP_UP_1 = {"Finish excercises","faster than your", "opponent!"};
	static final public String[] TRAINING_SCREEN_ONE_POP_UP_1 = {"Driving so heavy", "machine is not a","piece of cake!"};
	static final public String[] TRAINING_SCREEN_ONE_POP_UP_2 = {"Click buttons", "with a red arrow to", "turn on the sirene!"};
	static final public String[] TRAINING_SCREEN_ONE_POP_UP_3 = {"Good! Now click", " the red button to", "turn on the sirene!"};
	static final public String[] TRAINING_SCREEN_TWO_POP_UP_1 = {"Drive the truck","to the building","which is on fire!"};
	static final public String[] PRE_ELEVATOR_SCREEN_POP_UP_1 = {"Drive the truck","to building where","elevator jammed!"};
	static final public String[] ELEVATOR_SCREEN_POP_UP_1 = {"Hit the doors","with the hammer", "untill they open!"};
	static final public String[] PRE_RESCUE_CAT_POP_UP_1 = {"Drive the truck"," to the tree where"," the cat has stuck!"};
	static final public String[] RESCUE_CAT_POP_UP_1 = {"Lift the basket","high enough so the","cat could get down!"};
	static final public String[] PRE_RESCUE_METRO_POP_UP_1 = {"Drive the truck","to the metro station","to help people!"};
	static final public String[] RESCUE_METRO_POP_UP_1 = {"Hit the doors","with the hammer to","open jammed doors!"};	
	static final public String[] BIG_ROAD_RESCUE_POP_UP_1 = {"Drive through the","city and put off the", "fire from buldings!"};
	
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

	static final Vector2 LOGO_POSITION = new Vector2(128, -150);
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