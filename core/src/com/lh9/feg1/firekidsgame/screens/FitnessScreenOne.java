package com.lh9.feg1.firekidsgame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

	DataOrganizer dataOrganizer;
	FPSManager fpsManager;
	MenuWindow menuWindow;
	Bar speedBar;
	Dialogue dialogueWindow;
	CloudManager cloudManager;
	Variables variables;
	AssetsManager assetsManager;
	Camera camera;
	OrthographicCamera guiCamera;
	SpriteBatch batch;
	InputInterpreter inputInterpreter;

	Array<Sprite> bushes;
	Array<Sprite> trees;
	Array<Sprite> lakes;
	Array<Sprite> grassFlowers;
	Array<Sprite> stars;
	Array<Sprite> skies;
	ArrayList<Sprite> footMarks;
	Sprite playerHead;
	Sprite npcHead;
	Sprite guiStar;

	Human player;
	Human npc;

	Button menuButton;
	Button retryButton;
	Button playButton;
	Button pause;
	Button runButton;

	boolean victory;
	boolean exit;
	boolean firstDialogueClicked = false;
	boolean secondDialogueClicked = false;
	boolean finish = false;
	boolean enlargeStar;
	boolean leftFootnpc;
	boolean leftFootplayer = true;
	float footmarkSpawnTimernpc;
	float footmarkSpawnTimerplayer;
	float timerSpeednpc;
	float npcPositionLastFrame;
	float npcPositionTimer;
	int starsCollected = 0;
	int starsCollectedLastFrame;
	int starsAll = 0;

	final Starter game;

	public FitnessScreenOne(final Starter gam) {

		this.game = gam;
		
		cloudManager = game.getCloudManager();
		camera = game.getCamera();
		guiCamera = game.getGuiCamera();
		batch = game.getBatch();
		assetsManager = game.getAssetsManager();
		variables = new Variables();
		assetsManager.fontLittle.setColor(Color.WHITE);
		pause = new Button((int) variables.getPAUSE_BUTTON_POSITION().x, 120,
				assetsManager.pause);
		pause.goUp((int) variables.getPAUSE_BUTTON_POSITION().y);
		runButton = new Button(685, -200, assetsManager.runButtonLittle);
		runButton.goUp(30);
		runButton.setAlpha(0.5f);
		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();
		
		if (dataOrganizer.getGender() == true)
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowGirl,
					assetsManager.darkScreen, 250f, 150f,
					Variables.FITNESS_SCREEN_ONE_POP_UP_1, assetsManager.fontLittle);
		else
			dialogueWindow = new Dialogue(assetsManager.dialogueWindowBoy,
					assetsManager.darkScreen, 250f, 150f,
					Variables.MEET_THE_TRUCKS_POP_UP_1, assetsManager.fontLittle);
		
		
		fpsManager = new FPSManager(assetsManager.font, dataOrganizer.getFps());

		player = new Human();
		npc = new Human();

		if (dataOrganizer.getGender() == false) {
			player.create(assetsManager.spritesheetBoyRunning, 31, 1, 31, -100,
					35);

			npc.create(assetsManager.spritesheetGirlRunning, 31, 1, 31, -100,
					35);
			playerHead = new Sprite(assetsManager.boyButton);
			npcHead = new Sprite(assetsManager.girlButton);
		} else {
			player.create(assetsManager.spritesheetGirlRunning, 31, 1, 31,
					-100, 35);
			npc.create(assetsManager.spritesheetBoyRunning, 31, 1, 31, -100, 35);
			playerHead = new Sprite(assetsManager.girlButton);
			npcHead = new Sprite(assetsManager.boyButton);
		}

		player.setMaxSpeed(4);
		player.setSpeedAdder(0.4f);

		npc.setMaxSpeed(4);
		npc.setSpeedAdder(0.4f);

		npc.setAnimationTime(0.035f);
		player.setAnimationTime(0.035f);
		playerHead.setScale(0.5f);
		npcHead.setScale(0.5f);

		menuButton = new Button(400, 0, assetsManager.menu);
		playButton = new Button(450, 0, assetsManager.playButton);
		retryButton = new Button(500, 0, assetsManager.retryButton);
		playButton.goUp(300);
		retryButton.goUp(300);
		menuButton.goUp(300);

		menuWindow = new MenuWindow(null, assetsManager.darkScreen, 250, 200,
				menuButton, retryButton, playButton,
				variables.getFITNESS_SCREEN_ONE());

		inputInterpreter = new InputInterpreter();
		inputInterpreter.setCameras(camera, guiCamera);
		inputInterpreter.setCloudManager(cloudManager);
		inputInterpreter.setPauseButton(pause);
		inputInterpreter.setDialogueWindow(dialogueWindow);
		inputInterpreter.setRunButton(runButton);
		inputInterpreter.setControlledHuman(player);
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
			if (a < 15 || (a > 22 && a < 30)) {
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

		guiStar = new Sprite(assetsManager.star);
		guiStar.setScale(0.75f);
		guiStar.setPosition(0, 430);
		footMarks = new ArrayList<Sprite>();

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

		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();
		drawSky(delta);
		batch.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawBackground(delta);
		drawFootmarks(delta);
		drawCharacters(delta);
		drawPointer(delta);

		batch.end();
		batch.setProjectionMatrix(guiCamera.combined);
		batch.begin();

		drawGuiStarsCounter(delta);
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
		player.render(batch, delta);
		npc.render(batch, delta);
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

		if (exit == true)
			pause.setDontRespond(true);

		if (menuWindow.isVisibile() == true) {
			runButton.setDontRespond(true);
		} else {
			runButton.setDontRespond(false);
		}

		if (player.getSpeed() < 0)
			player.setSpeed(0);

		if (firstDialogueClicked == false
				&& dialogueWindow.isVisibile() == false) {
			firstDialogueClicked = true;
			npc.setSpeed(4f);
		}
		if (player.getX() > 4000 && finish == false) {
			pause.setDontRespond(true);
			victory = true;
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star, assetsManager.starSummary, assetsManager.starSummaryDesaturated, 3, starsCollected,true);
			dialogueWindow.popUp();
			runButton.setDontRespond(true);
			finish = true;
			assetsManager.stars.start();
		
		}
		if (npc.getX() > 4000 && finish == false) {
			victory = false;
			pause.setDontRespond(true);
			dialogueWindow.drawLevelSummary(assetsManager.cog,assetsManager.star, assetsManager.starSummary, assetsManager.starSummaryDesaturated, 3, starsCollected,false);
			dialogueWindow.popUp();
			finish = true;
			runButton.setDontRespond(true);
		}
		if (npc.getX() > 4000) {
			npc.setSpeed(0);

			speedBar.setVisibility(false);
		}
		if (player.getX() > 4000) {
			player.setSpeed(0);
			speedBar.setVisibility(false);
		}
		if (finish == true && dialogueWindow.isVisibile() == false
				&& exit == false) {
			cloudManager.start();
			exit = true;
		}

		updateCameraLogics(delta);
		updatenpcAction(delta);

	}

	void updateCameraLogics(double delta) {
		if (player.getX() >= 395 && player.getX() <= 3600) {
			camera.position.x = (player.getX());
		}
	}

	void drawBackground(float delta) {

		drawGrassFlowers(delta);
		drawLakes(delta);
		drawBushes(delta);
		drawTrees(delta);
		drawStars(delta);

		if (player.getX() <= 1200)
			batch.draw(assetsManager.parkBackgrounds[0], -10, 0);
		if (player.getX() <= 2000)
			batch.draw(assetsManager.parkBackgrounds[1], 789, 0);

		if (player.getX() >= 1200 && player.getX() < 2600)
			batch.draw(assetsManager.parkBackgrounds[2], 1589, 0);

		if (player.getX() >= 1600 && player.getX() < 3100)
			batch.draw(assetsManager.parkBackgrounds[5], 2049, 0);
		if (player.getX() >= 2100)
			batch.draw(assetsManager.parkBackgrounds[4], 2514, 0);
		if (player.getX() >= 2600)
			batch.draw(assetsManager.parkBackgrounds[3], 3314, 0);

	}

	void updatenpcAction(double delta) {
		if (npc.getX() < player.getX() - 100)
			timerSpeednpc += delta * 10;
		if (firstDialogueClicked == true)
			timerSpeednpc += delta;
		if (timerSpeednpc > 0.4) {
			timerSpeednpc = 0;
			npc.move();
		}
	}

	void drawParticles(float delta) {
		assetsManager.stars.update(delta);
		assetsManager.stars.draw(batch);
		// assetsManager.leaf.update(delta);
		// assetsManager.leaf.draw(batch);
		// if (player.getSpeed() > 7) {
		// }
		// Leafes that fall down once the speed is higher than 7
		// Not using this, particle effect is incomplete
	}

	void drawParticlesNonGui(float delta) {
	}

	void drawPointer(float delta) {
		if (player.getX() >= npc.getX())
			batch.draw(assetsManager.pointer, player.getX() + 25, 180);
		if (npc.getX() > player.getX())
			batch.draw(assetsManager.pointer, npc.getX() + 25, 180);
	}

	void drawBars(float delta) {

		batch.draw(assetsManager.speedBar, 200, 440);

		playerHead.setPosition(205 + player.getX() * 0.1f, 410);
		npcHead.setPosition(205 + npc.getX() * 0.1f, 410);

		playerHead.draw(batch);
		npcHead.draw(batch);

		speedBar.render(batch, delta, player.getSpeed());
	}

	void manageSelectingScreen() {
		if (cloudManager.getAllScalesEqualOne() == true && exit == true) {
			game.setCollectedStars(starsCollected + starsAll);
		if(victory == true)
			game.setScreen(new FitnessScreenTwo(game));
		else
			game.setScreen(new FitnessScreenOne(game));
				
		}

		if (inputInterpreter.getSelectedScreenName() == variables
				.getMENU_SCREEN()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
				game.setScreen(new MenuScreen(game));
			}
		}
		if (inputInterpreter.getSelectedScreenName() == variables
				.getFITNESS_SCREEN_ONE()) {
			if (cloudManager.getAllScalesEqualOne() == true) {
				game.setCollectedStars(starsCollected + starsAll);
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
			if (player.getX() > 395 && npcPositionLastFrame != player.getX()) {
				skies.get(a).setPosition(
						skies.get(a).getX() - delta * 2 * player.getSpeed(),
						skies.get(a).getY());

			}
		}
		npcPositionLastFrame += delta;
		if (npcPositionLastFrame > 0.1f) {
			npcPositionLastFrame = player.getX();
			npcPositionLastFrame = 0;
		}

	}

	void drawBushes(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < bushes.size; a++) {

			if ((player.getX() <= 400 && bushes.get(a).getX() < 800)
					|| player.getX() >= 3600
					|| (bushes.get(a).getX() - player.getX() < 400 && bushes
							.get(a).getWidth()
							+ bushes.get(a).getX()
							- player.getX() > -400)) {
				bushes.get(a).draw(batch);

				if (player.getX() >= 400
						&& (npcPositionLastFrame != player.getX())
						&& player.getX() <= 3600 && delta != 0)

					bushes.get(a).setPosition(
							(float) (bushes.get(a).getX() + (bushes.get(a)
									.getY() / 30) * playerSpeed * 0.1f),
							bushes.get(a).getY());
			}
		}
	}

	void drawLakes(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < lakes.size; a++) {

			if ((player.getX() <= 400 && bushes.get(a).getX() < 800)
					|| player.getX() >= 3600
					|| (lakes.get(a).getX() - player.getX() < 400 && lakes.get(
							a).getWidth()
							+ lakes.get(a).getX() - player.getX() > -400)) {
				lakes.get(a).draw(batch);

				if (player.getX() >= 400
						&& (npcPositionLastFrame != player.getX())
						&& player.getX() <= 3600 && delta != 0)

					lakes.get(a)
							.setPosition(
									(float) (lakes.get(a).getX() + (lakes
											.get(a).getY() / 30)
											* playerSpeed
											* 0.1f), lakes.get(a).getY());
			}
		}
	}

	void drawStars(float delta) {
		starsCollected = 0;
		for (int a = 0; a < stars.size; a++) {
			float starAlpha = 1;
			starAlpha = stars.get(a).getColor().a;
			if ((player.getX() <= 400 && stars.get(a).getX() < 800)
					|| player.getX() >= 3600
					|| (stars.get(a).getX() - player.getX() < 400 && stars.get(
							a).getWidth()
							+ stars.get(a).getX() - player.getX() > -400)) {
				stars.get(a).draw(batch);
			}

			if (player.getX() + 50 > stars.get(a).getX()) {
				starsCollected++;
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
		if (starsCollected > starsCollectedLastFrame)
			enlargeStar = true;

		starsCollectedLastFrame = starsCollected;
	}

	void drawGrassFlowers(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < grassFlowers.size; a++) {
			if ((player.getX() <= 400 && bushes.get(a).getX() < 800)
					|| player.getX() >= 3600
					|| (grassFlowers.get(a).getX() - player.getX() < 400 && grassFlowers
							.get(a).getWidth()
							+ grassFlowers.get(a).getX()
							- player.getX() > -400)) {
				grassFlowers.get(a).draw(batch);

				if (player.getX() >= 400
						&& (npcPositionLastFrame != player.getX())
						&& player.getX() <= 3600 && delta != 0)

					grassFlowers.get(a).setPosition(
							(float) (grassFlowers.get(a).getX() + (grassFlowers
									.get(a).getY() / 30) * playerSpeed * 0.1f),
							grassFlowers.get(a).getY());
			}
		}
	}

	void drawTrees(float delta) {

		float playerSpeed = Math.abs(player.getSpeed());
		if (Math.abs(player.getSpeed()) > 3)
			playerSpeed = 3;

		for (int a = 0; a < trees.size; a++) {

			if ((player.getX() <= 400 && trees.get(a).getX() < 800)
					|| player.getX() >= 3600
					|| (trees.get(a).getX() - player.getX() < 400 && trees.get(
							a).getWidth()
							+ trees.get(a).getX() - player.getX() > -400)) {
				trees.get(a).draw(batch);

				if (player.getX() >= 400
						&& (npcPositionLastFrame != player.getX())
						&& player.getX() <= 3600 && delta != 0)

					trees.get(a)
							.setPosition(
									(float) (trees.get(a).getX() + (trees
											.get(a).getY() / 30)
											* playerSpeed
											* 0.1f), trees.get(a).getY());
			}
		}
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

	void drawFootmarks(float delta) {

		footmarkSpawnTimernpc += delta;
		if (footmarkSpawnTimernpc > 0.15f) {
			footmarkSpawnTimernpc = 0;
			Sprite s = new Sprite(assetsManager.foot);
			if (leftFootnpc == true) {
				s.setPosition(npc.getX() + 10 + npc.getSpeed(), 37);
				leftFootnpc = false;
			} else {
				s.setPosition(npc.getX() + 10 + npc.getSpeed(), 32);
				leftFootnpc = true;
			}
			footMarks.add(s);
		}
		footmarkSpawnTimerplayer += delta;
		if (footmarkSpawnTimerplayer > 0.15f) {
			footmarkSpawnTimerplayer = 0;
			Sprite d = new Sprite(assetsManager.foot);
			if (leftFootplayer == true) {
				d.setPosition(player.getX() + 10 + player.getSpeed(), 37);
				leftFootplayer = false;
			} else {
				d.setPosition(player.getX() + 10 + player.getSpeed(), 32);
				leftFootplayer = true;
			}
			footMarks.add(d);
		}

		for (int a = 0; a < footMarks.size(); a++) {
			if (footMarks.get(a).getColor().a > 0)
				footMarks.get(a).setColor(1, 1, 1,
						footMarks.get(a).getColor().a - delta * 0.75f);

			if (footMarks.get(a).getColor().a <= 0.1f) {
				footMarks.remove(a);
			} else
				footMarks.get(a).draw(batch);
		}

	}
}