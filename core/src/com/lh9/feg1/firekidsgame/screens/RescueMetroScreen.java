package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

public class RescueMetroScreen implements Screen {

	double timerSpeedGirl;

	Bar speedBar;

	Sprite rescueMetroSadPeople;

	Human girl;

	Button pause;
	Button runButton;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;
	
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

	public RescueMetroScreen(final Starter gam) {

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

		rescueMetroSadPeople = new Sprite(assetsManager.rescueMetroSadPeople);
		rescueMetroSadPeople.setPosition(580, 155);
		rescueMetroSadPeople.setScale(0.35f);

		camera.zoom = 0.4f;
		camera.position.x = 1070;
		camera.position.y = 435;

		camera.moveX(1070, 0, 0, 100);
		camera.moveY(435, 0, 0, 100);
		camera.zoom(0.4f, 100f);

		dialogueWindow.popUp();

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlRunning, 5, 3, 11, -100, 35);

		inputInterpreter.setControlledHuman(girl);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 8);
		speedBar.setVisibility(true);
	
		
		menuButton = new Button(400, 110, assetsManager.menu);
		playButton = new Button(450, 110, assetsManager.playButton);
		retryButton = new Button(500, 110, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getRescueMetroScreen());
		
		inputInterpreter.setMenuWindow(menuWindow);

	}

	@Override
	public void render(float delta) {

		
		float deltaTemp = delta;
		
		if(menuWindow.isVisibile() == true)		
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
		// runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
		updateCameraLogics(delta);
		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			camera.reset();
			camera.zoom(1.1f, 3);
	//		camera.moveX(800, 2, 2, 4);
	//		camera.moveY(480,2, 2, 4);
	// 		camera.zoom(1.95f,100);
			camera.moveX(800, 2, 2, 4);
			camera.moveY(300,2, 2, 4);
	
			firstDialogueClicked = true;
			
		}
	}

	void updateCameraLogics(double delta) {

	}

	void drawBackground() {
		batch.draw(assetsManager.rescueMetro[0], 0, 0);
		batch.draw(assetsManager.rescueMetro[1], 797, 0);
		rescueMetroSadPeople.draw(batch);
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {
	}

	void drawBar(float delta) {
		// batch.draw(assetsManager.speedBar, 160, 440);
		// speedBar.render(batch, delta, boy.getSpeed());
	}
	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getRescueMetroScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new RescueMetroScreen(game));
			}
		}
	}

}