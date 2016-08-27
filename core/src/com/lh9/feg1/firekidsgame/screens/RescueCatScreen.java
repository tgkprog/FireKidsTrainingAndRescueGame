package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
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

public class RescueCatScreen implements Screen {

	int starsAll;
	Sprite cloudsFar;
	Sprite cloudsClose;
	Sprite basket;
	Truck truck;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Bar timeLeftBar;
	Bar counterLeftBar;
	Button menuButton;
	Button retryButton;
	Button playButton;
	Button up;
	Button down;
	Button runLeft;
	Button runRight;
	MenuWindow menuWindow;
	Bar speedBar;
	Human girl;
	Button pause;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	float cloudPositionAdder = 100;
	boolean cloudPositionAdd;

	float timerWin;
	float timerSpeedGirl;
	float greenTimer = 1;
	boolean exit;
	boolean startedClouds;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	float buttonsAlpha;
	float rotation;

	Array<Star> stars;
	Sprite guiStar;
	boolean enlargeStar;
	int starsCollected = 0;
	int starsCollectedLastFrame;

	final Starter game;

	public RescueCatScreen(final Starter gam) {

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

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);
		pause.setAlpha(0.5f);

		assetsManager.stars.setPosition(400, 480);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getCatRescueScreen());

		truck = new Truck();
		truck.create(assetsManager.truckNoBasket, 3, 3, 1, 3000, 350);
		truck.setMaxSpeed(20);
		truck.setMaxPositions(-1000, 3000);
		truck.loadWheel(assetsManager.wheel);
		truck.goLeft();
		truck.setScale(2.2f);
		truck.setSpeedAdder(15);
		truck.setFriction(5);

		up = new Button(10, -100, assetsManager.arrowUp);
		up.goUp(320);
		down = new Button(10, -100, assetsManager.arrowDown);
		down.goUp(210);

		runRight = new Button(685, -200, assetsManager.runButtonLittle);
		runRight.goUp(30);
		runLeft = new Button(35, -200, assetsManager.runButtonLittle);
		runLeft.goUp(30);

		runLeft.setAlpha(0);
		runRight.setAlpha(0);
		up.setAlpha(0);
		down.setAlpha(0);

		runLeft.setDontRespond(true);
		runRight.setDontRespond(true);
		up.setDontRespond(true);
		down.setDontRespond(true);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setControlledHuman(truck);
		inputInterpreter.setControlledTruck(truck);
		inputInterpreter.setRunButton(runLeft);
		inputInterpreter.setRunButtonSecond(runRight);
		inputInterpreter.loadDown(down);
		inputInterpreter.loadUp(up);

		cloudManager.stop();

		camera.reset();

		camera.position.x = 1357;
		camera.position.y = 1220;
		camera.zoom = 1.2f;

		camera.zoom(1.2f, 100);
		camera.moveX(1357, 100, 100, 100);
		camera.moveY(1220, 100, 100, 100);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);

		basket = new Sprite(assetsManager.basket);

		counterLeftBar = new Bar(assetsManager.barFilled,
				assetsManager.barNotFilled, 250, 10, 6);
		timeLeftBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 250, 455, 10);
		timeLeftBar.setVisibility(false);
		counterLeftBar.setVisibility(false);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		cloudsFar = new Sprite(assetsManager.cloudyBackgroundFar);
		cloudsClose = new Sprite(assetsManager.cloudyBackgroundClose);

		stars = new Array<Star>();

		for (int a = 0; a < 10; a++) {
			Star star;
			star = new Star(assetsManager.star, a * 200, 350, 2.5f);
			stars.add(star);
		}

		runLeft.setDontRespond(true);
		runRight.setDontRespond(true);
		
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

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground();
		drawStars(delta);
		drawCharacters(delta);
		drawPointer(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawGuiStarsCounter(delta);
		drawParticles(deltaTemp);
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
		batch.draw(assetsManager.catSad, 1265, 1157);
		basket.setRotation(rotation);
		basket.setScale(2.2f);
		basket.setPosition(truck.getX() + 400 + Math.abs(rotation * 6),
				750 + Math.abs(rotation * 6));
		basket.setColor(greenTimer, 1, greenTimer, 1);
		basket.draw(batch);
		truck.render(batch, delta);
	}

	void drawButtons(float delta) {
		pause.render(batch, delta);
		up.render(batch, delta);
		down.render(batch, delta);
		runLeft.render(batch, delta);
		runRight.render(batch, delta);
		// runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		if (cloudPositionAdd == false) {
			if (cloudPositionAdder < 500)
				cloudPositionAdder += delta * 30;
			if (cloudPositionAdder > 500) {
				cloudPositionAdd = true;
				cloudPositionAdder = 500;
			}
		} else {
			if (cloudPositionAdder > 100)
				cloudPositionAdder -= delta * 30;
			if (cloudPositionAdder < 100) {
				cloudPositionAdd = false;
				cloudPositionAdder = 100;
			}
		}

		if (finish == true && buttonsAlpha == 0
				&& secondDialogueClicked == false) {
			secondDialogueClicked = true;
			dialogueWindow.popUp();
		}
		if (finish == true && buttonsAlpha == 0
				&& secondDialogueClicked == true
				&& dialogueWindow.isVisibile() == false
				&& startedClouds == false) {
			cloudManager.start();
			startedClouds = true;
		}

		if (finish == true && buttonsAlpha > 0) {
			buttonsAlpha -= delta;
			if (buttonsAlpha < 0)
				buttonsAlpha = 0;
			up.setAlpha(buttonsAlpha);
			down.setAlpha(buttonsAlpha);
			runLeft.setAlpha(buttonsAlpha);
			runRight.setAlpha(buttonsAlpha);
			pause.setAlpha(buttonsAlpha);
			counterLeftBar.setVisibility(false);
			timeLeftBar.setVisibility(false);
		}

		if (timerWin >= 6) {
			finish = true;
			assetsManager.stars.start();
			runLeft.setDontRespond(true);
			runRight.setDontRespond(true);
			up.setDontRespond(true);
			down.setDontRespond(true);
			pause.setDontRespond(true);
		}
		checkCollisions(delta);

		if (up.getSelection() == true) {
			rotation -= delta * 60;
		}
		if (down.getSelection() == true) {
			rotation += delta * 60;
		}

		if (rotation < -50) {
			rotation = -50;
		}
		if (rotation < 0)
			rotation += delta * 10;
		if (rotation > 0)
			rotation = 0;

		truck.setPosition((int) truck.getX(), 600);

		updateCameraLogics(delta);
		
		if (firstDialogueClicked == true && buttonsAlpha < 0.5f
				&& camera.zoom >= 3f && finish == false) {
			buttonsAlpha += delta;

			if (buttonsAlpha > 0.5f)
				buttonsAlpha = 0.5f;

			up.setAlpha(buttonsAlpha);
			down.setAlpha(buttonsAlpha);
			runLeft.setAlpha(buttonsAlpha);
			runRight.setAlpha(buttonsAlpha);

			pause.setDontRespond(false);
			up.setDontRespond(false);
			down.setDontRespond(false);
			runLeft.setDontRespond(false);
			runRight.setDontRespond(false);
			timeLeftBar.setVisibility(true);
			counterLeftBar.setVisibility(true);
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			truck.setSpeed(16);
			camera.reset();
			camera.zoom(3.1f, 3.5f);
			camera.moveX(1235, 2, 2, 10);
			camera.moveY(755, 2, 2, 10);
			firstDialogueClicked = true;
		}
	}

	void updateCameraLogics(double delta) {

	}

	void drawBackground() {
		batch.draw(assetsManager.rescueCatBackground[0], 0, 765);
		batch.draw(assetsManager.rescueCatBackground[1], 1275, 765);
		batch.draw(assetsManager.rescueCatBackground[2], 0, 0);
		batch.draw(assetsManager.rescueCatBackground[3], 1275, 0);
	}

	void drawParticlesNonGui(float delta) {
		assetsManager.hit.draw(batch, delta);
	}

	void drawParticles(float delta) {
		assetsManager.stars.draw(batch, delta);
	}

	void drawPointer(float delta) {
	}

	void drawBars(float delta) {
		// speedBar.render(batch, delta, boy.getSpeed());
		timeLeftBar.render(batch, delta, 2);
		counterLeftBar.render(batch, delta, timerWin);
	}

	void manageSelectingScreen() {
		if (finish == true && buttonsAlpha == 0
				&& dialogueWindow.isVisibile() == false) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreenPlayed(4);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getCatRescueScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new RescueCatScreen(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void checkCollisions(float delta) {
		if (rotation < -21 && rotation > -29 && truck.getX() > 815
				&& truck.getX() < 945 && finish == false) {
			timerWin += delta;
			if (greenTimer > 0)
				greenTimer -= delta;
			if (greenTimer < 0)
				greenTimer = 0;

		} else {
			timerWin -= delta * 3;
			if (timerWin < 0)
				timerWin = 0;

			if (greenTimer < 1)
				greenTimer += delta;
			if (greenTimer > 1)
				greenTimer = 1;
		}
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

	void drawStars(float delta) {

		for (int a = 0; a < stars.size; a++) {

			stars.get(a).draw(batch, delta);

			if ((truck.getX() - 450 < stars.get(a).getX())) {

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
}