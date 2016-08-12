package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.animated.Truck;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.graphics.FallingText;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class MeetTheTrucksScreen implements Screen {

	ArrayList<Vector2> waterPositions;
	ArrayList<Vector2> waterVelocities;
	Sprite cartoonWater;
	ArrayList<Sprite> footMarks;
	Human girlHoseHydrant;
	Human girlHoseHydrantReversed;	
	FallingText eclipseFire;
	FallingText jump;
	FallingText rideTruck;
	Sprite truckLed;
	Truck truck;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Button menuButton;
	Sprite truckMiniature;
	Button up;
	Sprite boyHead;
	Button retryButton;
	Button playButton;
	Sprite fireMiniature;
	MenuWindow menuWindow;
	Bar speedBar;
	Human girl;
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
	Sprite oil;
	Sprite pointer;
	StaticAnimation fireAnimation;
	StaticAnimation fireAnimationBig;
	StaticAnimation fireAnimationBiggest;
	Vector2[] fireAnimationScales;

	float spawnWaterTimer;
	boolean leftFoot;
	float footmarkSpawnTimer;
	float waterRange;
	boolean pat = true;
	float jumpAlpha = 0.5f;
	float timerSpeedGirl;
	boolean exit;
	boolean pointersGoUp;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean truckPart;
	boolean eclipsePart;
	float runningPositionTimer;
	boolean girlHoseStarted;
	float pointersPosition;
	float truckBackDoorPosition = 108;
	boolean reverseHoseAnimation;
	final Starter game;

	public MeetTheTrucksScreen(final Starter gam) {

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

		runButton = new Button(700, -200, assetsManager.runButtonLittle);
		runButton.goUp(250);
		runButton.setAlpha(0.5f);

		up = new Button(10, -200, assetsManager.arrowUp);
		up.goUp(250);
		up.setAlpha(0.5f);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getMeetTheTrucks());

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlRunning, 5, 3, 11, -300, 35);
		girl.setMaxSpeed(5);
		truck = new Truck();
		truck.create(assetsManager.truckBlank, 3, 3, 1, 1550, 35);
		truck.setMaxSpeed(20);
		truck.setMaxPositions(-16300, 9500);
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
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setControlledHuman(girl);
		inputInterpreter.setJump(up);
		cloudManager.stop();

		boyHead = new Sprite(assetsManager.boyButton);
		boyHead.setScale(0.5f);

		camera.zoom = 0.98f;
		camera.position.x = 400;
		camera.position.y = 240;

		camera.moveX(390, 0, 0, 100);
		camera.moveY(240, 0, 0, 100);
		camera.zoom(0.98f, 100f);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 20);
		speedBar.setVisibility(false);
		speedBar.setSpeedometer(assetsManager.speedometer);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
		assetsManager.hit.scaleEffect(0.333f);

		truckLed = new Sprite(assetsManager.truckLed);

		oil = new Sprite(assetsManager.oil);
		pointer = new Sprite(assetsManager.pointer);

		jump = new FallingText(assetsManager.jumpText, 370, 390);
		rideTruck = new FallingText(assetsManager.rideTruckText, 330, 390);
		eclipseFire = new FallingText(assetsManager.eclipseFireText, 330, 390);

		fireAnimation = new StaticAnimation();
		fireAnimation.create(assetsManager.fireBig, 2, 3, 6, 12000, 30,
				MathUtils.random(0.08f, 0.15f));

		fireAnimationBig = new StaticAnimation();
		fireAnimationBig.create(assetsManager.fireBig, 2, 3, 6, 11950, 30,
				MathUtils.random(0.08f, 0.15f));
		fireAnimationBiggest = new StaticAnimation();
		fireAnimationBiggest.create(assetsManager.fireBig, 2, 3, 6, 11970, 50,
				MathUtils.random(0.08f, 0.15f));

		girlHoseHydrant = new Human();
		girlHoseHydrant.create(assetsManager.girlHoseHydrant, 1, 7, 7, 11500,
				30);
		girlHoseHydrantReversed = new Human();
		girlHoseHydrantReversed.create(assetsManager.girlHoseHydrantReversed, 1, 7, 7, 11500,
				30);
		girlHoseHydrantReversed.setAnimationTime(0.1f);
		
		fireAnimationScales = new Vector2[3];
		fireAnimationScales[0] = new Vector2(1, 1);
		fireAnimationScales[1] = new Vector2(1.2f, 1.2f);
		fireAnimationScales[2] = new Vector2(1.4f, 1.7f);

		fireAnimation.setScale(new Vector2(1f, 1f));
		fireAnimation.setContinous(true);
		fireAnimation.setWithPreviousFrame(true);
		fireAnimation.start();

		fireAnimationBig.setScale(new Vector2(1.2f, 1.2f));
		fireAnimationBig.setContinous(true);
		fireAnimationBig.setWithPreviousFrame(true);
		fireAnimationBig.start();

		fireAnimationBiggest.setScale(new Vector2(1.4f, 1.7f));
		fireAnimationBiggest.setContinous(true);
		fireAnimationBiggest.setWithPreviousFrame(true);
		fireAnimationBiggest.start();

		girlHoseHydrant.setOnceOnly();
		girlHoseHydrantReversed.setOnceOnly();
		
		footMarks = new ArrayList<Sprite>();

		fireMiniature = new Sprite(assetsManager.fireMiniature);
		fireMiniature.setScale(0.5f);

		truckMiniature = new Sprite(assetsManager.truckMiniature);
		truckMiniature.setScale(0.5f);

		cartoonWater = new Sprite(assetsManager.clouds[0]);
		cartoonWater.setScale(0.05f);
		cartoonWater.setColor(Color.BLUE);

		waterPositions = new ArrayList<Vector2>();
		waterVelocities = new ArrayList<Vector2>();

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

		drawBackground(delta);
		drawFootmarks(delta);
		drawCharacters(delta);
		drawParticlesNonGui(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawParticles(delta);
		drawBars(delta);
		drawTexts(delta);
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
		if (girl.getX() < 2270 || eclipsePart == true && girl.getX() <= 11750)
			girl.render(batch, delta);

		if(girl.getX() > 1000 && girl.getX() < 11000)
		truck.render(batch, delta);

		if (girl.getX() > 11750 && reverseHoseAnimation == false) {
			girlHoseHydrant.render(batch, delta);
		}
		girlHoseHydrant.setPosition(11700, 35);

		if (reverseHoseAnimation == true && girlHoseHydrantReversed.getSpeed() != 0) {
			girlHoseHydrantReversed.setPosition(11700, 35);
			girlHoseHydrantReversed.render(batch, delta);
			}
		if (reverseHoseAnimation == true && girlHoseHydrantReversed.getSpeed() == 0) {	
		girl.render(batch, delta);
		}

		
		if (Math.abs(girlHoseHydrant.getSpeed()) < 5f)
			girlHoseHydrant.setSpeed(0);
		if (Math.abs(girlHoseHydrantReversed.getSpeed()) < 5f)
			girlHoseHydrantReversed.setSpeed(0);
		
		if (girl.getX() >= 11750 && girlHoseStarted == false) {
			girlHoseHydrant.setSpeed(6);
			girlHoseStarted = true;
			girlHoseHydrant.setAnimationTime(0.1f);
		}

	}

	void drawButtons(float delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
		up.render(batch, delta);
	}

	void drawTexts(float delta) {
		if (girl.getX() > 0) {
			jump.start();
		}
		if (girl.getX() > 1300) {
			jump.stop();
			rideTruck.start();
		}
		if (girl.getX() > 9500) {
			jump.start();
			rideTruck.stop();
		}
		if (girl.getX() > 11400) {
			jump.stop();
			rideTruck.stop();
			eclipseFire.start();
		}

		eclipseFire.render(batch, delta);
		jump.render(batch, delta);
		rideTruck.render(batch, delta);

	}

	void drawWindows(float delta) {

		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		if (girl.getX() > 10510 && girl.getX() < 10550 && girl.getY() == 35) {
			girl.setSpeed(girl.getSpeed() * 0.3f);
			if (Math.abs(girl.getSpeed()) < 1f)
				girl.setSpeed(1);
		}
		if (girl.getX() > 10860 && girl.getX() < 10900 && girl.getY() == 35) {
			girl.setSpeed(girl.getSpeed() * 0.3f);

			if (Math.abs(girl.getSpeed()) < 1f)
				girl.setSpeed(1);
		}
		if (girl.getX() > 11210 && girl.getX() < 11250 && girl.getY() == 35) {
			girl.setSpeed(girl.getSpeed() * 0.3f);

			if (Math.abs(girl.getSpeed()) < 1f)
				girl.setSpeed(1);
		}

		if (girl.getY() == 35) {
			runningPositionTimer += delta;

			if (runningPositionTimer > 1 / girl.getSpeed() + 0.2f) {
				assetsManager.running
						.setPosition(girl.getX() + 10, girl.getY());
				runningPositionTimer = 0;
			} else
				assetsManager.running
						.setPosition(girl.getX() + 90, girl.getY());

			assetsManager.running.start();
		} else
			assetsManager.running.allowCompletion();

		if (girl.getY() != 35 && girl.getY() != 110) {
			pat = false;
		} else if (pat == false) {
			assetsManager.hit.setPosition(girl.getX() + 55, girl.getY());
			assetsManager.hit.start();
			pat = true;
		}
		if (girl.getX() < 2270 && truckPart == false) {
			truck.setSpeed(-0.001f);
			truck.setPosition(1550, 35);
		} else if (eclipsePart == false) {
			speedBar.setVisibility(true);
			truckPart = true;
			girl.setPosition((int) truck.getX() + 720, 35);

			if (truck.getSpeed() >= 0)
				truck.setSpeed(-0.001f);

			truck.setPosition((int) truck.getX(), 35);

			inputInterpreter.setRunButtonSecond(runButton);
			inputInterpreter.setRunButton(null);

			inputInterpreter.setControlledTruck(truck);
			inputInterpreter.setControlledHuman(truck);
		}

		if (truck.getX() >= 9000) {
			truck.setFriction(30);
		}

		if (truck.getX() >= 9000 && Math.abs(truck.getSpeed()) < 0.9f
				&& eclipsePart == false) {
			eclipsePart = true;

			inputInterpreter.setRunButton(runButton);
			inputInterpreter.setControlledHuman(girl);
			inputInterpreter.setControlledTruck(null);
			inputInterpreter.setRunButtonSecond(null);

			speedBar.setVisibility(false);
			girl.setSpeed(5);
			runButton.setDontRespond(false);
		}

		if (eclipsePart == true) {
			truck.setSpeed(-0.001f);
		}

		if (jumpAlpha > 0 && girl.getX() >= 1400 && eclipsePart == false) {
			jumpAlpha -= delta;
			if (jumpAlpha < 0)
				jumpAlpha = 0;
			up.setDontRespond(true);
			up.setAlpha(jumpAlpha);
		}

		if (eclipsePart == true && girl.getX() < 11650) {
			jumpAlpha += delta;
			if (jumpAlpha > 0.5f)
				jumpAlpha = 0.5f;
			up.setDontRespond(false);
			up.setAlpha(jumpAlpha);
		}

		if (girl.getX() >= 11750) {

			if (truckBackDoorPosition < 260)
				truckBackDoorPosition += 40 * delta;

			jumpAlpha -= delta;
			if (jumpAlpha < 0f)
				jumpAlpha = 0f;
			up.setDontRespond(true);
			up.setAlpha(jumpAlpha);
		}

		girl.setPosition((int) girl.getX(),
				(int) girl.getY() + (int) girl.getAccelerationJump());

		girl.setAccelerationJump(girl.getAccelerationJump() - delta * 50);

		if (girl.getAccelerationJump() < -10)
			girl.setAccelerationJump(-10);

		// 350,430
		if (girl.getX() >= 270 && girl.getX() < 280 && girl.getY() < 110) {
			girl.setPosition(270, (int) girl.getY());
		}
		if (girl.getX() > 270 && girl.getX() < 435) {
			if (girl.getY() < 110)
				girl.setPosition((int) girl.getX(), 110);
		}

		// 750,830
		if (girl.getX() >= 670 && girl.getX() < 680 && girl.getY() < 110) {
			girl.setPosition(670, (int) girl.getY());
		}
		if (girl.getX() > 670 && girl.getX() < 835) {
			if (girl.getY() < 110)
				girl.setPosition((int) girl.getX(), 110);
		}
		// 1150,1230
		if (girl.getX() >= 1070 && girl.getX() < 1080 && girl.getY() < 110) {
			girl.setPosition(1070, (int) girl.getY());
		}
		if (girl.getX() > 1070 && girl.getX() < 1235) {
			if (girl.getY() < 110)
				girl.setPosition((int) girl.getX(), 110);
		}

		if (girl.getY() < 35)
			girl.setPosition((int) girl.getX(), 35);

		if (girl.getX() >= 400 && secondDialogueClicked == false) {
			secondDialogueClicked = true;
			// dialogueWindow.popUp();
			// girl.setSpeed(0);
			// girl.resetStateTime();
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}

		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}
		if(girl.getX() > 14200  && exit == false){
			exit = true;
			cloudManager.start();
		}

		truck.setPosition((int) truck.getX(), 35);
		updateCameraLogics(delta);
		updateGirlAction(delta);

	}

	void updateCameraLogics(double delta) {
		if (girl.getX() >= 400 && girl.getX() < 5750 && truckPart == false) {
			camera.position.x = (girl.getX());
		}
		if (truckPart == true) {
			camera.position.x = truck.getX() + 720;
		}
		if (eclipsePart == true && girl.getX() <= 13500) {
			camera.position.x = girl.getX();
		} else if (eclipsePart == true) {
			camera.position.x = 13500;
		}

	}

	void drawFootmarks(float delta) {

		footmarkSpawnTimer += delta;
		if (footmarkSpawnTimer > 0.1f && girl.getY() == 35
				&& Math.abs(girl.getSpeed()) > 1f
				&& Math.abs(truck.getSpeed()) < 1) {
			footmarkSpawnTimer = 0;
			Sprite s = new Sprite(assetsManager.foot);
			if (leftFoot == true) {
				s.setPosition(girl.getX() + 10 + girl.getSpeed(), 37);
				leftFoot = false;
			} else {
				s.setPosition(girl.getX() + 10 + girl.getSpeed(), 32);
				leftFoot = true;
			}

			footMarks.add(s);

		}

		for (int a = 0; a < footMarks.size(); a++) {

			if (footMarks.get(a).getColor().a > 0)
				footMarks.get(a).setColor(1, 1, 1,
						footMarks.get(a).getColor().a - delta * 0.75f);

			if (footMarks.get(a).getColor().a <= 0.1f) {
				footMarks.remove(a);
			} else
				footMarks.get(a).draw(batch);
		}
	}

	void drawBackground(float delta) {
		if (girl.getX() <= 1200) {

			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
			batch.draw(assetsManager.wheel, 350, 35);
			batch.draw(assetsManager.wheel, 390, 35);
			batch.draw(assetsManager.wheel, 430, 35);

			batch.draw(assetsManager.wheel, 350, 75);
			batch.draw(assetsManager.wheel, 390, 75);
			batch.draw(assetsManager.wheel, 430, 75);

			batch.draw(assetsManager.sign, 150, 48);
		}
		if (girl.getX() <= 2000) {
			batch.draw(assetsManager.parkBackgrounds[1], 789, 0);

			batch.draw(assetsManager.wheel, 750, 35);
			batch.draw(assetsManager.wheel, 790, 35);
			batch.draw(assetsManager.wheel, 830, 35);

			batch.draw(assetsManager.wheel, 750, 75);
			batch.draw(assetsManager.wheel, 790, 75);
			batch.draw(assetsManager.wheel, 830, 75);

			batch.draw(assetsManager.wheel, 1150, 35);
			batch.draw(assetsManager.wheel, 1190, 35);
			batch.draw(assetsManager.wheel, 1230, 35);

			batch.draw(assetsManager.wheel, 1150, 75);
			batch.draw(assetsManager.wheel, 1190, 75);
			batch.draw(assetsManager.wheel, 1230, 75);
		

			pointer.setPosition(390, pointersPosition + 100);
			pointer.draw(batch);
			pointer.setPosition(790, pointersPosition + 100);
			pointer.draw(batch);
			pointer.setPosition(1190, pointersPosition + 100);
			pointer.draw(batch);


		}
		if (girl.getX() >= 1200 && girl.getX() < 2600) {

			batch.draw(assetsManager.parkBackgrounds[2], 1589, 0);
		}
		if (girl.getX() >= 1600 && girl.getX() < 3100)
		{
			batch.draw(assetsManager.parkBackgrounds[5], 2049, 0);
			pointer.setPosition(2290, pointersPosition + 200);
			pointer.draw(batch);
		}
		if (girl.getX() >= 2100 && girl.getX() < 4600)
			batch.draw(assetsManager.parkBackgrounds[4], 2514, 0);
		if (girl.getX() >= 2600 && girl.getX() < 5100)
			batch.draw(assetsManager.parkBackgrounds[3], 3314, 0);

		if (girl.getX() >= 3100 && girl.getX() < 7100)
			batch.draw(assetsManager.parkBackgrounds[0], 4109, 0);
		if (girl.getX() >= 3600 && girl.getX() < 7600)
			batch.draw(assetsManager.parkBackgrounds[1], 4908, 0);
		if (girl.getX() >= 4100 && girl.getX() < 8100)
			batch.draw(assetsManager.parkBackgrounds[2], 5708, 0);

		if (girl.getX() >= 4600 && girl.getX() < 8600)
			batch.draw(assetsManager.parkBackgrounds[5], 6170, 0);
		if (girl.getX() >= 5100 && girl.getX() < 9100)
			batch.draw(assetsManager.parkBackgrounds[4], 6635, 0);
		if (girl.getX() >= 6600 && girl.getX() < 9600)
			batch.draw(assetsManager.parkBackgrounds[3], 7435, 0);

		if (girl.getX() >= 7100 && girl.getX() < 10100)
			batch.draw(assetsManager.parkBackgrounds[0], 8230, 0);
		if (girl.getX() >= 7600 && girl.getX() < 10600)
			batch.draw(assetsManager.parkBackgrounds[1], 9010, 0);
		if (girl.getX() >= 8100 && girl.getX() < 11100)
			batch.draw(assetsManager.parkBackgrounds[2], 9800, 0);

		if (girl.getX() >= 9600 && girl.getX() < 12100){
			batch.draw(assetsManager.parkBackgrounds[5], 10260, 0);
		}
		if (girl.getX() >= 10100 && girl.getX() < 12500)
			batch.draw(assetsManager.parkBackgrounds[4], 10720, 0);
		if (girl.getX() >= 10600 && girl.getX() < 13000){
			batch.draw(assetsManager.parkBackgrounds[3], 11520, 0);
			batch.draw(assetsManager.truckBack, 11400, 35);
			batch.draw(assetsManager.truckBackDoor, 11451,
					(int) truckBackDoorPosition - 13);
		}
		if (girl.getX() >= 11600)
			batch.draw(assetsManager.parkBackgrounds[0], 12315, 0);
		if (girl.getX() >= 11800)
			batch.draw(assetsManager.parkBackgrounds[1], 13113, 0);
		if (girl.getX() >= 12000)
			batch.draw(assetsManager.parkBackgrounds[2], 13910, 0);


		drawOil();
		drawPointers(delta);

		fireAnimationBiggest.setScale(fireAnimationScales[0]);
		fireAnimationBig.setScale(fireAnimationScales[1]);
		fireAnimation.setScale(fireAnimationScales[2]);

		if (waterRange > 1.4f && waterRange < 1.7f) {
			if (fireAnimationScales[0].x > 0)
				fireAnimationScales[0].x -= delta;
			if (fireAnimationScales[0].x < 0)
				fireAnimationScales[0].x = 0;

			if (fireAnimationScales[0].y > 0)
				fireAnimationScales[0].y -= delta;
			if (fireAnimationScales[0].y < 0)
				fireAnimationScales[0].y = 0;
		}
		if (waterRange > 1.7f && waterRange < 2.2f) {
			if (fireAnimationScales[1].x > 0)
				fireAnimationScales[1].x -= delta;
			if (fireAnimationScales[1].x < 0)
				fireAnimationScales[1].x = 0;

			if (fireAnimationScales[1].y > 0)
				fireAnimationScales[1].y -= delta;
			if (fireAnimationScales[1].y < 0)
				fireAnimationScales[1].y = 0;
		}
		if (waterRange > 2.2f && waterRange < 2.55f) {
			if (fireAnimationScales[2].x > 0)
				fireAnimationScales[2].x -= delta;
			if (fireAnimationScales[2].x < 0)
				fireAnimationScales[2].x = 0;

			if (fireAnimationScales[2].y > 0)
				fireAnimationScales[2].y -= delta;
			if (fireAnimationScales[2].y < 0)
				fireAnimationScales[2].y = 0;
		}

		if(girl.getX()> 10600){
		fireAnimationBiggest.render(batch, delta);
		fireAnimationBig.render(batch, delta);
		fireAnimation.render(batch, delta);
		}
		if (girl.getX() <= 11750 ) {
			batch.draw(assetsManager.hoseHydrant, 11700, 38);
		}
		if(reverseHoseAnimation == true && girlHoseHydrantReversed.getSpeed() == 0){
			batch.draw(assetsManager.hoseHydrant, 11700, 38);	
		}

	
	//	 batch.draw(assetsManager.wall, 12700, 38);
		if (girl.getX() > 11750 && truckBackDoorPosition > 140)
			drawWater(delta);

	}

	void updateGirlAction(double delta) {

	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
	}

	void drawParticlesNonGui(float delta) {

		// assetsManager.running.draw(batch, delta);
		assetsManager.hit.draw(batch, delta);
	}

	void drawBars(float delta) {

		speedBar.render(batch, delta, Math.abs(truck.getSpeed()));

		batch.draw(assetsManager.speedBar, 160, 440);

		boyHead.setPosition(160 + girl.getX() * 0.0285f, 410);
		boyHead.draw(batch);

		fireMiniature.setPosition(495, 410);
		fireMiniature.draw(batch);

		if (truckPart == false)
			truckMiniature.setPosition(225, 410);
		else if (eclipsePart == false)
			truckMiniature.setPosition(160 + girl.getX() * 0.0285f, 410);

		truckMiniature.draw(batch);

	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(3);
				camera.reset();
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMeetTheTrucks()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(3);
				camera.reset();
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			camera.reset();
			game.setScreen(new MenuScreen(game));
		}

	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawOil() {

		oil.setScale(0.3f);
		oil.setPosition(10500, 10);
		oil.draw(batch);
		oil.setPosition(10850, 10);
		oil.draw(batch);
		oil.setPosition(11200, 10);
		oil.draw(batch);
	}

	void drawPointers(float delta) {

		pointer.setPosition(10590, pointersPosition);
		pointer.draw(batch);
		pointer.setPosition(10940, pointersPosition);
		pointer.draw(batch);
		pointer.setPosition(11290, pointersPosition);
		pointer.draw(batch);

		if (pointersGoUp == false) {
			if (pointersPosition < 100) {
				pointersPosition += delta * 80;
				if (pointersPosition > 100) {
					pointersPosition = 100;
					pointersGoUp = true;
				}
			}
		} else {
			if (pointersPosition > 50) {
				pointersPosition -= delta * 80;
				if (pointersPosition < 50) {
					pointersPosition = 50;
					pointersGoUp = false;
				}
			}
		}
	}

	void drawWater(float delta) {

		if (fireAnimationScales[2].y < 0.05f && fireAnimationScales[1].y < 0.05f
				&& fireAnimationScales[0].y < 0.05f && reverseHoseAnimation == false){
			reverseHoseAnimation = true;
			girlHoseHydrantReversed.setSpeed(6);
			girl.setSpeed(2.5f);
		}
		
		if (runButton.getSelection() == true)
			waterRange += 5 * delta;
		else if (waterRange > 0)
			waterRange -= delta;

		spawnWaterTimer += delta;
		if (spawnWaterTimer > 0.05f && reverseHoseAnimation == false) {

			spawnWaterTimer = 0;
			waterPositions.add(new Vector2(11690, -15));
			waterVelocities.add(new Vector2(MathUtils.random(waterRange,
					waterRange + 1), (MathUtils.random(waterRange,
					waterRange + 1))));

			for (int a = 0; a < waterPositions.size(); a++) {
				if (waterPositions.get(a).y < -300) {
					waterVelocities.remove(a);
					waterPositions.remove(a);
				}
			}
		}

		for (int a = 0; a < waterPositions.size(); a++) {
			waterPositions.get(a).x += waterVelocities.get(a).x;
			waterPositions.get(a).y += waterVelocities.get(a).y;
			waterVelocities.get(a).y -= delta * 4.5f;
			cartoonWater.setPosition(waterPositions.get(a).x,
					waterPositions.get(a).y);

			cartoonWater.draw(batch);
		}

	}
}