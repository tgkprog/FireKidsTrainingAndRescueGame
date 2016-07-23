package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class MeetTheTrucksScreen implements Screen {

	double timerSpeedGirl;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;

	Bar speedBar;

	Human girl;

	Button pause;
	Button runButton;

	Dialogue dialogueWindow;

	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;

	boolean finish = false;

	final Starter game;

	public MeetTheTrucksScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		pause = new Button((int) variables.getPauseButtonPosition().x, 120,
				assetsManager.pause);
		pause.goUp((int) variables.getPauseButtonPosition().y);
		runButton = new Button((int) variables.getRunButtonPosition().x, 0,
				assetsManager.runButton);
		runButton.goUp((int) variables.getRunButtonPosition().y);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		cloudManager.stop();

		camera.zoom = 0.98f;
		camera.position.x = 400;
		camera.position.y = 240;

		camera.moveX(390, 0, 0, 100);
		camera.moveY(240, 0, 0, 100);
		camera.zoom(0.98f, 100f);

		dialogueWindow.popUp();

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlRunning, 5, 3, 11, -300, 35);

		inputInterpreter.setControlledHuman(girl);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 8);
		speedBar.setVisibility(true);
		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getMeetTheTrucks());

		inputInterpreter.setMenuWindow(menuWindow);

	}

	@Override
	public void render(float delta) {
		float deltaTemp = delta;

		if (menuWindow.isVisibile() == true)
			delta = 0;
		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		drawBackground();
		drawCharacters(delta);
		drawPointer(delta);
		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawParticles(delta);
		drawBar(delta);
		drawButtons(deltaTemp);
		drawWindows(deltaTemp);
		cloudManager.render(batch, deltaTemp);
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

	void drawCharacters(float delta) {
		girl.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {

		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {

		if (girl.getX() >= 400 && secondDialogueClicked == false) {
			secondDialogueClicked = true;
			dialogueWindow.popUp();
			girl.setSpeed(0);
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}

		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setScreen(new FitnessScreenTwo(game));
		}

		updateCameraLogics(delta);
		updateGirlAction(delta);

	}

	void updateCameraLogics(double delta) {
		if (girl.getX() >= 400 && girl.getX() < 3600) {
			camera.position.x = (girl.getX());
		}

	}

	void drawBackground() {
		if (girl.getX() <= 1200)
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
		if (girl.getX() <= 2000)
			batch.draw(assetsManager.parkBackgrounds[1], 790, 0);

		if (girl.getX() >= 1200 && girl.getX() < 2600)
			batch.draw(assetsManager.parkBackgrounds[2], 1590, 0);

		if (girl.getX() >= 1600 && girl.getX() < 3100)
			batch.draw(assetsManager.parkBackgrounds[5], 2050, 0);
		if (girl.getX() >= 2100)
			batch.draw(assetsManager.parkBackgrounds[4], 2515, 0);
		if (girl.getX() >= 2600)
			batch.draw(assetsManager.parkBackgrounds[3], 3315, 0);

	}

	void updateGirlAction(double delta) {

	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);

		assetsManager.leaf.update(delta);
		assetsManager.leaf.draw(batch);

	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {

	}

	void drawBar(float delta) {

		// speedBar.setSpeed(boy.getSpeed());
		// speedBar.render(batch);
		// Works on a placeholder, but having no actual asset

	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMeetTheTrucks()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
	}
}