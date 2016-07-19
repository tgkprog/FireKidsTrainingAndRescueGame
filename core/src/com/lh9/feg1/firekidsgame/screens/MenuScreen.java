package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class MenuScreen implements Screen {

	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Button meetTheTrucks;
	Button fireStation;
	Button[] levelButtons;

	boolean madeShakeScreen;

	final Starter game;

	public MenuScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		fireStation = new Button(700, 100, assetsManager.fireStation);
		meetTheTrucks = new Button(-2, -200, assetsManager.longButton);

		levelButtons = new Button[7];

		for (int a = 0; a < 7; a++) {
			if (a == 0)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.fitness);
			if (a == 1)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.training);
			if (a == 2)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueBuilding);
			if (a == 3)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueCat);
			if (a == 4)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueTrain);
			if (a == 5)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.button);
			if (a == 6)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.bigRoadRescue);

			levelButtons[a].goUp(0);
		}

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMeetTheTrucks(meetTheTrucks);
		inputInterpreter.setLevelButtons(levelButtons);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setFireStation(fireStation);
		cloudManager.stop();

		fireStation.goUp(825);

		camera.position.x = 888;
		camera.position.y = 525;
		camera.zoom = 2.17f;

		camera.zoom(2.17f, 1.3f);
		camera.moveY(camera.position.y, 10, 10, 100);
		camera.moveX(camera.position.x, 10, 10, 100);

	}

	@Override
	public void render(float delta) {

		camera.position.x = 957;
		camera.position.y = 575;
		camera.zoom = 2.39f;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(assetsManager.mainBackground[0], 0, 576);
		batch.draw(assetsManager.mainBackground[1], 960, 576);
		batch.draw(assetsManager.mainBackground[2], 0, 0);
		batch.draw(assetsManager.mainBackground[3], 960, 0);
		fireStation.render(batch, (float) delta);
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

	void drawButtons(double delta) {
		meetTheTrucks.render(batch, (float) delta);
		for (int a = 0; a < 7; a++) {
			levelButtons[a].render(batch, (float) delta);
		}

	}

	void updateLogics(double delta) {
		if (fireStation.notMoving() == true && madeShakeScreen == false) {
			madeShakeScreen = true;
			fireStation.blink();
			camera.shakeScreen();
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMeetTheTrucks()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTrainingScreenTwo()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new TrainingScreenTwo(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTrainingScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new TrainingScreenOne(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFitnessScreenOne()) {
			if (cloudManager.getAllScalesEqualOne() == true) {

				game.setScreen(new FitnessScreenOne(game));
			}
		}
	}
}