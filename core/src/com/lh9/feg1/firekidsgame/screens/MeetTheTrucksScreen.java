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
import com.badlogic.gdx.utils.Array;
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

	Truck truck;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	MenuWindow menuWindow;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	Vector2[] fireAnimationScales;
	ArrayList<Vector2> waterPositions;
	ArrayList<Vector2> waterVelocities;

	Human player;
	Human playerHoseHydrant;
	Human playerHoseHydrantReversed;

	FallingText breakTheWall;
	FallingText eclipseFire;
	FallingText jump;
	FallingText rideTruck;

	Bar speedBar;
	Bar wallHits;

	Button menuButton;
	Button up;
	Button retryButton;
	Button playButton;
	Button pause;
	Button runButton;

	Sprite[] wallHitAnimation;
	Sprite truckLed;
	Sprite truckMiniature;
	Sprite playerHead;
	Sprite fireMiniature;
	Sprite oil;
	Sprite pointer;
	Sprite guiStar;
	Sprite cartoonWater;
	Sprite train;
	Sprite trainMiniature;

	Array<Sprite> bushes;
	Array<Sprite> trees;
	Array<Sprite> skies;
	Array<Sprite> lakes;
	Array<Sprite> grassFlowers;
	Array<Sprite> stars;
	ArrayList<Sprite> footMarks;

	StaticAnimation fireAnimation;
	StaticAnimation fireAnimationBig;
	StaticAnimation fireAnimationBiggest;

	int starsCollectedLastFrame;
	int starsCollected = 0;
	int starsAll = 0;
	int wallHitCounter = 24;
	boolean leftFoot;
	boolean pat = true;
	boolean exit;
	boolean pointersGoUp;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean truckPart;
	boolean eclipsePart;
	boolean playerHoseStarted;
	boolean finishingRun;
	boolean reverseHoseAnimation;
	boolean setWallHitCounter;
	boolean enlargeStar;
	float footmarkSpawnTimer;
	float PositionTimer = 0;
	float spawnWaterTimer;
	float waterRange;
	float jumpAlpha = 0.5f;
	float timerSpeedplayer;
	float truckBackDoorPosition = 108;
	float pointersPosition;
	float playerPositionLastFrame;
	float runningPositionTimer;
	float playerPositionTimer;

	final Starter game;

	public MeetTheTrucksScreen(final Starter gam) {

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

		runButton = new Button(700, -200, assetsManager.runButtonLittle);
		runButton.goUp(250);
		runButton.setAlpha(0.5f);

		up = new Button(10, -200, assetsManager.arrowUp);
		up.goUp(250);
		up.setAlpha(0.75f);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(null,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getMEET_THE_TRUCKS());

		wallHitAnimation = new Sprite[2];
		if (dataOrganizer.getGender() == true) {
			player = new Human();

			player.create(assetsManager.girlRunningSuit, 31, 1, 31, -150, 35);

			playerHead = new Sprite(assetsManager.girlButton);

			playerHoseHydrant = new Human();
			playerHoseHydrant.create(assetsManager.girlHoseHydrant, 1, 7, 7,
					11500, 30);
			playerHoseHydrantReversed = new Human();
			playerHoseHydrantReversed.create(
					assetsManager.girlHoseHydrantReversed, 1, 7, 7, 11500, 30);
			playerHoseHydrantReversed.setAnimationTime(0.1f);

			wallHitAnimation[0] = new Sprite(
					assetsManager.girlHammer_16_percent[0]);
			wallHitAnimation[1] = new Sprite(
					assetsManager.girlHammer_16_percent[1]);

			wallHitAnimation[0].setPosition(12560, 30);
			wallHitAnimation[1].setPosition(12560, 30);

		} else {
			player = new Human();
			player.create(assetsManager.boyRunningSuit, 31, 1, 31, -150, 35);

			playerHead = new Sprite(assetsManager.boyButton);

			playerHoseHydrant = new Human();
			playerHoseHydrant.create(assetsManager.boyHoseHydrant, 1, 7, 7,
					11500, 30);
			playerHoseHydrantReversed = new Human();
			playerHoseHydrantReversed.create(
					assetsManager.boyHoseHydrantReversed, 1, 7, 7, 11500, 30);
			playerHoseHydrantReversed.setAnimationTime(0.1f);

			wallHitAnimation[0] = new Sprite(
					assetsManager.boyHammer_16_percent[0]);
			wallHitAnimation[1] = new Sprite(
					assetsManager.boyHammer_16_percent[1]);
			wallHitAnimation[0].setPosition(12560, 30);
			wallHitAnimation[1].setPosition(12560, 30);

		}

		playerHead.setScale(0.5f);

		player.setMaxSpeed(5);
		player.setAnimationTime(0.035f);

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

		if(dataOrganizer.getGender() == true)
		dialogueWindow = new Dialogue(assetsManager.dialogueWindowGirl,
				assetsManager.darkScreen, 250f, 150f,
				Variables.MEET_THE_TRUCKS_POP_UP_1, assetsManager.fontLittle);
		else
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowBoy,
					assetsManager.darkScreen, 250f, 150f,
					Variables.MEET_THE_TRUCKS_POP_UP_1, assetsManager.fontLittle);
				
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setControlledHuman(player);
		inputInterpreter.setJump(up);
		cloudManager.stop();

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

		assetsManager.hit.scaleEffect(0.333f);

		truckLed = new Sprite(assetsManager.truckLed);

		oil = new Sprite(assetsManager.oil);
		pointer = new Sprite(assetsManager.pointer);

		jump = new FallingText(assetsManager.jumpText, 370, 390);
		rideTruck = new FallingText(assetsManager.rideTruckText, 330, 390);
		eclipseFire = new FallingText(assetsManager.eclipseFireText, 330, 390);
		breakTheWall = new FallingText(assetsManager.breakTheWallText, 320, 390);

		fireAnimation = new StaticAnimation();
		fireAnimation.create(assetsManager.fireBig, 2, 3, 6, 12000, 30,
				MathUtils.random(0.08f, 0.15f));

		fireAnimationBig = new StaticAnimation();
		fireAnimationBig.create(assetsManager.fireBig, 2, 3, 6, 11950, 30,
				MathUtils.random(0.08f, 0.15f));
		fireAnimationBiggest = new StaticAnimation();
		fireAnimationBiggest.create(assetsManager.fireBig, 2, 3, 6, 11970, 50,
				MathUtils.random(0.08f, 0.15f));
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

		playerHoseHydrant.setOnceOnly();
		playerHoseHydrantReversed.setOnceOnly();

		footMarks = new ArrayList<Sprite>();

		fireMiniature = new Sprite(assetsManager.fireMiniature);
		fireMiniature.setScale(0.5f);

		truckMiniature = new Sprite(assetsManager.truckMiniature);
		truckMiniature.setScale(0.5f);

		wallHits = new Bar(assetsManager.barNotFilled, assetsManager.barFilled,
				12490, 290, 20);

		cartoonWater = new Sprite(assetsManager.clouds[0]);
		cartoonWater.setScale(0.05f);
		cartoonWater.setColor(Color.BLUE);

		waterPositions = new ArrayList<Vector2>();
		waterVelocities = new ArrayList<Vector2>();
		bushes = new Array<Sprite>();
		trees = new Array<Sprite>();
		lakes = new Array<Sprite>();
		stars = new Array<Sprite>();
		grassFlowers = new Array<Sprite>();

		for (int a = 0; a < 15; a++) {
			Sprite lake = null;
			lake = new Sprite(assetsManager.lake);
			lake.setPosition((a) * 1000 + MathUtils.random(0, 300),
					MathUtils.random(50, 100));
			lakes.add(lake);
		}
		for (int a = 0; a < 15; a++) {
			Sprite grass = null;
			grass = new Sprite(assetsManager.grassFlowers);
			grass.setPosition((a) * 1000 + MathUtils.random(0, 300),
					MathUtils.random(50, 100));
			grassFlowers.add(grass);
		}
		Sprite star = new Sprite(assetsManager.star);
		star.setPosition(270, 50);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(300, 100);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(350, 140);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(670, 50);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(700, 100);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(750, 140);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(1070, 50);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(1100, 100);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(10758, 50);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(11140, 50);
		stars.add(star);

		star = new Sprite(assetsManager.star);
		star.setPosition(1150, 140);
		stars.add(star);

		for (int a = 0; a < 80; a++) {
			if (a < 15 || (a > 35 && a < 50) || a > 65) {
				Sprite s = null;
				s = new Sprite(assetsManager.star);

				s.setPosition(a * 100 + 2050, 50);
				stars.add(s);
			}
		}

		for (int a = 0; a < 8; a++) {
			Sprite s = null;
			s = new Sprite(assetsManager.star);
			s.setPosition(a * 100 + 12900, 50);
			stars.add(s);
		}

		for (int a = 0; a < 50; a++) {
			boolean randomBoolean = MathUtils.randomBoolean();
			Sprite bush = null;
			if (a % 2 == 0)
				bush = new Sprite(assetsManager.bushes[0]);
			else
				bush = new Sprite(assetsManager.bushes[1]);

			if (randomBoolean == true)
				bush.setPosition((a) * 350 + MathUtils.random(0, 100),
						MathUtils.random(35, 60));
			else
				bush.setPosition((a) * 350 + MathUtils.random(0, 100),
						MathUtils.random(150, 180));

			bushes.add(bush);
		}

		for (int a = 0; a < 50; a++) {

			boolean randomBoolean = MathUtils.randomBoolean();
			Sprite tree = null;
			if (a % 3 == 0)
				tree = new Sprite(assetsManager.trees[2]);
			else if (a % 2 == 0)
				tree = new Sprite(assetsManager.trees[0]);
			else
				tree = new Sprite(assetsManager.trees[1]);

			if (randomBoolean == true)
				tree.setPosition((a) * 600 + MathUtils.random(0, 100),
						MathUtils.random(45, 60));
			else
				tree.setPosition((a) * 600 + MathUtils.random(0, 100),
						MathUtils.random(150, 180));

			trees.add(tree);

		}

		skies = new Array<Sprite>();
		Sprite sky = new Sprite(assetsManager.sky[0]);
		sky.setPosition(0, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[2]);
		sky.setPosition(798, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[4]);
		sky.setPosition(1263, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[1]);
		sky.setPosition(2063, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[3]);
		sky.setPosition(2863, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[5]);
		sky.setPosition(3328, 200);
		skies.add(sky);

		Sprite fireStationComplete = new Sprite(
				assetsManager.fireStationComplete);
		fireStationComplete.setPosition(10000, 35);
		trees.add(fireStationComplete);

		guiStar = new Sprite(assetsManager.star);
		guiStar.setScale(0.75f);
		guiStar.setPosition(0, 430);

		starsAll = game.getCollectedStars();

		train = new Sprite(assetsManager.train);
		train.setPosition(-460, 200);

		trainMiniature = new Sprite(assetsManager.trainMiniature);
		trainMiniature.setScale(0.5f);

		Sprite solarTowers = new Sprite(assetsManager.buildings[0]);
		solarTowers.setPosition(200, 185);
		grassFlowers.add(solarTowers);
		/*
		 * for(int a =0;a<4;a++){ Sprite randomBuilding = new
		 * Sprite(assetsManager.buildings[a+1]); randomBuilding.setPosition(2400
		 * + a*700,45); trees.add(randomBuilding); }
		 * 
		 * Sprite buildingCinema = new Sprite(assetsManager.buildings[5]);
		 * buildingCinema.setPosition(11600, 185);
		 * grassFlowers.add(buildingCinema);
		 * 
		 * Sprite art = new Sprite(assetsManager.buildings[6]);
		 * art.setPosition(8500, 185); grassFlowers.add(art);
		 * 
		 * Sprite monument = new Sprite(assetsManager.buildings[7]);
		 * monument.setPosition(13000, 185); grassFlowers.add(monument);
		 */
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

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawSky(delta);
		batch.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);
		drawFootmarks(delta);
		drawCharacters(delta);
		drawParticlesNonGui(delta);
		drawNonGuiBars(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawParticles(delta);
		drawGuiStarsCounter(delta);
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

		if (player.getX() == 12550 && finishingRun == false) {

			if (runButton.getCounter() % 2 == 0)
				wallHitAnimation[0].draw(batch);
			else
				wallHitAnimation[1].draw(batch);

			assetsManager.hit.setPosition(12640, 135);
		}

		if (player.getX() < 2270 || eclipsePart == true
				&& player.getX() <= 11750
				&& !(player.getX() == 12550 && finishingRun == false))
			player.render(batch, delta);

		if (player.getX() > 1000 && player.getX() < 11000)
			truck.render(batch, delta);

		if (player.getX() > 11750 && reverseHoseAnimation == false) {
			playerHoseHydrant.render(batch, delta);
		}
		playerHoseHydrant.setPosition(11700, 35);

		if (reverseHoseAnimation == true
				&& playerHoseHydrantReversed.getSpeed() != 0) {
			playerHoseHydrantReversed.setPosition(11700, 35);
			playerHoseHydrantReversed.render(batch, delta);
		}
		if (reverseHoseAnimation == true
				&& playerHoseHydrantReversed.getSpeed() == 0
				&& !(player.getX() == 12550 && finishingRun == false)) {
			player.render(batch, delta);
		}

		if (Math.abs(playerHoseHydrant.getSpeed()) < 5f)
			playerHoseHydrant.setSpeed(0);
		if (Math.abs(playerHoseHydrantReversed.getSpeed()) < 5f)
			playerHoseHydrantReversed.setSpeed(0);

		if (player.getX() >= 11750 && playerHoseStarted == false) {
			playerHoseHydrant.setSpeed(6);
			playerHoseStarted = true;
			playerHoseHydrant.setAnimationTime(0.1f);
		}

	}

	void drawButtons(float delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
		up.render(batch, delta);
	}

	void drawTexts(float delta) {
		if (player.getX() > 0) {
			jump.start();
		}
		if (player.getX() > 1300) {
			jump.stop();
			rideTruck.start();
		}
		if (player.getX() > 9500) {
			jump.start();
			rideTruck.stop();
		}
		if (player.getX() > 11400) {
			jump.stop();
			rideTruck.stop();
			eclipseFire.start();
		}
		if (player.getX() > 11800) {
			jump.stop();
			rideTruck.stop();
			eclipseFire.stop();
		}
		if (player.getX() > 11900) {
			breakTheWall.start();
		}
		if (player.getX() > 12650)
			breakTheWall.stop();

		breakTheWall.render(batch, delta);
		eclipseFire.render(batch, delta);
		jump.render(batch, delta);
		rideTruck.render(batch, delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		if (player.getX() >= 12550 && finishingRun == false) {

			if (runButton.getSelection() == true) {
				assetsManager.hit.start();
			} else
				assetsManager.hit.allowCompletion();
			player.setSpeed(1);
			player.setPosition(12550, (int) player.getY());
			if (setWallHitCounter == false) {
				runButton.resetCounter();
				setWallHitCounter = true;
			}
			if (runButton.getCounter() >= 20)
				finishingRun = true;

		}

		if (player.getX() >= 14000)
			player.setPosition(14000, (int) player.getY());

		if (player.getX() > 10510 && player.getX() < 10550
				&& player.getY() == 35) {
			player.setSpeed(player.getSpeed() * 0.3f);
			if (Math.abs(player.getSpeed()) < 1f)
				player.setSpeed(1);
		}
		if (player.getX() > 10860 && player.getX() < 10900
				&& player.getY() == 35) {
			player.setSpeed(player.getSpeed() * 0.3f);

			if (Math.abs(player.getSpeed()) < 1f)
				player.setSpeed(1);
		}
		if (player.getX() > 11210 && player.getX() < 11250
				&& player.getY() == 35) {
			player.setSpeed(player.getSpeed() * 0.3f);

			if (Math.abs(player.getSpeed()) < 1f)
				player.setSpeed(1);
		}

		if (player.getY() == 35) {
			runningPositionTimer += delta;

			if (runningPositionTimer > 1 / player.getSpeed() + 0.2f) {
				assetsManager.running.setPosition(player.getX() + 10,
						player.getY());
				runningPositionTimer = 0;
			} else
				assetsManager.running.setPosition(player.getX() + 90,
						player.getY());

			assetsManager.running.start();
		} else
			assetsManager.running.allowCompletion();

		if (player.getY() != 35 && player.getY() != 110) {
			pat = false;
		} else if (pat == false) {
			assetsManager.hit.setPosition(player.getX() + 55, player.getY());
			assetsManager.hit.start();
			pat = true;
		}
		if (player.getX() < 2270 && truckPart == false) {
			truck.setSpeed(-0.001f);
			truck.setPosition(1550, 35);
		} else if (eclipsePart == false) {
			speedBar.setVisibility(true);
			truckPart = true;
			player.setPosition((int) truck.getX() + 720, 35);
			player.setSpeed(truck.getSpeed());
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
			inputInterpreter.setControlledHuman(player);
			inputInterpreter.setControlledTruck(null);
			inputInterpreter.setRunButtonSecond(null);

			speedBar.setVisibility(false);
			player.setSpeed(5);
			runButton.setDontRespond(false);
		}

		if (eclipsePart == true) {
			truck.setSpeed(-0.001f);
		}

		if (jumpAlpha > 0 && player.getX() >= 1400 && eclipsePart == false) {
			jumpAlpha -= delta;
			if (jumpAlpha < 0)
				jumpAlpha = 0;
			up.setDontRespond(true);
			up.setAlpha(jumpAlpha);
		}

		if (eclipsePart == true && player.getX() < 11650) {
			jumpAlpha += delta;
			if (jumpAlpha > 0.5f)
				jumpAlpha = 0.5f;
			up.setDontRespond(false);
			up.setAlpha(jumpAlpha);
		}

		if (player.getX() >= 11750 && reverseHoseAnimation == false) {

			if (truckBackDoorPosition < 260)
				truckBackDoorPosition += 40 * delta;

			jumpAlpha -= delta;
			if (jumpAlpha < 0f)
				jumpAlpha = 0f;
			up.setDontRespond(true);
			up.setAlpha(jumpAlpha);
		}
		if (reverseHoseAnimation == true) {
			if (truckBackDoorPosition > 108)
				truckBackDoorPosition -= 40 * delta;
			if (truckBackDoorPosition < 108)
				truckBackDoorPosition = 108;
		}

		player.setPosition((int) player.getX(), (int) player.getY()
				+ (int) player.getAccelerationJump());

		player.setAccelerationJump(player.getAccelerationJump() - delta * 50);

		if (player.getAccelerationJump() < -10)
			player.setAccelerationJump(-10);

		// 350,430
		if (player.getX() >= 270 && player.getX() < 280 && player.getY() < 110) {
			player.setPosition(270, (int) player.getY());
		}
		if (player.getX() > 270 && player.getX() < 435) {
			if (player.getY() < 110)
				player.setPosition((int) player.getX(), 110);
		}

		// 750,830
		if (player.getX() >= 670 && player.getX() < 680 && player.getY() < 110) {
			player.setPosition(670, (int) player.getY());
		}
		if (player.getX() > 670 && player.getX() < 835) {
			if (player.getY() < 110)
				player.setPosition((int) player.getX(), 110);
		}
		// 1150,1230
		if (player.getX() >= 1070 && player.getX() < 1080
				&& player.getY() < 110) {
			player.setPosition(1070, (int) player.getY());
		}
		if (player.getX() > 1070 && player.getX() < 1235) {
			if (player.getY() < 110)
				player.setPosition((int) player.getX(), 110);
		}

		if (player.getY() < 35)
			player.setPosition((int) player.getX(), 35);

		if (player.getX() >= 14000 && secondDialogueClicked == false) {
			secondDialogueClicked = true;
			//dialogueWindow.setDialogueText(Variables.MEET_THE_TRUCKS_POP_UP_2);
			int goldenStars = 0;
			if(player.getX() > train.getX())
				goldenStars = 3;
			else
				goldenStars = 2;
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star, assetsManager.starSummary, assetsManager.starSummaryDesaturated, goldenStars, starsCollected,true);
			dialogueWindow.popUp();
			
			assetsManager.stars.start();
			// player.setSpeed(0);
			// player.resetStateTime();
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
		if (player.getX() >= 14000 && exit == false
				&& secondDialogueClicked == true
				&& dialogueWindow.isVisibile() == false) {
			exit = true;
			cloudManager.start();
		}

		truck.setPosition((int) truck.getX(), 35);
		updateCameraLogics(delta);
		updateplayerAction(delta);

	}

	void updateCameraLogics(double delta) {
		if (player.getX() >= 395 && player.getX() < 5750 && truckPart == false) {
			camera.position.x = (player.getX());
		}
		if (truckPart == true) {
			camera.position.x = truck.getX() + 720;
		}
		if (eclipsePart == true && player.getX() <= 13500) {
			camera.position.x = player.getX();
		} else if (eclipsePart == true) {
			camera.position.x = 13500;
		}

	}

	void drawFootmarks(float delta) {

		footmarkSpawnTimer += delta;
		if (footmarkSpawnTimer > 0.1f && player.getY() == 35
				&& Math.abs(player.getSpeed()) > 1f
				&& Math.abs(truck.getSpeed()) < 1) {
			footmarkSpawnTimer = 0;
			Sprite s = new Sprite(assetsManager.foot);
			if (leftFoot == true) {
				s.setPosition(player.getX() + 10 + player.getSpeed(), 37);
				leftFoot = false;
			} else {
				s.setPosition(player.getX() + 10 + player.getSpeed(), 32);
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

		drawTrain(delta);
		drawGrassFlowers(delta);
		drawLakes(delta);
		drawBushes(delta);
		drawTrees(delta);
		drawStars(delta);

		if (player.getX() <= 1200) {

			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
		}
		if (player.getX() <= 2000) {
			batch.draw(assetsManager.parkBackgrounds[1], 789, 0);
		}
		if (player.getX() >= 1200 && player.getX() < 2600) {

			batch.draw(assetsManager.parkBackgrounds[2], 1589, 0);
		}
		if (player.getX() >= 1600 && player.getX() < 3100) {
			batch.draw(assetsManager.parkBackgrounds[5], 2049, 0);

			if (dataOrganizer.getPrompts() == false) {
				pointer.setPosition(2290, pointersPosition + 200);
				pointer.draw(batch);
			}
		}
		if (player.getX() >= 2100 && player.getX() < 4600)
			batch.draw(assetsManager.parkBackgrounds[4], 2514, 0);
		if (player.getX() >= 2600 && player.getX() < 5100)
			batch.draw(assetsManager.parkBackgrounds[3], 3314, 0);

		if (player.getX() >= 3100 && player.getX() < 7100)
			batch.draw(assetsManager.parkBackgrounds[0], 4109, 0);
		if (player.getX() >= 3600 && player.getX() < 7600)
			batch.draw(assetsManager.parkBackgrounds[1], 4908, 0);
		if (player.getX() >= 4100 && player.getX() < 8100)
			batch.draw(assetsManager.parkBackgrounds[2], 5708, 0);

		if (player.getX() >= 4600 && player.getX() < 8600)
			batch.draw(assetsManager.parkBackgrounds[5], 6170, 0);
		if (player.getX() >= 5100 && player.getX() < 9100)
			batch.draw(assetsManager.parkBackgrounds[4], 6635, 0);
		if (player.getX() >= 6600 && player.getX() < 9600)
			batch.draw(assetsManager.parkBackgrounds[3], 7435, 0);

		if (player.getX() >= 7100 && player.getX() < 10100)
			batch.draw(assetsManager.parkBackgrounds[0], 8230, 0);
		if (player.getX() >= 7600 && player.getX() < 10600)
			batch.draw(assetsManager.parkBackgrounds[1], 9010, 0);
		if (player.getX() >= 8100 && player.getX() < 11100)
			batch.draw(assetsManager.parkBackgrounds[2], 9800, 0);

		if (player.getX() >= 9600 && player.getX() < 12100) {
			batch.draw(assetsManager.parkBackgrounds[5], 10260, 0);
		}
		if (player.getX() >= 10100 && player.getX() < 12500)
			batch.draw(assetsManager.parkBackgrounds[4], 10720, 0);

		if (player.getX() >= 10600 && player.getX() < 13000) {
			batch.draw(assetsManager.parkBackgrounds[3], 11520, 0);
			batch.draw(assetsManager.truckBack, 11400, 35);
			batch.draw(assetsManager.truckBackDoor, 11451,
					(int) truckBackDoorPosition - 13);
		}
		if (player.getX() >= 11600)
			batch.draw(assetsManager.parkBackgrounds[0], 12315, 0);
		if (player.getX() >= 11800)
			batch.draw(assetsManager.parkBackgrounds[1], 13113, 0);
		if (player.getX() >= 12000)
			batch.draw(assetsManager.parkBackgrounds[2], 13910, 0);

		if (player.getX() < 1200) {
			batch.draw(assetsManager.barrel, 350, 35);
			batch.draw(assetsManager.barrel, 390, 35);
			batch.draw(assetsManager.barrel, 430, 35);

			batch.draw(assetsManager.barrel, 350, 75);
			batch.draw(assetsManager.barrel, 390, 75);
			batch.draw(assetsManager.barrel, 430, 75);

			batch.draw(assetsManager.sign, 150, 48);
		}
		if (player.getX() < 2000) {
			batch.draw(assetsManager.barrel, 750, 35);
			batch.draw(assetsManager.barrel, 790, 35);
			batch.draw(assetsManager.barrel, 830, 35);

			batch.draw(assetsManager.barrel, 750, 75);
			batch.draw(assetsManager.barrel, 790, 75);
			batch.draw(assetsManager.barrel, 830, 75);

			batch.draw(assetsManager.barrel, 1150, 35);
			batch.draw(assetsManager.barrel, 1190, 35);
			batch.draw(assetsManager.barrel, 1230, 35);

			batch.draw(assetsManager.barrel, 1150, 75);
			batch.draw(assetsManager.barrel, 1190, 75);
			batch.draw(assetsManager.barrel, 1230, 75);
		}

		drawOil();
		drawPointers(delta);

		fireAnimationBiggest.setScale(fireAnimationScales[0]);
		fireAnimationBig.setScale(fireAnimationScales[1]);
		fireAnimation.setScale(fireAnimationScales[2]);

		if (waterRange > 4)
			waterRange = 4;

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

		if (player.getX() > 10600 && player.getX() < 12500) {
			fireAnimationBiggest.render(batch, delta);
			fireAnimationBig.render(batch, delta);
			fireAnimation.render(batch, delta);
		}
		if (player.getX() <= 11750) {
			batch.draw(assetsManager.hoseHydrant, 11700, 38);
		}
		if (reverseHoseAnimation == true
				&& playerHoseHydrantReversed.getSpeed() == 0) {
			batch.draw(assetsManager.hoseHydrant, 11700, 38);
		}

		if (player.getX() > 11750 && truckBackDoorPosition > 140)
			drawWater(delta);

		if (player.getX() > 12000 && finishingRun == false)
			batch.draw(assetsManager.wall[0], 12600, 45);
		if (player.getX() >= 12550) {
			if (runButton.getCounter() > 7 && runButton.getCounter() < 16)
				batch.draw(assetsManager.wall[1], 12600, 45);
			if (runButton.getCounter() > 15)
				batch.draw(assetsManager.wall[2], 12600, 45);
		}

	}

	void updateplayerAction(double delta) {

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

		batch.draw(assetsManager.speedBar, 200, 440);

		playerHead.setPosition(200 + player.getX() * 0.0285f, 410);
		playerHead.draw(batch);

		fireMiniature.setPosition(535, 410);
		fireMiniature.draw(batch);

		trainMiniature.setPosition(200 + train.getX() * 0.0285f, 410);
		trainMiniature.draw(batch);

		if (truckPart == false)
			truckMiniature.setPosition(265, 410);
		else if (eclipsePart == false)
			truckMiniature.setPosition(200 + player.getX() * 0.0285f, 410);

		truckMiniature.draw(batch);

	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(3f);
				camera.reset();
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMEET_THE_TRUCKS()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(3f);
				camera.reset();
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			camera.reset();
			game.setCollectedStars(starsCollected + starsAll);
			assetsManager.hit.scaleEffect(3f);
			game.setScreen(new FoodsScreen(game));
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

		if (dataOrganizer.getPrompts() == false) {
			if (player.getX() < 2000 && dataOrganizer.getPrompts()) {

				pointer.setPosition(390, pointersPosition + 100);
				pointer.draw(batch);
				pointer.setPosition(790, pointersPosition + 100);
				pointer.draw(batch);
				pointer.setPosition(1190, pointersPosition + 100);
				pointer.draw(batch);

			}

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

	}

	void drawWater(float delta) {

		if (fireAnimationScales[2].y < 0.05f
				&& fireAnimationScales[1].y < 0.05f
				&& fireAnimationScales[0].y < 0.05f
				&& reverseHoseAnimation == false) {
			reverseHoseAnimation = true;
			playerHoseHydrantReversed.setSpeed(6);
			player.setSpeed(2.5f);
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

	void drawBushes(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < bushes.size; a++) {

			if ((player.getX() <= 395 && bushes.get(a).getX() < 800)
					|| player.getX() >= 13500
					|| (bushes.get(a).getX() - player.getX() < 400 && bushes
							.get(a).getWidth()
							+ bushes.get(a).getX()
							- player.getX() > -400)) {
				bushes.get(a).draw(batch);

				if (player.getX() >= 395
						&& (playerPositionLastFrame != player.getX())
						&& player.getX() <= 13500 && delta != 0)

					bushes.get(a).setPosition(
							(float) (bushes.get(a).getX() + (bushes.get(a)
									.getY() / 30) * playerSpeed * 0.1f),
							bushes.get(a).getY());
			}
		}
	}

	void drawLakes(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < lakes.size; a++) {

			if ((player.getX() <= 395 && bushes.get(a).getX() < 800)
					|| player.getX() >= 13500
					|| (lakes.get(a).getX() - player.getX() < 400 && lakes.get(
							a).getWidth()
							+ lakes.get(a).getX() - player.getX() > -400)) {
				lakes.get(a).draw(batch);

				if (player.getX() >= 395
						&& (playerPositionLastFrame != player.getX())
						&& player.getX() <= 13500 && delta != 0)

					lakes.get(a)
							.setPosition(
									(float) (lakes.get(a).getX() + (lakes
											.get(a).getY() / 30)
											* playerSpeed
											* 0.1f), lakes.get(a).getY());
			}
		}
	}

	void drawStars(float delta) {
		starsCollected = 0;
		for (int a = 0; a < stars.size; a++) {
			float starAlpha = 1;
			starAlpha = stars.get(a).getColor().a;
			if ((player.getX() <= 395 && stars.get(a).getX() < 800)
					|| player.getX() >= 13500
					|| (stars.get(a).getX() - player.getX() < 400 && stars.get(
							a).getWidth()
							+ stars.get(a).getX() - player.getX() > -400)) {
				stars.get(a).draw(batch);
			}

			if (player.getX() + 50 > stars.get(a).getX()) {
				starsCollected++;
				stars.get(a).setPosition(stars.get(a).getX(),
						stars.get(a).getY() + delta * 430);
				if (starAlpha - delta * 0.2f > 0) {

					stars.get(a).setAlpha(
							stars.get(a).getColor().a - 0.2f * delta);
					stars.get(a).rotate(delta * 350);
				} else {
					stars.get(a).setAlpha(0);
					// stars.removeIndex(a);
				}
			}
		}
		if (starsCollected > starsCollectedLastFrame)
			enlargeStar = true;

		starsCollectedLastFrame = starsCollected;
	}

	void drawGrassFlowers(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < grassFlowers.size; a++) {
			if ((player.getX() <= 395 && bushes.get(a).getX() < 800)
					|| player.getX() >= 13500
					|| (grassFlowers.get(a).getX() - player.getX() < 400 && grassFlowers
							.get(a).getWidth()
							+ grassFlowers.get(a).getX()
							- player.getX() > -400)) {
				grassFlowers.get(a).draw(batch);

				if (player.getX() >= 395
						&& (playerPositionLastFrame != player.getX())
						&& player.getX() <= 13500 && delta != 0)

					grassFlowers.get(a).setPosition(
							(float) (grassFlowers.get(a).getX() + (grassFlowers
									.get(a).getY() / 30) * playerSpeed * 0.1f),
							grassFlowers.get(a).getY());
			}
		}
	}

	void drawTrees(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < trees.size; a++) {

			if ((player.getX() <= 395 && trees.get(a).getX() < 800)
					|| player.getX() >= 13500
					|| (trees.get(a).getX() - player.getX() < 400 && trees.get(
							a).getWidth()
							+ trees.get(a).getX() - player.getX() > -400)) {
				trees.get(a).draw(batch);

				if (player.getX() >= 395
						&& (playerPositionLastFrame != player.getX())
						&& player.getX() <= 13500 && delta != 0)

					trees.get(a)
							.setPosition(
									(float) (trees.get(a).getX() + (trees
											.get(a).getY() / 30)
											* playerSpeed
											* 0.1f), trees.get(a).getY());
			}
		}
	}

	void drawSky(float delta) {
		batch.draw(assetsManager.grass, 0, 35);

		for (int a = 0; a < skies.size; a++) {
			if (skies.get(a).getX() <= 801 && skies.get(a).getX() > -801)
				skies.get(a).draw(batch);
			if (player.getX() > 395 && playerPositionLastFrame != player.getX()
					&& player.getX() <= 135000) {
				if (Math.abs(truck.getSpeed()) < 0.5f)
					skies.get(a).setPosition(
							skies.get(a).getX() - delta * 1.95f
									* player.getSpeed(), skies.get(a).getY());
				else
					skies.get(a).setPosition(
							skies.get(a).getX() + delta * 1.95f
									* truck.getSpeed(), skies.get(a).getY());

			}
		}
		playerPositionTimer += delta;
		if (playerPositionTimer > 0.1f) {
			playerPositionLastFrame = player.getX();
			playerPositionTimer = 0;
		}

	}

	void drawNonGuiBars(float delta) {
		if (player.getX() == 12550)
			wallHits.setVisibility(true);
		if (player.getX() > 12550)
			wallHits.setVisibility(false);
		wallHits.render(batch, delta, runButton.getCounter());
	}

	void drawGuiStarsCounter(float delta) {

		batch.draw(assetsManager.frameCollectibles,10,435);
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

	void drawTrain(float delta) {

		if (player.getX() > 100 && dialogueWindow.isVisibile() == false
				&& train.getX() < 14000)
			train.setPosition(train.getX() + delta * 275, train.getY());

		if (train.getX() - player.getX() < 400
				&& train.getWidth() + train.getX() - player.getX() > -400)

			train.draw(batch);
	}
}