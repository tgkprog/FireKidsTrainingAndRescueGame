package com.lh9.feg1.firekidsgame.utils;

import java.nio.charset.Charset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
//import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
//import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;
//import com.googlecode.gwt.crypto.client.TripleDesCipher;
//import com.googlecode.gwt.crypto.client.TripleDesKeyGenerator;
import com.lh9.feg1.firekidsgame.models.GameStateSave;

public class DataOrganizer {

	//TripleDesCipher encryptor;

	GameStateSave gameStateSave;

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
		//TripleDesKeyGenerator generator = new TripleDesKeyGenerator();
		//byte[] key = generator
		//		.decodeKey("04578a8f0be3a7109d9e5e86839e3bc41654927034df92ec"); // you
																				// can
																				// pass
																				// your
																				// own
																				// string
																				// here

		//encryptor = new TripleDesCipher();
		//encryptor.setKey(key);
	}

	public boolean[] getScreensPlayed() {
		return screensPlayed;
	}

	public void resetGameState() {

		screensPlayed = new boolean[7];
		voice = false;
		textureFiltering = true;
		fps = false;
		vibrations = false;
		screenAwake = false;
		gender = false;
		prompts = false;
		score = 0;
		experience = 0;

		GameStateSave model = new GameStateSave();
		model.setVoice(false);
		model.setTextureFiltering(true);
		model.setFps(false);
		model.setPrompts(false);
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

			gameStateSave = new GameStateSave();

			try {
				gameStateSave = json.fromJson(GameStateSave.class,
						decryptedGameStateSaveInString);
	
				voice = gameStateSave.getVoice();
				textureFiltering = gameStateSave.getTextureFiltering();
				fps = gameStateSave.getFps();
				gender = gameStateSave.getGender();
				prompts = gameStateSave.getPrompts();
				vibrations = gameStateSave.getVibrations();
				screenAwake = gameStateSave.getScreenAwake();
				experience = gameStateSave.getExperience();
				score = gameStateSave.getScore();
				screensPlayed = gameStateSave.getScreensPlayed();
		
			} catch (SerializationException e) {
				resetGameState();
			}
		} else {
			resetGameState();
			System.out.println("string destinated to encrypt is empty");
		}
	}

	private String encryptString(String string) {
	/*	byte[] base64TextToEncrypt = string.getBytes();
		try {
			base64TextToEncrypt = Base64.encode(base64TextToEncrypt);

		} catch (DataLengthException e1) {
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		}

		String plainText = new String(base64TextToEncrypt,
				Charset.forName("UTF8"));

		return plainText;*/
		return string;
	}

	private String decryptString(String string) {
		/*byte[] base64TextToDecrypt = null;
		try {
			base64TextToDecrypt = Base64.decode(string);
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		String plainText = new String(base64TextToDecrypt,
				Charset.forName("UTF8"));*/

		//return plainText;
		return string;
	}
}