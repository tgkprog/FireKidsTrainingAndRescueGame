package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
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

public class RescueMetroScreen implements Screen {

	Sprite ball;
	ArrayList<Vector3> ballEffect;
	Vector3 ballPosition;
	Truck truck;
	Sprite[] damage;
	Button[] hitboxes;
	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	Bar speedBar;
	Sprite rescueMetroSadPeople;
	Sprite[] girlAnimation;
	Button pause;
	Button runButton;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Bar timeBar;
	Bar progressBar;
	Sprite[] metroDoor;

	boolean finishDialogue;
	boolean moveLeft;
	boolean readyToPlay;
	boolean afterMinigameWindow;
	boolean zoomOut;
	float spawnBallTimer;
	float timerSpeedGirl;
	boolean[] selectedHitbox;
	float[] hitboxesAlpha;
	int minigameCounter = 20;
	float minigameTimeLeft = 1;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean minigameRunning;

	final Starter game;

	public RescueMetroScreen(final Starter gam) {

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

		menuButton = new Button(400, 110, assetsManager.menu);
		playButton = new Button(450, 110, assetsManager.playButton);
		retryButton = new Button(500, 110, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		truck = new Truck();
		truck.create(assetsManager.truckBlank, 3, 3, 1, 1265, 80);
		truck.setMaxSpeed(10);
		truck.setMaxPositions(-16300, 10000);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();
		truck.setAllowReverse(true);
		truck.setScale(1.4f);
		truck.downLane();
		truck.setFriction(0.5f);

		girlAnimation = new Sprite[2];

		girlAnimation[0] = new Sprite(assetsManager.girlHammer[0]);
		girlAnimation[1] = new Sprite(assetsManager.girlHammer[1]);

		girlAnimation[0].setScale(0.3f);
		girlAnimation[1].setScale(0.3f);

		girlAnimation[0].setPosition(360, -230);
		girlAnimation[1].setPosition(295, -230);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getRescueMetroScreen());

		hitboxes = new Button[6];
		selectedHitbox = new boolean[6];
		hitboxesAlpha = new float[6];

		damage = new Sprite[20];

		for (int a = 0; a < 20; a++) {
			damage[a] = new Sprite(assetsManager.clouds[1]);
			damage[a].setPosition(MathUtils.random(1000, 1400),
					MathUtils.random(350, 1000));
			damage[a].setRotation(MathUtils.random(0, 360));
			damage[a].setColor(0, 0, 0, 0);
			damage[a].setScale(MathUtils.random(0.1f, 0.3f));
		}
		for (int a = 0; a < 6; a++) {
			if (a % 2 == 0)
				hitboxes[a] = new Button(630, -100,
						assetsManager.runButtonLittle);
			else
				hitboxes[a] = new Button(768, -100,
						assetsManager.runButtonLittle);
			hitboxes[a].goUp(400 - a * 50);
			hitboxes[a].setAlpha(0);
			hitboxes[a].setDontRespond(true);
		}

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setHitboxes(hitboxes);

		cloudManager.stop();

		rescueMetroSadPeople = new Sprite(assetsManager.rescueMetroSadPeople);
		rescueMetroSadPeople.setPosition(580, 155);
		rescueMetroSadPeople.setScale(0.35f);

		camera.zoom = 0.4f;
		camera.position.x = 1070;
		camera.position.y = 435;

		camera.moveX(1070, 0, 0, 100);
		camera.moveY(435, 0, 0, 100);
		camera.zoom(0.4f, 100f);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 8);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		timeBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				250, 460, minigameTimeLeft);

		progressBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 250, 5, minigameCounter - 1);

		timeBar.setVisibility(false);
		progressBar.setVisibility(false);

		ballEffect = new ArrayList<Vector3>();
		ball = new Sprite(assetsManager.runButton);

		metroDoor = new Sprite[2];
		metroDoor[0] = new Sprite(assetsManager.metroDoor[0]);
		metroDoor[1] = new Sprite(assetsManager.metroDoor[1]);

	}

	@Override
	public void render(float delta) {

		// camera.zoom = 2.3f;

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

		// camera.zoom = 3;
		// Some space left, implement truck riding to the stage

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground();
		drawCharacters(delta);
		drawBallEffect(delta);
		drawHitBoxes(delta);
		drawParticlesNonGui(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

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

		if (zoomOut == false)
			girlAnimation[0].draw(batch);
		else
			girlAnimation[1].draw(batch);

		truck.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		// runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		if (minigameCounter == 0 && finishDialogue == false) {
			dialogueWindow.popUp();
			finishDialogue = true;
		}
		if (finishDialogue == true && dialogueWindow.isVisibile() == false
				&& finish == false) {
			finish = true;
			cloudManager.start();
		}

		if (camera.position.x <= 830) {
			readyToPlay = true;
		}

		if (moveLeft == false && camera.zoom > 1.05f) {
			moveLeft = true;
			camera.reset();
			camera.moveX(800, 2, 2, 4);
		}

		if (zoomOut == true) {
			camera.zoom(1.3f, 10);
		}
		if (camera.zoom >= 1.25f) {
			zoomOut = false;
			camera.zoom(1.1f, 10);
		}

		if (camera.zoom > 1.0f && readyToPlay == true) {
			randomizeMinigame();
			manageHitboxes(delta);
		}

		updateCameraLogics(delta);
		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			camera.reset();
			// camera.zoom(1.1f, 3);
			camera.zoom(1.1f, 1.5f);

			// camera.moveX(800, 2, 2, 4);
			camera.moveY(300, 2, 2, 4);
			truck.setSpeed(1.5f);
			firstDialogueClicked = true;
		}
	}

	void updateCameraLogics(double delta) {
	}

	void drawBackground() {
		batch.draw(assetsManager.rescueMetro[0], 0, 0);
		batch.draw(assetsManager.rescueMetro[1], 797, 0);
		rescueMetroSadPeople.draw(batch);

		if (minigameCounter < 19 && minigameCounter > 0) {
			metroDoor[0].setScale(0.625f, 0.66f);
			metroDoor[0].setPosition(540, 40);
			metroDoor[0].draw(batch);
		}
		if (minigameCounter == 0) {
			metroDoor[1].setScale(0.625f, 0.66f);
			metroDoor[1].setPosition(540, 40);
			metroDoor[1].draw(batch);

		}

	}

	void drawParticlesNonGui(float delta) {
		assetsManager.hit.draw(batch, delta);
	}

	void drawPointer(float delta) {
	}

	void drawBars(float delta) {
		timeBar.render(batch, delta, minigameTimeLeft);
		progressBar.render(batch, delta, minigameCounter);
	}

	void manageSelectingScreen() {
		if (finish == true && cloudManager.getAllScalesEqualOne() == true) {
			game.setScreen(new MenuScreen(game));
		}

		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getRescueMetroScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new RescueMetroScreen(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawElevatorDoor(float delta) {

	}

	void randomizeMinigame() {

		System.out.println(minigameCounter);

		if (minigameRunning == false && minigameCounter > 0) {

			timeBar.setVisibility(true);
			progressBar.setVisibility(true);

			minigameTimeLeft = 1;
			minigameCounter--;
			minigameRunning = true;

			if (minigameCounter != 0) {
				for (int a = 0; a < 6; a++)
					selectedHitbox[a] = false;

				selectedHitbox[MathUtils.random(0, 5)] = true;
			}
			for (int a = 0; a < 6; a++) {
				if (selectedHitbox[a] == true) {
					hitboxesAlpha[a] = 0.05f;
					// hitboxes[a].setPosition(700, hitboxes[a].getY());
				}
			}

			if (minigameCounter == 0) {
				if (minigameCounter == 0 && afterMinigameWindow == false) {
					afterMinigameWindow = true;
					timeBar.setVisibility(false);
					progressBar.setVisibility(false);
				}
			}

		}
	}

	void manageHitboxes(float delta) {
		if (minigameTimeLeft > 0)
			minigameTimeLeft -= delta;
		if (minigameTimeLeft < 0)
			minigameTimeLeft = 0;
		boolean allHitboxesAlphaZero = true;
		for (int a = 0; a < 6; a++) {
			if (hitboxesAlpha[a] > 0)
				allHitboxesAlphaZero = false;
			if (selectedHitbox[a] == true) {
				spawnBallTimer += delta;
				if (spawnBallTimer > 0.25f) {
					spawnBallTimer = 0;
					// spawnBall(hitboxes[a].getX() - 90, hitboxes[a].getY());
				}
				// if (hitboxes[a].getX() < 1000 && hitboxes[a].getY() > 350)
				// hitboxes[a].setPosition(hitboxes[a].getX() + delta * 550,
				// hitboxes[a].getY() - delta * 550);
				hitboxesAlpha[a] += delta * 10;
				hitboxes[a].setDontRespond(false);
				if (hitboxesAlpha[a] > 1)
					hitboxesAlpha[a] = 1;

				if (hitboxes[a].getSelection() == true) {
					damage[minigameCounter].setAlpha(1);
					hitboxes[a].setDontRespond(true);
					selectedHitbox[a] = false;
					zoomOut = true;
					assetsManager.hit.setPosition(hitboxes[a].getX() + 75,
							hitboxes[a].getY() + 75);
					assetsManager.hit.start();
				}
			} else {
				hitboxes[a].setDontRespond(true);
				hitboxesAlpha[a] -= delta * 10;
				if (hitboxesAlpha[a] < 0)
					hitboxesAlpha[a] = 0;
			}
			hitboxes[a].setAlpha(hitboxesAlpha[a]);
		}
		if (allHitboxesAlphaZero == true) {
			minigameRunning = false;
		}
	}

	void drawBallEffect(float delta) {
		for (int a = 0; a < ballEffect.size(); a++) {

			ball.setPosition(ballEffect.get(a).x, ballEffect.get(a).y);
			ball.setScale(ballEffect.get(a).z);
			ball.draw(batch);

			ballEffect.get(a).y += ballEffect.get(a).z * 5;

			if (ballEffect.get(a).z > 0)
				ballEffect.get(a).z -= delta * 0.5f;
			if (ballEffect.get(a).z < 0)
				ballEffect.remove(a);
		}
	}

	void spawnBall(float x, float y) {
		ballPosition = new Vector3(x, y, 0.5f);
		ballEffect.add(ballPosition);
	}

	void drawHitBoxes(float delta) {
		for (int a = 0; a < 6; a++) {
			hitboxes[a].render(batch, delta);
		}
		for (int a = 0; a < 20; a++) {
			// damage[a].draw(batch);
		}

	}

}
