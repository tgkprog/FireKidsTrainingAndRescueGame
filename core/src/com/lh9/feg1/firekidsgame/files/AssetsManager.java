package com.lh9.feg1.firekidsgame.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.lh9.feg1.firekidsgame.utils.DataOrganizer;

public class AssetsManager extends Thread {

	DataOrganizer dataOrganizer;

	public Texture oilButton;
	public Texture boltButton;
	public Texture oil;
	public Texture bolt;
	public Texture barFilledBlue;
	public Texture barNotFilledBlue;
	public Texture voiceText;
	public Texture vibrationsText;
	public Texture textureFilteringText;
	public Texture fpsText;
	public Texture screenAwakeText;
	public Texture lane;
	public Texture boyTorso;
	public Texture authors;
	public Texture rescueMetroSadPeople;
	public Texture settingsText;
	public Texture authorsText;
	public Texture arrow;
	public Texture longButton;
	public Texture runButtonGreen;
	public Texture boyHeadBigBlonde;
	public Texture button;
	public Texture fireStation;
	public Texture pause;
	public Texture menu;
	public Texture carRed;
	public Texture carYellow;
	public Texture boyButton;
	public Texture girlButton;
	public Texture carPink;
	public Texture carBlue;
	public Texture carGreen;
	public Texture rescueCatBackground[];
	public Texture handTruckFront;
	public Texture truckLed;
	public Texture hoseAnimation[];
	public Texture fountainAnimation[];
	public Texture hoseAnimationReversed[];
	public Texture fireMiniature;
	public Texture truckBlank[];
	public Texture levelBackgrounds[];
	public Texture fitnessBackground[];
	public Texture bigRoad[];
	public Texture clouds[];
	public Texture trainBasketAnimation[];
	public Texture dialogueWindow;
	public Texture parkBackgrounds[];
	public Texture runButton;
	public Texture runButtonLittle;
	public Texture handwheelNoHand;
	public Texture wheel;
	public Texture boyMainMenu;
	public Texture girlMainMenu;
	public Texture boyMenuHand;
	public Texture spritesheetGirlRunning;
	public Texture spritesheetBoyRunning;
	public Texture boyWaving[];
	public Texture spritesheetBoyElliptical[];
	public Texture spritesheetGirlElliptical[];
	public Texture spritesheetGirlWeights[];
	public Texture spritesheetBoyWeights[];
	public Texture fireBar;
	public Texture pointer;
	public Texture bar;
	public Texture settings;
	public Texture boyHeadBig;
	public Texture girlHeadBig;
	public Texture girlHead;
	public Texture boyHead;
	public Texture switchButton;
	public Texture speedBar;
	public Texture playButton;
	public Texture retryButton;
	public Texture darkScreen;
	public Texture truckCockpit[];
	public Texture truckFront[];
	public Texture rescueMetro[];
	public Texture cockpitPart;
	public Texture ledCockpit;
	public Texture handSpritesheet;
	public Texture barNotFilled;
	public Texture barFilled;
	public Texture fireBig;
	public Texture peopleGround;
	public Texture peopleBuilding;
	public Texture handwheelBig;
	public Texture spritesheetTruck;
	public Texture mainBackground[];
	public Texture arrowUp;
	public Texture arrowDown;
	public Texture boyHeadCockpit;
	public Texture girlHeadCockpit;
	public Texture girlHandCockpit;
	public Texture bigRoadRescue;
	public Texture training;
	public Texture meetTheTrucks;
	public Texture rescueBuilding;
	public Texture rescueCat;
	public Texture fitness;
	public Texture rescueTrain;
	public Texture yellowSectionLeft;
	public Texture yellowSectionMiddle;
	public Texture yellowSectionUp;
	public Texture glass;

	public ParticleEffect stars;
	public ParticleEffect leaf;
	public ParticleEffect truckEmissions;
	public ParticleEffect buttonEffect;
	public ParticleEffect water;
	public ParticleEffect[] smoke;
	public ParticleEffect[] fireSmoke;

	public BitmapFont font;

	boolean assetsLoaded;

	public void run() {

		this.setPriority(MAX_PRIORITY);

		dataOrganizer = new DataOrganizer();
		dataOrganizer.loadData();

		loadFonts();
		loadParticles();
		loadTextures();
		setFilters();

		assetsLoaded = true;
	}

