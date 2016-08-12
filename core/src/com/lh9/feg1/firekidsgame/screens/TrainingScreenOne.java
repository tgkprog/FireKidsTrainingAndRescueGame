package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.Bar;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.graphics.LaneManager;
import com.lh9.feg1.firekidsgame.graphics.LedManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;
import com.lh9.feg1.firekidsgame.windows.Dialogue;
import com.lh9.feg1.firekidsgame.windows.MenuWindow;

public class TrainingScreenOne implements Screen {

	static final Vector2 yellowSectionMiddlePosition = new Vector2(355, 220);
	static final Vector2 yellowSectionLeftPosition = new Vector2(5, 240);
	static final Vector2 yellowSectionUpLeftPosition = new Vector2(0, 390);
	static final Vector2 yellowSectionUpRightPosition = new Vector2(770, 390);
	boolean[] yellowButtons;
	boolean minigameRunning;
	boolean afterMinigameWindow;
	boolean drawTime;
	boolean goRed;
	boolean thirdDialogueClicked;
	boolean finish;
	boolean sirene;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	float timerSpeedGirl;
	float timerFrontTruck;
	float endingPointerScale;
	float shakeScreenTimer;
	float timerSirene;
	float timerSecondWindow;
	float minigameTimeLeft = 2f;
	int minigameCounter = 30;

	Sprite windowCounter;
	Sprite yellowSectionMiddlePointer;
	Sprite yellowSectionLeftPointer;
	Sprite yellowSectionUpLeftPointer;
	Sprite yellowSectionUpRightPointer;
	Sprite glassSprite;
	Button yellowSectionMiddle;
	Button yellowSectionLeft;
	Sprite endingPointer;
	Button yellowSectionUpLeft;
	Button yellowSectionUpRight;
	Vector3 normalColor;
	Vector3 redColor;
	StaticAnimation hand;
	LedManager middleLeds;
	LaneManager laneManager;
	Human boy;
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
	Arrow firstArrow;
	Arrow secondArrow;
	Arrow boyHead;
	Arrow girlHead;
	Arrow handwheel;
	Arrow handwheelCockpit;
	Arrow boyHeadCockpit;
	Arrow girlHeadCockpit;
	Arrow girlHandCockpit;
	Arrow truckFrontHand;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Bar timeLeftBar;
	Bar counterLeftBar;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;

	final Starter game;

	public TrainingScreenOne(final Starter gam) {

		this.game = gam;

		normalColor = new Vector3(1, 1, 1);
		redColor = new Vector3(1, 0, 0);

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		firstArrow = new Arrow(440, 790, assetsManager.arrow, 90, 120);
		secondArrow = new Arrow(705, 780, assetsManager.arrow, 20, 120);
		firstArrow.setAlpha(0);
		secondArrow.setAlpha(0);

		handwheel = new Arrow(1490, 200, assetsManager.handwheelBig, -13f, 2f);
		handwheel.setAlpha(1);
		handwheel.setScale(0.8f);

		handwheelCockpit = new Arrow(50, -300, assetsManager.handwheelNoHand,
				-10f, 5f);
		handwheelCockpit.setAlpha(1);
		handwheelCockpit.setScale(0.8f);
		handwheelCockpit.setAlpha(0);

		boyHeadCockpit = new Arrow(1650, -100, assetsManager.boyHeadCockpit,
				-0.3f, 0.3f);
		boyHeadCockpit.setAlpha(1);
		boyHeadCockpit.setScale(1f);
		boyHeadCockpit.setAlpha(0);

		girlHeadCockpit = new Arrow(100, -100, assetsManager.girlHeadCockpit,
				-0.3f, 0.3f);
		girlHeadCockpit.setAlpha(1);
		girlHeadCockpit.setScale(1f);
		girlHeadCockpit.setAlpha(0);

		girlHandCockpit = new Arrow(100, -100, assetsManager.girlHandCockpit,
				-0.3f, 1.5f);
		girlHandCockpit.setAlpha(1);
		girlHandCockpit.setScale(1f);
		girlHandCockpit.setAlpha(0);

		truckFrontHand = new Arrow(205, 520, assetsManager.handTruckFront,
				-0.5f, 0.5f);
		truckFrontHand.setAlpha(1);
		truckFrontHand.setScale(0.8f);

		boyHead = new Arrow(280, 675, assetsManager.boyHeadBig, -0.7f, 0.7f);
		boyHead.setAlpha(1);
		boyHead.setScale(0.8f);
		girlHead = new Arrow(1490, 420, assetsManager.girlHeadBig, -0.5f, 0.5f);
		girlHead.setAlpha(1);
		girlHead.setScale(0.8f);
		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(400);

		runButton = new Button(390, -100, assetsManager.runButtonLittle);
		runButton.goUp(110);
		runButton.setDontRespond(true);
		runButton.setAlpha(0);

		yellowSectionMiddle = new Button((int) yellowSectionMiddlePosition.x,
				-100, assetsManager.yellowSectionMiddle);
		yellowSectionMiddle.goUp((int) yellowSectionMiddlePosition.y);

		yellowSectionLeft = new Button((int) yellowSectionLeftPosition.x, -100,
				assetsManager.yellowSectionLeft);
		yellowSectionLeft.goUp((int) yellowSectionLeftPosition.y);

		yellowSectionUpLeft = new Button((int) yellowSectionUpLeftPosition.x,
				-100, assetsManager.yellowSectionUp);
		yellowSectionUpLeft.goUp((int) yellowSectionUpLeftPosition.y);

		yellowSectionUpRight = new Button((int) yellowSectionUpRightPosition.x,
				-100, assetsManager.yellowSectionUp);
		yellowSectionUpRight.goUp((int) yellowSectionUpRightPosition.y);

		yellowSectionMiddle.setAlpha(0);
		yellowSectionLeft.setAlpha(0);
		yellowSectionUpLeft.setAlpha(0);
		yellowSectionUpRight.setAlpha(0);

		boy = new Human();
		boy.create(assetsManager.spritesheetBoyElliptical, 4, 2, 7, 400, 50);
		girl = new Human();
		girl.create(assetsManager.spritesheetGirlElliptical, 4, 2, 7, 100, 50);
		boy.setMaxSpeed(4f);
		girl.setMaxSpeed(4f);
		boy.setAnimationOnly(true);
		girl.setAnimationOnly(true);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getTrainingScreen());

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		// inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setControlledHuman(boy);
		inputInterpreter.setMenuWindow(menuWindow);
		inputInterpreter.setYellowSectionButtons(yellowSectionMiddle,
				yellowSectionLeft, yellowSectionUpLeft, yellowSectionUpRight);

