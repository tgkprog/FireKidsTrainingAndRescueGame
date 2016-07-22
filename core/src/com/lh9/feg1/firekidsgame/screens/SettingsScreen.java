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
import com.lh9.feg1.firekidsgame.graphics.LaneManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class SettingsScreen implements Screen {

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

		menu = new Button(395, -250, assetsManager.menu);
		menu.goUp(180);
		settingsText = new Button(225, -250, assetsManager.settingsText);
		settingsText.goUp(410);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		cloudManager.stop();

		camera.reset();
		camera.position.x = 1270;
		camera.position.y = 765;
		camera.zoom = 3.175f;

		camera.moveX(1270, 0, 0, 100);
		camera.moveY(765, 0, 0, 100);
		camera.zoom(3.175f, 100f);

		firstArrow = new Arrow(440, 790, assetsManager.arrow, 90, 120);
		secondArrow = new Arrow(705, 780, assetsManager.arrow, 20, 120);
		firstArrow.setAlpha(1);
		secondArrow.setAlpha(1);


		laneManager = new LaneManager(assetsManager.lane, 1320, 1450);
		laneManager.setAlpha(1);
	}

	@Override
	public void render(float delta) {
		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(assetsManager.truckCockpit[0], 0, 765);
		batch.draw(assetsManager.truckCockpit[1], 1275, 765);
		batch.draw(assetsManager.truckCockpit[2], 0, 0);
		batch.draw(assetsManager.truckCockpit[3], 1275, 0);
		laneManager.render(batch, delta);
		batch.draw(assetsManager.cockpitPart, 1100, 0);
		firstArrow.render(batch, delta);
		secondArrow.render(batch, delta);
		batch.end();

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawButtons(delta);
		cloudManager.render(batch, delta);
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
		menu.render(batch, delta);
		settingsText.render(batch, delta);
	}

	void updateLogics(double delta) {
		if (blinked == false && settingsText.notMoving() == true) {
			blinked = true;
			settingsText.blink();
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
	}
}