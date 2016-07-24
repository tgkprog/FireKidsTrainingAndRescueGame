package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Car;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
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

public class BigRoadRescueScreen implements Screen {

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Sprite boyHead;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;
	Sprite truckLed;
	StaticAnimation fountains[];
	Human girlHose;
	Human girlHoseReversed;
	Sprite fireMiniature;
	Bar speedBar;
	Bar fireBar;
	Vector2[][] firePositions;
	Vector2[] fireRange;
	Truck truck;
	ArrayList<StaticAnimation> fire;
	ArrayList<Car> cars;
	Button up;
	Button down;
	Button pause;
	Button runLeft;
	Button runRight;
	Button eclipseFire;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	float fireScale;
	float delayFire;
	float minigameTimeLeft = 3;
	float girlTimer;
	float waterTimer;

	int minigameCounter = 10;
	int randomFire;
	int fireCounter;

	boolean ledRed;
	boolean minigameRunning;
	boolean afterMinigameWindow;
	boolean lastWindowPopUp;
	double timerSpeedGirl;
	double timerSpawnCar;
	double timerLastPopUp;
	boolean peopleRescued;
	boolean cameraFirstZoom;
	boolean cameraSecondZoom;
	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public BigRoadRescueScreen(final Starter gam) {

		setFirePositions();

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(400);
		up = new Button(10, -100, assetsManager.arrowUp);
		up.goUp(360);
		down = new Button(10, -100, assetsManager.arrowDown);
		down.goUp(250);
		runRight = new Button(685, -200, assetsManager.runButtonLittle);
		runRight.goUp(30);
		runLeft = new Button(35, -200, assetsManager.runButtonLittle);
		runLeft.goUp(30);
		eclipseFire = new Button(350, -200, assetsManager.runButton);
		eclipseFire.goUp(650);

		runLeft.setAlpha(0.5f);
		runRight.setAlpha(0.5f);
		eclipseFire.setAlpha(0f);

		runLeft.setDontRespond(true);
		runRight.setDontRespond(true);
		pause.setDontRespond(true);
		up.setDontRespond(true);
		down.setDontRespond(true);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getBigRoadRescueScreen());