		cloudManager.stop();

		camera.position.x = 1270;
		camera.position.y = 765;
		camera.zoom = 3.175f;

		camera.moveX(1270, 0, 0, 100);
		camera.moveY(765, 0, 0, 100);
		camera.zoom(3.175f, 100f);

		dialogueWindow.popUp();

		laneManager = new LaneManager(assetsManager.lane, 1320, 1450);
		laneManager.setAlpha(1);
		middleLeds = new LedManager(assetsManager.ledCockpit);
		middleLeds.setAlpha(1);

		hand = new StaticAnimation();
		hand.create(assetsManager.handSpritesheet, 2, 2, 3, 1000, -5, 0.1f);
		hand.setContinous(false);

		windowCounter = new Sprite(assetsManager.longButton);
		windowCounter.setScale(0);

		glassSprite = new Sprite(assetsManager.glass);
		glassSprite.setScale(2f, 1.5f);
		glassSprite.setPosition(630, 565);

		yellowSectionMiddlePointer = new Sprite(assetsManager.pointer);
		yellowSectionLeftPointer = new Sprite(assetsManager.pointer);
		yellowSectionUpLeftPointer = new Sprite(assetsManager.pointer);
		yellowSectionUpRightPointer = new Sprite(assetsManager.pointer);
		yellowSectionUpLeftPointer.setRotation(270);
		yellowSectionUpRightPointer.setRotation(90);

		yellowSectionMiddlePointer.setPosition(410, 270);
		yellowSectionLeftPointer.setPosition(35, 310);
		yellowSectionUpLeftPointer.setPosition(60, 385);
		yellowSectionUpRightPointer.setPosition(700, 385);

		yellowSectionMiddlePointer.setAlpha(0);
		yellowSectionLeftPointer.setAlpha(0);
		yellowSectionUpLeftPointer.setAlpha(0);
		yellowSectionUpRightPointer.setAlpha(0);

		yellowSectionMiddlePointer.setScale(0);
		yellowSectionLeftPointer.setScale(0);
		yellowSectionUpLeftPointer.setScale(0);
		yellowSectionUpRightPointer.setScale(0);

		endingPointer = new Sprite(assetsManager.pointer);
		endingPointer.setScale(0);
		endingPointer.setPosition(410, 210);
		yellowButtons = new boolean[4];

		timeLeftBar = new Bar(assetsManager.barFilled,
				assetsManager.barNotFilled, 250, 450, minigameTimeLeft);

