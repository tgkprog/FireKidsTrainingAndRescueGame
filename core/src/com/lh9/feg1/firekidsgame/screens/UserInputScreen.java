package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.animated.Car;
import com.lh9.feg1.firekidsgame.camera.Camera;
import com.lh9.feg1.firekidsgame.files.AssetsManager;
import com.lh9.feg1.firekidsgame.graphics.CloudManager;
import com.lh9.feg1.firekidsgame.graphics.FPSManager;
import com.lh9.feg1.firekidsgame.ui.Button;
import com.lh9.feg1.firekidsgame.ui.InputInterpreter;
import com.lh9.feg1.firekidsgame.ui.MyTextInputListener;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class UserInputScreen implements Screen {

	MyTextInputListener textInputListener = new MyTextInputListener();

	String[] userInput;

	Button textFireEngineGame;
	Button textUserInformation;
	Button textEmail;
	Button textNick;
	Button textFacebook;
	Button textGoogle;
	Button textTwitter;
	Button textWebsite;
	Button textName;
	Button textInfoFromServer;
	Button textMoreFromServer;

	Button email;
	Button nick;
	Button facebook;
	Button google;
	Button twitter;
	Button website;
	Button name;
	Button infoFromServer;
	Button moreFromServer;

	Button[] inputButtons;

	FPSManager fpsManager;
	DataOrganizer dataOrganizer;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	OrthographicCamera buildingsCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;
	Button closeButton;
	Button menu;

	ArrayList<Car> cars;
	Array<Sprite> buildings;
	Sprite[] roadBackground;
	Sprite[] road;

	float fontAlpha;
	float sortBuildingsByPosition;
	int lastTimeCarLane = 1;

	final Starter game;

	public UserInputScreen(final Starter gam) {

		this.game = gam;

		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();

		menu = new Button(715, -50, assetsManager.menu);
		menu.goUp(395);
		closeButton = new Button(15, -50, assetsManager.closeButton);
		closeButton.goUp(395);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		cloudManager.stop();

		buildingsCamera = new OrthographicCamera(800, 480);
		buildingsCamera.zoom = 2;

		camera.reset();

		camera.zoom = 0.44f;
		camera.position.x = 500;
		camera.position.y = 32;

		camera.moveX(500, 0, 0, 100);
		camera.moveY(32, 0, 0, 100);
		camera.zoom(0.44f, 100f);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		roadBackground = new Sprite[2];
		roadBackground[0] = new Sprite(assetsManager.road[1]);
		roadBackground[1] = new Sprite(assetsManager.road[2]);
		roadBackground[0].setPosition(0, 0);
		roadBackground[1].setPosition(948, 0);

		road = new Sprite[4];
		for (int a = 0; a < 4; a++) {
			road[a] = new Sprite(assetsManager.road[0]);
			road[a].setPosition(a * 275, -100);
			road[a].setScale(1, 0.75f);
		}

		buildings = new Array<Sprite>();

		for (int a = 0; a < 8; a++) {
			spawnBuilding(350 * a - 800);
		}

		textFireEngineGame = new Button(265, -100, assetsManager.fireEngineGame);
		textUserInformation = new Button(300, -135,
				assetsManager.userInformation);
		textFireEngineGame.goUp(440);
		textUserInformation.goUp(400);

		textEmail = new Button(10, -100, assetsManager.email);
		textNick = new Button(10, -130, assetsManager.nick);
		textFacebook = new Button(10, -160, assetsManager.facebook);
		textGoogle = new Button(10, -190, assetsManager.google);
		textTwitter = new Button(10, -220, assetsManager.twitter);
		textWebsite = new Button(10, -250, assetsManager.website);
		textName = new Button(10, -280, assetsManager.name);
		textInfoFromServer = new Button(10, -310, assetsManager.infoFromServer);
		textMoreFromServer = new Button(10, -340, assetsManager.moreFromServer);

		textEmail.goUp(350);
		textNick.goUp(310);
		textFacebook.goUp(280 - 10 * 1);
		textGoogle.goUp(250 - 10 * 2);
		textTwitter.goUp(220 - 10 * 3);
		textWebsite.goUp(190 - 10 * 4);
		textName.goUp(160 - 10 * 5);
		textInfoFromServer.goUp(130 - 10 * 6);
		textMoreFromServer.goUp(100 - 10 * 7);

		email = new Button(270, -100, assetsManager.frameCollectiblesLong);
		nick = new Button(270, -130, assetsManager.frameCollectiblesLong);
		facebook = new Button(270, -160, assetsManager.frameCollectiblesLong);
		google = new Button(270, -190, assetsManager.frameCollectiblesLong);
		twitter = new Button(270, -220, assetsManager.frameCollectiblesLong);
		website = new Button(270, -250, assetsManager.frameCollectiblesLong);
		name = new Button(270, -280, assetsManager.frameCollectiblesLong);
		infoFromServer = new Button(270, -310,
				assetsManager.frameCollectiblesLong);
		moreFromServer = new Button(270, -340,
				assetsManager.frameCollectiblesLong);

		email.goUp(350 - 1);
		nick.goUp(310 - 2);
		facebook.goUp(280 - 3 - 10 * 1);
		google.goUp(250 - 4 - 10 * 2);
		twitter.goUp(220 - 5 - 10 * 3);
		website.goUp(190 - 6 - 10 * 4);
		name.goUp(160 - 7 - 10 * 5);
		infoFromServer.goUp(130 - 8 - 10 * 6);
		moreFromServer.goUp(100 - 9 - 10 * 7);

		cars = new ArrayList<Car>();
		for (int a = 0; a < 4; a++) {
			spawnRandomCar(a);
		}

		userInput = new String[7];
		for (int a = 0; a < 7; a++) {
			userInput[a] = new String("click to type");
		}
		assetsManager.fontLittle.setColor(1, 1, 1, 0);

		Button[] inputButtons = new Button[7];
		inputButtons[0] = email;
		inputButtons[1] = nick;
		inputButtons[2] = facebook;
		inputButtons[3] = google;
		inputButtons[4] = twitter;
		inputButtons[5] = website;
		inputButtons[6] = name;
		inputInterpreter.setUserInputButtons(inputButtons);
		inputInterpreter.setTextInputListener(textInputListener);
	}

	@Override
	public void render(float delta) {

		if (Gdx.graphics.getRawDeltaTime() > 0.05f
				&& Gdx.graphics.getDeltaTime() > 0.05f)
			delta = 0;

		updateLogics(delta);

		camera.update(delta);
		guiCamera.update();
		buildingsCamera.update();

		Gdx.gl.glClearColor(1, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);

		batch.end();
		batch.setProjectionMatrix(buildingsCamera.combined);
		batch.begin();

		drawBuildings(delta);
		drawCars(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawTexts(delta);
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
		menu.render(batch, delta);
		closeButton.render(batch, delta);

	}

	void drawTexts(float delta) {
		textFireEngineGame.render(batch, delta);
		textUserInformation.render(batch, delta);
		textEmail.render(batch, delta);
		textNick.render(batch, delta);
		textFacebook.render(batch, delta);
		textGoogle.render(batch, delta);
		textTwitter.render(batch, delta);
		textWebsite.render(batch, delta);
		textName.render(batch, delta);
		textInfoFromServer.render(batch, delta);
		textMoreFromServer.render(batch, delta);

		email.render(batch, delta);
		nick.render(batch, delta);
		facebook.render(batch, delta);
		google.render(batch, delta);
		twitter.render(batch, delta);
		website.render(batch, delta);
		name.render(batch, delta);
		infoFromServer.render(batch, delta);
		moreFromServer.render(batch, delta);

		for (int a = 0; a < userInput.length; a++) {
			assetsManager.fontLittle.draw(batch, userInput[a], 280,
					378 - a * 41);
		}
		assetsManager.fontLittle.draw(batch, "no info", 280, 92);
		assetsManager.fontLittle.draw(batch, "no info", 280, 50);
	}

	void updateLogics(double delta) {


		if (inputInterpreter.getUserInputID() != -1) {
				if (textInputListener.getInput() != "")
			userInput[inputInterpreter.getUserInputID()] = textInputListener
					.getInput();
		}

		fontAlpha += delta;
		if (fontAlpha > 2)
			assetsManager.fontLittle.setColor(1, 1, 1, fontAlpha - 2);
		if (fontAlpha > 3)
			assetsManager.fontLittle.setColor(1, 1, 1, 1);

		if (buildings.size < 8)
			spawnBuilding();
		for (int a = 0; a < buildings.size; a++) {
			if (buildings.get(a).getX() <= -800 - buildings.get(a).getWidth()) {
				buildings.removeIndex(a);
			}
		}
		sortBuildingsByPosition += delta;

		if (sortBuildingsByPosition > 1) {
			sortBuildingsByPosition = 0;
			Array<Sprite> tempArray = new Array<Sprite>();

			for (int b = 0; b < buildings.size; b++) {
				float closestY = -220;
				int closestYindex = 0;
				for (int a = 0; a < buildings.size; a++) {
					if (buildings.get(a).getY() > closestY
							&& !tempArray.contains(buildings.get(a), false)) {
						closestY = buildings.get(a).getY();
						closestYindex = a;
					}
				}
				tempArray.add(buildings.get(closestYindex));
			}
			buildings = tempArray;
		}
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawBackground(float delta) {
		for (int a = 0; a < roadBackground.length; a++) {
			roadBackground[a].setPosition(
					roadBackground[a].getX() - delta * 15, 0);
			if (roadBackground[a].getX() <= -948)
				roadBackground[a].setPosition(948, 0);
			roadBackground[a].draw(batch);
		}
		for (int a = 0; a < road.length; a++) {
			road[a].setPosition(road[a].getX() - delta * 55, -100);
			if (road[a].getX() <= -274)
				road[a].setPosition(3 * 275, -100);
			road[a].draw(batch);
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawBuildings(float delta) {
		for (int a = 0; a < buildings.size; a++) {
			buildings.get(a).draw(batch);

			buildings.get(a).setPosition(
					buildings.get(a).getX() + (buildings.get(a).getY() / 30)
							* delta * 30, buildings.get(a).getY());
		}
	}

	void spawnBuilding() {

		int random = MathUtils.random(0, 7);
		if (random != 1 || random != 2 || random != 3 || random != 4)
			random = MathUtils.random(0, 7);

		Sprite building = new Sprite(assetsManager.buildings[random]);
		building.setPosition(1600, MathUtils.random(-220, -135));
		buildings.add(building);
	}

	void spawnBuilding(int x) {
		int random = MathUtils.random(0, 7);
		if (random != 1 || random != 2 || random != 3 || random != 4)
			random = MathUtils.random(0, 7);

		Sprite building = new Sprite(assetsManager.buildings[random]);
		building.setPosition(x, MathUtils.random(-220, -135));
		buildings.add(building);
	}

	void spawnRandomCar(int a) {

		Car car;
		car = new Car();

		int random = MathUtils.random(1, 5);

		if (random == 1)
			car.create(assetsManager.carRed, 1, 1, 1, -1500 * (a + 1), -100);
		if (random == 2)
			car.create(assetsManager.carYellow, 1, 1, 1, -1500 * (a + 1), -100);
		if (random == 3)
			car.create(assetsManager.carPink, 1, 1, 1, -1500 * (a + 1), -100);
		if (random == 4)
			car.create(assetsManager.carBlue, 1, 1, 1, -1500 * (a + 1), -100);
		if (random == 5)
			car.create(assetsManager.carGreen, 1, 1, 1, -1500 * (a + 1), -100);

		random = MathUtils.random(1, 2);
		if (MathUtils.randomBoolean() == true) {
			if (lastTimeCarLane == 1)
				random = 2;
			else
				random = 1;
		}

		car.setMaxSpeed(2.5f);
		car.setMaxPositions(-30350, 3550);
		car.loadWheel(assetsManager.wheel);
		car.goRight();
		car.goAutomatically(true);
		car.setSpeed(2.5f);
		cars.add(car);

		lastTimeCarLane = random;
	}

	void drawCars(float delta) {
		for (int a = 0; a < cars.size(); a++) {
			if (cars.get(a).getX() > 1600) {
				cars.get(a).setPosition(-1500, -350);
			}
			cars.get(a).render(batch, delta);
			cars.get(a).manageGoAutomatically(delta);
			cars.get(a).setPosition((int) cars.get(a).getX(), -350);
			assetsManager.smoke[a].setPosition(cars.get(a).getX(), cars.get(a)
					.getY());
			assetsManager.smoke[a].draw(batch, delta);
		}
	}
}