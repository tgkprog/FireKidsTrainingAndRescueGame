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
	Button textName;
	Button textWhatYouWantToBe;

	Button name;
	Button whatYouWantToBe;
	Button saveButton;

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
		saveButton = new Button(365, -50, assetsManager.runButtonGreen);
		saveButton.goUp(395);

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setMenu(menu);
		inputInterpreter.setCloudManager(cloudManager);
		// I assign it to any of the buttons, doesn't matter which, I just need
		// to
		// check if it's clicked
		cloudManager.stop();

		buildingsCamera = new OrthographicCamera(800, 480);
		buildingsCamera.zoom = 2;

		camera.reset();

		camera.zoom = 1f;
		camera.position.x = 1275;
		camera.position.y = 533;

		camera.moveX(1275, 0, 0, 100);
		camera.moveY(533, 0, 0, 100);
		camera.zoom(1f, 100f);

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
		textUserInformation = new Button(300, -135, assetsManager.userInformation);
		textFireEngineGame.goUp(440);
		textUserInformation.goUp(400);

		textName = new Button(10, -100, assetsManager.name);
		textWhatYouWantToBe = new Button(10, -160, assetsManager.name);
		
		textName.goUp(300);
		textWhatYouWantToBe.goUp(240);

		name = new Button(270, -100, assetsManager.frameCollectiblesLong);
		whatYouWantToBe = new Button(270, -160, assetsManager.frameCollectiblesLong);

		name.goUp(300);
		whatYouWantToBe.goUp(240);

		cars = new ArrayList<Car>();
		for (int a = 0; a < 4; a++) {
			spawnRandomCar(a);
		}

		userInput = new String[7];
		String[] savedValues = dataOrganizer.getUserInputScreenValues();
		if (savedValues != null && savedValues.length >= 7) {
			for (int a = 0; a < 7; a++) {
				userInput[a] = savedValues[a];
				if (userInput[a] == null) {
					userInput[a] = "";
				}
			}
		} else {
			for (int a = 0; a < 7; a++) {
				userInput[a] = "";
			}
		}
		
		if (userInput[6].equals("click to type")) {
			userInput[6] = "";
		}
		if (userInput[1].equals("click to type")) {
			userInput[1] = "";
		}
		
		assetsManager.fontLittle.setColor(1, 1, 1, 0);

		Button[] inputButtons = new Button[3];
		inputButtons[0] = name;
		inputButtons[1] = whatYouWantToBe;
		inputButtons[2] = saveButton;
		inputInterpreter.setUserInputButtons(inputButtons);
		inputInterpreter.setTextInputListener(textInputListener);
	}

	@Override
	public void render(float delta) {

		camera.zoom = 2f;
		camera.position.x = 1700;

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

		//

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
		saveButton.render(batch, delta);
	}

	void drawTexts(float delta) {
		textFireEngineGame.render(batch, delta);
		textUserInformation.render(batch, delta);
		textName.render(batch, delta);

		name.render(batch, delta);
		whatYouWantToBe.render(batch, delta);

		assetsManager.fontLittle.draw(batch, userInput[6], 280, 328);
		assetsManager.fontLittle.draw(batch, "Goals", 10, 268);
		assetsManager.fontLittle.draw(batch, userInput[1], 280, 268);
		
		assetsManager.font.getData().setScale(0.35f);
		
		assetsManager.font.setColor(0.1f, 0.1f, 0.3f, 0.1f);
		assetsManager.font.draw(batch, "I will write what I want to on my diary.", 12, 198);
		assetsManager.font.draw(batch, "Study, eat well and exercise to reach my goals", 12, 168);
		assetsManager.font.draw(batch, "* we do not store your information anywhere except", 12, 128);
		assetsManager.font.draw(batch, "  on your phone, its not sent to any server", 12, 98);
		
		assetsManager.font.setColor(1f, 1f, 1f, 1f);
		assetsManager.font.draw(batch, "I will write what I want to on my diary.", 10, 200);
		assetsManager.font.draw(batch, "Study, eat well and exercise to reach my goals", 10, 170);
		assetsManager.font.draw(batch, "* we do not store your information anywhere except", 10, 130);
		assetsManager.font.draw(batch, "  on your phone, its not sent to any server", 10, 100);
		
		assetsManager.font.getData().setScale(1f);
	}

	void updateLogics(double delta) {

		if(menu.getCounter() > 0 && menu.isBlockedFromInteraction() == false){
			assetsManager.click.play();
			menu.setDontRespond(true);
		}
		
		if (closeButton.getSelection() == true) {
			assetsManager.click.play();
			menu.blink();
		}

		if (inputInterpreter.getUserInputID() == 0) {
			if (textInputListener.getInput() != "")
				userInput[6] = textInputListener.getInput();
		}
		
		if (inputInterpreter.getUserInputID() == 1) {
			if (textInputListener.getInput() != "")
				userInput[1] = textInputListener.getInput();
		}
		
		if (inputInterpreter.getUserInputID() == 2) {
			assetsManager.click.play();
			dataOrganizer.setUserInputScreenValues(userInput);
			dataOrganizer.saveData();
			menu.blink();
		}

		fontAlpha += 1.1 * delta;
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
		if (inputInterpreter.getSelectedScreenName() == variables.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawBackground(float delta) {
		batch.draw(assetsManager.userInputBackground[0], 533, 533);
		batch.draw(assetsManager.userInputBackground[1], 1275, 533);
		batch.draw(assetsManager.userInputBackground[2], 533, 0);
		batch.draw(assetsManager.userInputBackground[3], 1275, 0);
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