		counterLeftBar = new Bar(assetsManager.barFilledBlue,
				assetsManager.barNotFilledBlue, 250, 425, minigameCounter);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
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
		drawCharacters(delta);
		drawArrows(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawButtons(deltaTemp);
		drawParticles(delta);
		drawWindows(deltaTemp);
		drawTime(delta);
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
	}

	void drawButtons(float delta) {

		// pause.render(batch, (float) delta);
		// Not rendering because it's not worth to have a pause in this screen

		runButton.render(batch, delta);
		yellowSectionMiddle.render(batch, delta);
		yellowSectionLeft.render(batch, delta);
		yellowSectionUpLeft.render(batch, delta);
		yellowSectionUpRight.render(batch, delta);

		yellowSectionMiddlePointer.draw(batch);
		yellowSectionLeftPointer.draw(batch);
		yellowSectionUpLeftPointer.draw(batch);
		yellowSectionUpRightPointer.draw(batch);

		endingPointer.setScale(endingPointerScale);
		endingPointer.draw(batch);

	}

	void drawWindows(float delta) {
		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void drawParticles(float delta) {
		assetsManager.hit.draw(batch, delta);
	}

	void updateLogics(float delta) {

		managePointers(delta);

		if (afterMinigameWindow == true && dialogueWindow.isVisibile() == false) {
			runButton.setDontRespond(false);
			if(endingPointerScale < 1)
			endingPointerScale += 4*delta;
			if(endingPointerScale > 1)
				endingPointerScale = 1;
			
		}

		if (secondDialogueClicked == true
				&& dialogueWindow.isVisibile() == false) {
			minigameTimeLeft -= delta;
			randomizeMinigame();
			drawTime = true;
		}

		if (firstDialogueClicked == true)
			timerFrontTruck += delta;

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
		}
		updateCameraLogics(delta);

		if (firstDialogueClicked == true) {
			if (runButton.getSelection() == true && sirene == false) {
				sirene = true;
			}
		}
		if (sirene == true) {
			timerSecondWindow += delta;
		}
		if (timerSecondWindow > 2 && thirdDialogueClicked == false) {
			dialogueWindow.popUp();
			thirdDialogueClicked = true;
		}
		if (sirene == true && timerSecondWindow > 2
				&& dialogueWindow.isVisibile() == false && finish == false) {
			cloudManager.start();
			finish = true;
		}
		if (cloudManager.getAllScalesEqualOne() == true && sirene == true) {
			game.setScreen(new TrainingScreenTwo(game));
		}
	}

	void updateCameraLogics(double delta) {
		shakeScreenTimer += delta;
		if (shakeScreenTimer > 0.5f) {
			shakeScreenTimer = 0;
			// camera.shakeScreen();
			// It's annoying
		}
	}

