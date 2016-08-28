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
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class AuthorsScreen implements Screen {

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Arrow truckFrontHand;
	Arrow girlHead;
	Arrow boyHead;
	Arrow middleBoyHead;
	Arrow handwheel;
	Sprite glassSprite;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Button menu;
	Button authorsText;
	Button nevena;
	Button tushar;
	Button daniel;
	Button programming;
	Button graphics;
	
	boolean blinked;

	final Starter game;

	public AuthorsScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		menu = new Button(715, -250, assetsManager.menu);
		menu.goUp(360);
		authorsText = new Button(255, -500, assetsManager.authorsText);
		authorsText.goUp(0);

		daniel = new Button(50, -250, assetsManager.daniel);
		daniel.goUp(430);

		nevena = new Button(510, -250, assetsManager.nevena);
		nevena.goUp(430);

		tushar = new Button(280, -250, assetsManager.tushar);
		tushar.goUp(430);
		
		graphics = new Button(485, -250, assetsManager.graphicsText);
		graphics.goUp(100);
		
		programming = new Button(125, -250, assetsManager.programmingText);
		programming.goUp(100);
		
		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		cloudManager.stop();

		camera.reset();
		camera.position.x = 1270;
		camera.position.y = 765;
		camera.zoom = 3.175f;

		camera.moveX(1270, 0, 0, 100);
		camera.moveY(765, 0, 0, 100);
		camera.zoom(3.175f, 100f);

		glassSprite = new Sprite(assetsManager.glass);
		glassSprite.setScale(2f, 1.5f);
		glassSprite.setPosition(630, 565);

		handwheel = new Arrow(1490, 200, assetsManager.handwheelBig, -13f, 2f);
		handwheel.setAlpha(1);
		handwheel.setScale(0.8f);

		boyHead = new Arrow(280, 675, assetsManager.boyHeadBig, -0.7f, 0.7f);
		boyHead.setAlpha(1);
		boyHead.setScale(0.8f);

		girlHead = new Arrow(1490, 420, assetsManager.girlHeadBig, -0.5f, 0.5f);
		girlHead.setAlpha(1);
		girlHead.setScale(0.8f);

		middleBoyHead = new Arrow(880, 655, assetsManager.boyHeadBigBlonde,
				-0.5f, 0.5f);
		middleBoyHead.setAlpha(1);
		middleBoyHead.setScale(0.8f);

		truckFrontHand = new Arrow(205, 520, assetsManager.handTruckFront,
				-0.5f, 0.5f);
		truckFrontHand.setAlpha(1);
		truckFrontHand.setScale(0.8f);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawButtons(delta);
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
		daniel.render(batch, delta);
		nevena.render(batch, delta);
		tushar.render(batch, delta);
		programming.render(batch, delta);
		graphics.render(batch, delta);
		authorsText.render(batch, delta);
	}

	void updateLogics(double delta) {
		if (blinked == false && authorsText.notMoving() == true) {
			blinked = true;
			authorsText.blink();
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawBackground(float delta) {
		batch.draw(assetsManager.truckFront[0], 0, 765);
		batch.draw(assetsManager.truckFront[1], 1275, 765);
		batch.draw(assetsManager.truckFront[2], 0, 0);
		batch.draw(assetsManager.truckFront[3], 1275, 485);
		truckFrontHand.render(batch, delta);
		girlHead.render(batch, delta);
		middleBoyHead.render(batch, delta);
		boyHead.render(batch, delta);
		handwheel.render(batch, delta);
		batch.draw(assetsManager.truckFront[4], 1275, 0);
		batch.draw(assetsManager.boyTorso, 920, 480);
		glassSprite.draw(batch);
	}

	void drawFps() {
		fpsManager.render(batch);
	}
}