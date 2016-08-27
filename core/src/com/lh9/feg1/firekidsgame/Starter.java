package com.lh9.feg1.firekidsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.screens.LogoScreen;

public class Starter extends Game {

	Texture logo;
	Texture loading;
	Sprite logoSprite;
	Sprite loadingSprite;
	Camera camera;
	OrthographicCamera guiCamera;
	Viewport viewport;
	Viewport guiViewport;
	AssetsManager assetsManager;
	SpriteBatch batch;
	CloudManager cloudManager;

	int collectedStars;
	boolean[] screensPlayed;

	@Override
	public void create() {
		screensPlayed = new boolean[8];

		cloudManager = new CloudManager();
		assetsManager = new AssetsManager();
		camera = new Camera(800, 480);
		guiCamera = new OrthographicCamera(800, 480);

		camera.position.x = 400;
		camera.position.y = 240;
		guiCamera.position.x = 400;
		guiCamera.position.y = 240;

		batch = new SpriteBatch();
		viewport = new FillViewport(480, 800, camera);
		guiViewport = new FillViewport(480, 800, guiCamera);

		loadBasicTextures();

		this.setScreen(new LogoScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	void loadBasicTextures() {
		logo = new Texture("others/Pink-Engine-Front.png");
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		logoSprite = new Sprite(logo);
		loading = new Texture("texts/loading.png");
		loading.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		loadingSprite = new Sprite(loading);
	}

	public Camera getCamera() {
		return camera;
	}

	public OrthographicCamera getGuiCamera() {
		return guiCamera;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public AssetsManager getAssetsManager() {
		return assetsManager;
	}

	public Sprite getLogoSprite() {
		return logoSprite;
	}

	public Sprite getLoadingSprite() {
		return loadingSprite;
	}

	public CloudManager getCloudManager() {
		return cloudManager;
	}

	public boolean[] getScreensPlayed() {
		return screensPlayed;
	}

	public boolean getScreenPlayed(int id) {
		return screensPlayed[id];
	}

	public void setScreenPlayed(int id) {
		screensPlayed[id] = true;
	}

	public int getCollectedStars() {
		return collectedStars;
	}

	public void setCollectedStars(int collectedStars) {
		this.collectedStars = collectedStars;
	}
}