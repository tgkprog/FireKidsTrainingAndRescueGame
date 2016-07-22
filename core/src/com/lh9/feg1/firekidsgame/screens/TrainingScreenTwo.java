package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Car;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;

public class TrainingScreenTwo implements Screen {

	Bar speedBar;

	StaticAnimation fountains[];

	boolean lastWindowPopUp;
	double timerSpeedGirl;
	double timerSpawnCar;
	double timerLastPopUp;
	float peopleGroundTimer;
	float peopleBuildingTimer;
	boolean peopleRescued;
	boolean cameraFirstZoom;
	boolean cameraSecondZoom;
	Truck truck;

	ArrayList<StaticAnimation> fire;
	StaticAnimation peopleGround;
	StaticAnimation peopleBuilding;
	ArrayList<Car> cars;

	Button up;
	Button down;
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

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public TrainingScreenTwo(final Starter gam) {

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
		runButton = new Button(650, 0, assetsManager.runButton);
		runButton.goUp(220);

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
		truck.setMaxPositions(-14350, 1550);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();

		cars = new ArrayList<Car>();
		for (int a = 0; a < 8; a++) {
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

	}

	@Override
	public void render(float delta) {

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

		for (int a = 0; a < 8; a++) {

			if (cars.get(a).getX() > truck.getX() - 800
					&& cars.get(a).getX() < truck.getX() + 1150) {
				assetsManager.smoke[a].setPosition(cars.get(a).getX(), cars
						.get(a).getY());
				assetsManager.smoke[a].draw(batch, delta);
			}

		}

		int closestCarLane = 135;
		int closestCarIndex = 0;

		for (int a = 0; a < 8; a++) {
			if (cars.get(a).getX() > truck.getX() - 800
					&& cars.get(a).getX() < truck.getX() + 850) {
				closestCarLane = (int) cars.get(a).getY();
				closestCarIndex = a;
			}
			cars.get(a).manageGoAutomatically(delta);
		}

		if (closestCarLane == 135) {
			truck.render(batch, delta);

			if (firstDialogueClicked == true)
				for (int a = 0; a < 8; a++) {

					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);

					if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
						for (int b = 0; b < 8; b++) {
							if (b != a)
								cars.get(b).waitSec();
						}
						truck.bump();
						break;
					}
				}

		} else if (closestCarLane == 210) {

			for (int a = 0; a < 8; a++) {

				if (firstDialogueClicked == true)
					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);
				if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
					for (int b = 0; b < 8; b++) {
						if (b != a)
							cars.get(b).waitSec();
					}
					truck.bump();
					break;
				}
			}
			truck.render(batch, delta);
		} else {

			for (int a = 0; a < 8; a++) {
				if (a == closestCarIndex)
					if (cars.get(a).getX() > truck.getX() - 800
							&& cars.get(a).getX() < truck.getX() + 1150)
						cars.get(a).render(batch, delta);
				if (cars.get(a).checkCollision(truck.getX(), truck.getY()) == true) {
					for (int b = 0; b < 8; b++) {
						if (b != a)
							cars.get(b).waitSec();
					}
					truck.bump();
					break;
				}
			}

			truck.render(batch, delta);

		}

	}

	void checkCarsCollisions() {
		for (int a = 0; a < 8; a++) {

		}

	}

	void drawButtons(float delta) {
		// assetsManager.buttonEffect.draw(batch, delta);
		// if(runButton.getSelection() == true){
		// assetsManager.buttonEffect.start();
		// }
		// else
		// assetsManager.buttonEffect.reset();
		pause.render(batch, delta);
		runButton.render(batch, delta);
		up.render(batch, delta);
		down.render(batch, delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
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
			spawnRandomCar(10);
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

		if (truck.getX() <= -14350) {

			if (cameraFirstZoom == false) {
				camera.reset();
				camera.zoom(1.3f, 7);
				camera.moveX(camera.position.x + 200, 10, 10, 10);
				camera.moveY(camera.position.y + 150, 10, 10, 10);
				cameraFirstZoom = true;
			}
			speedBar.setVisibility(false);
			timerLastPopUp += delta;
			timerSpawnCar += delta;
			peopleGroundTimer += delta;
			peopleBuildingTimer += delta;
			truck.runAnimation();
			runButton.setDontRespond(true);
			truck.animationLane();
			for (int a = 0; a < 8; a++) {
				cars.get(a).upLane();
			}
		}
		if (truck.getX() <= -14200)
			truck.animationLane();

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
		if (truck.getX() <= 500 && cameraFirstZoom == false)
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
			batch.draw(assetsManager.bigRoad[18], -13600, 0);
		if (truck.getX() > -19000 && truck.getX() < -12100) {
			batch.draw(assetsManager.bigRoad[19], -14400, 0);
		}
		if (truck.getX() > -19000 && truck.getX() < -13700) {
			batch.draw(assetsManager.bigRoad[20], -15200, 0);
			fountains[6].render(batch, delta);

		}
		if (truck.getX() > -18500 && truck.getX() < -14500)
			batch.draw(assetsManager.bigRoad[21], -16000, 0);

		// if (truck.getX() > -18400)
		// batch.draw(assetsManager.bigRoad[22], -16800, 0);
		// if (truck.getX() > -19200)
		// batch.draw(assetsManager.bigRoad[23], -17600, 0);
		if (truck.getX() < -13000)
			for (int a = 0; a < 6; a++) {
				assetsManager.fireSmoke[a].setPosition(fire.get(a).getX() + 50,
						fire.get(a).getY());
				assetsManager.fireSmoke[a].draw(batch, delta);
			}
		if (truck.getX() < -13000)
			for (int a = 0; a < fire.size(); a++) {
				fire.get(a).render(batch, delta);
			}
		peopleGround.render(batch, delta);
		peopleBuilding.render(batch, delta);
	}

	void drawBar(float delta) {
		batch.draw(assetsManager.speedBar, 160, 440);
		batch.draw(assetsManager.boyHead, 530 + truck.getX() * 0.0255f, 435);

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
}