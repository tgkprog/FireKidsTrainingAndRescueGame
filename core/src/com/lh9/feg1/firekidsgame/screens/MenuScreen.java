package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Human;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.Arrow;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class MenuScreen implements Screen {

	Arrow star;
	Arrow cog;
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
	Button userScreenButton;
	Button[] starsCounterButtons;
	Button[] levelButtons;
	String collectedStarsInString;
	String cogsInString;
	Sprite frameCollectibles;

	long bellSoundID;
	float positions = -1000;
	float bellsVolume = 1;
	boolean madeShakeScreen;
	boolean startedBells;

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
		userScreenButton = new Button(720, 100, assetsManager.userScreenButton);

		if (dataOrganizer.getGender() == false)
			gender = new Button(5, 105, assetsManager.boyButton);
		else
			gender = new Button(5, 105, assetsManager.girlButton);

		settings.goUp(405);
		authors.goUp(405);
		gender.goUp(390);
		userScreenButton.goUp(405);
		
		
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
		inputInterpreter.setScreensPlayed(game.getScreensPlayed());
		inputInterpreter.setUserScreenButton(userScreenButton);

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

		if (game.getCollectedStars() == 0) {
			game.setCollectedStars(dataOrganizer.getScore());
		} else {
			dataOrganizer.setScore(game.getCollectedStars());
		}
		if (game.getCogs() == 0) {
			game.setCogs(dataOrganizer.getExperience());
		} else {
			dataOrganizer.setExperience(game.getCogs());
		}

		frameCollectibles = new Sprite(assetsManager.frameCollectibles);

		collectedStarsInString = new String(Integer.toString(dataOrganizer
				.getScore()));
		cogsInString = new String(Integer.toString(dataOrganizer
				.getExperience()));

		int numberOfZerosToCompleteStarsString = 7 - collectedStarsInString
				.length();
		for (int a = 0; a < numberOfZerosToCompleteStarsString; a++) {
			collectedStarsInString = "0" + collectedStarsInString;
		}
		numberOfZerosToCompleteStarsString = 7 - cogsInString.length();
		for (int a = 0; a < numberOfZerosToCompleteStarsString; a++) {
			cogsInString = "0" + cogsInString;
		}

		dataOrganizer.setScreensPlayed(game.getScreensPlayed());
		dataOrganizer.saveData();

		star = new Arrow(270, 415, assetsManager.star, -72, 60);
		star.setAlpha(1);
		star.setScale(1);

		cog = new Arrow(480, 415, assetsManager.cog, -72, 60);
		cog.setAlpha(1);
		cog.setScale(1.2f);
		
		assetsManager.fontLittle.setColor(1,1,1,1);
	}

	static int timeArrow = 0;

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
		if(timeArrow < 600 ) {
			timeArrow++;
			final int tt = (int) timeArrow / 30;
			if(tt % 2 == 0) {
				final float yy = (float) (meetTheTrucks.getY() + (meetTheTrucks.getBounds().getHeight() * 1.1) + assetsManager.pointer.getHeight() );
				final float xx = (float) (meetTheTrucks.getX() + 37 + (meetTheTrucks.getBounds().getWidth() * 0.65));
				batch.draw(assetsManager.pointer, xx, yy);System.out.println("x  " + xx+ ", " + meetTheTrucks.getX() +  "  w " +  (meetTheTrucks.getBounds().getWidth()));
			}
		}
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

		assetsManager.fontLittle.setColor(Color.WHITE);
		assetsManager.fontLittle.draw(batch, collectedStarsInString, 330, 448);
		assetsManager.fontLittle.draw(batch, cogsInString, 540, 448);

		star.render(batch, delta * 0.75f);
		cog.render(batch, delta * 0.75f);
		gender.render(batch, delta);
		userScreenButton.render(batch, delta);
		meetTheTrucks.render(batch, (float) delta);
		settings.render(batch, delta);
		authors.render(batch, delta);
		for (int a = 0; a < 7; a++) {
			levelButtons[a].render(batch, (float) delta);
		}
	}

	void updateLogics(double delta) {

		if (positions < 0)
			positions += delta * 100 + Math.abs(positions) * 0.025f;
		if (positions > 0)
			positions = 0;

		if (assetsManager.backgroundPlayed == false) {
			if (dataOrganizer.getGender() == false) {
				assetsManager.backgroundBoy.play();
				assetsManager.backgroundBoy.setVolume(.6f);
				//assetsManager.backgroundBoy.setLooping(true);
			}
			if (dataOrganizer.getGender() == true) {
				assetsManager.backgroundGirl.play();
				assetsManager.backgroundGirl.setVolume(.6f);
				//assetsManager.backgroundGirl.
				// (true);
			}
			assetsManager.backgroundPlayed = true;
		}
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
				.getMEET_THE_TRUCKS()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new MeetTheTrucksScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName().equals(Variables.USER_INPUT_SCREEN)) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new UserInputScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getELEVATOR_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new PreElevatorScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getCAT_RESCUE_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new PreRescueCatScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getAUTHORS_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new AuthorsScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getSETTINGS_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new SettingsScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getBIG_ROAD_RESCUE_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new BigRoadRescueScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getRESCUE_METRO_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new PreRescueMetroScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTRAINING_SCREEN_TWO()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new TrainingScreenTwo(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getTRAINING_SCREEN_ONE()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				dataOrganizer.saveData();
				game.setScreen(new TrainingScreenOne(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFITNESS_SCREEN_ONE()) {
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

		if (inputInterpreter.getSelectedScreenName() != "No button clicked") {
			buzzer.setSpeed(10f);
			if (startedBells == false) {
				assetsManager.click.play();
				bellSoundID = assetsManager.bell.play();
				startedBells = true;
			}
		}
		if (positions < 0)
			buzzer.setSpeed(0f);

		if (startedBells == true) {
			assetsManager.bell.setVolume(bellSoundID, bellsVolume);
			if (bellsVolume > 0)
				bellsVolume -= delta * 0.55f;
			if (bellsVolume < 0)
				bellsVolume = 0;
		}

		frameCollectibles.setScale(3, 3);
		frameCollectibles.setPosition(800, 1030);
		frameCollectibles.draw(batch);
		frameCollectibles.setPosition(1300, 1030);
		frameCollectibles.draw(batch);

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