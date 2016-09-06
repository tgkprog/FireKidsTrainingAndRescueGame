package com.lh9.feg1.firekidsgame.models;

public class GameStateSave {

	boolean[] screensPlayed;
	boolean voice;
	boolean textureFiltering;
	boolean fps;
	boolean vibrations;
	boolean screenAwake;
	boolean gender;
	boolean prompts;

	int score;
	int experience;

	public boolean[] getScreensPlayed() {
		return screensPlayed;
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
}