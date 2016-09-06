package com.lh9.feg1.firekidsgame.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.googlecode.gwt.crypto.client.AESCipher;
import com.googlecode.gwt.crypto.client.TripleDesKeyGenerator;
import com.lh9.feg1.firekidsgame.models.GameStateSave;

public class DataOrganizer {

	AESCipher encryptor;

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

	public DataOrganizer() {
		TripleDesKeyGenerator generator = new TripleDesKeyGenerator();
		byte[] key = generator
				.decodeKey("04578a8f0be3a7109d9e5e86839e3bc41654927034df92ec"); // you
																				// can
																				// pass
																				// your
																				// own
																				// string
																				// here

		encryptor = new AESCipher();
		encryptor.setKey(key);
	}

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

		GameStateSave model = new GameStateSave();
		model.setVoice(false);
		model.setTextureFiltering(true);
		model.setFps(false);
		model.setPrompts(true);
		model.setVibrations(false);
		model.setScreenAwake(true);
		model.setGender(false);
		model.setExperience(0);
		model.setScore(0);
		model.setScreensPlayed(screensPlayed);

		Json json = new Json();
		String dataSaveInJson = json.toJson(model);

		String encryptedGameStateSaveInString = encryptString(dataSaveInJson);

		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		preferences.putString("encryptedGameStateSave",
				encryptedGameStateSaveInString);
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

		GameStateSave model = new GameStateSave();
		model.setVoice(voice);
		model.setTextureFiltering(textureFiltering);
		model.setFps(fps);
		model.setPrompts(prompts);
		model.setVibrations(vibrations);
		model.setScreenAwake(screenAwake);
		model.setGender(gender);
		model.setExperience(experience);
		model.setScore(score);
		model.setScreensPlayed(screensPlayed);

		Json json = new Json();
		String dataSaveInJson = json.toJson(model);
		String encryptedGameStateSaveInString = encryptString(dataSaveInJson);

		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		preferences.putString("encryptedGameStateSave",
				encryptedGameStateSaveInString);
		preferences.flush();
	}

	public void loadData() {

		screensPlayed = new boolean[7];
		String encryptedGameStateSaveInString = " ";
		String decryptedGameStateSaveInString = " ";

		Preferences preferences = Gdx.app
				.getPreferences("Application prefferences");
		encryptedGameStateSaveInString = preferences.getString(
				"encryptedGameStateSave", " ");

		if (!encryptedGameStateSaveInString.equals(" ")) {

			decryptedGameStateSaveInString = decryptString(encryptedGameStateSaveInString);

			System.out.println("encrypted:");
			System.out.println(encryptedGameStateSaveInString);

			System.out.println("decrypted:");
			System.out.println(decryptedGameStateSaveInString);

			Json json = new Json();

			GameStateSave model = json.fromJson(GameStateSave.class,
					decryptedGameStateSaveInString);

			voice = model.getVoice();
			textureFiltering = model.getTextureFiltering();
			fps = model.getFps();
			gender = model.getGender();
			prompts = model.getPrompts();
			vibrations = model.getVibrations();
			screenAwake = model.getScreenAwake();
			experience = model.getExperience();
			score = model.getScore();
			screensPlayed = model.getScreensPlayed();
		} else {
			resetGame();
			System.out.println("string destinated to encrypt is empty");
		}
	}

	private String encryptString(String string) {

		System.out.println("I am now encrypting string: " + string);

		String encoded = Base64.getEncoder().encodeToString(string.getBytes());

		System.out.println("Now it's: " + encoded);
		System.out.println("And again, after decryption: "
				+ decryptString(encoded));

		return encoded;
	}

	private String decryptString(String string) {

		byte[] barr = Base64.getDecoder().decode(string);

		System.out.println("Decrypted string, it's now:" + barr);

		string = new String(barr, StandardCharsets.UTF_8);

		return string;
	}
}