		truck = new Truck();
		truck.create(assetsManager.truckBlank, 3, 3, 1, 1550, 135);
		truck.setMaxSpeed(20);
		truck.setMaxPositions(-16300, 1550);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();
		truck.setAllowReverse(true);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runLeft);
		inputInterpreter.setRunButtonSecond(runRight);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setControlledHuman(truck);
		inputInterpreter.loadDown(down);
		inputInterpreter.loadUp(up);
		inputInterpreter.setControlledTruck(truck);
		inputInterpreter.setEclipseFire(eclipseFire);

		camera.zoom = 1.9f;
		camera.position.x = 800;
		camera.position.y = 470;

		camera.moveX(800, 0, 0, 100);
		camera.moveY(470, 0, 0, 100);
		camera.zoom(1.9f, 100f);

		loadFountains();
		spawnCars();

		girlHose = new Human();
		girlHose.create(assetsManager.hoseAnimation, 1, 1, 15, 10, 10);
		girlHose.setAnimationOnly(true);
		girlHose.setMaxSpeed(2);
		girlHose.setOnceOnly();

		girlHoseReversed = new Human();
		girlHoseReversed.create(assetsManager.hoseAnimationReversed, 1, 1, 15,
				10, 10);
		girlHoseReversed.setAnimationOnly(true);
		girlHoseReversed.setMaxSpeed(2);
		girlHoseReversed.setOnceOnly();

		loadFireAnimations();

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 20);
		fireBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 20);
		fireBar.setVisibility(true);

		randomizeMinigame();

		assetsManager.water.getEmitters().get(0).setContinuous(false);
		assetsManager.water.start();

		truckLed = new Sprite(assetsManager.truckLed);
		boyHead = new Sprite(assetsManager.boyButton);
		boyHead.setScale(0.5f);
		fireMiniature = new Sprite(assetsManager.fireMiniature);
		fireMiniature.setScale(0);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		dialogueWindow.popUp();
		cloudManager.stop();
	}

	@Override
	public void render(float delta) {

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

		drawBackground(delta);
		drawCharacters(delta);
		drawParticles(delta);
		drawFireUtilities(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawButtons(deltaTemp);
		drawBars(delta);
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

		if (truck.getSpeed() >= 0) {
			girlHose.setPosition((int) truck.getX() + 300,
					(int) truck.getY() + 250);
			girlHoseReversed.setPosition((int) truck.getX() + 300,
					(int) truck.getY() + 250);
			assetsManager.water.setPosition((int) truck.getX() + 385,
					(int) truck.getY() + 390);
		} else {
			girlHose.setPosition((int) truck.getX() + 470,
					(int) truck.getY() + 250);
			girlHoseReversed.setPosition((int) truck.getX() + 470,
					(int) truck.getY() + 250);
			assetsManager.water.setPosition((int) truck.getX() + 385,
					(int) truck.getY() + 390);
		}

		if (waterTimer == 1) {
			assetsManager.water.getEmitters().get(0).setContinuous(true);
		}
		if (waterTimer < 1 || minigameRunning == false || fireScale < 1) {
			assetsManager.water.getEmitters().get(0).setContinuous(false);
		}

		if (truck.getX() > fireRange[randomFire].x - 120
				&& truck.getX() < fireRange[randomFire].x - 110) {

			if (waterTimer < 1)
				waterTimer += delta;
			if (waterTimer > 1)
				waterTimer = 1;

			if (fireScale == 1) {
				girlHose.render(batch, delta);
				girlHoseReversed.resetStateTime();
			} else if (fireScale >= 0) {
				girlHoseReversed.render(batch, delta);
				girlHose.resetStateTime();
				girlHoseReversed.setSpeed(2);
			}
		} else {
			if (waterTimer > 0)
				waterTimer -= delta;
			if (waterTimer < 0)
				waterTimer = 0;

			girlHose.render(batch, delta);
			girlHose.resetStateTime();
		}

		truck.render(batch, delta);
		if (truck.getSpeed() >= 0) {
			truckLed.setPosition(truck.getX() + 30 + truck.getSpeed(),
					truck.getY() + 45);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 70 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 115 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 165 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 835 + truck.getSpeed(),
					truck.getY() + 45);
			truckLed.draw(batch);
		} else {
			truckLed.setPosition(truck.getX() + 810 + truck.getSpeed(),
					truck.getY() + 45);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 675 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 720 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 770 + truck.getSpeed(),
					truck.getY() + 183);
			truckLed.draw(batch);
			truckLed.setPosition(truck.getX() + 5 + truck.getSpeed(),
					truck.getY() + 45);
			truckLed.draw(batch);
		}
	}

	void checkCarsCollisions() {

	}

	void drawButtons(float delta) {
		pause.render(batch, delta);
		runRight.render(batch, delta);
		runLeft.render(batch, delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		checkCarsCollisions();

		if (ledRed == true) {
			if (truckLed.getColor().r < 1) {
				truckLed.setColor(truckLed.getColor().r + delta, 1, 1, 1);
				if (truckLed.getColor().r > 1) {
					truckLed.setColor(1, 1, 1, 1);
					ledRed = false;
				}
			}
		} else {
			truckLed.setColor(truckLed.getColor().r - delta, 1, 1, 1);
			if (truckLed.getColor().r < 0) {
				truckLed.setColor(0, 1, 1, 1);
				ledRed = false;
			}
		}

		if (Math.abs(truck.getSpeed()) > 0)
			speedBar.setVisibility(true);
		else
			speedBar.setVisibility(false);

		randomizeMinigame();
		manageFire(delta);

		if (cloudManager.getAllScalesEqualOne() == true
				&& lastWindowPopUp == true)
			game.setScreen(new MenuScreen(game));

		if (timerLastPopUp > 8 && lastWindowPopUp == false) {
			dialogueWindow.popUp();
			lastWindowPopUp = true;
		}

		if (lastWindowPopUp == true && dialogueWindow.isVisibile() == false
				&& cloudManager.getAllScalesEqualZero() == true) {
			cloudManager.start();
		}
		if (timerSpawnCar > 5f) {
			timerSpawnCar = 0;
			// spawnRandomCar(10);
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
			runLeft.setDontRespond(false);
			runRight.setDontRespond(false);
			pause.setDontRespond(false);
			// up.setDontRespond(false);
			// down.setDontRespond(false);
		}
		updateCameraLogics(delta);
	}

	void updateCameraLogics(double delta) {
		if (truck.getX() <= 500 && cameraFirstZoom == false
				&& truck.getX() > -15000)
			camera.position.x = truck.getX() + 300;
	}

	void drawBackground(float delta) {

		if (truck.getX() > -800) {
			batch.draw(assetsManager.bigRoad[0], 800, 0);
			fountains[0].render(batch, delta);
		}
		if (truck.getX() > -1600)
			batch.draw(assetsManager.bigRoad[1], 0, 0);
		if (truck.getX() > -2400 && truck.getX() < 700)
			batch.draw(assetsManager.bigRoad[2], -800, 0);
		if (truck.getX() > -3200 && truck.getX() < -100)
			batch.draw(assetsManager.bigRoad[3], -1600, 0);
		if (truck.getX() > -4000 && truck.getX() < -900) {
			batch.draw(assetsManager.bigRoad[4], -2400, 0);
			fountains[1].render(batch, delta);
		}
		if (truck.getX() > -4800 && truck.getX() < -1700)
			batch.draw(assetsManager.bigRoad[5], -3200, 0);
		if (truck.getX() > -5600 && truck.getX() < -2500)
			batch.draw(assetsManager.bigRoad[6], -4000, 0);
		if (truck.getX() > -6400 && truck.getX() < -3300) {
			batch.draw(assetsManager.bigRoad[7], -4800, 0);
			fountains[2].render(batch, delta);
		}
		if (truck.getX() > -7200 && truck.getX() < -4100)
			batch.draw(assetsManager.bigRoad[8], -5600, 0);
		if (truck.getX() > -8000 && truck.getX() < -4900)
			batch.draw(assetsManager.bigRoad[9], -6400, 0);
		if (truck.getX() > -8800 && truck.getX() < -5700) {
			batch.draw(assetsManager.bigRoad[10], -7200, 0);
			fountains[3].render(batch, delta);
		}
		if (truck.getX() > -9600 && truck.getX() < -6500)
			batch.draw(assetsManager.bigRoad[11], -8000, 0);
		if (truck.getX() > -10400 && truck.getX() < -7300)
			batch.draw(assetsManager.bigRoad[12], -8800, 0);
		if (truck.getX() > -11200 && truck.getX() < -8100) {
			batch.draw(assetsManager.bigRoad[13], -9600, 0);
			fountains[4].render(batch, delta);
		}
		if (truck.getX() > -12000 && truck.getX() < -8900)
			batch.draw(assetsManager.bigRoad[14], -10400, 0);
		if (truck.getX() > -12800 && truck.getX() < -9700)
			batch.draw(assetsManager.bigRoad[15], -11200, 0);
		if (truck.getX() > -13600 && truck.getX() < -10500)
			batch.draw(assetsManager.bigRoad[16], -12000, 0);
		if (truck.getX() > -14400 && truck.getX() < -11300) {
			batch.draw(assetsManager.bigRoad[17], -12800, 4);
			fountains[5].render(batch, delta);
		}
		if (truck.getX() > -15200 && truck.getX() < -12100)
			batch.draw(assetsManager.bigRoad[18], -13600, 0);
		if (truck.getX() > -19000 && truck.getX() < -12100)
			batch.draw(assetsManager.bigRoad[19], -14400, 0);
		if (truck.getX() > -19000 && truck.getX() < -13700) {
			batch.draw(assetsManager.bigRoad[20], -15200, 0);
			fountains[6].render(batch, delta);
		}
		if (truck.getX() > -18500 && truck.getX() < -14500)
			batch.draw(assetsManager.bigRoad[21], -16000, 0);
		for (int a = 0; a < 6; a++) {
			assetsManager.fireSmoke[a].setPosition(fire.get(a).getX() + 50,
					fire.get(a).getY());
			assetsManager.fireSmoke[a].draw(batch, delta);
		}
		for (int a = 0; a < fire.size(); a++) {
			fire.get(a).render(batch, delta);
		}
	}

	void drawBars(float delta) {
		batch.draw(assetsManager.speedBar, 160, 440);
		fireMiniature
				.setPosition(
						530 + (fireRange[randomFire].x + fireRange[randomFire].y) / 2 * 0.0255f,
						410);
		fireMiniature.setScale(fireScale * 0.5f);
		fireMiniature.draw(batch);
		speedBar.render(batch, delta, Math.abs(truck.getSpeed()));
		boyHead.setPosition(530 + truck.getX() * 0.0255f, 410);
		boyHead.draw(batch);
	}

	void drawParticles(float delta) {
		assetsManager.truckEmissions.draw(batch, delta);
		if (truck.getAllowMovingReverse() == false)
			assetsManager.truckEmissions.setPosition(truck.getX() + 880,
					truck.getY() + 20);
		else {
			if (truck.getSpeed() >= 0)
				assetsManager.truckEmissions.setPosition(truck.getX() + 880,
						truck.getY() + 20);
			else
				assetsManager.truckEmissions.setPosition(truck.getX() - 25,
						truck.getY() + 20);

		}
		assetsManager.water.draw(batch, delta);
	}

	void spawnRandomCar(int a) {

		Car car;
		car = new Car();

		int random = MathUtils.random(1, 5);

		if (random == 1)
			car.create(assetsManager.carRed, 1, 1, 1, -3000 * (a + 1), 210);
		if (random == 2)
			car.create(assetsManager.carYellow, 1, 1, 1, -3000 * (a + 1), 210);
		if (random == 3)
			car.create(assetsManager.carPink, 1, 1, 1, -3000 * (a + 1), 210);
		if (random == 4)
			car.create(assetsManager.carBlue, 1, 1, 1, -3000 * (a + 1), 210);
		if (random == 5)
			car.create(assetsManager.carGreen, 1, 1, 1, -3000 * (a + 1), 210);

		random = MathUtils.random(1, 2);

		if (random == 1) {
			car.downLane();
			car.setPosition((int) car.getX(), 135);
		} else {
			car.upLane();
			car.setPosition((int) car.getX(), 210);
		}

		car.setMaxSpeed(6);
		car.setMaxPositions(-30350, 1550);
		car.loadWheel(assetsManager.wheel);
		car.goRight();
		car.goAutomatically(true);
		car.setSpeed(10f);

		cars.add(car);

	}

	void randomizeMinigame() {
		if (minigameRunning == false && minigameCounter > 1 && delayFire == 0) {
			eclipseFire.setDontRespond(true);
			fireCounter = 20;
			eclipseFire.resetCounter();
			minigameCounter--;
			minigameRunning = true;
			if (minigameCounter != 0) {
				int tempRandom = MathUtils.random(0, 5);
				while (tempRandom == randomFire) {
					tempRandom = MathUtils.random(0, 5);
				}
				randomFire = tempRandom;
			}
			for (int a = 0; a < fire.size(); a++) {
				assetsManager.fireSmoke[a].getEmitters().get(0).start();
				assetsManager.fireSmoke[a].setPosition(
						(int) firePositions[randomFire][a].x,
						(int) firePositions[randomFire][a].y);
				fire.get(a).setPosition((int) firePositions[randomFire][a].x,
						(int) firePositions[randomFire][a].y);
			}
			fireBar.setVisibility(true);
			if (minigameCounter == 0) {
				if (minigameCounter == 0 && afterMinigameWindow == false) {
					dialogueWindow.popUp();
					afterMinigameWindow = true;
				}
			}
		}
	}

	void manageFire(float delta) {

		eclipseFire.setAlpha(fireScale * 1f);
		fireBar.setPosition(
				(int) ((fireRange[randomFire].x + fireRange[randomFire].y) / 2) - 75,
				830);
		eclipseFire.setPosition(
				(int) (fireRange[randomFire].x + fireRange[randomFire].y) / 2,
				650);

		for (int a = 0; a < fire.size(); a++) {
			if (minigameRunning == false)
				assetsManager.fireSmoke[a].getEmitters().get(0)
						.setContinuous(false);
			else
				assetsManager.fireSmoke[a].getEmitters().get(0)
						.setContinuous(true);

			fire.get(a).setScale(new Vector2(fireScale, fireScale));
		}

		if (minigameRunning == true) {

			if (eclipseFire.getCounter() == 20) {
				minigameRunning = false;
				fireBar.setVisibility(false);
			}

			if (truck.getX() > fireRange[randomFire].x - 300
					&& truck.getX() < fireRange[randomFire].y + 300) {
				truck.animationLane();
			}
			if (truck.getX() > fireRange[randomFire].x - 120
					&& truck.getX() < fireRange[randomFire].x - 110) {
				truck.setSpeed(0);
				girlTimer += delta;
				if (girlTimer > 0.1f) {
					girlTimer = 0;
					if (fireScale == 1) {
						girlHose.move();
					} else if (fireScale > 0) {

						girlHoseReversed.move();
						girlHoseReversed.setSpeed(2);
					}
				}
				if (fireScale == 1) {
					eclipseFire.setDontRespond(false);
				} else {
					eclipseFire.setDontRespond(true);
				}
			}

			if (delayFire < 1) {
				delayFire += delta;
				if (delayFire > 1)
					delayFire = 1;
			}
			if (delayFire == 1) {
				if (fireScale < 1) {
					fireScale += delta;
					if (fireScale > 1)
						fireScale = 1;
				}

			}
		} else if (delayFire > 0) {
			truck.upLane();

			delayFire -= delta;
			if (delayFire < 0)
				delayFire = 0;
			fireScale -= delta;
			if (fireScale < 0)
				fireScale = 0;
		}

	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getBigRoadRescueScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new BigRoadRescueScreen(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void setFirePositions() {

		firePositions = new Vector2[6][6];
		fireRange = new Vector2[6];

		for (int a = 0; a < 6; a++) {
			for (int b = 0; b < 6; b++)
				firePositions[a][b] = new Vector2();
		}

		firePositions[0][0].add(-14000, 720);
		firePositions[0][1].add(-13760, 620);
		firePositions[0][2].add(-13960, 520);
		firePositions[0][3].add(-13860, 770);
		firePositions[0][4].add(-13800, 370);
		firePositions[0][5].add(-14000, 280);
		fireRange[0] = new Vector2(-14000, -13760);
		// Range between -14000,-13760
		firePositions[1][0].add(-8100, 720);
		firePositions[1][1].add(-8200, 620);
		firePositions[1][2].add(-7900, 520);
		firePositions[1][3].add(-7500, 420);
		firePositions[1][4].add(-7700, 720);
		firePositions[1][5].add(-8000, 460);
		fireRange[1] = new Vector2(-8200, -7700);
		// Range between -8200,-7700
		firePositions[2][0].add(-5700, 720);
		firePositions[2][1].add(-5800, 620);
		firePositions[2][2].add(-5900, 520);
		firePositions[2][3].add(-5950, 420);
		firePositions[2][4].add(-6000, 720);
		firePositions[2][5].add(-5750, 460);
		fireRange[2] = new Vector2(-6000, -5700);
		// Range between -6000,-5700
		firePositions[3][0].add(-4700, 720);
		firePositions[3][1].add(-4500, 620);
		firePositions[3][2].add(-4400, 820);
		firePositions[3][3].add(-4150, 420);
		firePositions[3][4].add(-4000, 620);
		firePositions[3][5].add(-4650, 460);
		fireRange[3] = new Vector2(-4700, -4000);
		// Range between -4700,-4000
		firePositions[4][0].add(-1800, 680);
		firePositions[4][1].add(-2000, 420);
		firePositions[4][2].add(-1500, 480);
		firePositions[4][3].add(-2200, 520);
		firePositions[4][4].add(-1700, 420);
		firePositions[4][5].add(-1950, 660);
		fireRange[4] = new Vector2(-2200, -1500);
		// Range between -2200,-1500
		firePositions[5][0].add(800, 620);
		firePositions[5][1].add(200, 420);
		firePositions[5][2].add(400, 300);
		firePositions[5][3].add(700, 400);
		firePositions[5][4].add(600, 490);
		firePositions[5][5].add(500, 660);
		fireRange[5] = new Vector2(200, 800);
		// Range between 200,800
	}

	void loadFireAnimations() {

		fire = new ArrayList<StaticAnimation>();
		for (int a = 0; a < 6; a++) {

			StaticAnimation fireAnimation = new StaticAnimation();

			if (a == 0)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -14000,
						-1000, MathUtils.random(0.08f, 0.15f));
			if (a == 1)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13760,
						-1000, MathUtils.random(0.08f, 0.15f));
			if (a == 2)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13960,
						-1000, MathUtils.random(0.08f, 0.15f));
			if (a == 3)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13860,
						-1000, MathUtils.random(0.08f, 0.15f));
			if (a == 4)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13800,
						-1000, MathUtils.random(0.08f, 0.15f));
			if (a == 5)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -14000,
						-1000, MathUtils.random(0.08f, 0.15f));

			fireAnimation.setScale(new Vector2(MathUtils.random(1, 1.5f),
					MathUtils.random(1, 1.5f)));
			fireAnimation.setContinous(true);
			fireAnimation.setWithPreviousFrame(true);
			fireAnimation.start();
			fire.add(fireAnimation);

		}

	}

	void spawnCars() {
		cars = new ArrayList<Car>();
		for (int a = 0; a < 8; a++) {
			// spawnRandomCar(a);
		}
	}

	void loadFountains() {
		fountains = new StaticAnimation[7];
		for (int a = 0; a < 7; a++) {
			fountains[a] = new StaticAnimation();
			fountains[a].create(assetsManager.fountainAnimation, 5, 1, 5,
					850 - a * 2600, 250);
			fountains[a].setAnimationTime(0.2f);
			fountains[a].setContinous(false);
			fountains[a].setWithPreviousFrame(true);
			fountains[a].start();
		}
		fountains[3].setScale(new Vector2(1.65f, 1.65f));
		fountains[3].setPosition((int) fountains[3].getX() - 115,
				(int) fountains[4].getY() + 110);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawFireUtilities(float delta) {
		eclipseFire.render(batch, delta);
		fireBar.render(batch, delta, 20 - eclipseFire.getCounter());
	}
}