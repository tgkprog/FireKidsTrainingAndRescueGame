package com.lh9.feg1.firekidsgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class DataOrganizer {

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
		preferences.flush();
	}

	public void loadData() {
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
	}
}
