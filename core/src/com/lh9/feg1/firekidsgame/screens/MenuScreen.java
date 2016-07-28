package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class MenuScreen implements Screen {

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	Human boy;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Button meetTheTrucks;
	Button fireStation;
	Button settings;
	Button authors;
	Button gender;
	Button[] levelButtons;

	float positions = -1000;
	
	boolean madeShakeScreen;

	final Starter game;

	public MenuScreen(final Starter gam) {

		this.game = gam;

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		fireStation = new Button(700, 100, assetsManager.fireStation);
		settings = new Button(180, 100, assetsManager.settings);
		authors = new Button(100, 105, assetsManager.authors);
		meetTheTrucks = new Button(-2, -200, assetsManager.longButton);

		if (dataOrganizer.getGender() == false)
			gender = new Button(5, 105, assetsManager.boyButton);
		else
			gender = new Button(5, 105, assetsManager.girlButton);

		settings.goUp(405);
		authors.goUp(405);
		gender.goUp(390);

		boy = new Human();
		boy.create(assetsManager.boyWaving, 1, 1, 6, 1550, 0);
		boy.setMaxSpeed(0.25f);
		boy.setAnimationOnly(true);
		boy.setSpeed(3.5f);
		
		levelButtons = new Button[7];

		for (int a = 0; a < 7; a++) {
			if (a == 0)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.fitness);
			if (a == 1)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.training);
			if (a == 2)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueBuilding);
			if (a == 3)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueCat);
			if (a == 4)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.rescueTrain);
			if (a == 5)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.button);
			if (a == 6)
				levelButtons[a] = new Button(178 + 89 * a, -200 - (a * 50),
						assetsManager.bigRoadRescue);

			levelButtons[a].goUp(0);
		}

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMeetTheTrucks(meetTheTrucks);
		inputInterpreter.setLevelButtons(levelButtons);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setFireStation(fireStation);
		inputInterpreter.setSettings(settings);
		inputInterpreter.setAuthors(authors);
		inputInterpreter.setDataOrganizer(dataOrganizer);
		inputInterpreter.setGenderButton(gender);

		cloudManager.stop();

		fireStation.goUp(825);
		camera.reset();

		camera.position.x = 957;
		camera.position.y = 575;
		camera.zoom = 2.39f;

		camera.zoom(2.39f, 100);
		camera.moveX(957, 100, 100, 100);
		camera.moveY(575, 100, 100, 100);

		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());
	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground();
		drawTexts(delta);
		drawCharacters(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawButtons(delta);
		drawClouds(delta);
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
		gender.render(batch, delta);
		meetTheTrucks.render(batch, (float) delta);
		settings.render(batch, delta);
		authors.render(batch, delta);
		for (int a = 0; a < 7; a++) {
			levelButtons[a].render(batch, (float) delta);
		}
	}

	void updateLogics(double delta) {
		
		if(positions < 0)
			positions += delta*100 + Math.abs(positions)*0.025f;
		if(positions > 0)
			positions = 0;
		
		if (dataOrganizer.getGender() == false && gender.getSelection() == true) {
			gender.setTexture(assetsManager.boyButton);
			dataOrganizer.setGender(false);
		}
		if (dataOrganizer.getGender() == true && gender.getSelection() == true) {
			gender.setTexture(assetsManager.girlButton);
			dataOrganizer.setGender(true);
		}
		if (fireStation.notMoving() == true && madeShakeScreen == false) {
			madeShakeScreen = true;
			fireStation.blink();
			// camera.shakeScreen();
			// Not needed
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMeetTheTrucks()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getCatRescueScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new PreRescueCatScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getAuthorsScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new AuthorsScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getSettingsScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new SettingsScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getBigRoadRescueScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new BigRoadRescueScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getRescueMetroScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new RescueMetroScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTrainingScreenTwo()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new TrainingScreenTwo(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTrainingScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new TrainingScreenOne(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFitnessScreenOne()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new FitnessScreenOne(game));
			}
		}
	}

	void drawCharacters(float delta) {
		batch.draw(assetsManager.girlMainMenu, 0, positions);
		boy.setPosition(1550, (int)positions);
		boy.render(batch, delta);
		boy.setSpeed(2.5f);
		batch.draw(assetsManager.helmet1, 5, 715 + Math.abs(positions));
		batch.draw(assetsManager.helmet2, 1700, 715 + Math.abs(positions));
			
	}

	void drawBackground() {
		batch.draw(assetsManager.mainBackground[0], 0, 576);
		batch.draw(assetsManager.mainBackground[1], 960, 576);
		batch.draw(assetsManager.mainBackground[2], 0, 0);
		batch.draw(assetsManager.mainBackground[3], 960, 0);
	}

	void drawTexts(float delta) {
		fireStation.render(batch, delta);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawFps() {
		fpsManager.render(batch);
	}
}