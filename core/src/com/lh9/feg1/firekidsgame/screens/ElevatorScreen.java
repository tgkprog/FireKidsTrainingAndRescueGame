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
import com.lh9.feg1.firekidsgame.animated.Human;
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

public class ElevatorScreen implements Screen {

	ArrayList<Vector3> ballEffect;
	Vector3 ballPosition;
	Sprite dogsGirl;
	Sprite[] damage;
	Button[] hitboxes;
	Bar timeBar;
	Bar progressBar;
	Sprite elevatorDoor[];
	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	Bar speedBar;
	Sprite rescueMetroSadPeople;
	Human girl;
	Human dogHappy;
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
	Sprite ball;
	float spawnBallTimer;
	float dogsGirlY = -50;
	float leftDoorPosition;
	float rightDoorPosition;
	boolean zoomDogGirl;
	boolean zoomOut;
	boolean[] selectedHitbox;
	float[] hitboxesAlpha;
	int minigameCounter = 20;
	float minigameTimeLeft = 1;
	float doorAlpha;
	float timerSpeedGirl;
	float dogSadTimer;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean minigameRunning;
	boolean afterMinigameWindow;
	boolean finishDialogue;
	boolean startedClouds;
	final Starter game;

	public ElevatorScreen(final Starter gam) {

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

		girl = new Human();
		girl.create(assetsManager.girlHammer, 1, 2, 2, 400, 405);
		girl.setAnimationOnly(true);
		girl.setOnceOnly();
		girl.setSpeed(1);

		dogHappy = new Human();
		dogHappy.create(assetsManager.dogHappy, 2, 1, 2, 400, 500);

		menuButton = new Button(400, 110, assetsManager.menu);
		playButton = new Button(450, 110, assetsManager.playButton);
		retryButton = new Button(500, 110, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

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

			hitboxes[a] = new Button(800, 0, assetsManager.runButton);
			hitboxes[a].goUp(1150 - a * 150);
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
		inputInterpreter.setControlledHuman(girl);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setHitboxes(hitboxes);

		cloudManager.stop();

		rescueMetroSadPeople = new Sprite(assetsManager.rescueMetroSadPeople);
		rescueMetroSadPeople.setPosition(580, 155);
		rescueMetroSadPeople.setScale(0.35f);

		camera.reset();

		camera.zoom = 1.3f;
		camera.position.x = 1275;
		camera.position.y = 635;

		camera.zoom(1.3f, 100);
		camera.moveX(1275, 100, 100, 100);
		camera.moveY(635, 100, 100, 100);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 8);
		speedBar.setVisibility(true);

		elevatorDoor = new Sprite[2];

		elevatorDoor[0] = new Sprite(assetsManager.elevatorDoor[0]);
		elevatorDoor[0].setAlpha(0);
		elevatorDoor[0].setPosition(780, 317);

		elevatorDoor[1] = new Sprite(assetsManager.elevatorDoor[1]);
		elevatorDoor[1].setAlpha(0);
		elevatorDoor[1].setPosition(1251, 317);

		leftDoorPosition = 780;
		rightDoorPosition = 1251;

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		timeBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				250, 460, minigameTimeLeft);

		progressBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 250, 5, minigameCounter - 1);

		timeBar.setVisibility(false);
		progressBar.setVisibility(false);

		dogsGirl = new Sprite(assetsManager.dogsGirl);
		dogsGirl.setScale(0.7f,0.9f);
		
		ballEffect = new ArrayList<Vector3>();
		ball = new Sprite(assetsManager.runButton);
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

		dogSadTimer += delta;

		if (minigameCounter > 0) {
			if (dogSadTimer <= 3.3f)
				batch.draw(assetsManager.dogWorried[0], 1030, 330);
			if (dogSadTimer > 3.3f)
				batch.draw(assetsManager.dogWorried[1], 1030, 330);
		} else {
			if (dogSadTimer <= 3.3f)
				batch.draw(assetsManager.dogHappy[0], 1030, 330);
			if (dogSadTimer > 3.3f)
				batch.draw(assetsManager.dogHappy[1], 1030, 330);
		}

		if (dogSadTimer > 3.4f)
			dogSadTimer = 0;

		drawElevatorDoor(delta);

		girl.render(batch, delta);

		dogsGirl.draw(batch);
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

		if (camera.zoom <= 1.55 && finishDialogue == false
				&& minigameCounter == 0) {
			dialogueWindow.popUp();
			finishDialogue = true;
		}
		if (finishDialogue == true && dialogueWindow.isVisibile() == false
				&& startedClouds == false) {
			startedClouds = true;
			cloudManager.start();
		}

		if (zoomDogGirl == false)
			dogsGirl.setPosition((int) (1800 - 130 * doorAlpha), -50);
		else
			dogsGirl.setPosition((int) (1800 - 130 * doorAlpha)
					- (dogsGirlY - camera.zoom) * 100,
					(dogsGirlY - camera.zoom) * 200 - 50);

		girl.setPosition((int) (220 + 130 * doorAlpha), 150);

		if (minigameCounter == 0) {
			if (leftDoorPosition > 320)
				leftDoorPosition -= delta * 390 - (780 - leftDoorPosition)
						* 0.005f;
			if (leftDoorPosition < 320)
				leftDoorPosition = 320;

			if (rightDoorPosition < 1735)
				rightDoorPosition += delta * 390 - (780 - leftDoorPosition)
						* 0.005f;
			if (rightDoorPosition > 1735)
				rightDoorPosition = 1735;
		}

		if (rightDoorPosition == 1735 && leftDoorPosition == 320
				&& zoomDogGirl == false) {
			dogsGirlY = camera.zoom;
			camera.zoom(1.5f, 3);
			camera.moveX(1605, 2, 2, 4);
			camera.moveY(660, 2, 2, 4);
			zoomDogGirl = true;
		}

		if (zoomOut == true) {
			camera.zoom(3.15f, 50);
		}
		if (camera.zoom >= 3.05f) {
			zoomOut = false;
			camera.zoom(2.6f, 50);
		}

		if (camera.zoom >= 2.5f) {
			manageHitboxes(delta);
			randomizeMinigame();
		}

		updateCameraLogics(delta);
		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			camera.reset();
			// 3.15 is max zoom here in elevator screen
			camera.zoom(2.6f, 7f);
			camera.moveX(1275, 1, 1, 2.5f);
			camera.moveY(775, 1, 1, 2.5f);
			firstDialogueClicked = true;
		}
	}

	void updateCameraLogics(double delta) {
	}

	void drawBackground() {
		batch.draw(assetsManager.elevatorBackground[0], 0, 765);
		batch.draw(assetsManager.elevatorBackground[1], 1275, 765);
		batch.draw(assetsManager.elevatorBackground[2], 0, 0);
		batch.draw(assetsManager.elevatorBackground[3], 1275, 0);
	}

	void drawParticlesNonGui(float delta) {
		assetsManager.hit.draw(batch, delta);
	}

	void drawHitBoxes(float delta) {
		for (int a = 0; a < 6; a++) {
			hitboxes[a].render(batch, delta);
		}
		for (int a = 0; a < 20; a++) {
			// damage[a].draw(batch);
		}

	}

	void drawBars(float delta) {
		timeBar.render(batch, delta, minigameTimeLeft);
		progressBar.render(batch, delta, minigameCounter);
	}

	void manageSelectingScreen() {

		if (finishDialogue == true && dialogueWindow.isVisibile() == false) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
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
				game.setScreen(new ElevatorScreen(game));
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
		if (firstDialogueClicked == true && doorAlpha < 1) {
			doorAlpha += delta * 0.55f;
			if (doorAlpha > 1)
				doorAlpha = 1;
			elevatorDoor[0].setAlpha(doorAlpha);
			elevatorDoor[1].setAlpha(doorAlpha);
		}
		
		elevatorDoor[0].setPosition(leftDoorPosition, elevatorDoor[0].getY());
		elevatorDoor[1].setPosition(rightDoorPosition, elevatorDoor[1].getY());

		elevatorDoor[0].draw(batch);
		elevatorDoor[1].draw(batch);
	}

	void randomizeMinigame() {
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
					hitboxes[a].setPosition(900, hitboxes[a].getY());
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
					spawnBall(hitboxes[a].getX() - 90, hitboxes[a].getY());
				}
				if (hitboxes[a].getX() < 1580 && hitboxes[a].getY() > 350)
					hitboxes[a].setPosition(hitboxes[a].getX() + delta * 550,
							hitboxes[a].getY() - delta * 550);
				hitboxesAlpha[a] += delta * 10;
				hitboxes[a].setDontRespond(false);
				if (hitboxesAlpha[a] > 1)
					hitboxesAlpha[a] = 1;

				if (hitboxes[a].getSelection() == true) {
					damage[minigameCounter].setAlpha(1);
					hitboxes[a].setDontRespond(true);
					selectedHitbox[a] = false;
					girl.resetStateTime();
					girl.setSpeed(2);
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
}