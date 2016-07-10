package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.ScreenTransition;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class MenuScreen implements Screen {

	ScreenTransition blackTransition;
	ScreenTransition[] levelTransitions;

	Variables variables;
	AssetsManager assetsManager;
	OrthographicCamera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	Button meetTheTrucks;
	Button[] levelButtons;

	final Starter game;

	public MenuScreen(final Starter gam) {

		this.game = gam;

		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		blackTransition = game.getScreenTransition();
		blackTransition.setAlphaOne();

		meetTheTrucks = new Button(-2, -200, assetsManager.longButton);
		meetTheTrucks.goUp(0);
		meetTheTrucks.select();

		levelButtons = new Button[7];

		for (int a = 0; a < 7; a++) {
			levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
					assetsManager.button);
			levelButtons[a].goUp(0);
		}

		levelTransitions = new ScreenTransition[8];

		for (int a = 0; a < 8; a++) {
			levelTransitions[a] = new ScreenTransition(
					assetsManager.levelBackgrounds[a]);
			levelTransitions[a].brighten();
		}

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMeetTheTrucks(meetTheTrucks);
		inputInterpreter.setLevelButtons(levelButtons);
		inputInterpreter.setLevelTransitions(levelTransitions);
		levelTransitions[0].darken();
	}

	@Override
	public void render(float delta) {
		camera.update();
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		drawLevels(delta);
		drawButtons(delta);
		batch.end();

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		blackTransition.renderBrighten(batch, delta);
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

	void drawLevels(double delta) {
		for (int a = 0; a < 8; a++) {
			levelTransitions[a].render(batch, delta);
		}
	}

	void drawButtons(double delta) {
		meetTheTrucks.render(batch, (float) delta);
		for (int a = 0; a < 7; a++) {
			levelButtons[a].render(batch, (float) delta);
		}

	}
}