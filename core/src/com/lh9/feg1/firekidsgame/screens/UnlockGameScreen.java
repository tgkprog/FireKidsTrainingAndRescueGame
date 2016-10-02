package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class UnlockGameScreen implements Screen {

	// We use these textures very rarely, so we use them here and dispose after

	Texture[] background;

	Texture unlockFull;
	Texture unlock;
	Texture continueAsFree;
	Texture unlockedSuccessfully;

	Texture thumbnailCat;
	Texture thumbnailTrain;
	Texture thumbnailElevator;
	Texture thumbnailRoadRescue;

	Button cat;
	Button train;
	Button elevator;
	Button roadRescue;

	Button textFireEngineGame;
	Button textUnlockFull;
	Button textUnlock;
	Button textContinueAsFree;
	Button textUnlockedSuccessfully;

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	OrthographicCamera buildingsCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	Button buyButton;
	Button menu;

	float timerExit = 1.8f;
	boolean exitCloudsStarted;
	
	final Starter game;

	public UnlockGameScreen(final Starter gam) {

		thumbnailCat = new Texture("thumbnails/thumbnailCat.png");
		thumbnailCat.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		thumbnailTrain = new Texture("thumbnails/thumbnailTrain.png");
		thumbnailTrain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		thumbnailElevator = new Texture("thumbnails/thumbnailElevator.png");
		thumbnailElevator.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		thumbnailRoadRescue = new Texture("thumbnails/thumbnailRoadRescue.png");
		thumbnailRoadRescue.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		menu = new Button(270, -250, assetsManager.menu);
		menu.goUp(5);
		buyButton = new Button(5, -250, assetsManager.playButton);
		buyButton.goUp(5);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setGeneralPurposeButton(buyButton);
		cloudManager.stop();

		buildingsCamera = new OrthographicCamera(800, 480);
		buildingsCamera.zoom = 2;

		camera.reset();

		camera.zoom = 1f;
		camera.position.x = 1275;
		camera.position.y = 533;

		camera.moveX(1275, 0, 0, 100);
		camera.moveY(533, 0, 0, 100);
		camera.zoom(1f, 100f);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		textFireEngineGame = new Button(265, -100, assetsManager.fireEngineGame);
		textFireEngineGame.goUp(440);

		cat = new Button(20, -100, thumbnailCat);
		cat.goUp(235);
		train = new Button(310, -150, thumbnailTrain);
		train.goUp(235);
		elevator = new Button(20, -200, thumbnailElevator);
		elevator.goUp(95);
		roadRescue = new Button(310, -250, thumbnailRoadRescue);
		roadRescue.goUp(95);

		background = new Texture[4];
		for (int a = 0; a < 4; a++) {
			background[a] = new Texture("backgrounds/UnlockGameScreen/"
					+ (a + 1) + ".png");
			background[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		unlockFull = new Texture("texts/unlockFull.png");
		unlockFull.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		unlock = new Texture("texts/unlock.png");
		unlock.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		continueAsFree = new Texture("texts/continueAsFree.png");
		continueAsFree.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		unlockedSuccessfully = new Texture("texts/unlocked_succesfully.png");
		unlockedSuccessfully.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);

		textUnlockFull = new Button(10, -100, unlockFull);
		textUnlockedSuccessfully = new Button(150, -100, unlockedSuccessfully);
		textUnlockFull.goUp(380);
		textUnlock = new Button(80, -250, unlock);
		textUnlock.goUp(20);
		textContinueAsFree = new Button(345, -250, continueAsFree);
		textContinueAsFree.goUp(20);
	}

	@Override
	public void render(float delta) {

		camera.zoom = 2f;
		camera.position.x = 1700;

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();
		buildingsCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);

		batch.end();
		batch.setProjectionMatrix(buildingsCamera.combined);
		batch.begin();

		//

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawTexts(delta);
		drawButtons(delta);
		assetsManager.stars.draw(batch, delta);
		drawClouds(delta);
		drawFps();

		batch.end();

		manageSelectingScreen();

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

	void drawButtons(float delta) {
		menu.render(batch, delta);
		buyButton.render(batch, delta);
	}

	void drawTexts(float delta) {
		cat.render(batch, delta);
		train.render(batch, delta);
		elevator.render(batch, delta);
		roadRescue.render(batch, delta);

		textUnlockFull.render(batch, delta);
		textUnlock.render(batch, delta);
		textContinueAsFree.render(batch, delta);

		textFireEngineGame.render(batch, delta);
		if (buyButton.getCounter() > 0)
			textUnlockedSuccessfully.render(batch, delta);
	}

	void updateLogics(float delta) {

		if(buyButton.getCounter() > 0)
		{
			timerExit -= delta;
		}
		if(timerExit <= 0 && exitCloudsStarted == false){
			cloudManager.start();
			exitCloudsStarted = true;
		}
		
		if (menu.getCounter() > 0 && menu.isBlockedFromInteraction() == false) {
			assetsManager.click.play();
			menu.setDontRespond(true);
		}
		if (buyButton.getCounter() > 0
				&& buyButton.isBlockedFromInteraction() == false) {
			assetsManager.click.play();
			buyButton.setDontRespond(true);
			menu.setDontRespond(true);
			textUnlockedSuccessfully.goUp(250);
			assetsManager.stars.start();
			assetsManager.stars.allowCompletion();
			dataOrganizer.setFullVersionUnlocked(true);
			cat.blink();
			train.blink();
			elevator.blink();
			roadRescue.blink();
		}
	}

	void manageSelectingScreen() {	
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN() || timerExit <= 0) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				for (int a = 0; a < 4; a++)
					background[a].dispose();

				unlockFull.dispose();
				unlock.dispose();
				continueAsFree.dispose();
				unlockedSuccessfully.dispose();
				thumbnailCat.dispose();
				thumbnailTrain.dispose();
				thumbnailElevator.dispose();
				thumbnailRoadRescue.dispose();

				System.out.println(dataOrganizer.isFullVersionUnlocked());
				dataOrganizer.saveData();
				
				game.setScreen(new MenuScreen(game));
			}
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawBackground(float delta) {
		batch.draw(background[0], 743, 533);
		batch.draw(background[1], 1485, 533);
		batch.draw(background[2], 743, 0);
		batch.draw(background[3], 1485, 0);
	}

	void drawFps() {
		fpsManager.render(batch);
	}
}