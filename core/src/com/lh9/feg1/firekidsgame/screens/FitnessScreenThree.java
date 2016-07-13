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
import com.lh9.feg1.firekidsgame.files.windows.Dialogue;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.SpeedBar;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class FitnessScreenThree implements Screen {

	double timerSpeedGirl;

	Human boy;
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
	SpeedBar speedBar;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public FitnessScreenThree(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(350);

		runButton = new Button(710, 0, assetsManager.runButton);
		runButton.goUp(150);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,assetsManager.darkScreen, 250f, 150f,
				assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		speedBar = new SpeedBar(assetsManager.speedBar, 10, 450);
		cloudManager.stop();

		camera.zoom = 0.98f;
		camera.position.x = 400;
		camera.position.y = 240;
		camera.moveX(240, 0, 0, 0);
		camera.zoom(0.98f, 1.3f);

		dialogueWindow.popUp();

		boy = new Human();
		boy.create(assetsManager.spritesheetBoyElliptical,4, 2, 7, 400, 50);

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlElliptical, 4, 2, 7, 100, 50);

		boy.setMaxSpeed(4f);
		girl.setMaxSpeed(4f);

		boy.setAnimationOnly(true);
		girl.setAnimationOnly(true);

		inputInterpreter.setControlledHuman(boy);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

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
		drawParticles(delta);
		drawCounters();
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
		boy.render(batch, delta);
		girl.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}
		if (boy.getCounter() == 60 && finish == false) {
			dialogueWindow.popUp();
			runButton.setDontRespond(true);
			finish = true;
			assetsManager.stars.start();
		}
		if (girl.getCounter() == 60 && finish == false) {
			dialogueWindow.popUp();
			finish = true;
			runButton.setDontRespond(true);
		}
		if (finish == true){
			girl.setSpeed(0);
			boy.setSpeed(0);
		}
		updateCameraLogics(delta);
		updateGirlAction(delta);
	
		if(finish == true && dialogueWindow.isVisibile() == false && exit == false){
			cloudManager.start();
			exit = true;
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setScreen(new MenuScreen(game));
		}
	
	}

	void updateCameraLogics(double delta) {
		if (boy.getX() >= 400 && boy.getX() < 3600) {
			camera.position.x = (boy.getX());
		}
	}

	void drawBackground() {
		batch.draw(assetsManager.levelBackgrounds[1], 0, 0);
	}

	void updateGirlAction(double delta) {
		if (girl.getCounter() < boy.getCounter() - 3)
			timerSpeedGirl += delta * 10;
		if (firstDialogueClicked == true)
			timerSpeedGirl += delta;
		if (timerSpeedGirl > 0.18) {
			timerSpeedGirl = 0;
			if (firstDialogueClicked == true && finish == false)
				girl.move();
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);

		assetsManager.leaf.update(delta);
		assetsManager.leaf.draw(batch);
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawCounters() {
		assetsManager.font.draw(batch, Integer.toString(boy.getCounter()) + " - 60",
				boy.getX() + 80, 350);
		assetsManager.font.draw(batch, Integer.toString(girl.getCounter())+" - 60",
				girl.getX() + 80, 350);
	
	}
}