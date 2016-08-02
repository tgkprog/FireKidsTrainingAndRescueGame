package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class PreRescueCatScreen implements Screen {

	Sprite roadSprite;
	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;
	Sprite boyHead;
	Sprite girlHead;
	Bar speedBar;
	Truck truck;
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

	float timerSpeedGirl;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public PreRescueCatScreen(final Starter gam) {

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
		runButton = new Button(685, -200, assetsManager.runButtonLittle);
		runButton.goUp(30);
		runButton.setAlpha(0.5f);

		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getFitnessScreenOne());

		truck = new Truck();
		truck.create(assetsManager.truckBlank, 3, 3, 1, 4000, -300);
		truck.setMaxSpeed(10);
		truck.setMaxPositions(-16300, 10000);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();
		truck.setAllowReverse(true);
		truck.setScale(0.5f);
		truck.downLane();

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setControlledHuman(truck);
		inputInterpreter.setControlledTruck(truck);
		inputInterpreter.setMenuWindow(menuWindow);

		camera.zoom = 0.98f;
		camera.position.x = 3600;
		camera.position.y = 240;

		camera.moveX(3600, 0, 0, 100);
		camera.moveY(240, 0, 0, 100);
		camera.zoom(0.98f, 100f);

		dialogueWindow.popUp();
		cloudManager.stop();

		assetsManager.stars.setPosition(400, 480);
		// assetsManager.leaf.setPosition(-100, 200);
		// Not using this effect

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 4);
		speedBar.setVisibility(true);

		boyHead = new Sprite(assetsManager.boyButton);
		girlHead = new Sprite(assetsManager.girlButton);
		boyHead.setScale(0.5f);
		girlHead.setScale(0.5f);
		roadSprite = new Sprite(assetsManager.roadPart);
		roadSprite.setScale(1, 0.5f);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		float deltaTemp = delta;

		if (menuWindow.isVisibile() == true)
			delta = 0;

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
		drawBars(delta);
		drawButtons(deltaTemp);
		drawWindows(deltaTemp);
		drawClouds(deltaTemp);
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

	void drawCharacters(float delta) {
		truck.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {

		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {

		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setScreen(new RescueCatScreen(game));
		}

		updateCameraLogics(delta);

	}

	void updateCameraLogics(double delta) {

		camera.zoom = 0.98f;
		
		if (truck.getX() >= 400 && truck.getX() < 3300) {
			camera.position.x = (truck.getX()) + 300;
		}
	}

	void drawBackground() {
		if (truck.getX() <= 1200) {
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
			roadSprite.setPosition(-10, -45);
			roadSprite.draw(batch);
		}
		if (truck.getX() <= 2000) {
			batch.draw(assetsManager.parkBackgrounds[1], 790, 0);
			roadSprite.setPosition(790, -45);
			roadSprite.draw(batch);
		}
		if (truck.getX() >= 1200 && truck.getX() < 2600) {
			batch.draw(assetsManager.parkBackgrounds[2], 1590, 0);
			roadSprite.setPosition(1590, -45);
			roadSprite.draw(batch);
		}
		if (truck.getX() >= 1600 && truck.getX() < 3100) {
			batch.draw(assetsManager.parkBackgrounds[5], 2050, 0);
			roadSprite.setPosition(2050, -45);
			roadSprite.draw(batch);
		}
		if (truck.getX() >= 2100) {
			batch.draw(assetsManager.parkBackgrounds[4], 2515, 0);
			roadSprite.setPosition(2515, -45);
			roadSprite.draw(batch);
		}
		if (truck.getX() >= 2600) {
			batch.draw(assetsManager.parkBackgrounds[3], 3315, 0);
			roadSprite.setPosition(3315, -45);
			roadSprite.draw(batch);
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
		// assetsManager.leaf.update(delta);
		// assetsManager.leaf.draw(batch);
		// if (truck.getSpeed() > 7) {
		// }
		// Leafes that fall down once the speed is higher than 7
		// Not using this, particle effect is incomplete
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {

	}

	void drawBars(float delta) {

		batch.draw(assetsManager.speedBar, 160, 440);

		boyHead.setPosition(165 + truck.getX() * 0.1f, 410);

		boyHead.draw(batch);

		// speedBar.render(batch, delta, truck.getSpeed());
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFitnessScreenOne()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new FitnessScreenOne(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}
}