package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.StaticAnimation;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.files.windows.Dialogue;
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.Background;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.LaneManager;
import com.lh9.feg1.firekidsgame.graphics.LedManager;
import com.lh9.feg1.firekidsgame.graphics.SpeedBar;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class TrainingScreenOne implements Screen {

	Background truckFront;
	Background cockpit;

	Vector3 normalColor;
	Vector3 redColor;

	Sprite glassSprite;
	
	double timerSpeedGirl;
	double timerFrontTruck;
	double shakeScreenTimer;
	double timerSirene;
	double timerSecondWindow;

	StaticAnimation hand;

	boolean goRed;
	boolean thirdDialogueClicked;
	LedManager middleLeds;

	boolean finish;
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
	SpeedBar speedBar;

	Arrow firstArrow;
	Arrow secondArrow;
	Arrow boyHead;
	Arrow girlHead;
	Arrow handwheel;

	boolean sirene;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;

	final Starter game;

	public TrainingScreenOne(final Starter gam) {

		normalColor = new Vector3(1, 1, 1);
		redColor = new Vector3(1, 0, 0);

		this.game = gam;

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

		handwheel = new Arrow(1490, 370, assetsManager.handwheelBig, -13f, 2f);
		handwheel.setAlpha(1);
		handwheel.setScale(0.8f);
		boyHead = new Arrow(280, 675, assetsManager.boyHeadBig, -0.7f, 0.7f);
		boyHead.setAlpha(1);
		boyHead.setScale(0.8f);
		girlHead = new Arrow(1490, 420, assetsManager.girlHeadBig, -0.5f, 0.5f);
		girlHead.setAlpha(1);
		girlHead.setScale(0.8f);
		pause = new Button(710, 120, assetsManager.pause);
		pause.goUp(350);

		runButton = new Button(390, -100, assetsManager.runButton);
		runButton.goUp(110);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		speedBar = new SpeedBar(assetsManager.speedBar, 10, 450);
		cloudManager.stop();

		camera.position.x = 1270;
		camera.position.y = 765;
		camera.zoom = 3.175f;

		camera.moveX(1270, 0, 0, 100);
		camera.moveY(765, 0, 0, 100);
		camera.zoom(3.175f, 100f);

		dialogueWindow.popUp();

		boy = new Human();
		boy.create(assetsManager.spritesheetBoyElliptical, 4, 2, 7, 400, 50);

		girl = new Human();
		girl.create(assetsManager.spritesheetGirlElliptical, 4, 2, 7, 100, 50);

		boy.setMaxSpeed(4f);
		girl.setMaxSpeed(4f);

		boy.setAnimationOnly(true);
		girl.setAnimationOnly(true);

		inputInterpreter.setControlledHuman(boy);

		laneManager = new LaneManager(assetsManager.lane, 1320, 1450);
		middleLeds = new LedManager(assetsManager.ledCockpit);
		middleLeds.setAlpha(1);
		runButton.setAlpha(0);
		hand = new StaticAnimation();
		hand.create(assetsManager.handSpritesheet, 2, 2, 3, 1000, -5, 0.1f);
		hand.setContinous(false);

		truckFront = new Background();
		truckFront.add(assetsManager.truckFront[0], 0, 765);
		truckFront.add(assetsManager.truckFront[1], 1275, 765);
		truckFront.add(assetsManager.truckFront[2], 0, 0);
		truckFront.add(assetsManager.truckFront[3], 1275, 485);
		truckFront.add(girlHead);
		truckFront.add(boyHead);
		truckFront.add(handwheel);
		truckFront.add(assetsManager.truckFront[4], 1275, 0);
		glassSprite = new Sprite(assetsManager.glass);
		glassSprite.setScale(2f,1.5f);
		glassSprite.setPosition(630, 565);
		truckFront.add(glassSprite);

		laneManager.setAlpha(1);

		cockpit = new Background();
		cockpit.add(assetsManager.truckCockpit[0], 0, 765);
		cockpit.add(assetsManager.truckCockpit[1], 1275, 765);
		cockpit.add(assetsManager.truckCockpit[2], 0, 0);
		cockpit.add(assetsManager.truckCockpit[3], 1275, 0);
		cockpit.add(firstArrow);
		cockpit.add(secondArrow);
		cockpit.addLaneManager(laneManager);
		cockpit.addLedManager(middleLeds);
		//cockpit.add(assetsManager.cockpitPart, 950, 0);
	}

	@Override
	public void render(float delta) {

		
		
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
		drawButtons(delta);
		drawWindows(delta);
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
	}

	void drawButtons(double delta) {
		// pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
	}

	void drawWindows(double delta) {
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(double delta) {
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
			game.setScreen(new MenuScreen(game));
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
				firstArrow.red();
				secondArrow.red();
				middleLeds.red();
			} else {
				batch.setColor(1, 1, 1, 1);
				laneManager.normal();
				firstArrow.normal();
				secondArrow.normal();
				middleLeds.normal();
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
			batch.draw(assetsManager.cockpitPart, 950, 0);
			middleLeds.render(batch, delta);
			if(sirene == true)
			hand.render(batch, delta);
			
			
			if (timerFrontTruck - 1.5f <= 1.0f) {
				firstArrow.setAlpha((float) timerFrontTruck - 1.5f);
				secondArrow.setAlpha((float) timerFrontTruck - 1.5f);
				laneManager.setAlpha((float) timerFrontTruck - 1.5f);
				middleLeds.setAlpha((float) timerFrontTruck - 1.5f);
				runButton.setAlpha((float) timerFrontTruck - 1.5f);
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
			girlHead.render(batch, delta);
			boyHead.render(batch, delta);
			handwheel.render(batch, delta);
			batch.draw(assetsManager.truckFront[4], 1275, 0);
			glassSprite.draw(batch);
		} else if (timerFrontTruck <= 2.5f) {
			handwheel.setAlpha(2.5f - (float) timerFrontTruck);
			girlHead.setAlpha(2.5f - (float) timerFrontTruck);
			boyHead.setAlpha(2.5f - (float) timerFrontTruck);
			glassSprite.setAlpha(2.5f - (float) timerFrontTruck);
			batch.setColor(1f, 1f, 1f, 2.5f - (float) timerFrontTruck);
			batch.draw(assetsManager.truckFront[0], 0, 765);
			batch.draw(assetsManager.truckFront[1], 1275, 765);
			batch.draw(assetsManager.truckFront[2], 0, 0);
			batch.draw(assetsManager.truckFront[3], 1275, 485);
			girlHead.render(batch, delta);
			boyHead.render(batch, delta);
			handwheel.render(batch, delta);
			//batch.draw(assetsManager.glass,0,0);
			batch.draw(assetsManager.truckFront[4], 1275, 0);
			glassSprite.draw(batch);
			batch.setColor(1, 1, 1, 1);
		}

	}

	void drawArrows(float delta) {
		firstArrow.render(batch, delta);
		secondArrow.render(batch, delta);
	}
}