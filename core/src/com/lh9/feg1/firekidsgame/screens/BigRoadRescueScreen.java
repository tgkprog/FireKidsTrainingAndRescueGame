package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Car;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.files.windows.Dialogue;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class BigRoadRescueScreen implements Screen {

	int randomFire;
	boolean minigameRunning;
	boolean afterMinigameWindow;
	float minigameTimeLeft = 3;
	int minigameCounter = 10;

	Bar speedBar;
	Vector2[][] firePositions;
	Vector2[] fireRange;

	boolean lastWindowPopUp;
	double timerSpeedGirl;
	double timerSpawnCar;
	double timerLastPopUp;
	boolean peopleRescued;
	boolean cameraFirstZoom;
	boolean cameraSecondZoom;
	Truck truck;

	ArrayList<StaticAnimation> fire;
	ArrayList<Car> cars;

	Button up;
	Button down;
	Button pause;
	Button runLeft;
	Button runRight;

	ParticleEffect[] smoke;
	ParticleEffect[] fireSmoke;

	Dialogue dialogueWindow;

	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public BigRoadRescueScreen(final Starter gam) {

		firePositions = new Vector2[6][6];
		fireRange = new Vector2[6];

		for(int a = 0;a< 6;a++){
			for(int b =0;b<6;b++)
			firePositions[a][b] = new Vector2();
		}
		
		firePositions[0][0].add(-14000, 720);
		firePositions[0][1].add(-13760, 620);
		firePositions[0][2].add(-13960, 520);
		firePositions[0][3].add(-13860, 770);
		firePositions[0][4].add(-13800, 370);
		firePositions[0][5].add(-14000, 280);
		fireRange[0] = new Vector2(-14000, -13760);
		// Between -14000,-13760
		firePositions[1][0].add(-8100, 720);
		firePositions[1][1].add(-8200, 620);
		firePositions[1][2].add(-7900, 520);
		firePositions[1][3].add(-7500, 420);
		firePositions[1][4].add(-7700, 720);
		firePositions[1][5].add(-8000, 460);
		fireRange[1] = new Vector2(-8200, -7700);
		// Between -8200,-7700
		firePositions[2][0].add(-5700, 720);
		firePositions[2][1].add(-5800, 620);
		firePositions[2][2].add(-5900, 520);
		firePositions[2][3].add(-5950, 420);
		firePositions[2][4].add(-6000, 720);
		firePositions[2][5].add(-5750, 460);
		fireRange[2] = new Vector2(-6000, -5700);
		// Between -6000,-5700
		firePositions[3][0].add(-4700, 720);
		firePositions[3][1].add(-4500, 620);
		firePositions[3][2].add(-4400, 820);
		firePositions[3][3].add(-4150, 420);
		firePositions[3][4].add(-4000, 620);
		firePositions[3][5].add(-4650, 460);
		fireRange[3] = new Vector2(-4700, -4000);
		// Between -4700,-4000
		firePositions[4][0].add(-1800, 680);
		firePositions[4][1].add(-2000, 420);
		firePositions[4][2].add(-1500, 480);
		firePositions[4][3].add(-2200, 520);
		firePositions[4][4].add(-1700, 420);
		firePositions[4][5].add(-1950, 660);
		fireRange[4] = new Vector2(-2200, -1500);
		// Between -2200,-1500
		firePositions[5][0].add(800, 620);
		firePositions[5][1].add(200, 420);
		firePositions[5][2].add(400, 300);
		firePositions[5][3].add(700, 400);
		firePositions[5][4].add(600, 490);
		firePositions[5][5].add(500, 660);
		fireRange[5] = new Vector2(-200, -800);
		// Between 200,800

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

		runLeft.setAlpha(0.5f);
		runRight.setAlpha(0.5f);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runLeft);
		inputInterpreter.setRunButtonSecond(runRight);

		cloudManager.stop();

		runLeft.setDontRespond(true);
		runRight.setDontRespond(true);

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
		truck.setMaxPositions(-14350, 1550);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();

		cars = new ArrayList<Car>();
		for (int a = 0; a < 8; a++) {
			// spawnRandomCar(a);
		}
		inputInterpreter.setControlledHuman(truck);

		assetsManager.leaf.setPosition(-100, 200);
		assetsManager.stars.setPosition(400, 480);

		inputInterpreter.loadDown(down);
		inputInterpreter.loadUp(up);
		inputInterpreter.setControlledTruck(truck);

		smoke = new ParticleEffect[8];
		for (int a = 0; a < 8; a++) {
			smoke[a] = new ParticleEffect();
			smoke[a].load(Gdx.files.internal("particles/truckEmissions"),
					Gdx.files.internal("particles/"));
		}

		fireSmoke = new ParticleEffect[6];
		for (int a = 0; a < 6; a++) {
			fireSmoke[a] = new ParticleEffect();
			fireSmoke[a].load(Gdx.files.internal("particles/fireSmoke"),
					Gdx.files.internal("particles/"));
			fireSmoke[a].start();

		}
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

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 20);
		speedBar.setVisibility(true);

		truck.setAllowReverse(true);
		randomizeMinigame();
		
	}

	@Override
	public void render(float delta) {
		
		for(int a =0;a<6;a++){
			fireSmoke[a].getEmitters().get(0).setContinuous(false);
			fire.get(a).setScale(new Vector2(0.5f,0.5f));
		}
		
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
		// drawBar();
		drawButtons(delta);
		drawWindows(delta);
		drawBar(delta);
		cloudManager.render(batch, delta);
		batch.end();
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

	void checkCarsCollisions() {

	}

	void drawButtons(float delta) {
		// assetsManager.buttonEffect.draw(batch, delta);
		// if(runButton.getSelection() == true){
		// assetsManager.buttonEffect.start();
		// }
		// else
		// assetsManager.buttonEffect.reset();
		pause.render(batch, delta);
		runRight.render(batch, delta);
		runLeft.render(batch, delta);
		up.render(batch, delta);
		down.render(batch, delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {
	
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

		if (truck.getX() <= -14350) {
			/*
			 * if (cameraFirstZoom == false) { camera.reset(); camera.zoom(1.3f,
			 * 7); camera.moveX(camera.position.x + 200, 10, 10, 10);
			 * camera.moveY(camera.position.y + 150, 10, 10, 10);
			 * cameraFirstZoom = true; } speedBar.setVisibility(false);
			 * timerLastPopUp += delta; timerSpawnCar += delta;
			 * peopleGroundTimer += delta; peopleBuildingTimer += delta;
			 * truck.runAnimation(); runLeft.setDontRespond(true);
			 * runRight.setDontRespond(true); truck.animationLane();
			 */

		}
		// if (truck.getX() <= -14200)
		// truck.animationLane();

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
			runLeft.setDontRespond(false);
			runRight.setDontRespond(false);
			pause.setDontRespond(false);
			up.setDontRespond(false);
			down.setDontRespond(false);
			randomizeMinigame();
			
		}
		updateCameraLogics(delta);

	}

	void updateCameraLogics(double delta) {
		if (truck.getX() <= 500 && cameraFirstZoom == false)
			camera.position.x = truck.getX() + 300;
	}

	void drawBackground(float delta) {
		if (truck.getX() > -800)
			batch.draw(assetsManager.bigRoad[0], 800, 0);
		if (truck.getX() > -1600)
			batch.draw(assetsManager.bigRoad[1], 0, 0);
		if (truck.getX() > -2400 && truck.getX() < 700)
			batch.draw(assetsManager.bigRoad[2], -800, 0);
		if (truck.getX() > -3200 && truck.getX() < -100)
			batch.draw(assetsManager.bigRoad[3], -1600, 0);
		if (truck.getX() > -4000 && truck.getX() < -900)
			batch.draw(assetsManager.bigRoad[4], -2400, 0);
		if (truck.getX() > -4800 && truck.getX() < -1700)
			batch.draw(assetsManager.bigRoad[5], -3200, 0);
		if (truck.getX() > -5600 && truck.getX() < -2500)
			batch.draw(assetsManager.bigRoad[6], -4000, 0);
		if (truck.getX() > -6400 && truck.getX() < -3300)
			batch.draw(assetsManager.bigRoad[7], -4800, 0);
		if (truck.getX() > -7200 && truck.getX() < -4100)
			batch.draw(assetsManager.bigRoad[8], -5600, 0);
		if (truck.getX() > -8000 && truck.getX() < -4900)
			batch.draw(assetsManager.bigRoad[9], -6400, 0);
		if (truck.getX() > -8800 && truck.getX() < -5700)
			batch.draw(assetsManager.bigRoad[10], -7200, 0);
		if (truck.getX() > -9600 && truck.getX() < -6500)
			batch.draw(assetsManager.bigRoad[11], -8000, 0);
		if (truck.getX() > -10400 && truck.getX() < -7300)
			batch.draw(assetsManager.bigRoad[12], -8800, 0);
		if (truck.getX() > -11200 && truck.getX() < -8100)
			batch.draw(assetsManager.bigRoad[13], -9600, 0);
		if (truck.getX() > -12000 && truck.getX() < -8900)
			batch.draw(assetsManager.bigRoad[14], -10400, 0);
		if (truck.getX() > -12800 && truck.getX() < -9700)
			batch.draw(assetsManager.bigRoad[15], -11200, 0);
		if (truck.getX() > -13600 && truck.getX() < -10500)
			batch.draw(assetsManager.bigRoad[16], -12000, 0);
		if (truck.getX() > -14400 && truck.getX() < -11300)
			batch.draw(assetsManager.bigRoad[17], -12800, 4);
		if (truck.getX() > -15200 && truck.getX() < -12100)
			batch.draw(assetsManager.bigRoad[18], -13600, 0);
		if (truck.getX() > -16000 && truck.getX() < -12900)
			batch.draw(assetsManager.bigRoad[19], -14400, 0);
		if (truck.getX() > -16800 && truck.getX() < -13700)
			batch.draw(assetsManager.bigRoad[20], -15200, 0);
		if (truck.getX() > -17600 && truck.getX() < -14500)
			batch.draw(assetsManager.bigRoad[21], -16000, 0);
		// if (truck.getX() > -18400)
		// batch.draw(assetsManager.bigRoad[22], -16800, 0);
		// if (truck.getX() > -19200)
		// batch.draw(assetsManager.bigRoad[23], -17600, 0);
		// if (truck.getX() < -13000)
		for (int a = 0; a < 6; a++) {
			fireSmoke[a].setPosition(fire.get(a).getX() + 50, fire.get(a)
					.getY());
			fireSmoke[a].draw(batch, delta);
		}
		// if (truck.getX() < -13000)
		for (int a = 0; a < fire.size(); a++) {
			fire.get(a).render(batch, delta);
		}
	}

	void drawBar(float delta) {
		batch.draw(assetsManager.speedBar, 160, 440);
		batch.draw(assetsManager.boyHead, 530 + truck.getX() * 0.0255f, 435);
		batch.draw(assetsManager.girlHead,530 + (fireRange[randomFire].x + fireRange[randomFire].y)/2 * 0.0255f, 435);
		speedBar.render(batch, delta, Math.abs(truck.getSpeed()));

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
		if (a == 7) {
			car.upLane();
			car.setPosition((int) car.getX(), 210);
		}
		if (a == 10) {
			car.upLane();
			car.setPosition((int) -15000, 210);
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
		if (minigameRunning == false && minigameCounter > 0) {
			minigameCounter--;
			minigameRunning = true;

			if (minigameCounter != 0) {
				randomFire = MathUtils.random(0, 5);
			}

			if (minigameCounter == 0) {
				if (minigameCounter == 0 && afterMinigameWindow == false) {
					dialogueWindow.popUp();
					afterMinigameWindow = true;
				}
			}

		}
	}

	void manageFire(float delta) {
		if (minigameRunning == true)
			for (int a = 0; a < fire.size(); a++) {
				fire.get(a).setPosition((int) firePositions[randomFire][a].x,
						(int) firePositions[randomFire][a].y);
			}
	}

}