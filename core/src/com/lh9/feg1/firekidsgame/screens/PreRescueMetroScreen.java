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
import com.badlogic.gdx.utils.Array;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Car;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.graphics.Star;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class PreRescueMetroScreen implements Screen {

	Array<Star> stars;
	
	Sprite guiStar;
	Sprite fireMiniature;
	Sprite truckLed;
	Sprite playerHead;
	Sprite pointer;
	
	Button menuButton;
	Button retryButton;
	Button playButton;
	Button up;
	Button down;
	Button pause;
	Button runButton;
	
	StaticAnimation peopleGround;
	StaticAnimation peopleBuilding;
	
	ArrayList<StaticAnimation> fire;
	StaticAnimation fountains[];
	
	ArrayList<Car> cars;
	Truck truck;
	MenuWindow menuWindow;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Bar speedBar;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	boolean enlargeStar;
	boolean ledRed;
	boolean lastWindowPopUp;
	boolean peopleRescued;
	boolean cameraFirstZoom;
	boolean cameraSecondZoom;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean goForward;
	float timerSpeedGirl;
	float timerSpawnCar;
	float timerLastPopUp;
	float pointerScale;
	float peopleGroundTimer;
	float peopleBuildingTimer;
	int lastTimeCarLane;
	int starsAll;
	int starsCollected;
	int starsCollectedLastFrame;
		
	final Starter game;

	public PreRescueMetroScreen(final Starter gam) {

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
		up.goUp(320);
		down = new Button(10, -100, assetsManager.arrowDown);
		down.goUp(210);
		runButton = new Button(685, -200, assetsManager.runButtonLittle);
		runButton.goUp(30);
		runButton.setAlpha(0.5f);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);

		cloudManager.stop();

		runButton.setDontRespond(true);
		pause.setDontRespond(true);
		up.setDontRespond(true);
		down.setDontRespond(true);

		camera.zoom = 1.9f;
		camera.position.x = 800;
		camera.position.y = 470;

		camera.moveX(800, 0, 0, 100);
		camera.moveY(470, 0, 0, 100);
		camera.zoom(1.9f, 100f);

		dialogueWindow.popUp();

		truck = new Truck();

		truck.create(assetsManager.trainBasketAnimation, 3, 3, 14, 1550, 135);
		truck.setMaxSpeed(20);
		truck.setMaxPositions(-17000, 1550);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();
		truck.setSpeed(5);
		
		cars = new ArrayList<Car>();
		for (int a = 0; a < 10; a++) {
			spawnRandomCar(a);
		}
		inputInterpreter.setControlledHuman(truck);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		inputInterpreter.loadDown(down);
		inputInterpreter.loadUp(up);
		inputInterpreter.setControlledTruck(truck);

		peopleGround = new StaticAnimation();
		peopleGround.create(assetsManager.peopleGround, 3, 1, 3, -14000, 720,
				MathUtils.random(0.08f, 0.4f));
		peopleGround.setContinous(true);
		peopleGround.setScale(new Vector2(0f, 0f));
		peopleGround.start();
		peopleBuilding = new StaticAnimation();
		peopleBuilding.create(assetsManager.peopleBuilding, 2, 1, 2, -14000,
				720, MathUtils.random(0.08f, 0.4f));
		peopleBuilding.setContinous(true);
		peopleBuilding.start();
		peopleGround.setPosition(-14400, 275);
		peopleBuilding.setPosition(-13950, 780);

		fire = new ArrayList<StaticAnimation>();

		for (int a = 0; a < 6; a++) {

			StaticAnimation fireAnimation = new StaticAnimation();

			if (a == 0)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -14000,
						720, MathUtils.random(0.08f, 0.15f));
			if (a == 1)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13760,
						620, MathUtils.random(0.08f, 0.15f));
			if (a == 2)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13960,
						520, MathUtils.random(0.08f, 0.15f));
			if (a == 3)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13860,
						770, MathUtils.random(0.08f, 0.15f));
			if (a == 4)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -13800,
						370, MathUtils.random(0.08f, 0.15f));
			if (a == 5)
				fireAnimation.create(assetsManager.fireBig, 2, 3, 6, -14000,
						280, MathUtils.random(0.08f, 0.15f));

			fireAnimation.setScale(new Vector2(MathUtils.random(1, 1.5f),
					MathUtils.random(1, 1.5f)));
			fireAnimation.setContinous(true);
			fireAnimation.setWithPreviousFrame(true);
			fireAnimation.start();
			fire.add(fireAnimation);

		}

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 20);
		speedBar.setVisibility(true);

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

		truckLed = new Sprite(assetsManager.truckLed);
		pointer = new Sprite(assetsManager.pointer);
		pointer.rotate(270);
		pointer.setScale(0);
		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getTRAINING_SCREEN_TWO());

		inputInterpreter.setMenuWindow(menuWindow);

		
		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		if(dataOrganizer.getGender() == false)
		playerHead = new Sprite(assetsManager.boyButton);
		else
			playerHead = new Sprite(assetsManager.girlButton);
		
			playerHead.setScale(0.5f);
		fireMiniature = new Sprite(assetsManager.trainMiniature);
		fireMiniature.setScale(0.5f);
		fireMiniature.setPosition(120, 410);


		stars = new Array<Star>();

		for (int a = 0; a < 250; a++) {
			Star star;
			if (a % 2 == 0)
				star = new Star(assetsManager.star, 1250 - a * 200, 135, 1.5f);
			else
				star = new Star(assetsManager.star, 1250 - a * 200, 210, 1.5f);
			stars.add(star);
		}

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
		checkCarsCollisions();
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

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawGuiStarsCounter(delta);
		drawButtons(deltaTemp);
		drawWindows(deltaTemp);
		drawBars(delta);
		drawClosestCarPointer();
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

		for (int a = 0; a < 10; a++) {
			if (cars.get(a).getX() > truck.getX() - 800
					&& cars.get(a).getX() < truck.getX() + 1150) {
				assetsManager.smoke[a].setPosition(cars.get(a).getX(), cars
						.get(a).getY());
				assetsManager.smoke[a].draw(batch, delta);
			}
		}
		int closestCarLane = 135;
		int closestCarIndex = 0;
		float closestCarPosition = -15000;
		for (int a = 0; a < 10; a++) {
			if (cars.get(a).getX() > truck.getX() - 800
					&& cars.get(a).getX() < truck.getX() + 850) {
				closestCarLane = (int) cars.get(a).getY();
				closestCarIndex = a;
			}
			cars.get(a).manageGoAutomatically(delta);
		}

		for (int a = 0; a < 10; a++) {
			if (cars.get(a).getX() > closestCarPosition
					&& cars.get(a).getX() < truck.getX()) {
				closestCarPosition = cars.get(a).getX();
				closestCarIndex = a;
			}
		}

		if (truck.getX() < 0)
			if (Math.abs(cars.get(closestCarIndex).getX()
					+ Math.abs(truck.getX())) < 1000
					&& truck.getX() > cars.get(closestCarIndex).getX()) {
				if (pointerScale < 1)
					pointerScale += delta * 6;
				else
					pointerScale = 1;
			} else {
				if (pointerScale > 0)
					pointerScale -= delta * 6;
				else
					pointerScale = 0;

				if (cars.get(closestCarIndex).getY() == 135) {
					if (pointerScale == 0)
						pointer.setPosition(25, 25);

				} else if (cars.get(closestCarIndex).getY() == 210) {

					if (pointerScale == 0)
						pointer.setPosition(25, 100);

				} else
					pointerScale = 0;
			}

		if (closestCarLane == 135) {

			truck.render(batch, delta);

			if (firstDialogueClicked == true)
				for (int a = 0; a < 10; a++) {

					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);

					if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
						for (int b = 0; b < 10; b++) {
							if (b != a)
								cars.get(b).waitSec();
						}
						truck.bump();
						break;
					}
				}

		} else if (closestCarLane == 210) {

			for (int a = 0; a < 10; a++) {

				if (firstDialogueClicked == true)
					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);
				if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
					for (int b = 0; b < 10; b++) {
						if (b != a)
							cars.get(b).waitSec();
					}
					truck.bump();
					break;
				}
			}
			truck.render(batch, delta);
		} else {

			for (int a = 0; a < 10; a++) {
				if (a == closestCarIndex)
					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);
				if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
					for (int b = 0; b < 10; b++) {
						if (b != a)
							cars.get(b).waitSec();
					}
					truck.bump();
					break;
				}
			}

			truck.render(batch, delta);

		}
		if (Math.abs(truck.getSpeed()) >= 0) {
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
		}
	}

	void checkCarsCollisions() {
		for (int a = 0; a < 10; a++) {
		}
	}

	void drawButtons(float delta) {
		pause.render(batch, delta);
		runButton.render(batch, delta);
		up.render(batch, delta);
		down.render(batch, delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {
		pointer.setScale((float) pointerScale);

		if (menuWindow.isVisibile() == true) {
			runButton.setDontRespond(true);
			up.setDontRespond(true);
			down.setDontRespond(true);
		} else {
			runButton.setDontRespond(false);
			up.setDontRespond(false);
			down.setDontRespond(false);
		}
		
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

		if (cloudManager.getAllScalesEqualOne() == true
				&& lastWindowPopUp == true){
			game.setCollectedStars(starsCollected + starsAll);
			game.setScreen(new MenuScreen(game));
		}
		if (timerLastPopUp > 8 && lastWindowPopUp == false) {
			dialogueWindow.popUp();
			lastWindowPopUp = true;
		}

		if (lastWindowPopUp == true && dialogueWindow.isVisibile() == false
				&& cloudManager.getAllScalesEqualZero() == true) {
			cloudManager.start();
		}

		if (peopleBuildingTimer > 4f) {
			if (peopleBuildingTimer <= 5)
				peopleBuilding.setScale(new Vector2(5 - peopleBuildingTimer,
						5 - peopleBuildingTimer));
		}
		if (peopleGroundTimer > 6.5f) {
			peopleRescued = true;

			if (cameraSecondZoom == false) {
				camera.zoom(0.8f, 7);
				camera.reset();
				camera.moveX(-14430, 10, 10, 5);
				camera.moveY(camera.position.y - 150, 10, 10, 5);
				cameraSecondZoom = true;
			}

			if (peopleGroundTimer <= 7.7f)
				peopleGround.setScale(new Vector2(peopleBuildingTimer - 6.5f,
						peopleBuildingTimer - 6.5f));
		}

		if (truck.getX() <= -16000) {

			if (cameraFirstZoom == false) {
	
				camera.reset();
				camera.zoom(-1.5f, 25);
			//	camera.moveX(camera.position.x + 200, 10, 10, 10);
			//	camera.moveY(camera.position.y + 150, 10, 10, 10);
				cameraFirstZoom = true;
			
				cloudManager.start();
			}
			speedBar.setVisibility(false);
			runButton.setDontRespond(true);
			
			for (int a = 0; a < 10; a++) {
				cars.get(a).setSpeed(0);
			}
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
			runButton.setDontRespond(false);
			pause.setDontRespond(false);
			up.setDontRespond(false);
			down.setDontRespond(false);
		}
		updateCameraLogics(delta);

	}

	void updateCameraLogics(double delta) {
		if (truck.getX() <= 500 && cameraFirstZoom == false && truck.getX() > -15500)
			camera.position.x = truck.getX() + 300;
	}

	void drawBackground(float delta) {

		if (truck.getX() > -800) {
			batch.draw(assetsManager.bigRoad[0], 800, 0);
			fountains[0].render(batch, delta);
		}
		if (truck.getX() > -1600)
			batch.draw(assetsManager.bigRoad[1], 0, 0);
		if (truck.getX() > -2400 && truck.getX() < 700) {

			batch.draw(assetsManager.bigRoad[2], -800, 0);
		}
		if (truck.getX() > -3200 && truck.getX() < -100) {
			batch.draw(assetsManager.bigRoad[3], -1600, 0);
		}
		if (truck.getX() > -4000 && truck.getX() < -900) {
			batch.draw(assetsManager.bigRoad[4], -2400, 0);

			fountains[1].render(batch, delta);
		}
		if (truck.getX() > -4800 && truck.getX() < -1700) {
			batch.draw(assetsManager.bigRoad[5], -3200, 0);
		}
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
		if (truck.getX() > -13600 && truck.getX() < -10500) {
			batch.draw(assetsManager.bigRoad[16], -12000, 0);

		}

		if (truck.getX() > -14400 && truck.getX() < -11300) {
			batch.draw(assetsManager.bigRoad[17], -12800, 4);
			fountains[5].render(batch, delta);

		}
		if (truck.getX() > -15200 && truck.getX() < -12100)
			batch.draw(assetsManager.bigRoad[18], -13600, -2);
		if (truck.getX() > -19000 && truck.getX() < -12100) {
			batch.draw(assetsManager.bigRoad[19], -14400, 0);
		}
		if (truck.getX() > -19000 && truck.getX() < -13700) {
			batch.draw(assetsManager.bigRoad[20], -15200, 0);
			fountains[6].render(batch, delta);

		}
		if (truck.getX() > -18500 && truck.getX() < -14500)
			batch.draw(assetsManager.bigRoad[21], -16000, 0);

		drawStars(delta);

	}

	void drawBars(float delta) {
		batch.draw(assetsManager.speedBar, 160, 440);

		playerHead.setPosition(530 + truck.getX() * 0.0255f, 410);
		playerHead.draw(batch);
		fireMiniature.draw(batch);
		speedBar.render(batch, delta, truck.getSpeed());

	}

	void drawParticles(float delta) {
		assetsManager.truckEmissions.draw(batch, delta);
		assetsManager.truckEmissions.setPosition(truck.getX() + 880,
				truck.getY() + 20);
	}

	void spawnRandomCar(int a) {

		Car car;
		car = new Car();

		int random = MathUtils.random(1, 5);

		if (random == 1)
			car.create(assetsManager.carRed, 1, 1, 1, -1600 * (a + 1), 210);
		if (random == 2)
			car.create(assetsManager.carYellow, 1, 1, 1, -1600 * (a + 1), 210);
		if (random == 3)
			car.create(assetsManager.carPink, 1, 1, 1, -1600 * (a + 1), 210);
		if (random == 4)
			car.create(assetsManager.carBlue, 1, 1, 1, -1600 * (a + 1), 210);
		if (random == 5)
			car.create(assetsManager.carGreen, 1, 1, 1, -1600 * (a + 1), 210);

		random = MathUtils.random(1, 2);
		if (MathUtils.randomBoolean() == true) {
			if (lastTimeCarLane == 1)
				random = 2;
			else
				random = 1;
		}

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

		lastTimeCarLane = random;
	}

	void manageSelectingScreen() {
	if(cameraFirstZoom == true && cloudManager.getAllScalesEqualOne()== true){
		game.setCollectedStars(starsCollected + starsAll);
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
				.getTRAINING_SCREEN_TWO()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new PreRescueMetroScreen(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawClosestCarPointer() {
		pointer.draw(batch);
	}
	void drawStars(float delta) {

		for (int a = 0; a < stars.size; a++) {

			if ((truck.getX() <= -15000 && stars.get(a).getX() < 800)
					|| truck.getX() >= 400
					|| (stars.get(a).getX() - truck.getX() < 1300 && stars.get(
							a).getWidth()
							+ stars.get(a).getX() - truck.getX() > -1300)) {
				stars.get(a).draw(batch, delta);
			}

			if ((truck.getX() - 50 < stars.get(a).getX()
					&& truck.getX() + 900 > stars.get(a).getX() && Math
					.abs(truck.getY() - stars.get(a).getY()) < 10)) {
				
				if (stars.get(a).getHit() == false) {
					starsCollected++;
					stars.get(a).setHit();
				}
			}

		}
		if (starsCollected > starsCollectedLastFrame)
			enlargeStar = true;

		starsCollectedLastFrame = starsCollected;
	}

	void drawGuiStarsCounter(float delta) {
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
		assetsManager.fontLittle.draw(batch, Integer.toString(starsCollected + starsAll),
				60, 463);
	}
}