	void drawBackground(float delta) {
		if (sirene == true) {
			if (goRed == true) {
				batch.setColor(1, 0, 0, 1);
				laneManager.red();
				hand.red();
				firstArrow.red();
				secondArrow.red();
				middleLeds.red();
				handwheelCockpit.red();
				boyHeadCockpit.red();
				girlHeadCockpit.red();
				girlHandCockpit.red();
				yellowSectionMiddle.red();
				yellowSectionLeft.red();
				yellowSectionUpLeft.red();
				yellowSectionUpRight.red();
			} else {
				batch.setColor(1, 1, 1, 1);
				laneManager.normal();
				firstArrow.normal();
				secondArrow.normal();
				middleLeds.normal();
				handwheel.normal();
				boyHeadCockpit.normal();
				girlHeadCockpit.normal();
				girlHandCockpit.normal();
				hand.normal();
				yellowSectionMiddle.normal();
				yellowSectionLeft.normal();
				yellowSectionUpLeft.normal();
				yellowSectionUpRight.normal();
			}

			if (goRed == true) {
				timerSirene += delta;
				if (timerSirene > 0.5f) {
					timerSirene = 0;
					goRed = false;
				}
			} else {
				timerSirene += delta;
				if (timerSirene > 0.5f) {
					timerSirene = 0;
					goRed = true;
				}
			}

		}

		if (timerFrontTruck >= 1.5f) {
			batch.draw(assetsManager.truckCockpit[0], 0, 765);
			batch.draw(assetsManager.truckCockpit[1], 1275, 765);
			batch.draw(assetsManager.truckCockpit[2], 0, 0);
			batch.draw(assetsManager.truckCockpit[3], 1275, 0);
			laneManager.render(batch, delta);
			batch.draw(assetsManager.cockpitPart, 1100, 0);
			middleLeds.render(batch, delta);
			if (sirene == true) {
				hand.render(batch, delta);
				hand.start();
			}
			if (timerFrontTruck - 1.5f <= 1.0f) {
				handwheelCockpit.setAlpha((float) timerFrontTruck - 1.5f);
				boyHeadCockpit.setAlpha((float) timerFrontTruck - 1.5f);
				girlHeadCockpit.setAlpha((float) timerFrontTruck - 1.5f);
				girlHandCockpit.setAlpha((float) timerFrontTruck - 1.5f);
				firstArrow.setAlpha((float) timerFrontTruck - 1.5f);
				secondArrow.setAlpha((float) timerFrontTruck - 1.5f);
				laneManager.setAlpha((float) timerFrontTruck - 1.5f);
				middleLeds.setAlpha((float) timerFrontTruck - 1.5f);
				runButton.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionMiddle.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionLeft.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionUpLeft.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionUpRight.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionMiddlePointer
						.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionLeftPointer
						.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionUpLeftPointer
						.setAlpha((float) timerFrontTruck - 1.5f);
				yellowSectionUpRightPointer
						.setAlpha((float) timerFrontTruck - 1.5f);
			}
			if (timerFrontTruck > 2.5f && secondDialogueClicked == false) {
				dialogueWindow.popUp();
				secondDialogueClicked = true;
			}
		}
		if (timerFrontTruck <= 1.5f) {
			batch.draw(assetsManager.truckFront[0], 0, 765);
			batch.draw(assetsManager.truckFront[1], 1275, 765);
			batch.draw(assetsManager.truckFront[2], 0, 0);
			batch.draw(assetsManager.truckFront[3], 1275, 485);
			truckFrontHand.render(batch, delta);
			girlHead.render(batch, delta);
			boyHead.render(batch, delta);
			handwheel.render(batch, delta);
			batch.draw(assetsManager.truckFront[4], 1275, 0);
			glassSprite.draw(batch);
		} else if (timerFrontTruck <= 2.5f) {
			truckFrontHand.setAlpha(2.5f - (float) timerFrontTruck);
			handwheel.setAlpha(2.5f - (float) timerFrontTruck);
			girlHead.setAlpha(2.5f - (float) timerFrontTruck);
			boyHead.setAlpha(2.5f - (float) timerFrontTruck);
			glassSprite.setAlpha(2.5f - (float) timerFrontTruck);
			batch.setColor(1f, 1f, 1f, 2.5f - (float) timerFrontTruck);
			batch.draw(assetsManager.truckFront[0], 0, 765);
			batch.draw(assetsManager.truckFront[1], 1275, 765);
			batch.draw(assetsManager.truckFront[2], 0, 0);
			batch.draw(assetsManager.truckFront[3], 1275, 485);
			truckFrontHand.render(batch, delta);
			girlHead.render(batch, delta);
			boyHead.render(batch, delta);
			handwheel.render(batch, delta);
			batch.draw(assetsManager.truckFront[4], 1275, 0);
			glassSprite.draw(batch);
			batch.setColor(1, 1, 1, 1);
		}

	}

	void drawArrows(float delta) {
		handwheelCockpit.render(batch, delta);
		girlHandCockpit.render(batch, delta);
		boyHeadCockpit.render(batch, delta);
		girlHeadCockpit.render(batch, delta);
		firstArrow.render(batch, delta);
		secondArrow.render(batch, delta);
	}

	void drawTime(float delta) {

		if (drawTime == true) {

			timeLeftBar.render(batch, delta, minigameTimeLeft);
			counterLeftBar.render(batch, delta, 29 - minigameCounter);

			if (afterMinigameWindow == false) {
				timeLeftBar.setVisibility(true);
				counterLeftBar.setVisibility(true);
			} else {
				timeLeftBar.setVisibility(false);
				counterLeftBar.setVisibility(false);
			}

		}
	}

	void randomizeMinigame() {
		if (minigameRunning == false && minigameCounter > 0) {
			minigameCounter--;
			minigameRunning = true;

			if (minigameCounter != 0) {
				for (int a = 0; a < 4; a++)
					yellowButtons[a] = false;
				yellowButtons[MathUtils.random(0, 3)] = true;
			}

			if (minigameCounter == 0) {
				if (minigameCounter == 0 && afterMinigameWindow == false) {
					dialogueWindow.popUp();
					afterMinigameWindow = true;
				}
			}

		}
	}

