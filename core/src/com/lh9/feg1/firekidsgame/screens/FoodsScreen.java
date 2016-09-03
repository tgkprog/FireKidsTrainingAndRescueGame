package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class FoodsScreen implements Screen {

	Sprite guiStar;
	Sprite currentFood;
	Sprite windowCounter;

	Button menuButton;
	Button retryButton;
	Button playButton;
	Button pause;
	Button yes;
	Button no;

	Bar timeBar;
	Bar counterBar;

	Arrow playerHead;
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

	static final int healthyFood[] = { 0, 1, 4, 7 };
	float timerSpeedGirl;
	float timeBarTimer;
	float totalSpentTime;
	int minigameCounter = 10;
	int currentFoodID;
	int starsCollectedLastFrame;
	int starsCollected;
	int starsAll;
	boolean enlargeStar;
	boolean minigameRunning;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean clicked;
	boolean victory;
	boolean checkedAnswer = true;

	final Starter game;

	public FoodsScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		pause = new Button((int) variables.getPAUSE_BUTTON_POSITION().x, 120,
				assetsManager.pause);
		pause.goUp((int) variables.getPAUSE_BUTTON_POSITION().y);

		yes = new Button(640, -200, assetsManager.runButtonGreen);
		yes.goUp(30);
		yes.setAlpha(1);

		no = new Button(10, -200, assetsManager.runButton);
		no.goUp(30);
		no.setAlpha(1);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		if (dataOrganizer.getGender() == false) {
			playerHead = new Arrow(875, 620, assetsManager.boyHeadBig, -0.7f,
					0.7f);
			playerHead.setAlpha(1);
			playerHead.setScale(0.65f);
		} else {
			playerHead = new Arrow(810, 400, assetsManager.girlHeadBig, -0.7f,
					0.7f);
			playerHead.setAlpha(1);
			playerHead.setScale(0.65f);

		}
		if (dataOrganizer.getGender() == true) {
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowGirl,
					assetsManager.darkScreen, 250f, 150f,
					Variables.FOODS_POP_UP_1, assetsManager.fontLittle);
		} else {
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowBoy,
					assetsManager.darkScreen, 250f, 150f,
					Variables.FOODS_POP_UP_1, assetsManager.fontLittle);
		}
		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(null, assetsManager.darkScreen, 250, 200,
				menuButton, retryButton, playButton,
				variables.getFOODS_SCREEN());

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(no);
		inputInterpreter.setRunButtonSecond(yes);
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
		assetsManager.click.play();
		assetsManager.stars.setPosition(400, 480);
		// assetsManager.leaf.setPosition(-100, 200);
		// Not using this

		windowCounter = new Sprite(assetsManager.longButton);
		windowCounter.setScale(0);

		timeBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 3.5f);
		timeBar.setVisibility(false);
		timeBarTimer = 3.5f;

		counterBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 260, 450, 10);
		counterBar.setVisibility(false);
		currentFood = new Sprite(assetsManager.food[0]);
		currentFood.setPosition(5000, 5000);

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

		drawBackground(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawGuiStarsCounter(delta);
		drawFood(delta);
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

	void drawButtons(float delta) {
		pause.render(batch, (float) delta);
		yes.render(batch, delta);
		no.render(batch, delta);
	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {

		if(dialogueWindow.isVisibile() == false && menuWindow.isVisibile() == false)
		totalSpentTime += delta;
		
		if (timeBarTimer <= 0) {
			victory = false;
			minigameCounter = 0;
			minigameRunning = false;
			finish = true;
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star,
					assetsManager.starSummary,
					assetsManager.starSummaryDesaturated, 0, starsCollected,
					false);
			dialogueWindow.popUp();
			assetsManager.click.play();
		}

		if (firstDialogueClicked == true) {
			counterBar.setVisibility(true);
			timeBar.setVisibility(true);
		}

		randomizeMinigame(delta);

		if (menuWindow.isVisibile() == true) {
			yes.setDontRespond(true);
			no.setDontRespond(true);

		} else {
			yes.setDontRespond(false);
			no.setDontRespond(false);
		}

		if (minigameCounter == 0 && finish == false) {
			finish = true;
			
			int goldenStars = 1;
			
			if(totalSpentTime < 20)
				goldenStars = 2;
			if(totalSpentTime < 15)
				goldenStars = 3;
			
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star,
					assetsManager.starSummary,
					assetsManager.starSummaryDesaturated, goldenStars, starsCollected,
					true);
			dialogueWindow.popUp();
			assetsManager.stars.start();
			victory = true;
			assetsManager.click.play();
		}

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}
		if (finish == true) {
			timeBar.setVisibility(false);
			counterBar.setVisibility(false);
		}
		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}
		updateCameraLogics(delta);
	}

	void updateCameraLogics(double delta) {
	}

	void drawBackground(float delta) {

		batch.draw(assetsManager.foodBackground[0], 0, 720);
		batch.draw(assetsManager.foodBackground[1], 1275, 720);
		batch.draw(assetsManager.foodBackground[3], 0, 0);
		batch.draw(assetsManager.foodBackground[2], 1275, 0);
		batch.draw(assetsManager.boyTorso, 920, 510);

		batch.draw(assetsManager.food[0], 2360, 600);
		batch.draw(assetsManager.food[1], 2170, 600);
		batch.draw(assetsManager.food[4], 2100, 600);
		batch.draw(assetsManager.food[7], 2160, 600);

		batch.draw(assetsManager.food[2], 320, 600);
		batch.draw(assetsManager.food[3], 260, 600);
		batch.draw(assetsManager.food[5], 90, 600);
		batch.draw(assetsManager.food[6], 320, 650);

		playerHead.render(batch, delta);
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
		assetsManager.hit.draw(batch, delta);
		assetsManager.hit.allowCompletion();

		if (no.getSelection() == true && minigameRunning == true
				&& currentFood.getX() < 330 && clicked == false) {
			assetsManager.hit.setPosition(90, 110);
			assetsManager.hit.start();
			clicked = true;
			timeBarTimer = 3.5f;
			if (checkAnswer() == true)
				starsCollected++;
			else {
				minigameCounter = 0;
				minigameRunning = false;
				finish = true;
				victory = false;
				dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star,
						assetsManager.starSummary,
						assetsManager.starSummaryDesaturated, 0,
						starsCollected, false);
				dialogueWindow.popUp();
				assetsManager.click.play();
			}
		}
		if (yes.getSelection() == true && minigameRunning == true
				&& currentFood.getX() < 330 && clicked == false) {
			assetsManager.hit.setPosition(700, 110);
			assetsManager.hit.start();
			clicked = true;
			timeBarTimer = 3.5f;

			if (checkAnswer() == true)
				starsCollected++;
			else {
				minigameCounter = 0;
				minigameRunning = false;
				finish = true;
				victory = false;
				dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star,
						assetsManager.starSummary,
						assetsManager.starSummaryDesaturated, 0,
						starsCollected, false);
				dialogueWindow.popUp();
				assetsManager.click.play();
			}
		}
		// assetsManager.leaf.update(delta);
		// assetsManager.leaf.draw(batch);
		// Not using this
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawBars(float delta) {
		counterBar.render(batch, delta, minigameCounter);
		timeBar.render(batch, delta, timeBarTimer);
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFOODS_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new FoodsScreen(game));
			}
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			if (victory == true) {
				game.setScreenPlayed(0);
				game.setCogs(game.getCogs() + 1);
				game.setScreen(new MenuScreen(game));
			} else
				game.setScreen(new FoodsScreen(game));

			game.setCollectedStars(starsCollected + starsAll);

		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void randomizeMinigame(float delta) {
		if (firstDialogueClicked == true && minigameCounter > 0
				&& minigameRunning == false && checkedAnswer == true) {
			checkedAnswer = false;
			currentFoodID = MathUtils.random(0, 7);
			yes.setDontRespond(false);
			no.setDontRespond(false);
			currentFood = new Sprite(assetsManager.food[currentFoodID]);
			currentFood.setPosition(805, 50);
			minigameRunning = true;
		}
	}

	void drawFood(float delta) {

		if (minigameRunning == true)
			if (timeBarTimer > 0)
				timeBarTimer -= delta;
		if (timeBarTimer < 0)
			timeBarTimer = 0;

		if (currentFood.getX() > 320 && minigameRunning == true
				&& clicked == false)
			currentFood.setPosition(
					currentFood.getX() - delta * (currentFood.getX() - 319)
							* 10, currentFood.getY());
		if (currentFood.getX() < 320 && minigameRunning == true
				&& clicked == false) {
			currentFood.setPosition(320, currentFood.getY());
		}
		if (clicked == true) {
			if (currentFood.getX() > -2000 && minigameRunning == true)
				currentFood.setPosition(
						currentFood.getX() - 5
								* Math.abs(delta * (350 - currentFood.getX())),
						currentFood.getY());
			yes.setDontRespond(true);
			no.setDontRespond(true);
		}
		currentFood.draw(batch);

		if (currentFood.getX() < -500) {
			minigameRunning = false;
			clicked = false;
			minigameCounter--;
		}
	}

	boolean checkAnswer() {
		checkedAnswer = true;

		if (clicked == true) {
			if (no.getSelection() == true) {
				for (int a = 0; a < healthyFood.length; a++) {
					if (currentFoodID == healthyFood[a]) {
						return false;
					}
				}
				return true;
			} else if (yes.getSelection() == true) {
				for (int a = 0; a < healthyFood.length; a++) {
					if (currentFoodID == healthyFood[a]) {

						return true;
					}
				}
				return false;
			}
		}
		return false;
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
}