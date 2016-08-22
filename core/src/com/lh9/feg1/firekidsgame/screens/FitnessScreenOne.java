package com.lh9.feg1.firekidsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
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

public class FitnessScreenOne implements Screen {

	float girlPositionLastFrame;
	float girlPositionTimer;

	Array<Sprite> bushes;
	Array<Sprite> trees;
	Array<Sprite> lakes;
	Array<Sprite> grassFlowers;
	Array<Sprite> stars;
	Array<Sprite> skies;
	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	Button menuButton;
	Button retryButton;
	Button playButton;
	MenuWindow menuWindow;
	Sprite boyHead;
	Sprite girlHead;
	Bar speedBar;
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

	float timerSpeedGirl;

	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;

	final Starter game;

	public FitnessScreenOne(final Starter gam) {

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

		dialogueWindow = new Dialogue(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250f, 150f, assetsManager.button);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		if (dataOrganizer.getGender() == false) {
			boy = new Human();
			boy.create(assetsManager.spritesheetBoyRunning, 31, 1, 31, -100, 35);
			boy.setMaxSpeed(4);
			boy.setSpeedAdder(0.4f);

			girl = new Human();
			girl.create(assetsManager.spritesheetGirlRunning, 31, 1, 31, -100,
					35);
			girl.setMaxSpeed(4);
			girl.setSpeedAdder(0.4f);

			boyHead = new Sprite(assetsManager.boyButton);
			girlHead = new Sprite(assetsManager.girlButton);
			boyHead.setScale(0.5f);
			girlHead.setScale(0.5f);
		} else {
			boy = new Human();
			boy.create(assetsManager.spritesheetGirlRunning, 31, 1, 31, -100, 35);
			boy.setMaxSpeed(4);
			boy.setSpeedAdder(0.4f);

			girl = new Human();
			girl.create(assetsManager.spritesheetBoyRunning, 31, 1, 31, -100, 35);
			girl.setMaxSpeed(4);
			girl.setSpeedAdder(0.4f);

			boyHead = new Sprite(assetsManager.girlButton);
			girlHead = new Sprite(assetsManager.boyButton);
			boyHead.setScale(0.5f);
			girlHead.setScale(0.5f);
		}

		girl.setAnimationTime(0.035f);
		boy.setAnimationTime(0.035f);
		
		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(assetsManager.dialogueWindow,
				assetsManager.darkScreen, 250, 200, menuButton, retryButton,
				playButton, variables.getFitnessScreenOne());

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setControlledHuman(boy);
		inputInterpreter.setMenuWindow(menuWindow);

		camera.zoom = 0.98f;
		camera.position.x = 400;
		camera.position.y = 240;

		camera.moveX(390, 0, 0, 100);
		camera.moveY(240, 0, 0, 100);
		camera.zoom(0.98f, 100f);

		dialogueWindow.popUp();
		cloudManager.stop();

		assetsManager.stars.setPosition(400, 480);
		// assetsManager.leaf.setPosition(-100, 200);
		// Not using this effect

		speedBar = new Bar(assetsManager.barFilled, assetsManager.barNotFilled,
				260, 10, 4);
		speedBar.setVisibility(true);

		skies = new Array<Sprite>();
		Sprite sky = new Sprite(assetsManager.sky[0]);
		sky.setPosition(0, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[2]);
		sky.setPosition(798, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[4]);
		sky.setPosition(1263, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[1]);
		sky.setPosition(2063, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[3]);
		sky.setPosition(2863, 200);
		skies.add(sky);

		sky = new Sprite(assetsManager.sky[5]);
		sky.setPosition(3328, 200);
		skies.add(sky);

		bushes = new Array<Sprite>();
		trees = new Array<Sprite>();
		lakes = new Array<Sprite>();
		stars = new Array<Sprite>();
		grassFlowers = new Array<Sprite>();

		for (int a = 0; a < 15; a++) {
			Sprite lake = null;
			lake = new Sprite(assetsManager.lake);
			lake.setPosition((a) * 1000 + MathUtils.random(0, 300),
					MathUtils.random(50, 100));
			lakes.add(lake);
		}
		for (int a = 0; a < 15; a++) {
			Sprite grass = null;
			grass = new Sprite(assetsManager.grassFlowers);
			grass.setPosition((a) * 1000 + MathUtils.random(0, 300),
					MathUtils.random(50, 100));
			grassFlowers.add(grass);
		}

		for (int a = 0; a < 80; a++) {
			if (a < 15 || (a > 35 && a < 50) || a > 65) {
				Sprite s = null;
				s = new Sprite(assetsManager.star);

				s.setPosition(a * 100 + 500, 80);
				stars.add(s);
			}
		}

		for (int a = 0; a < 50; a++) {
			boolean randomBoolean = MathUtils.randomBoolean();
			Sprite bush = null;
			if (a % 2 == 0)
				bush = new Sprite(assetsManager.bushes[0]);
			else
				bush = new Sprite(assetsManager.bushes[1]);

			if (randomBoolean == true)
				bush.setPosition((a) * 350 + MathUtils.random(0, 100),
						MathUtils.random(35, 60));
			else
				bush.setPosition((a) * 350 + MathUtils.random(0, 100),
						MathUtils.random(150, 180));

			bushes.add(bush);
		}

		for (int a = 0; a < 50; a++) {

			boolean randomBoolean = MathUtils.randomBoolean();
			Sprite tree = null;
			if (a % 3 == 0)
				tree = new Sprite(assetsManager.trees[2]);
			else if (a % 2 == 0)
				tree = new Sprite(assetsManager.trees[0]);
			else
				tree = new Sprite(assetsManager.trees[1]);

			if (randomBoolean == true)
				tree.setPosition((a) * 600 + MathUtils.random(0, 100),
						MathUtils.random(45, 60));
			else
				tree.setPosition((a) * 600 + MathUtils.random(0, 100),
						MathUtils.random(150, 180));

			trees.add(tree);

		}

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

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawSky(delta);
		batch.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);
		drawCharacters(delta);
		drawPointer(delta);

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
		boy.render(batch, delta);
		girl.render(batch, delta);
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

