package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.files.windows.Dialogue;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.SpeedBar;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class TrainingScreenTwo implements Screen {

	double timerSpeedGirl;

	Truck truck;

	Button up;
	Button down;
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
	SpeedBar speedBar;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public TrainingScreenTwo(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(350);

		up = new Button(30, -100, assetsManager.arrowUp);
		up.goUp(380);
		down = new Button(30, -100, assetsManager.arrowDown);
		down.goUp(310);
		runButton = new Button(710, 0, assetsManager.runButton);
		runButton.goUp(150);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		speedBar = new SpeedBar(assetsManager.speedBar, 10, 450);
		cloudManager.stop();

		camera.zoom = 1.9f;
		camera.position.x = 800;
		camera.position.y = 470;

		camera.moveX(800, 0, 0, 100);
		camera.moveY(470, 0, 0, 100);
		camera.zoom(1.9f, 100f);

		dialogueWindow.popUp();

		truck = new Truck();
		truck.create(assetsManager.spritesheetTruck, 3, 3, 9, 1000, 135);
		truck.setMaxSpeed(20);
		truck.setMaxPositions(-14350, 1000);

		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();

		inputInterpreter.setControlledHuman(truck);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		inputInterpreter.loadDown(down);
		inputInterpreter.loadUp(up);
		inputInterpreter.setControlledTruck(truck);
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
		drawBackground();
		drawCharacters(delta);
		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		// drawBar();
		drawButtons(delta);
		drawWindows(delta);
		cloudManager.render(batch, delta);
		batch.end();
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
		truck.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
		up.render(batch, (float) delta);
		down.render(batch, (float) delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
		
		if (truck.getX() <= -14350) {
			truck.runAnimation();
			truck.animationLane();
			runButton.setDontRespond(true);
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}
		updateCameraLogics(delta);

	}

	void updateCameraLogics(double delta) {
		if (truck.getX() <= 500 && truck.getX() > -14200)
			camera.position.x = truck.getX() + 300;
	}

	void drawBackground() {
		batch.draw(assetsManager.bigRoad[0], 0, 0);
		batch.draw(assetsManager.bigRoad[1], -1600, 0);
		batch.draw(assetsManager.bigRoad[2], -3200, 0);
		batch.draw(assetsManager.bigRoad[3], -4800, 0);
		batch.draw(assetsManager.bigRoad[4], -6400, 0);
		batch.draw(assetsManager.bigRoad[5], -8000, 0);
		batch.draw(assetsManager.bigRoad[6], -9600, 0);
		batch.draw(assetsManager.bigRoad[7], -11200, 0);
		batch.draw(assetsManager.bigRoad[8], -12800, 0);
		batch.draw(assetsManager.bigRoad[9], -14400, 0);
		batch.draw(assetsManager.bigRoad[10], -16000, 0);
		batch.draw(assetsManager.bigRoad[11], -18600, 0);
	}

	void drawBar() {
		batch.draw(assetsManager.bar, 260, 410);
		batch.draw(assetsManager.boyHead, 270 + truck.getX() * 0.067f, 410);
	}
}