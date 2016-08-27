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

	Human buzzer;
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
	Button[] starsCounterButtons;
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
		meetTheTrucks = new Button(25, -200, assetsManager.meetTheTrucks);

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

		buzzer = new Human();
		buzzer.create(assetsManager.buzzer, 3, 1, 3, 1000, 450);
		buzzer.setMaxSpeed(3f);
		buzzer.setAnimationOnly(true);

		levelButtons = new Button[7];
		/*
		 * starsCounterButtons = new Button[6]; starsCounterButtons[0] = new
		 * Button(270, -150, assetsManager.starButton);
		 * starsCounterButtons[0].goUp(420); for (int a = 1; a < 6; a++) {
		 * starsCounterButtons[a] = new Button(270 + 45 * a, -200 - (a * 50),
		 * assetsManager.starButtonEmpty); starsCounterButtons[a].goUp(420); }
		 */
		for (int a = 0; a < 7; a++) {
			if (a == 0)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.fitness);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.fitness_desaturated);
			if (a == 1)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.training);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.training_desaturated);
			if (a == 2)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueBuilding);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueBuilding_desaturated);
			if (a == 3)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueCat);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueCat_desaturated);
			if (a == 4)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueTrain);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.rescueTrain_desaturated);
			if (a == 5)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.elevatorButton);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.elevatorButton_desaturated);
			if (a == 6)
				if (game.getScreenPlayed(a) == true)
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.bigRoadRescue);
				else
					levelButtons[a] = new Button(120 + 95 * a, -200 - (a * 50),
							assetsManager.bigRoadRescue_desaturated);

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
		inputInterpreter.getScreensPlayed(game.getScreensPlayed());

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

		System.out.println(game.getCollectedStars());

		if (game.getCollectedStars() == 0) {
			game.setCollectedStars(dataOrganizer.getScore());
			System.out.println("setting collected stars");
		} else {
			System.out.println("setting new score");
			dataOrganizer.setScore(game.getCollectedStars());
		}
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

		drawBackground(delta);
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
		// assetsManager.fontLittle.draw(batch, "123",
		// 680, 425);
		// batch.draw(assetsManager.star,740,385);
		gender.render(batch, delta);
		meetTheTrucks.render(batch, (float) delta);
		settings.render(batch, delta);
		authors.render(batch, delta);
		for (int a = 0; a < 7; a++) {
			levelButtons[a].render(batch, (float) delta);
		}
		// for (int a = 0; a < 6; a++) {
		// starsCounterButtons[a].render(batch, (float) delta);
		// }
	}

	void updateLogics(double delta) {

		if (positions < 0)
			positions += delta * 100 + Math.abs(positions) * 0.025f;
		if (positions > 0)
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
				.getElevatorScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new PreElevatorScreen(game));
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
				game.setScreen(new PreRescueMetroScreen(game));
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
				.getTrainingScreenOne()) {
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
		boy.setPosition(1550, (int) positions);
		boy.render(batch, delta);
		boy.setSpeed(2.5f);
		batch.draw(assetsManager.helmet1, 5, 715 + Math.abs(positions));
		batch.draw(assetsManager.helmet2, 1700, 715 + Math.abs(positions));
	}

	void drawBackground(float delta) {
		batch.draw(assetsManager.mainBackground[0], 0, 576);
		batch.draw(assetsManager.mainBackground[1], 960, 576);
		batch.draw(assetsManager.mainBackground[2], 0, 0);
		batch.draw(assetsManager.mainBackground[3], 960, 0);

		buzzer.setPosition(875, 400);
		buzzer.render(batch, delta);
		buzzer.setPosition(1410, 400);
		buzzer.render(batch, delta);

		if (inputInterpreter.getSelectedScreenName() != "No button clicked")
			buzzer.setSpeed(10f);
		if (positions < 0)
			buzzer.setSpeed(0f);
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