	public boolean getAssetsLoaded() {
		return assetsLoaded;
	}

	void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/comic-andy.regular.ttf"));
		// This font if from the internet
		// Free for commercial use license
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 120;
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;
		font = generator.generateFont(parameter);
		font.setColor(Color.BLACK);
	}

	void loadParticles() {
		stars = new ParticleEffect();
		stars.load(Gdx.files.internal("particles/starsGoodYellow180"),
				Gdx.files.internal("particles/"));
		water = new ParticleEffect();
		water.load(Gdx.files.internal("particles/water"),
				Gdx.files.internal("particles/"));
		leaf = new ParticleEffect();
		leaf.load(Gdx.files.internal("particles/leaf"),
				Gdx.files.internal("particles/"));
		truckEmissions = new ParticleEffect();
		truckEmissions.load(Gdx.files.internal("particles/truckEmissions"),
				Gdx.files.internal("particles/"));
		buttonEffect = new ParticleEffect();
		buttonEffect.load(Gdx.files.internal("particles/buttonEffect"),
				Gdx.files.internal("particles/"));
		buttonEffect.setPosition(730, 310);
		buttonEffect.allowCompletion();
		smoke = new ParticleEffect[12];
		for (int a = 0; a < 12; a++) {
			smoke[a] = new ParticleEffect();
			smoke[a].load(Gdx.files.internal("particles/truckEmissions"),
					Gdx.files.internal("particles/"));
		}
		fireSmoke = new ParticleEffect[6];
		for (int a = 0; a < 6; a++) {
			fireSmoke[a] = new ParticleEffect();
			fireSmoke[a].load(Gdx.files.internal("particles/fireSmoke"),
					Gdx.files.internal("particles/"));
			fireSmoke[a].start();
		}
	}

	void loadTextures() {
		barFilledBlue = new Texture("others/barFilledBlue.png");
		barNotFilledBlue = new Texture("others/barNotFilledBlue.png");
		oilButton = new Texture("buttons/buttonOil.png");
		boltButton = new Texture("buttons/buttonBolt.png");
		oil = new Texture("others/oil.png");
		bolt = new Texture("others/bolt.png");
		rescueMetroSadPeople = new Texture(
				"backgrounds/Rescue-metro-sad-people.png");
		spritesheetBoyRunning = new Texture(
				"spritesheets/spritesheetBoyRunning.png");
		spritesheetGirlRunning = new Texture(
				"spritesheets/spritesheetGirlRunning.png");
		darkScreen = new Texture("backgrounds/blackScreen.png");
		arrowUp = new Texture("buttons/arrow-up.png");
		arrowDown = new Texture("buttons/arrow-down.png");
		runButton = new Texture("buttons/runButton.png");
		runButtonLittle = new Texture("buttons/Button-2.png");
		runButtonGreen = new Texture("buttons/Button-1.png");
		settings = new Texture("buttons/settings.png");
		pointer = new Texture("others/this.png");
		dialogueWindow = new Texture("windows/windowPlaceholder.png");
		fireStation = new Texture("buttons/fireStation.png");
		settingsText = new Texture("buttons/Settings-text.png");
		authorsText = new Texture("buttons/Authors-text.png");
		pause = new Texture("buttons/Pause.png");
		switchButton = new Texture("buttons/Switch 3.png");
		playButton = new Texture("buttons/Play-button.png");
		retryButton = new Texture("buttons/Retry-button.png");
		menu = new Texture("buttons/Menu-Button.png");
		longButton = new Texture("buttons/greenButtonLong.png");
		arrow = new Texture("others/arrow.png");
		handwheelNoHand = new Texture("others/handwheelNoHand.png");
		button = new Texture("buttons/greenButton.png");
		wheel = new Texture("others/wheel.png");
		fireBar = new Texture("others/fireBar.png");
		girlHead = new Texture("girl/girlHead.png");
		boyHead = new Texture("boy/boyHead.png");
		voiceText = new Texture("texts/Voice-text.png");
		vibrationsText = new Texture("texts/Vibrations-text.png");
		textureFilteringText = new Texture("texts/Texture-filtering-text.png");
		fpsText = new Texture("texts/Fps-text.png");
		screenAwakeText = new Texture("texts/Screen-awake-text.png");
		speedBar = new Texture("others/speedBar.png");
		lane = new Texture("others/lane.png");
		authors = new Texture("buttons/Authors-button.png");
		carRed = new Texture("spritesheets/carRed.png");
		carYellow = new Texture("spritesheets/carYellow.png");
		carPink = new Texture("spritesheets/carPink.png");
		fireMiniature = new Texture("others/Fire3-1.png");
		carBlue = new Texture("spritesheets/carBlue.png");
		carGreen = new Texture("spritesheets/carGreen.png");
		handTruckFront = new Texture("boy/handTruckFront.png");
		truckLed = new Texture("others/truckLed.png");
		boyHeadBig = new Texture("boy/boyHeadBig.png");
		girlHeadBig = new Texture("girl/girlHeadBig.png");
		cockpitPart = new Texture("backgrounds/cockpitPart.png");
		ledCockpit = new Texture("others/ledCockpit.png");
		fireBig = new Texture("spritesheets/fireBig.png");
		boyButton = new Texture("buttons/Boy-button.png");
		girlButton = new Texture("buttons/Girl-button.png");
		peopleGround = new Texture("spritesheets/peopleGround.png");
		peopleBuilding = new Texture("spritesheets/peopleBuilding.png");
		barFilled = new Texture("others/barFilled.png");
		barNotFilled = new Texture("others/barNotFilled.png");
		yellowSectionUp = new Texture("buttons/ledRedSection3.png");
		yellowSectionMiddle = new Texture("buttons/yellowSection1.png");
		yellowSectionLeft = new Texture("buttons/yellowSection2.png");
		boyTorso = new Texture("boy/boyTorso.png");
		boyHeadBigBlonde = new Texture("boy/boyHeadBigBlonde.png");
		boyMainMenu = new Texture("boy/boyMainMenu.png");
		girlMainMenu = new Texture("girl/girlMainMenu.png");
		boyMenuHand = new Texture("boy/boyMenuHand.png");
		handSpritesheet = new Texture("others/spritesheetPress.png");
		boyHeadCockpit = new Texture("boy/boyHeadCockpit.png");
		girlHeadCockpit = new Texture("girl/girlHeadCockpit.png");
		girlHandCockpit = new Texture("girl/handCockpit.png");

		spritesheetGirlWeights = new Texture[3];
		spritesheetGirlElliptical = new Texture[7];
		spritesheetBoyElliptical = new Texture[7];
		spritesheetBoyWeights = new Texture[3];
		truckBlank = new Texture[1];
		truckBlank[0] = new Texture("others/Train-basket-blank.png");

		spritesheetGirlWeights[0] = new Texture(
				"girl/fitnessBenchGirl/Fitness-bench-press-Girl-1.png");
		spritesheetGirlWeights[1] = new Texture(
				"girl/fitnessBenchGirl/Fitness-bench-press-Girl-2.png");
		spritesheetGirlWeights[2] = new Texture(
				"girl/fitnessBenchGirl/Fitness-bench-press-Girl-3.png");

		spritesheetBoyWeights[0] = new Texture(
				"boy/fitnessBenchBoy/Fitness-bench-press-boy-1.png");
		spritesheetBoyWeights[1] = new Texture(
				"boy/fitnessBenchBoy/Fitness-bench-press-boy-2.png");
		spritesheetBoyWeights[2] = new Texture(
				"boy/fitnessBenchBoy/Fitness-bench-press-boy-3.png");

		spritesheetGirlElliptical[0] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-1.png");
		spritesheetGirlElliptical[1] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-2.png");
		spritesheetGirlElliptical[2] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-3.png");
		spritesheetGirlElliptical[3] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-4.png");
		spritesheetGirlElliptical[4] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-5.png");
		spritesheetGirlElliptical[5] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-6.png");
		spritesheetGirlElliptical[6] = new Texture(
				"girl/fitnessEllipticalGirl/Eliptical-Girl-7.png");

		spritesheetBoyElliptical[0] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-1.png");
		spritesheetBoyElliptical[1] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-2.png");
		spritesheetBoyElliptical[2] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-3.png");
		spritesheetBoyElliptical[3] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-4.png");
		spritesheetBoyElliptical[4] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-5.png");
		spritesheetBoyElliptical[5] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-6.png");
		spritesheetBoyElliptical[6] = new Texture(
				"boy/fitnessEllipticalBoy/Eliptical-7.png");

		bar = new Texture("others/bar.png");
		glass = new Texture("backgrounds/glass.png");
		handwheelBig = new Texture("others/handwheelBig.png");
		spritesheetTruck = new Texture("spritesheets/spritesheetTruck.png");

		bigRoadRescue = new Texture("buttons/bigRoadRescue.png");
		training = new Texture("buttons/training.png");
		meetTheTrucks = new Texture("buttons/meetTheTrucks.png");
		rescueBuilding = new Texture("buttons/rescueBuilding.png");
		rescueCat = new Texture("buttons/rescueCat.png");
		fitness = new Texture("buttons/fitness.png");
		rescueTrain = new Texture("buttons/rescueTrain.png");

		truckFront = new Texture[5];
		truckFront[0] = new Texture("backgrounds/truckFront1.png");
		truckFront[1] = new Texture("backgrounds/truckFront2.png");
		truckFront[2] = new Texture("backgrounds/truckFront3.png");
		truckFront[3] = new Texture("backgrounds/truckFront4.png");
		truckFront[4] = new Texture("backgrounds/truckFront5.png");

		rescueCatBackground = new Texture[4];
		rescueCatBackground[0] = new Texture("backgrounds/rescueCat/1.png");
		rescueCatBackground[1] = new Texture("backgrounds/rescueCat/2.png");
		rescueCatBackground[2] = new Texture("backgrounds/rescueCat/3.png");
		rescueCatBackground[3] = new Texture("backgrounds/rescueCat/4.png");

		rescueMetro = new Texture[2];
		rescueMetro[0] = new Texture("backgrounds/rescueMetro/1.png");
		rescueMetro[1] = new Texture("backgrounds/rescueMetro/2.png");

		boyWaving = new Texture[6];
		boyWaving[0] = new Texture("boy/Boy-waving-1.png");
		boyWaving[1] = new Texture("boy/Boy-waving-2.png");
		boyWaving[2] = new Texture("boy/Boy-waving-3.png");
		boyWaving[3] = new Texture("boy/Boy-waving-4.png");
		boyWaving[4] = new Texture("boy/Boy-waving-5.png");
		boyWaving[5] = new Texture("boy/Boy-waving-6.png");

		trainBasketAnimation = new Texture[14];
		trainBasketAnimation[0] = new Texture(
				"spritesheets/trainBasket/Train-basket-1.png");
		trainBasketAnimation[1] = new Texture(
				"spritesheets/trainBasket/Train-basket-2.png");
		trainBasketAnimation[2] = new Texture(
				"spritesheets/trainBasket/Train-basket-3.png");
		trainBasketAnimation[3] = new Texture(
				"spritesheets/trainBasket/Train-basket-4.png");
		trainBasketAnimation[4] = new Texture(
				"spritesheets/trainBasket/Train-basket-5.png");
		trainBasketAnimation[5] = new Texture(
				"spritesheets/trainBasket/Train-basket-6.png");
		trainBasketAnimation[6] = new Texture(
				"spritesheets/trainBasket/Train-basket-7.png");
		trainBasketAnimation[7] = new Texture(
				"spritesheets/trainBasket/Train-basket-8.png");
		trainBasketAnimation[8] = new Texture(
				"spritesheets/trainBasket/Train-basket-9.png");
		trainBasketAnimation[9] = new Texture(
				"spritesheets/trainBasket/Train-basket-10.png");
		trainBasketAnimation[10] = new Texture(
				"spritesheets/trainBasket/Train-basket-11.png");
		trainBasketAnimation[11] = new Texture(
				"spritesheets/trainBasket/Train-basket-12.png");
		trainBasketAnimation[12] = new Texture(
				"spritesheets/trainBasket/Train-basket-13.png");
		trainBasketAnimation[13] = new Texture(
				"spritesheets/trainBasket/Train-basket-14.png");

		bigRoad = new Texture[24];
		bigRoad[0] = new Texture("backgrounds/bigRoad/bigRoad1B.png");
		bigRoad[1] = new Texture("backgrounds/bigRoad/bigRoad1A.png");
		bigRoad[2] = new Texture("backgrounds/bigRoad/bigRoad2B.png");
		bigRoad[3] = new Texture("backgrounds/bigRoad/bigRoad2A.png");
		bigRoad[4] = new Texture("backgrounds/bigRoad/bigRoad3B.png");
		bigRoad[5] = new Texture("backgrounds/bigRoad/bigRoad3A.png");
		bigRoad[6] = new Texture("backgrounds/bigRoad/bigRoad4B.png");
		bigRoad[7] = new Texture("backgrounds/bigRoad/bigRoad4A.png");
		bigRoad[8] = new Texture("backgrounds/bigRoad/bigRoad5B.png");
		bigRoad[9] = new Texture("backgrounds/bigRoad/bigRoad5A.png");
		bigRoad[10] = new Texture("backgrounds/bigRoad/bigRoad6B.png");
		bigRoad[11] = new Texture("backgrounds/bigRoad/bigRoad6A.png");
		bigRoad[12] = new Texture("backgrounds/bigRoad/bigRoad7B.png");
		bigRoad[13] = new Texture("backgrounds/bigRoad/bigRoad7A.png");
		bigRoad[14] = new Texture("backgrounds/bigRoad/bigRoad8B.png");
		bigRoad[15] = new Texture("backgrounds/bigRoad/bigRoad8A.png");
		bigRoad[16] = new Texture("backgrounds/bigRoad/bigRoad9B.png");
		bigRoad[17] = new Texture("backgrounds/bigRoad/bigRoad9A.png");
		bigRoad[18] = new Texture("backgrounds/bigRoad/bigRoad10B.png");
		bigRoad[19] = new Texture("backgrounds/bigRoad/bigRoad10A.png");
		bigRoad[20] = new Texture("backgrounds/bigRoad/bigRoad11B.png");
		bigRoad[21] = new Texture("backgrounds/bigRoad/bigRoad11A.png");
		bigRoad[22] = new Texture("backgrounds/bigRoad/bigRoad12B.png");
		bigRoad[23] = new Texture("backgrounds/bigRoad/bigRoad12A.png");

		truckCockpit = new Texture[4];

		truckCockpit[0] = new Texture("backgrounds/truckCockpit1.png");
		truckCockpit[1] = new Texture("backgrounds/truckCockpit2.png");
		truckCockpit[2] = new Texture("backgrounds/truckCockpit3.png");
		truckCockpit[3] = new Texture("backgrounds/truckCockpit4.png");

		levelBackgrounds = new Texture[1];
		levelBackgrounds[0] = new Texture(
				"backgrounds/Station-meet-trucks_0.png");

		hoseAnimation = new Texture[15];
		hoseAnimation[0] = new Texture("girl/hoseAnimation/1.png");
		hoseAnimation[1] = new Texture("girl/hoseAnimation/2.png");
		hoseAnimation[2] = new Texture("girl/hoseAnimation/3.png");
		hoseAnimation[3] = new Texture("girl/hoseAnimation/4.png");
		hoseAnimation[4] = new Texture("girl/hoseAnimation/5.png");
		hoseAnimation[5] = new Texture("girl/hoseAnimation/6.png");
		hoseAnimation[6] = new Texture("girl/hoseAnimation/7.png");
		hoseAnimation[7] = new Texture("girl/hoseAnimation/8.png");
		hoseAnimation[8] = new Texture("girl/hoseAnimation/9.png");
		hoseAnimation[9] = new Texture("girl/hoseAnimation/10.png");
		hoseAnimation[10] = new Texture("girl/hoseAnimation/11.png");
		hoseAnimation[11] = new Texture("girl/hoseAnimation/12.png");
		hoseAnimation[12] = new Texture("girl/hoseAnimation/13.png");
		hoseAnimation[13] = new Texture("girl/hoseAnimation/14.png");
		hoseAnimation[14] = new Texture("girl/hoseAnimation/15.png");

		fitnessBackground = new Texture[4];
		fitnessBackground[0] = new Texture("backgrounds/fitness/fitness1.png");
		fitnessBackground[1] = new Texture("backgrounds/fitness/fitness2.png");
		fitnessBackground[2] = new Texture("backgrounds/fitness/fitness3.png");
		fitnessBackground[3] = new Texture("backgrounds/fitness/fitness4.png");

		fountainAnimation = new Texture[5];
		fountainAnimation[0] = new Texture(
				"spritesheets/fountainAnimation/1.png");
		fountainAnimation[1] = new Texture(
				"spritesheets/fountainAnimation/2.png");
		fountainAnimation[2] = new Texture(
				"spritesheets/fountainAnimation/3.png");
		fountainAnimation[3] = new Texture(
				"spritesheets/fountainAnimation/4.png");
		fountainAnimation[4] = new Texture(
				"spritesheets/fountainAnimation/5.png");

		parkBackgrounds = new Texture[6];
		parkBackgrounds[0] = new Texture("backgrounds/park1.png");
		parkBackgrounds[1] = new Texture("backgrounds/park2.png");
		parkBackgrounds[2] = new Texture("backgrounds/park3.png");
		parkBackgrounds[3] = new Texture("backgrounds/park1reversed.png");
		parkBackgrounds[4] = new Texture("backgrounds/park2reversed.png");
		parkBackgrounds[5] = new Texture("backgrounds/park3reversed.png");

		clouds = new Texture[3];
		clouds[0] = new Texture("others/cartoonCloud.png");
		clouds[1] = new Texture("others/cartoonCloud.png");
		clouds[2] = new Texture("others/cartoonCloud.png");

		mainBackground = new Texture[4];
		mainBackground[0] = new Texture("backgrounds/mainBackground1.png");
		mainBackground[1] = new Texture("backgrounds/mainBackground2.png");
		mainBackground[2] = new Texture("backgrounds/mainBackground3.png");
		mainBackground[3] = new Texture("backgrounds/mainBackground4.png");

	}

	void setFilters() {

		TextureFilter textureFilter;

		if (dataOrganizer.getTextureFiltering() == true)
			textureFilter = TextureFilter.Linear;
		else
			textureFilter = TextureFilter.Nearest;

		for (int a = 0; a < 4; a++) {
			rescueCatBackground[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 7; a++) {
			spritesheetBoyElliptical[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 7; a++) {
			spritesheetGirlElliptical[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			spritesheetGirlWeights[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			spritesheetBoyWeights[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 9; a++) {
			trainBasketAnimation[a].setFilter(textureFilter,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 1; a++) {
			levelBackgrounds[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			fitnessBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 6; a++) {
			parkBackgrounds[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			rescueMetro[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 6; a++) {
			boyWaving[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			clouds[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			mainBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			truckCockpit[a].setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
		}
		for (int a = 0; a < 5; a++) {
			truckFront[a].setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
		}
		for (int a = 0; a < 15; a++) {
			hoseAnimation[a].setFilter(textureFilter, textureFilter);
		}
		hoseAnimationReversed = new Texture[15];
		for (int a = 0; a < 15; a++) {
			hoseAnimationReversed[a] = hoseAnimation[14 - a];
		}

		for (int a = 0; a < 24; a++) {
			bigRoad[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 5; a++) {
			fountainAnimation[a].setFilter(textureFilter, textureFilter);
		}

		spritesheetGirlRunning.setFilter(textureFilter, textureFilter);
		spritesheetBoyRunning.setFilter(textureFilter, textureFilter);
		pause.setFilter(textureFilter, textureFilter);
		menu.setFilter(textureFilter, textureFilter);
		barFilled.setFilter(textureFilter, textureFilter);
		barNotFilled.setFilter(textureFilter, textureFilter);
		longButton.setFilter(textureFilter, textureFilter);
		arrow.setFilter(textureFilter, textureFilter);
		button.setFilter(textureFilter, textureFilter);
		rescueMetroSadPeople.setFilter(textureFilter, textureFilter);
		fireStation.setFilter(textureFilter, textureFilter);
		boyButton.setFilter(textureFilter, textureFilter);
		girlButton.setFilter(textureFilter, textureFilter);
		runButton.setFilter(textureFilter, textureFilter);
		runButtonGreen.setFilter(textureFilter, textureFilter);
		runButtonLittle.setFilter(textureFilter, textureFilter);
		pointer.setFilter(textureFilter, textureFilter);
		speedBar.setFilter(textureFilter, textureFilter);
		switchButton.setFilter(textureFilter, textureFilter);
		lane.setFilter(textureFilter, textureFilter);
		carRed.setFilter(textureFilter, textureFilter);
		carYellow.setFilter(textureFilter, textureFilter);
		carPink.setFilter(textureFilter, textureFilter);
		retryButton.setFilter(textureFilter, textureFilter);
		playButton.setFilter(textureFilter, textureFilter);
		carBlue.setFilter(textureFilter, textureFilter);
		carGreen.setFilter(textureFilter, textureFilter);
		vibrationsText.setFilter(textureFilter, textureFilter);
		fpsText.setFilter(textureFilter, textureFilter);
		screenAwakeText.setFilter(textureFilter, textureFilter);
		textureFilteringText.setFilter(textureFilter, textureFilter);
		voiceText.setFilter(textureFilter, textureFilter);
		barFilledBlue.setFilter(textureFilter, textureFilter);
		barNotFilledBlue.setFilter(textureFilter, textureFilter);
		settings.setFilter(textureFilter, textureFilter);
		cockpitPart.setFilter(textureFilter, textureFilter);
		ledCockpit.setFilter(textureFilter, textureFilter);
		handSpritesheet.setFilter(textureFilter, textureFilter);
		boyHeadBig.setFilter(textureFilter, textureFilter);
		girlHeadBig.setFilter(textureFilter, textureFilter);
		glass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		handwheelBig.setFilter(textureFilter, textureFilter);
		spritesheetTruck.setFilter(textureFilter, textureFilter);
		wheel.setFilter(textureFilter, textureFilter);
		arrowUp.setFilter(textureFilter, textureFilter);
		arrowDown.setFilter(textureFilter, textureFilter);
		fireBar.setFilter(textureFilter, textureFilter);
		handTruckFront.setFilter(textureFilter, textureFilter);
		handwheelNoHand.setFilter(textureFilter, textureFilter);
		boyHeadCockpit.setFilter(textureFilter, textureFilter);
		girlHeadCockpit.setFilter(textureFilter, textureFilter);
		girlHandCockpit.setFilter(textureFilter, textureFilter);
		fireBig.setFilter(textureFilter, textureFilter);
		peopleGround.setFilter(textureFilter, textureFilter);
		peopleBuilding.setFilter(textureFilter, textureFilter);
		bigRoadRescue.setFilter(textureFilter, textureFilter);
		training.setFilter(textureFilter, textureFilter);
		truckLed.setFilter(textureFilter, textureFilter);
		meetTheTrucks.setFilter(textureFilter, textureFilter);
		rescueBuilding.setFilter(textureFilter, textureFilter);
		rescueCat.setFilter(textureFilter, textureFilter);
		fitness.setFilter(textureFilter, textureFilter);
		rescueTrain.setFilter(textureFilter, textureFilter);
		yellowSectionUp.setFilter(textureFilter, textureFilter);
		yellowSectionLeft.setFilter(textureFilter, textureFilter);
		yellowSectionMiddle.setFilter(textureFilter, textureFilter);
		settingsText.setFilter(textureFilter, textureFilter);
		boyTorso.setFilter(textureFilter, textureFilter);
		boyHeadBigBlonde.setFilter(textureFilter, textureFilter);
		authorsText.setFilter(textureFilter, textureFilter);
		fireMiniature.setFilter(textureFilter, textureFilter);
		boyMainMenu.setFilter(textureFilter, textureFilter);
		girlMainMenu.setFilter(textureFilter, textureFilter);
		oilButton.setFilter(textureFilter, textureFilter);
		boltButton.setFilter(textureFilter, textureFilter);
		oil.setFilter(textureFilter, textureFilter);
		bolt.setFilter(textureFilter, textureFilter);
		boyMenuHand.setFilter(textureFilter, textureFilter);
		truckBlank[0].setFilter(textureFilter, textureFilter);
		authors.setFilter(textureFilter, textureFilter);
	}
}