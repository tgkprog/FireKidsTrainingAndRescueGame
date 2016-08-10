package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.animated.Truck;
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

public class MeetTheTrucksScreen implements Screen {

	Sprite truckLed;
	Truck truck;
	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Button menuButton;
	Button up;
	Sprite boyHead;
	Button retryButton;
	Button playButton;
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

	boolean pat = true;
	float jumpAlpha = 0.5f;
	float timerSpeedGirl;
	boolean exit;
	boolean firstDialogueClicked;
	boolean secondDialogueClicked;
	boolean finish;
	boolean truckPart;
	boolean eclipsePart;

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

		runButton = new Button(685, -200, assetsManager.runButtonLittle);
		runButton.goUp(30);
		runButton.setAlpha(0.5f);

		up = new Button(10, -200, assetsManager.arrowUp);
		up.goUp(30);
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

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
		assetsManager.hit.scaleEffect(0.3f);

		truckLed = new Sprite(assetsManager.truckLed);
		
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

		drawBackground();
		drawCharacters(delta);
		drawPointer(delta);
		drawParticlesNonGui(delta);
		
		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

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
		if (girl.getX() < 2270 || eclipsePart == true)
			girl.render(batch, delta);

		truck.render(batch, delta);
	}

	void drawButtons(float delta) {
		pause.render(batch, (float) delta);
		runButton.render(batch, (float) delta);
		up.render(batch, delta);
	}

	void drawWindows(float delta) {

		menuWindow.draw(batch, delta);
		dialogueWindow.draw(batch, delta);
	}

	void updateLogics(float delta) {
		
		if(girl.getY() != 35 && girl.getY() != 110 ){
			pat = false;
		}
		else if(pat == false) {
			assetsManager.hit.setPosition(girl.getX()+55, girl.getY());
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
		
		if(eclipsePart == true){
			jumpAlpha += delta;
			if (jumpAlpha > 0.5f)
				jumpAlpha = 0.5f;
			up.setDontRespond(false);
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
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setScreen(new FitnessScreenTwo(game));
		}

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
		if (eclipsePart == true) {
			camera.position.x = girl.getX();
		}
	}

	void drawBackground() {
		if (girl.getX() <= 1200) {
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
			batch.draw(assetsManager.wheel, 350, 35);
			batch.draw(assetsManager.wheel, 390, 35);
			batch.draw(assetsManager.wheel, 430, 35);

			batch.draw(assetsManager.wheel, 350, 75);
			batch.draw(assetsManager.wheel, 390, 75);
			batch.draw(assetsManager.wheel, 430, 75);
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
		}
		if (girl.getX() >= 1200 && girl.getX() < 2600) {
			batch.draw(assetsManager.parkBackgrounds[2], 1589, 0);
		}
		if (girl.getX() >= 1600 && girl.getX() < 3100)
			batch.draw(assetsManager.parkBackgrounds[5], 2049, 0);
		if (girl.getX() >= 2100  && girl.getX() < 4600)
			batch.draw(assetsManager.parkBackgrounds[4], 2514, 0);
		if (girl.getX() >= 2600  && girl.getX() < 5100)
			batch.draw(assetsManager.parkBackgrounds[3], 3314, 0);
		
		if (girl.getX() >= 3100  && girl.getX() < 7100)
			batch.draw(assetsManager.parkBackgrounds[0], 4109, 0);
		if (girl.getX() >= 3600  && girl.getX() < 7600)
			batch.draw(assetsManager.parkBackgrounds[1], 4908, 0);
		if (girl.getX() >= 4100  && girl.getX() < 8100)
			batch.draw(assetsManager.parkBackgrounds[2], 5708, 0);

		if (girl.getX() >= 4600  && girl.getX() < 8600)
			batch.draw(assetsManager.parkBackgrounds[5], 6170, 0);
		if (girl.getX() >= 5100  && girl.getX() < 9100)
			batch.draw(assetsManager.parkBackgrounds[4], 6635, 0);
		if (girl.getX() >= 5600  && girl.getX() < 9600)
			batch.draw(assetsManager.parkBackgrounds[3], 7435, 0);

		if (girl.getX() >= 6100  && girl.getX() < 10100)
			batch.draw(assetsManager.parkBackgrounds[0], 8230, 0);
		if (girl.getX() >= 6600  && girl.getX() < 10600)
			batch.draw(assetsManager.parkBackgrounds[1], 9010, 0);
		if (girl.getX() >= 7100  && girl.getX() < 11100)
			batch.draw(assetsManager.parkBackgrounds[2], 9800, 0);

		if (girl.getX() >= 7600  && girl.getX() < 12100)
			batch.draw(assetsManager.parkBackgrounds[5], 10260, 0);
		if (girl.getX() >= 8100  && girl.getX() < 12500)
			batch.draw(assetsManager.parkBackgrounds[4], 10720, 0);
		if (girl.getX() >= 8600  && girl.getX() < 13000)
			batch.draw(assetsManager.parkBackgrounds[3], 11520, 0);

		if (girl.getX() >= 9100  && girl.getX() < 13600)
			batch.draw(assetsManager.parkBackgrounds[0], 12315, 0);
		if (girl.getX() >= 9600  && girl.getX() < 14600)
			batch.draw(assetsManager.parkBackgrounds[1], 13113, 0);
		if (girl.getX() >= 10100  && girl.getX() < 15600)
			batch.draw(assetsManager.parkBackgrounds[2], 13910, 0);

		
		
	}

	void updateGirlAction(double delta) {

	}
	
	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
	}

	void drawParticlesNonGui(float delta) {

		assetsManager.hit.draw(batch, delta);

	}

	void drawPointer(float delta) {
	}

	void drawBars(float delta) {
		speedBar.render(batch, delta, Math.abs(truck.getSpeed()));
		
		batch.draw(assetsManager.speedBar, 160, 440);
		
		boyHead.setPosition(150 + truck.getX() * 0.0255f, 410);
		boyHead.draw(batch);
		
		speedBar.render(batch, delta, truck.getSpeed());

	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(1);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMeetTheTrucks()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				assetsManager.hit.scaleEffect(1);
				game.setScreen(new MeetTheTrucksScreen(game));
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