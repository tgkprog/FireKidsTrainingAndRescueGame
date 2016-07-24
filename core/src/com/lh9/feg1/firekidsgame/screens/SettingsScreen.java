package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.graphics.LaneManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class SettingsScreen implements Screen {

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Arrow firstArrow;
	Arrow secondArrow;
	LaneManager laneManager;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Button menu;
	Button settingsText;
	Button fps;
	Button fpsText;
	Button textureFiltering;
	Button textureFilteringText;
	Button voice;
	Button voiceText;
	Button vibrations;
	Button vibrationsText;
	Button screenAwake;
	Button screenAwakeText;

	boolean blinked;

	final Starter game;

	public SettingsScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		menu = new Button(395, -450, assetsManager.menu);
		menu.goUp(180);

		voice = new Button(265, -450, assetsManager.switchButton);
		voice.goUp(25);
		screenAwake = new Button(265, -450, assetsManager.switchButton);
		screenAwake.goUp(120);
		textureFiltering = new Button(535, -450, assetsManager.switchButton);
		textureFiltering.goUp(205);
		vibrations = new Button(535, -450, assetsManager.switchButton);
		vibrations.goUp(120);
		fps = new Button(535, -450, assetsManager.switchButton);
		fps.goUp(25);

		settingsText = new Button(225, -450, assetsManager.settingsText);
		settingsText.goUp(410);
		vibrationsText = new Button(605, -450, assetsManager.vibrationsText);
		vibrationsText.goUp(135);
		fpsText = new Button(605, -450, assetsManager.fpsText);
		fpsText.goUp(40);
		textureFilteringText = new Button(35, -450,
				assetsManager.textureFilteringText);
		textureFilteringText.goUp(40);
		voiceText = new Button(605, -450, assetsManager.voiceText);
		voiceText.goUp(220);
		screenAwakeText = new Button(35, -450, assetsManager.screenAwakeText);
		screenAwakeText.goUp(135);

		firstArrow = new Arrow(440, 790, assetsManager.arrow, 90, 120);
		secondArrow = new Arrow(705, 780, assetsManager.arrow, 20, 120);
		firstArrow.setAlpha(1);
		secondArrow.setAlpha(1);

		laneManager = new LaneManager(assetsManager.lane, 1320, 1450);
		laneManager.setAlpha(1);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setSettingsButtons(fps, textureFiltering, voice,
				vibrations, screenAwake, dataOrganizer);

		cloudManager.stop();

		camera.reset();
		camera.position.x = 1270;
		camera.position.y = 765;
		camera.zoom = 3.175f;

		camera.moveX(1270, 0, 0, 100);
		camera.moveY(765, 0, 0, 100);
		camera.zoom(3.175f, 100f);

		if (dataOrganizer.getFps() == true) {
			fps.red();
		}
		if (dataOrganizer.getVoice() == true) {
			voice.red();
		}
		if (dataOrganizer.getVibrations() == true) {
			vibrations.red();
		}
		if (dataOrganizer.getTextureFiltering() == true) {
			textureFiltering.red();
		}
		if (dataOrganizer.getScreenAwake() == true) {
			screenAwake.red();
		}

		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawButtons(delta);
		drawTexts(delta);
		drawClouds(delta);
		drawFps();

		batch.end();

		manageSelectingScreen();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	void drawButtons(float delta) {
		fps.render(batch, delta);
		voice.render(batch, delta);
		textureFiltering.render(batch, delta);
		vibrations.render(batch, delta);
		screenAwake.render(batch, delta);
		menu.render(batch, delta);
	}

	void drawTexts(float delta) {
		textureFilteringText.render(batch, delta);
		fpsText.render(batch, delta);
		vibrationsText.render(batch, delta);
		voiceText.render(batch, delta);
		screenAwakeText.render(batch, delta);
		settingsText.render(batch, delta);
	}

	void updateLogics(double delta) {
		if (blinked == false && settingsText.notMoving() == true) {
			blinked = true;
			settingsText.blink();
		}

		if (dataOrganizer.getFps() == true) {
			fps.red();
		} else {
			fps.normal();
		}
		if (dataOrganizer.getVoice() == true) {
			voice.red();
		} else {
			voice.normal();
		}
		if (dataOrganizer.getVibrations() == true) {
			vibrations.red();
		} else {
			vibrations.normal();
		}
		if (dataOrganizer.getTextureFiltering() == true) {
			textureFiltering.red();
		} else {
			textureFiltering.normal();
		}
		if (dataOrganizer.getScreenAwake() == true) {
			screenAwake.red();
		} else {
			screenAwake.normal();
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new MenuScreen(game));
			}
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawBackground(float delta) {
		batch.draw(assetsManager.truckCockpit[0], 0, 765);
		batch.draw(assetsManager.truckCockpit[1], 1275, 765);
		batch.draw(assetsManager.truckCockpit[2], 0, 0);
		batch.draw(assetsManager.truckCockpit[3], 1275, 0);
		laneManager.render(batch, delta);
		batch.draw(assetsManager.cockpitPart, 1100, 0);
		firstArrow.render(batch, delta);
		secondArrow.render(batch, delta);
	}

	void drawFps() {
		fpsManager.render(batch);
	}
}