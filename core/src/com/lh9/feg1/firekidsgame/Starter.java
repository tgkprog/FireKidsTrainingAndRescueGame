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
	Sprite logoSprite;
	Camera camera;
	OrthographicCamera guiCamera;
	Viewport viewport;
	Viewport guiViewport;
	AssetsManager assetsManager;
	SpriteBatch batch;
	CloudManager cloudManager;

	@Override
	public void create() {

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
		logo = new Texture("station/Pink-Engine-Front.png");
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		logoSprite = new Sprite(logo);
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

	public CloudManager getCloudManager() {
		return cloudManager;
	}
}