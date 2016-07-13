package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class LogoScreen implements Screen {

	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	Sprite logoSprite;
	double logoScale = 0;
	double changingScreenTimer;
	boolean changeScreen = false;
	final Starter game;

	public LogoScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		variables = new Variables();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		logoSprite = game.getLogoSprite();
		logoSprite.setScale(0);
		logoSprite.setPosition(variables.getLogoPosition().x,
				variables.getLogoPosition().y);
	}

	@Override
	public void render(float delta) {

		manageLoadingAssets();
		manageChangingScreens(delta);
		manageLogoScale(delta);

		camera.update();
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		logoSprite.draw(batch);
		cloudManager.render(batch, delta);
		batch.end();

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		batch.end();

		manageChangingScreen();
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

	void manageLogoScale(double delta) {
		if (assetsManager.getAssetsLoaded() == false) {
			if (logoScale < variables.getMaxLogoScale())
				logoScale += delta;
			if (logoScale > variables.getMaxLogoScale())
				logoScale = variables.getMaxLogoScale();
		} else if (changeScreen == true) {
			if (logoScale > 0 && Gdx.graphics.getRawDeltaTime() < 0.05f)
				logoScale -= delta;
			if (logoScale < 0)
				logoScale = 0;
		}
		if(logoScale <= 1)
		logoSprite.setScale((float) logoScale);
	}

	void manageLoadingAssets() {
		if (logoScale == variables.getMaxLogoScale()) {
			if (assetsManager.isAlive() == false
					&& assetsManager.getAssetsLoaded() == false
					&& logoSprite.getScaleX() == variables.getMaxLogoScale())
				assetsManager.run();
		}
	}

	void manageChangingScreens(double delta) {
		if (assetsManager.getAssetsLoaded() == true && Gdx.graphics.getRawDeltaTime() < 0.05f)
			changingScreenTimer += delta;
		if (assetsManager.getAssetsLoaded() == true
				&& cloudManager.isLoaded() == false) {
			cloudManager.load(assetsManager.clouds);
			cloudManager.start();
		}
		if (changingScreenTimer > variables.getDelayChangingScreens())
			changeScreen = true;
	}

	void manageChangingScreen() {
		if (changeScreen == true && Gdx.graphics.getRawDeltaTime() < 0.05f && logoScale == 0 && cloudManager.getAllScalesEqualOne() == true)  {
			game.setScreen(new MenuScreen(game));
		}
	}
}