		if (boy.getSpeed() < 0)
			boy.setSpeed(0);

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
			girl.setSpeed(4f);
		}
		if (boy.getX() > 4000 && finish == false) {
			pause.setDontRespond(true);
			dialogueWindow.popUp();
			runButton.setDontRespond(true);
			finish = true;
			assetsManager.stars.start();
		}
		if (girl.getX() > 4000 && finish == false) {
			dialogueWindow.popUp();
			finish = true;
			runButton.setDontRespond(true);
		}
		if (girl.getX() > 4000) {
			girl.setSpeed(0);

			speedBar.setVisibility(false);
		}
		if (boy.getX() > 4000) {
			boy.setSpeed(0);
			speedBar.setVisibility(false);
		}
		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {

			camera.reset();
			camera.zoom = 0.98f;

			game.setScreen(new FitnessScreenTwo(game));
		}

		updateCameraLogics(delta);
		updateGirlAction(delta);

	}

	void updateCameraLogics(double delta) {
		if (boy.getX() >= 400 && boy.getX() < 3600) {
			camera.position.x = (boy.getX());
		}
	}

	void drawBackground(float delta) {

		drawGrassFlowers(delta);
		drawLakes(delta);
		drawBushes(delta);
		drawTrees(delta);
		drawStars(delta);

		if (boy.getX() <= 1200)
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
		if (boy.getX() <= 2000)
			batch.draw(assetsManager.parkBackgrounds[1], 789, 0);

		if (boy.getX() >= 1200 && boy.getX() < 2600)
			batch.draw(assetsManager.parkBackgrounds[2], 1589, 0);

		if (boy.getX() >= 1600 && boy.getX() < 3100)
			batch.draw(assetsManager.parkBackgrounds[5], 2049, 0);
		if (boy.getX() >= 2100)
			batch.draw(assetsManager.parkBackgrounds[4], 2514, 0);
		if (boy.getX() >= 2600)
			batch.draw(assetsManager.parkBackgrounds[3], 3314, 0);

	}

	void updateGirlAction(double delta) {
		if (girl.getX() < boy.getX() - 100)
			timerSpeedGirl += delta * 10;
		if (firstDialogueClicked == true)
			timerSpeedGirl += delta;
		if (timerSpeedGirl > 0.4) {
			timerSpeedGirl = 0;
			girl.move();
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
		// assetsManager.leaf.update(delta);
		// assetsManager.leaf.draw(batch);
		// if (boy.getSpeed() > 7) {
		// }
		// Leafes that fall down once the speed is higher than 7
		// Not using this, particle effect is incomplete
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {
		if (boy.getX() >= girl.getX())
			batch.draw(assetsManager.pointer, boy.getX() + 25, 180);
		if (girl.getX() > boy.getX())
			batch.draw(assetsManager.pointer, girl.getX() + 25, 180);
	}

	void drawBars(float delta) {

		batch.draw(assetsManager.speedBar, 160, 440);

		boyHead.setPosition(165 + boy.getX() * 0.1f, 410);
		girlHead.setPosition(165 + girl.getX() * 0.1f, 410);

		boyHead.draw(batch);
		girlHead.draw(batch);

		speedBar.render(batch, delta, boy.getSpeed());
	}

	void manageSelectingScreen() {
		if (inputInterpreter.getSelectedScreenName() == variables
				.getMenuScreen()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFitnessScreenOne()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setScreen(new FitnessScreenOne(game));
			}
		}
	}

	void drawFps() {
		fpsManager.render(batch);
	}

	void drawClouds(float delta) {
		cloudManager.render(batch, delta);
	}

	void drawSky(float delta) {
		batch.draw(assetsManager.grass, 0, 35);

		for (int a = 0; a < skies.size; a++) {
			if (skies.get(a).getX() <= 801 && skies.get(a).getX() > -801)
				skies.get(a).draw(batch);
			if (boy.getX() > 395 && girlPositionLastFrame != boy.getX()) {
				skies.get(a).setPosition(
						skies.get(a).getX() - delta * 2 * boy.getSpeed(),
						skies.get(a).getY());

			}
		}
		girlPositionLastFrame += delta;
		if (girlPositionLastFrame > 0.1f) {
			girlPositionLastFrame = boy.getX();
			girlPositionLastFrame = 0;
		}

	}

	void drawBushes(float delta) {

		float boySpeed = Math.abs(boy.getSpeed());
		if (Math.abs(boy.getSpeed()) > 3)
			boySpeed = 3;

		for (int a = 0; a < bushes.size; a++) {

			if (bushes.get(a).getX() - boy.getX() < 900
					&& bushes.get(a).getX() - boy.getX() > -1400
					|| (bushes.get(a).getX() > 12400 && boy.getX() >= 13500)) {
				bushes.get(a).draw(batch);

				if (boy.getX() >= 395 && (girlPositionLastFrame != boy.getX())
						&& boy.getX() <= 3600 && delta != 0)

					bushes.get(a).setPosition(
							(float) (bushes.get(a).getX() + (bushes.get(a)
									.getY() / 30) * boySpeed * 0.1f),
							bushes.get(a).getY());
			}
		}
	}

	void drawLakes(float delta) {

		float boySpeed = Math.abs(boy.getSpeed());
		if (Math.abs(boy.getSpeed()) > 3)
			boySpeed = 3;

		for (int a = 0; a < lakes.size; a++) {

			if (lakes.get(a).getX() - boy.getX() < 850
					&& lakes.get(a).getX() - boy.getX() > -1550
					|| (lakes.get(a).getX() > 12400 && boy.getX() >= 13500)) {
				lakes.get(a).draw(batch);

				if (boy.getX() >= 395 && (girlPositionLastFrame != boy.getX())
						&& boy.getX() <= 3600 && delta != 0)

					lakes.get(a)
							.setPosition(
									(float) (lakes.get(a).getX() + (lakes
											.get(a).getY() / 30)
											* boySpeed
											* 0.1f), lakes.get(a).getY());
			}
		}
	}

	void drawStars(float delta) {

		for (int a = 0; a < stars.size; a++) {
			float starAlpha = 1;
			starAlpha = stars.get(a).getColor().a;
			if (stars.get(a).getX() - boy.getX() < 950
					&& stars.get(a).getX() - boy.getX() > -1550
					|| (stars.get(a).getX() > 12400 && boy.getX() >= 13500)
					&& stars.get(a).getColor().a != 0) {
				stars.get(a).draw(batch);
			}

			if (boy.getX() + 50 > stars.get(a).getX()) {

				stars.get(a).setPosition(stars.get(a).getX(),
						stars.get(a).getY() + delta * 430);
				if (starAlpha - delta * 0.2f > 0) {
					stars.get(a).setAlpha(
							stars.get(a).getColor().a - 0.2f * delta);
					stars.get(a).rotate(delta * 350);
				} else {
					stars.get(a).setAlpha(0);
					// stars.removeIndex(a);
				}
			}
		}
	}

	void drawGrassFlowers(float delta) {

		float boySpeed = Math.abs(boy.getSpeed());
		if (Math.abs(boy.getSpeed()) > 3)
			boySpeed = 3;

		for (int a = 0; a < grassFlowers.size; a++) {

			if (grassFlowers.get(a).getX() - boy.getX() < 850
					&& grassFlowers.get(a).getX() - boy.getX() > -1650
					|| (lakes.get(a).getX() > 12400 && boy.getX() >= 13500)) {
				grassFlowers.get(a).draw(batch);

				if (boy.getX() >= 395 && (girlPositionLastFrame != boy.getX())
						&& boy.getX() <= 3600 && delta != 0)

					grassFlowers.get(a).setPosition(
							(float) (grassFlowers.get(a).getX() + (lakes.get(a)
									.getY() / 30) * boySpeed * 0.1f),
							grassFlowers.get(a).getY());
			}
		}
	}

	void drawTrees(float delta) {

		float boySpeed = Math.abs(boy.getSpeed());
		if (Math.abs(boy.getSpeed()) > 3)
			boySpeed = 3;

		for (int a = 0; a < trees.size; a++) {

			if ((trees.get(a).getX() - boy.getX()) < 850
					&& trees.get(a).getX() - boy.getX() > -1550
					|| (trees.get(a).getX() > 12900 && boy.getX() >= 13500)) {
				trees.get(a).draw(batch);

				if (boy.getX() >= 395 && (girlPositionLastFrame != boy.getX())
						&& boy.getX() <= 3600 && delta != 0)

					trees.get(a)
							.setPosition(
									(float) (trees.get(a).getX() + (trees
											.get(a).getY() / 30)
											* boySpeed
											* 0.1f), trees.get(a).getY());
			}
		}
	}

}