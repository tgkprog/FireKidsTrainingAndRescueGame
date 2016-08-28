package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class FitnessScreenTwo implements Screen {

	Sprite guiStar;
	Sprite windowCounter;
	
	Human player;
	Human npc;
	
	Button menuButton;
	Button retryButton;
	Button playButton;
	Button pause;
	Button runButton;
	
	Bar playerBar;
	Bar npcBar;
	
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

	float timerSpeednpc;
	int starsAll;
	int starsCollected;
	boolean enlargeStar;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;

	final Starter game;

	public FitnessScreenTwo(final Starter gam) {

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

		runButton = new Button(685, -200, assetsManager.runButtonLittle);
		runButton.goUp(30);
		runButton.setAlpha(0.5f);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		player = new Human();
		npc = new Human();
		
		if (dataOrganizer.getGender() == false) {
			player.create(assetsManager.spritesheetBoyWeights, 2, 2, 3, 1200, 50);
			npc.create(assetsManager.spritesheetGirlWeights, 2, 2, 3, 300, 50);
		} else {
			player.create(assetsManager.spritesheetGirlWeights, 2, 2, 3, 1200, 50);
			npc.create(assetsManager.spritesheetBoyWeights, 2, 2, 3, 300, 50);
		}
		
		player.setMaxSpeed(1.6f);
		npc.setMaxSpeed(1.6f);
		player.setAnimationOnly(true);
		npc.setAnimationOnly(true);
		player.setSpeedAdder(0.25f);
		npc.setSpeedAdder(0.25f);
		npc.setFriction(1.35f);
		player.setFriction(1.35f);

		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getFitnessScreenTwo());

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setControlledHuman(player);
		inputInterpreter.setMenuWindow(menuWindow);

		cloudManager.stop();

		camera.reset();

		camera.zoom = 3.0f;
		camera.position.x = 1275;
		camera.position.y = 720;

		camera.zoom(3.0f, 100);
		camera.moveX(1275, 100, 100, 100);
		camera.moveY(720, 100, 100, 100);

		dialogueWindow.popUp();

		assetsManager.stars.setPosition(400, 480);
		// assetsManager.leaf.setPosition(-100, 200);
		// Not using this

		windowCounter = new Sprite(assetsManager.longButton);
		windowCounter.setScale(0);

		playerBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				340, 430, 60);
		npcBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				10, 430, 60);
		playerBar.setVisibility(true);
		npcBar.setVisibility(true);

		guiStar = new Sprite(assetsManager.star);
		guiStar.setScale(0.75f);
		guiStar.setPosition(0, 380);

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
		drawCharacters(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawGuiStarsCounter(delta);
		drawParticles(delta);
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
		player.render(batch, delta);
		npc.render(batch, delta);
	}

	void drawButtons(double delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
	}

	void drawWindows(float delta) {

		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {

		if (menuWindow.isVisibile() == true) {
			runButton.setDontRespond(true);
		} else {
			runButton.setDontRespond(false);
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}
		if (player.getCounter() == 60 && finish == false) {
			dialogueWindow.popUp();
			runButton.setDontRespond(true);
			finish = true;
			assetsManager.stars.start();
		}
		if (npc.getCounter() == 60 && finish == false) {
			dialogueWindow.popUp();
			finish = true;
			runButton.setDontRespond(true);
		}
		if (finish == true) {
			npc.setSpeed(0);
			player.setSpeed(0);
			playerBar.setVisibility(false);
			npcBar.setVisibility(false);
		}
		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setCollectedStars(starsCollected + starsAll);
			game.setScreen(new FitnessScreenThree(game));
		}
		updateCameraLogics(delta);
		updatenpcAction(delta);

	}

	void updateCameraLogics(double delta) {
	}

	void drawBackground() {
		batch.draw(assetsManager.fitnessBackground[0], 0, 765);
		batch.draw(assetsManager.fitnessBackground[1], 1275, 765);
		batch.draw(assetsManager.fitnessBackground[2], 0, 0);
		batch.draw(assetsManager.fitnessBackground[3], 1275, 0);
	}

	void updatenpcAction(double delta) {
		if (npc.getCounter() < player.getCounter() - 3)
			timerSpeednpc += delta * 6;
		if (firstDialogueClicked == true)
			timerSpeednpc += delta;
		if (timerSpeednpc > 0.18) {
			timerSpeednpc = 0;
			if (firstDialogueClicked == true && finish == false)
				npc.move();
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);

		// assetsManager.leaf.update(delta);
		// assetsManager.leaf.draw(batch);
		// Not using this
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawBars(float delta) {
		starsCollected = player.getCounter();
		playerBar.render(batch, delta, player.getCounter());
		npcBar.render(batch, delta, npc.getCounter());
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFitnessScreenTwo()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new FitnessScreenTwo(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
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
		assetsManager.fontLittle.draw(batch,
				Integer.toString(starsCollected + starsAll), 60, 413);
	}
}