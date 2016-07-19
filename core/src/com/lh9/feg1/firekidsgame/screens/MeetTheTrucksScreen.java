package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.files.windows.Dialogue;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class MeetTheTrucksScreen implements Screen {

	Button pause;
	Dialogue dialogueWindow;

	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	boolean zoomedOut = false;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean thirdDialogueClicked = false;
	boolean firstTruckZoomedIn = false;
	boolean secondTruckZoomedIn = false;
	boolean thirdTruckZoomedIn = false;

	final Starter game;

	public MeetTheTrucksScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(350);
		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 100f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		cloudManager.stop();

		camera.position.x = 400;
		camera.position.y = 240;
		camera.zoom = 0.5f;
		camera.zoom(1f, 1.3f);
		camera.moveY(camera.position.y - 65, 10, 10, 10f);
		camera.moveX(400, 100, 0, 100);
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
		batch.draw(assetsManager.levelBackgrounds[0], 0, 0);
		batch.end();

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
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

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {

		if (camera.zoom == 1 && zoomedOut == false) {
			dialogueWindow.popUp();
			zoomedOut = true;
		}
		if(zoomedOut == true && dialogueWindow.isVisibile() == false && firstDialogueClicked == false){
			camera.changePosition(100);
			firstDialogueClicked = true;
		}
		
		if (cloudManager.getAllScalesEqualOne() == true
				&& thirdTruckZoomedIn == true) {
			game.setScreen(new FitnessScreenOne(game));
		}
	}
}