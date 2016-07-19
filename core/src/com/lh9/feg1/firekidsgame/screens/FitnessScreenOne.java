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

public class FitnessScreenOne implements Screen {

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

	public FitnessScreenOne(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		pause = new Button((int)variables.getPauseButtonPosition().x, 120, assetsManager.pause);
		pause.goUp((int)variables.getPauseButtonPosition().y);
		runButton = new Button((int)variables.getRunButtonPosition().x, 0, assetsManager.runButton);
		runButton.goUp((int)variables.getRunButtonPosition().y);

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

		camera.moveX(390, 0, 0, 100);
		camera.moveY(240, 0, 0, 100);		
		camera.zoom(0.98f, 100f);

		dialogueWindow.popUp();

		boy = new Human();
		boy.create(assetsManager.spritesheetBoyRunning, 5, 3, 11, -100, 35);

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlRunning, 5, 3, 11, -100, 35);

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
		drawPointer(delta);
		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawParticles(delta);
		drawBar();
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
			girl.setSpeed(4f);
		}
		if (boy.getX() > 4000 && finish == false) {

			dialogueWindow.popUp();
			runButton.setDontRespond(true);
			finish = true;
			assetsManager.stars.start();
		}
		if (girl.getX() > 4000 && finish == false) {
			dialogueWindow.popUp();
			finish = true;
			runButton.setDontRespond(true);
		}
		if (girl.getX() > 4000)
			girl.setSpeed(0);
		if (boy.getX() > 4000)
			boy.setSpeed(0);

		if(finish == true && dialogueWindow.isVisibile() == false && exit == false){
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
		if (boy.getX() >= 400 && boy.getX() < 3600) {
			camera.position.x = (boy.getX());
		}
	}

	void drawBackground() {
		System.out.println(boy.getX());
		if (boy.getX() <= 1200)
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
		if (boy.getX() >= 400)
			batch.draw(assetsManager.parkBackgrounds[1], 790, 0);
		if (boy.getX() >= 1200 && boy.getX() < 2600)
			batch.draw(assetsManager.parkBackgrounds[2], 1590, 0);

		if (boy.getX() >= 1600 && boy.getX() < 3100)
			batch.draw(assetsManager.parkBackgrounds[5], 2050, 0);
		if (boy.getX() >= 2100)
			batch.draw(assetsManager.parkBackgrounds[4], 2515, 0);
		if (boy.getX() >= 2600)
			batch.draw(assetsManager.parkBackgrounds[3], 3315, 0);

	}

	void updateGirlAction(double delta) {
		if (girl.getX() < boy.getX() - 100)
			timerSpeedGirl += delta * 10;
		if (firstDialogueClicked == true)
			timerSpeedGirl += delta;
		if (timerSpeedGirl > 0.4) {
			timerSpeedGirl = 0;
			girl.move();
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);

		assetsManager.leaf.update(delta);
		assetsManager.leaf.draw(batch);

		if (boy.getSpeed() > 7) {
			// assetsManager.leaf.setPosition(boy.getX(),0);
			// assetsManager.leaf.start();
			// Particle effect not finished
		}
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {
		if (boy.getX() >= girl.getX())
			batch.draw(assetsManager.pointer, boy.getX() + 25, 180);
		if (girl.getX() > boy.getX())
			batch.draw(assetsManager.pointer, girl.getX() + 25, 180);
	}

	void drawBar() {
		batch.draw(assetsManager.bar, 260, 410);
		batch.draw(assetsManager.boyHead, 270 + boy.getX() * 0.067f, 410);
		batch.draw(assetsManager.girlHead, 270 + girl.getX() * 0.067f, 410);

		// speedBar.setSpeed(boy.getSpeed());
		// speedBar.render(batch);
		// Works on a placeholder, but having no actual asset

	}
}