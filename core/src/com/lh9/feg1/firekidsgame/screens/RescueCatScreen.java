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

public class RescueCatScreen implements Screen {

	double timerSpeedGirl;

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

	public RescueCatScreen(final Starter gam) {

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


		
		camera.reset();

		camera.position.x = 1357;
		camera.position.y = 1220;
		camera.zoom = 1.2f;
		
		camera.zoom(1.2f, 100);
		camera.moveX(1357, 100, 100, 100);
		camera.moveY(1220, 100, 100, 100);

		dialogueWindow.popUp();

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlRunning, 5, 3, 11, -100, 35);

		inputInterpreter.setControlledHuman(girl);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 8);
		speedBar.setVisibility(true);
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
		drawBar(delta);
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
		girl.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		// runButton.render(batch, (float) delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
		updateCameraLogics(delta);
		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
		
			
			camera.reset();
			camera.zoom(3.1f, 3.5f);
			camera.moveX(1235, 2, 2, 10);
			camera.moveY(755,2, 2, 10);
			firstDialogueClicked = true;
			
		}
	}

	void updateCameraLogics(double delta) {

	}

	void drawBackground() {
		batch.draw(assetsManager.rescueCatBackground[0], 0, 765);
		batch.draw(assetsManager.rescueCatBackground[1], 1275, 765);
		batch.draw(assetsManager.rescueCatBackground[2], 0, 0);
		batch.draw(assetsManager.rescueCatBackground[3], 1275, 0);
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {
	}

	void drawBar(float delta) {
		// batch.draw(assetsManager.speedBar, 160, 440);
		// speedBar.render(batch, delta, boy.getSpeed());
	}
}