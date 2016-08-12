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
	Sprite loadingSprite;

	double logoScale;
	double changingScreenTimer;

	boolean changeScreen;

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

		loadingSprite = game.getLoadingSprite();
		loadingSprite.setScale(0);
		loadingSprite.setPosition(265, 47);

	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.045f
				|| Gdx.graphics.getDeltaTime() > 0.045f)
			delta = 0;

		manageLoadingAssets();
		manageChangingScreens(delta);
		manageLogoScale(delta);

		camera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawLogo();
		drawLoading();
		drawClouds(delta);

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
		if (logoScale <= 1 && Gdx.graphics.getRawDeltaTime() < 0.05f
				&& changeScreen == false) {
			logoSprite.setScale((float) logoScale);
			loadingSprite.setScale((float) logoScale*2);
		}
	}

	void manageLoadingAssets() {
		if (logoScale == variables.getMaxLogoScale()) {
			if (assetsManager.isAlive() == false
					&& assetsManager.getAssetsLoaded() == false
					&& logoSprite.getScaleX() == variables.getMaxLogoScale())
			{
				assetsManager.run();
			}
			}
	}

	void manageChangingScreens(double delta) {

		if (assetsManager.getAssetsLoaded() == true
				&& Gdx.graphics.getRawDeltaTime() < 0.04f)
			changingScreenTimer += delta;
		if (assetsManager.getAssetsLoaded() == true
				&& cloudManager.isLoaded() == false && Gdx.graphics.getDeltaTime() < 0.04f) {
			cloudManager.load(assetsManager.clouds);
			cloudManager.start();
		}
		if (changingScreenTimer > variables.getDelayChangingScreens()
				&& Gdx.graphics.getRawDeltaTime() < 0.04f)
			changeScreen = true;
	}

	void manageChangingScreen() {
		if (changeScreen == true && Gdx.graphics.getRawDeltaTime() < 0.04f
				&& logoScale == 0
				&& cloudManager.getAllScalesEqualOne() == true) {
			game.setScreen(new MenuScreen(game));
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawLogo() {
		logoSprite.draw(batch);
	}

	void drawLoading() {
		loadingSprite.draw(batch);
	}
}