	void managePointers(float delta) {

		boolean allPointersScaleZero = false;
		if (yellowSectionMiddlePointer.getScaleX() == 0
				&& yellowSectionLeftPointer.getScaleX() == 0
				&& yellowSectionUpLeftPointer.getScaleX() == 0
				&& yellowSectionUpRightPointer.getScaleX() == 0)
			allPointersScaleZero = true;

		for (int a = 0; a < 4; a++) {
			if (yellowButtons[a] == true) {
				allPointersScaleZero = false;
				break;
			} else
				allPointersScaleZero = true;
		}

		if (allPointersScaleZero == true && minigameRunning == true) {
			minigameTimeLeft = 2f;
			minigameRunning = false;
		}

		if (yellowButtons[0] == true) {
			if (yellowSectionMiddlePointer.getScaleX() < 1) {
				yellowSectionMiddlePointer.setScale(yellowSectionMiddlePointer
						.getScaleX() + delta * 3);
				if (yellowSectionMiddlePointer.getScaleX() > 1)
					yellowSectionMiddlePointer.setScale(1);
			}
			if (yellowSectionMiddle.getSelection() == true) {
				yellowButtons[0] = false;
				assetsManager.hit.setPosition(yellowSectionMiddle.getX()+50, yellowSectionMiddle.getY());
				assetsManager.hit.allowCompletion();
				assetsManager.hit.start();

			}
		} else if (yellowSectionMiddlePointer.getScaleX() > 0) {
			yellowSectionMiddlePointer.setScale(yellowSectionMiddlePointer
					.getScaleX() - delta * 3);
			if (yellowSectionMiddlePointer.getScaleX() < 0)
				yellowSectionMiddlePointer.setScale(0);
		}
		if (yellowButtons[1] == true) {
			if (yellowSectionLeftPointer.getScaleX() < 1) {
				yellowSectionLeftPointer.setScale(yellowSectionLeftPointer
						.getScaleX() + delta * 3);
				if (yellowSectionLeftPointer.getScaleX() > 1)
					yellowSectionLeftPointer.setScale(1);
			}
			if (yellowSectionLeft.getSelection() == true) {
				yellowButtons[1] = false;
				assetsManager.hit.setPosition(yellowSectionLeft.getX(), yellowSectionLeft.getY());
				assetsManager.hit.allowCompletion();
				assetsManager.hit.start();
			}
		} else if (yellowSectionLeftPointer.getScaleX() > 0) {
			yellowSectionLeftPointer.setScale(yellowSectionLeftPointer
					.getScaleX() - delta * 3);
			if (yellowSectionLeftPointer.getScaleX() < 0)
				yellowSectionLeftPointer.setScale(0);
		}
		if (yellowButtons[2] == true) {
			if (yellowSectionUpLeftPointer.getScaleX() < 1) {
				yellowSectionUpLeftPointer.setScale(yellowSectionUpLeftPointer
						.getScaleX() + delta * 3);
				if (yellowSectionUpLeftPointer.getScaleX() > 1)
					yellowSectionUpLeftPointer.setScale(1);
			}
			if (yellowSectionUpLeft.getSelection() == true) {
				yellowButtons[2] = false;
				assetsManager.hit.setPosition(yellowSectionUpLeft.getX(), yellowSectionUpLeft.getY());
				assetsManager.hit.allowCompletion();
				assetsManager.hit.start();
			}
		} else if (yellowSectionUpLeftPointer.getScaleX() > 0) {
			yellowSectionUpLeftPointer.setScale(yellowSectionUpLeftPointer
					.getScaleX() - delta * 3);
			if (yellowSectionUpLeftPointer.getScaleX() < 0)
				yellowSectionUpLeftPointer.setScale(0);
		}
		if (yellowButtons[3] == true) {
			if (yellowSectionUpRightPointer.getScaleX() < 1) {
				yellowSectionUpRightPointer
						.setScale(yellowSectionUpRightPointer.getScaleX()
								+ delta * 3);
				if (yellowSectionUpRightPointer.getScaleX() > 1)
					yellowSectionUpRightPointer.setScale(1);
			}
			if (yellowSectionUpRight.getSelection() == true) {
				yellowButtons[3] = false;
				assetsManager.hit.setPosition(yellowSectionUpRight.getX(), yellowSectionUpRight.getY());
				assetsManager.hit.allowCompletion();
				assetsManager.hit.start();
				
			}
		} else if (yellowSectionUpRightPointer.getScaleX() > 0) {
			yellowSectionUpRightPointer.setScale(yellowSectionUpRightPointer
					.getScaleX() - delta * 3);
			if (yellowSectionUpRightPointer.getScaleX() < 0)
				yellowSectionUpRightPointer.setScale(0);
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
				.getTrainingScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new TrainingScreenOne(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}
}