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

	Button[] hitboxes;
	Button pause;
	Button runButton;
	Button menuButton;
	Button retryButton;
	Button playButton;

	Sprite[] peopleSad;
	Sprite[] peopleHappy;
	Sprite[] playerAnimation;
	Sprite[] metroDoor;
	Sprite ball;
	Sprite guiStar;

	ArrayList<Vector3> ballEffect;
	Vector3 ballPosition;

	Bar timeBar;
	Bar progressBar;

	Truck truck;
	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	MenuWindow menuWindow;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	float spawnBallTimer;
	float minigameTimeLeft = 1.5f;
	float timerSpeedGirl;
	float[] hitboxesAlpha;

	boolean finalAnimationFinished;
	boolean[] selectedHitbox;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean minigameRunning;
	boolean enlargeStar;
	boolean finishDialogue;
	boolean moveLeft;
	boolean readyToPlay;
	boolean afterMinigameWindow;
	boolean zoomOut;
	boolean victory;

	int minigameCounter = 20;
	int starsAll;
	int starsCollected;
	int starsCollectedLastFrame;

	final Starter game;

	public RescueMetroScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		pause = new Button((int) variables.getPAUSE_BUTTON_POSITION().x, 120,
				assetsManager.pause);
		pause.goUp((int) variables.getPAUSE_BUTTON_POSITION().y);
		runButton = new Button((int) variables.getRUN_BUTTON_POSITION().x, 0,
				assetsManager.runButton);
		runButton.goUp((int) variables.getRUN_BUTTON_POSITION().y);

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

		playerAnimation = new Sprite[2];

		if (dataOrganizer.getGender() == true) {
			playerAnimation[0] = new Sprite(assetsManager.girlHammer[0]);
			playerAnimation[1] = new Sprite(assetsManager.girlHammer[1]);
		} else {
			playerAnimation[0] = new Sprite(assetsManager.boyHammer[0]);
			playerAnimation[1] = new Sprite(assetsManager.boyHammer[1]);
		}
		playerAnimation[0].setScale(0.3f);
		playerAnimation[1].setScale(0.3f);

		playerAnimation[0].setPosition(390, -230);
		playerAnimation[1].setPosition(325, -230);

		menuWindow = new MenuWindow(null, assetsManager.darkScreen, 250, 200,
				menuButton, retryButton, playButton,
				variables.getRESCUE_METRO_SCREEN());

		hitboxes = new Button[6];
		selectedHitbox = new boolean[6];
		hitboxesAlpha = new float[6];

		peopleSad = new Sprite[4];
		peopleHappy = new Sprite[4];

		for (int a = 0; a < 4; a++) {
			peopleSad[a] = new Sprite(assetsManager.peopleSad[a]);
			peopleHappy[a] = new Sprite(assetsManager.peopleHappy[a]);
			peopleSad[a].setPosition(1000 + 60 * a, 120);
			peopleSad[a].setScale(0.8f);
			peopleHappy[a].setPosition(1000 + 60 * a, 120);
			peopleHappy[a].setScale(0.8f);

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
		if (dataOrganizer.getGender() == true)
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowGirl,
					assetsManager.darkScreen, 250f, 150f,
					Variables.RESCUE_METRO_POP_UP_1, assetsManager.fontLittle);
		else
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowBoy,
					assetsManager.darkScreen, 250f, 150f,
					Variables.RESCUE_METRO_POP_UP_1, assetsManager.fontLittle);

		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setHitboxes(hitboxes);

		cloudManager.stop();

		camera.zoom = 0.4f;
		camera.position.x = 1070;
		camera.position.y = 435;

		camera.moveX(1070, 0, 0, 100);
		camera.moveY(435, 0, 0, 100);
		camera.zoom(0.4f, 100f);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);

		timeBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				250, 460, minigameTimeLeft);

		progressBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 250, 5, minigameCounter - 1);

		timeBar.setVisibility(false);
		progressBar.setVisibility(false);

		ballEffect = new ArrayList<Vector3>();
		ball = new Sprite(assetsManager.runButton);

		metroDoor = new Sprite[3];
		metroDoor[0] = new Sprite(assetsManager.metroDoor[0]);
		metroDoor[1] = new Sprite(assetsManager.metroDoor[1]);
		metroDoor[2] = new Sprite(assetsManager.metroDoor[2]);

		guiStar = new Sprite(assetsManager.star);
		guiStar.setScale(0.75f);
		guiStar.setPosition(0, 430);

		starsAll = game.getCollectedStars();
	}

	@Override
	public void render(float delta) {

		inputInterpreter.checkKeyboardInput();

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

		drawGuiStarsCounter(delta);
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

		for (int a = 0; a < 4; a++) {
			if (minigameCounter > 0)
				peopleSad[a].draw(batch);
			else
				peopleHappy[a].draw(batch);
		}

		batch.draw(assetsManager.metroDoor[3], 930, 140);

		if (zoomOut == false)
			playerAnimation[0].draw(batch);
		else
			playerAnimation[1].draw(batch);

		if (minigameCounter == 0) {
			metroDoor[1].setScale(0.66f, 0.68f);
			metroDoor[1].setPosition(585, 40);
			metroDoor[1].draw(batch);
		}

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

		if(minigameTimeLeft <= 0 && minigameCounter > 0 && finishDialogue == false){
			finalAnimationFinished = true;
			victory = false;
			minigameRunning = false;
			finishDialogue = true;
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star,
					assetsManager.starSummary,
					assetsManager.starSummaryDesaturated, 0, starsCollected,
					false);
			dialogueWindow.popUp();
		}
		
		
		if (minigameCounter == 0) {
			
			if (camera.zoom < 0.8)
				finalAnimationFinished = true;
			
			camera.zoom(0.6f, 1);
	
			for (int a = 0; a < 4; a++) {

				if (peopleHappy[a].getX() >= 765 - a * 30
						&& peopleHappy[a].getY() >= 60)
					peopleHappy[a].setPosition(peopleHappy[a].getX() - delta
							* 120 - delta * a * 10, peopleHappy[a].getY());

				if (peopleHappy[a].getX() <= 700 - a * 30) {
					if (peopleHappy[a].getY() > 0)
						peopleHappy[a].setPosition(peopleHappy[a].getX(),
								peopleHappy[a].getY() - delta * 100);

				}
			}
		}

		if (minigameCounter == 0 && finishDialogue == false
				&& finalAnimationFinished == true && camera.zoom  < 1.18f) {
			dialogueWindow.popUp();
			assetsManager.stars.start();
			finishDialogue = true;
			dialogueWindow.drawLevelSummary(assetsManager.cog,
					assetsManager.star, assetsManager.starSummary,
					assetsManager.starSummaryDesaturated, 3, starsCollected,
					true);
			timeBar.setVisibility(false);
			progressBar.setVisibility(false);
			victory = true;
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
		if (finish == false) {
			if (zoomOut == true) {
				camera.zoom(1.3f, 10);
			}
			if (camera.zoom >= 1.25f) {
				zoomOut = false;
				camera.zoom(1.1f, 10);
			}
		}
		if (camera.zoom > 1.0f && readyToPlay == true && finish == false) {
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
		batch.draw(assetsManager.rescueMetro[1], 840, 0);

		if (minigameCounter == 19 || minigameCounter == 20) {
			batch.draw(assetsManager.metroDoor[4], 655, 135);
		}
		if (minigameCounter < 19 && minigameCounter > 0) {
			metroDoor[0].setScale(0.66f, 0.68f);
			metroDoor[0].setPosition(585, 40);
			metroDoor[0].draw(batch);
		}
		if (minigameCounter == 0) {

			metroDoor[2].setPosition(682, 42);
			metroDoor[2].setScale(0.66f, 0.68f);
			metroDoor[2].draw(batch);
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
			if(victory == true){
			game.setCollectedStars(starsCollected + starsAll);
			game.setScreenPlayed(5);
			game.setCogs(game.getCogs() + 1);
			game.setScreen(new MenuScreen(game));
			}
			else
				game.setScreen(new RescueMetroScreen(game));
					
			}

		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getRESCUE_METRO_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
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

		if (minigameRunning == false && minigameCounter > 0 && minigameTimeLeft >0) {

			timeBar.setVisibility(true);
			progressBar.setVisibility(true);

			minigameTimeLeft = 1.5f;
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
				if (afterMinigameWindow == false) {
					afterMinigameWindow = true;
					timeBar.setVisibility(false);
					progressBar.setVisibility(false);
				}
			}

		}
		else
		{
			timeBar.setVisibility(true);
			progressBar.setVisibility(true);}
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
				if(finish == false)
				hitboxes[a].setDontRespond(false);
				if (hitboxesAlpha[a] > 1)
					hitboxesAlpha[a] = 1;

				if (hitboxes[a].getSelection() == true) {
					hitboxes[a].setDontRespond(true);
					selectedHitbox[a] = false;
					zoomOut = true;
					assetsManager.hit.setPosition(hitboxes[a].getX() + 75,
							hitboxes[a].getY() + 75);
					assetsManager.hit.start();
					starsCollected++;
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

	void drawGuiStarsCounter(float delta) {

		batch.draw(assetsManager.frameCollectibles, 10, 435);

		if (enlargeStar == true) {
			if (guiStar.getScaleX() < 0.9f)
				guiStar.setScale(guiStar.getScaleX() + 3 * delta);
		} else if (guiStar.getScaleX() > 0.75f)
			guiStar.setScale(guiStar.getScaleX() - delta * 3);

		if (guiStar.getScaleX() < 0.75f)
			guiStar.setScale(0.75f);
		if (guiStar.getScaleX() > 0.9f) {
			guiStar.setScale(0.9f);
			enlargeStar = false;
		}
		guiStar.draw(batch);
		assetsManager.fontLittle.draw(batch,
				Integer.toString(starsCollected + starsAll), 60, 463);
	}

}
