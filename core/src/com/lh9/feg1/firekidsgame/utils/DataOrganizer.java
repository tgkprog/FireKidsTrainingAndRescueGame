package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class DataOrganizer {

	boolean[] screensPlayed;
	boolean voice;
	boolean textureFiltering;
	boolean fps;
	boolean vibrations;
	boolean screenAwake;
	boolean gender;
	boolean prompts;
	// False is for boy
	// True is for girl
	int score;
	int experience;

	public boolean[] getScreensPlayed() {
		return screensPlayed;
	}

	public void resetGame() {

		screensPlayed = new boolean[7];
		voice = false;
		textureFiltering = true;
		fps = false;
		vibrations = false;
		screenAwake = false;
		gender = false;
		prompts = true;
		score = 0;
		experience = 0;

		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		preferences.putBoolean("voice", false);
		preferences.putBoolean("textureFiltering", true);
		preferences.putBoolean("fps", false);
		preferences.putBoolean("prompts", false);
		preferences.putBoolean("vibrations", false);
		preferences.putBoolean("screenAwake", false);
		preferences.putBoolean("gender", false);
		preferences.putInteger("experience", 0);
		preferences.putInteger("score", 0);
		preferences.putBoolean(Variables.FOODS_SCREEN, false);
		preferences.putBoolean(Variables.FITNESS_SCREEN_THREE, false);
		preferences.putBoolean(Variables.TRAINING_SCREEN_ONE, false);
		preferences.putBoolean(Variables.TRAINING_SCREEN_TWO, false);
		preferences.putBoolean(Variables.CAT_RESCUE_SCREEN, false);
		preferences.putBoolean(Variables.RESCUE_METRO_SCREEN, false);
		preferences.putBoolean(Variables.ELEVATOR_SCREEN, false);
		preferences.flush();
	}

	public void setScreensPlayed(boolean[] screensPlayed) {
		this.screensPlayed = screensPlayed;
	}

	public int getScore() {
		return score;
	}

	public boolean getPrompts() {
		return prompts;
	}

	public int getExperience() {
		return experience;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setPrompts(boolean prompts) {
		this.prompts = prompts;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public boolean getGender() {
		return gender;
	}

	public boolean getVoice() {
		return voice;
	}

	public boolean getTextureFiltering() {
		return textureFiltering;
	}

	public boolean getFps() {
		return fps;
	}

	public boolean getVibrations() {
		return vibrations;
	}

	public boolean getScreenAwake() {
		return screenAwake;
	}

	public void setVoice(boolean voice) {
		this.voice = voice;
	}

	public void setTextureFiltering(boolean textureFiltering) {
		this.textureFiltering = textureFiltering;
	}

	public void setFps(boolean fps) {
		this.fps = fps;
	}

	public void setVibrations(boolean vibrations) {
		this.vibrations = vibrations;
	}

	public void setScreenAwake(boolean screenAwake) {
		this.screenAwake = screenAwake;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void saveData() {
		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		preferences.putBoolean("voice", voice);
		preferences.putBoolean("textureFiltering", textureFiltering);
		preferences.putBoolean("fps", fps);
		preferences.putBoolean("prompts", prompts);
		preferences.putBoolean("vibrations", vibrations);
		preferences.putBoolean("screenAwake", screenAwake);
		preferences.putBoolean("gender", gender);
		preferences.putInteger("experience", experience);
		preferences.putInteger("score", score);
		preferences.putBoolean(Variables.FOODS_SCREEN, screensPlayed[0]);
		preferences
				.putBoolean(Variables.FITNESS_SCREEN_THREE, screensPlayed[1]);
		preferences.putBoolean(Variables.TRAINING_SCREEN_ONE, screensPlayed[2]);
		preferences.putBoolean(Variables.TRAINING_SCREEN_TWO, screensPlayed[3]);
		preferences.putBoolean(Variables.CAT_RESCUE_SCREEN, screensPlayed[4]);
		preferences.putBoolean(Variables.RESCUE_METRO_SCREEN, screensPlayed[5]);
		preferences.putBoolean(Variables.ELEVATOR_SCREEN, screensPlayed[6]);
		preferences.flush();
	}

	public void loadData() {

		screensPlayed = new boolean[7];

		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		voice = preferences.getBoolean("voice", true);
		textureFiltering = preferences.getBoolean("textureFiltering", true);
		fps = preferences.getBoolean("fps", false);
		gender = preferences.getBoolean("gender", false);
		prompts = preferences.getBoolean("prompts", false);
		vibrations = preferences.getBoolean("vibrations", true);
		screenAwake = preferences.getBoolean("screenAwake", true);
		experience = preferences.getInteger("experience", 0);
		score = preferences.getInteger("score", 0);
		screensPlayed[0] = preferences
				.getBoolean(Variables.FOODS_SCREEN, false);
		screensPlayed[1] = preferences.getBoolean(
				Variables.FITNESS_SCREEN_THREE, false);
		screensPlayed[2] = preferences.getBoolean(
				Variables.TRAINING_SCREEN_ONE, false);
		screensPlayed[3] = preferences.getBoolean(
				Variables.TRAINING_SCREEN_TWO, false);
		screensPlayed[4] = preferences.getBoolean(Variables.CAT_RESCUE_SCREEN,
				false);
		screensPlayed[5] = preferences.getBoolean(
				Variables.RESCUE_METRO_SCREEN, false);
		screensPlayed[6] = preferences.getBoolean(Variables.ELEVATOR_SCREEN,
				false);
	}
}
