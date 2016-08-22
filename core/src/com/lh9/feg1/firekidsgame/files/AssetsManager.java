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

	public Texture starButton;
	public Texture starButtonEmpty;
	public Texture fireStationComplete;
	public Texture[] food;
	public Texture star;
	public Texture grassFlowers;
	public Texture[] trees;
	public Texture[] girlRunningSuit;
	public Texture[] boyRunningSuit;
	public Texture[] bushes;
	public Texture[] foodBackground;
	public Texture[] metroDoor;
	public Texture cloudyBackgroundClose;
	public Texture cloudyBackgroundFar;
	public Texture truckBack;
	public Texture grass;
	public Texture[] sky;
	public Texture tree;
	public Texture truckBackDoor;
	public Texture[] girlHoseHydrant;
	public Texture[] boyHoseHydrant;
	public Texture[] girlHoseHydrantReversed;
	public Texture[] boyHoseHydrantReversed;
	public Texture catMiniature;
	public Texture truckMiniature;
	public Texture programmingText;
	public Texture ground;
	public Texture graphicsText;
	public Texture trainMiniature;
	public Texture speedometer;
	public Texture elevatorMiniature;
	public Texture elevatorButton;
	public Texture hoseHydrant;
	public Texture dogsGirl;
	public Texture damage;
	public Texture sign;
	public Texture breakTheWallText;
	public Texture eclipseFireText;
	public Texture jumpText;
	public Texture[] wall;
	public Texture rideTruckText;
	public Texture[] girlHammer;
	public Texture[] boyHammer;
	public Texture[] boyHammer_16_percent;
	public Texture[] girlHammer_16_percent;
	public Texture helmet1;
	public Texture helmet2;
	public Texture daniel;
	public Texture nevena;
	public Texture tushar;
	public Texture catHappy;
	public Texture catSad;
	public Texture[] elevatorDoor;
	public Texture[] dogWorried;
	public Texture[] dogHappy;
	public Texture[] elevatorBackground;
	public Texture[] buzzer;
	public Texture oilButton;
	public Texture boltButton;
	public Texture oil;
	public Texture bolt;
	public Texture barFilledBlue;
	public Texture basket;
	public Texture truckNoBasket[];
	public Texture barNotFilledBlue;
	public Texture voiceText;
	public Texture vibrationsText;
	public Texture roadPart;
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
	public Texture foot;
	public Texture boyHeadBigBlonde;
	public Texture button;
	public Texture fireStation;
	public Texture pause;
	public Texture menu;

	public Texture carRedButton;
	public Texture carYellowButton;
	public Texture carPinkButton;
	public Texture carBlueButton;
	public Texture carGreenButton;

	public Texture carRed;
	public Texture carYellow;
	public Texture carPink;
	public Texture carBlue;
	public Texture carGreen;

	public Texture boyButtonButton;
	public Texture girlButtonButton;
	public Texture boyButton;
	public Texture girlButton;
	public Texture rescueCatBackground[];
	public Texture handTruckFront;
	public Texture truckLed;
	public Texture lake;
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

	public Texture[] spritesheetGirlRunning;
	public Texture[] spritesheetBoyRunning;

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
	public Texture barrel;
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
	public Texture womanHappy;

	public ParticleEffect running;
	public ParticleEffect stars;
	public ParticleEffect leaf;
	public ParticleEffect hit;
	public ParticleEffect truckEmissions;
	public ParticleEffect buttonEffect;
	public ParticleEffect water;
	public ParticleEffect[] smoke;
	public ParticleEffect[] fireSmoke;

	public BitmapFont font;
	public BitmapFont fontLittle;

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
		
		parameter.size = 50;
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;
		fontLittle = generator.generateFont(parameter);
		fontLittle.setColor(Color.BLACK);
		
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
		hit = new ParticleEffect();
		hit.load(Gdx.files.internal("particles/hit"),
				Gdx.files.internal("particles/"));

		running = new ParticleEffect();
		running.load(Gdx.files.internal("particles/running"),
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
		dogsGirl = new Texture("others/dogsGirl.png");
		barNotFilledBlue = new Texture("others/barNotFilledBlue.png");
		oilButton = new Texture("buttons/buttonOil.png");
		boltButton = new Texture("buttons/buttonBolt.png");
		oil = new Texture("others/Oil.png");
		bolt = new Texture("others/Bolt.png");
		rescueMetroSadPeople = new Texture(
				"backgrounds/Rescue-metro-sad-people.png");

		spritesheetBoyRunning = new Texture[31];
		for (int a = 0; a < 11; a++) {
			spritesheetBoyRunning[a] = new Texture("spritesheets/running_boy/"
					+ (a + 1) + ".png");
		}
		spritesheetGirlRunning = new Texture[31];
		for (int a = 0; a < 11; a++) {
			spritesheetGirlRunning[a] = new Texture(
					"spritesheets/running_girl/" + (a + 1) + ".png");
		}

		spritesheetBoyRunning[11] = spritesheetBoyRunning[0];
		spritesheetBoyRunning[12] = spritesheetBoyRunning[1];
		spritesheetBoyRunning[13] = spritesheetBoyRunning[2];
		spritesheetBoyRunning[14] = spritesheetBoyRunning[3];
		spritesheetBoyRunning[15] = spritesheetBoyRunning[4];
		spritesheetBoyRunning[16] = spritesheetBoyRunning[5];
		spritesheetBoyRunning[17] = spritesheetBoyRunning[6];
		spritesheetBoyRunning[18] = spritesheetBoyRunning[9];
		spritesheetBoyRunning[19] = spritesheetBoyRunning[10];
		spritesheetBoyRunning[20] = spritesheetBoyRunning[11];

		spritesheetBoyRunning[21] = spritesheetBoyRunning[11];
		spritesheetBoyRunning[22] = spritesheetBoyRunning[12];
		spritesheetBoyRunning[23] = spritesheetBoyRunning[13];
		spritesheetBoyRunning[24] = spritesheetBoyRunning[14];
		spritesheetBoyRunning[25] = spritesheetBoyRunning[15];
		spritesheetBoyRunning[26] = spritesheetBoyRunning[16];
		spritesheetBoyRunning[27] = spritesheetBoyRunning[17];
		spritesheetBoyRunning[28] = spritesheetBoyRunning[18];
		spritesheetBoyRunning[29] = spritesheetBoyRunning[19];
		spritesheetBoyRunning[30] = spritesheetBoyRunning[20];

		spritesheetGirlRunning[11] = spritesheetGirlRunning[0];
		spritesheetGirlRunning[12] = spritesheetGirlRunning[1];
		spritesheetGirlRunning[13] = spritesheetGirlRunning[2];
		spritesheetGirlRunning[14] = spritesheetGirlRunning[3];
		spritesheetGirlRunning[15] = spritesheetGirlRunning[4];
		spritesheetGirlRunning[16] = spritesheetGirlRunning[5];
		spritesheetGirlRunning[17] = spritesheetGirlRunning[6];
		spritesheetGirlRunning[18] = spritesheetGirlRunning[7];
		spritesheetGirlRunning[19] = spritesheetGirlRunning[8];
		spritesheetGirlRunning[20] = spritesheetGirlRunning[10];

		spritesheetGirlRunning[21] = spritesheetGirlRunning[11];
		spritesheetGirlRunning[22] = spritesheetGirlRunning[12];
		spritesheetGirlRunning[23] = spritesheetGirlRunning[13];
		spritesheetGirlRunning[24] = spritesheetGirlRunning[14];
		spritesheetGirlRunning[25] = spritesheetGirlRunning[15];
		spritesheetGirlRunning[26] = spritesheetGirlRunning[16];
		spritesheetGirlRunning[27] = spritesheetGirlRunning[17];
		spritesheetGirlRunning[28] = spritesheetGirlRunning[18];
		spritesheetGirlRunning[29] = spritesheetGirlRunning[19];
		spritesheetGirlRunning[30] = spritesheetGirlRunning[20];

		darkScreen = new Texture("backgrounds/blackScreen.png");
		arrowUp = new Texture("buttons/arrow-up.png");
		fireStationComplete = new Texture(
				"backgrounds/fire_station_complete.png");
		arrowDown = new Texture("buttons/arrow-down.png");
		runButton = new Texture("buttons/runButton.png");
		runButtonLittle = new Texture("buttons/Button-2.png");
		foot = new Texture("others/foot.png");
		barrel = new Texture("others/Barrel.png");
		truckBack = new Texture("others/truckBackOpen.png");
		truckBackDoor = new Texture("others/truckBackDoor.png");
		runButtonGreen = new Texture("buttons/Button-1.png");
		settings = new Texture("buttons/settings.png");
		pointer = new Texture("others/this.png");
		speedometer = new Texture("others/speedometer.png");
		dialogueWindow = new Texture("windows/windowPlaceholder.png");
		starButton = new Texture("buttons/starButton.png");
		starButtonEmpty = new Texture("buttons/starButtonEmpty.png");
		fireStation = new Texture("buttons/fireStation.png");
		settingsText = new Texture("buttons/Settings-text.png");
		authorsText = new Texture("buttons/Authors-text.png");
		pause = new Texture("buttons/Pause.png");
		switchButton = new Texture("buttons/Switch 3.png");
		playButton = new Texture("buttons/Play-button.png");
		retryButton = new Texture("buttons/Retry-button.png");
		menu = new Texture("buttons/Menu-Button.png");
		daniel = new Texture("texts/daniel.png");
		nevena = new Texture("texts/nevena.png");
		tushar = new Texture("texts/tushar.png");
		longButton = new Texture("buttons/greenButtonLong.png");
		arrow = new Texture("others/arrow.png");
		helmet1 = new Texture("others/helmet1.png");
		helmet2 = new Texture("others/helmet2.png");
		handwheelNoHand = new Texture("others/handwheelNoHand.png");
		button = new Texture("buttons/greenButton.png");
		catHappy = new Texture("spritesheets/cat/Cat-happy.png");
		catSad = new Texture("spritesheets/cat/Cat-worried.png");
		wheel = new Texture("others/wheel.png");
		fireBar = new Texture("others/fireBar.png");
		girlHead = new Texture("girl/girlHead.png");
		basket = new Texture("spritesheets/basket.png");
		roadPart = new Texture("backgrounds/roadPart.png");
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
		carBlue = new Texture("spritesheets/carBlue.png");
		carGreen = new Texture("spritesheets/carGreen.png");

		carRedButton = new Texture("buttons/carRedButton.png");
		carYellowButton = new Texture("buttons/carYellowButton.png");
		carPinkButton = new Texture("buttons/carPinkButton.png");
		carBlueButton = new Texture("buttons/carBlueButton.png");
		carGreenButton = new Texture("buttons/carGreenButton.png");

		elevatorButton = new Texture("buttons/elevator.png");
		fireMiniature = new Texture("others/Fire3-1.png");
		hoseHydrant = new Texture("others/hoseHydrant.png");
		handTruckFront = new Texture("boy/handTruckFront.png");
		truckLed = new Texture("others/truckLed.png");
		lake = new Texture("backgrounds/Lake.png");
		trainMiniature = new Texture("buttons/trainMiniature.png");
		elevatorMiniature = new Texture("buttons/elevatorMiniature.png");
		boyHeadBig = new Texture("boy/boyHeadBig.png");
		girlHeadBig = new Texture("girl/girlHeadBig.png");
		cockpitPart = new Texture("backgrounds/cockpitPart.png");
		ledCockpit = new Texture("others/ledCockpit.png");
		fireBig = new Texture("spritesheets/fireBig.png");
		tree = new Texture("backgrounds/rescueCat/tree.png");
		catMiniature = new Texture("buttons/catMiniature.png");
		boyButton = new Texture("buttons/Boy-button.png");
		girlButton = new Texture("buttons/Girl-button.png");
		damage = new Texture("others/damage.png");
		peopleGround = new Texture("spritesheets/peopleGround.png");
		peopleBuilding = new Texture("spritesheets/peopleBuilding.png");
		barFilled = new Texture("others/barFilled.png");
		grassFlowers = new Texture("backgrounds/grassFlowers.png");
		star = new Texture("particles/star.png");
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
		womanHappy = new Texture("backgrounds/elevator/womanHappy.png");
		girlHandCockpit = new Texture("girl/handCockpit.png");
		programmingText = new Texture("texts/Programming.png");
		ground = new Texture("backgrounds/ground.png");
		graphicsText = new Texture("texts/Graphics.png");
		truckMiniature = new Texture("buttons/truckMiniature.png");
		sign = new Texture("texts/Sign.png");
		breakTheWallText = new Texture("texts/Break-the-wall.png");
		eclipseFireText = new Texture("texts/Elipse-fire.png");
		jumpText = new Texture("texts/Jump.png");
		wall = new Texture[3];
		for (int a = 0; a < 3; a++) {
			wall[a] = new Texture("others/wall/" + (a + 1) + ".png");
		}
		rideTruckText = new Texture("texts/Ride-Truck.png");

		elevatorDoor = new Texture[2];

		elevatorDoor[0] = new Texture("backgrounds/elevator/door1.png");
		elevatorDoor[1] = new Texture("backgrounds/elevator/door2.png");

		spritesheetGirlWeights = new Texture[3];
		spritesheetGirlElliptical = new Texture[7];
		spritesheetBoyElliptical = new Texture[7];
		spritesheetBoyWeights = new Texture[3];
		truckBlank = new Texture[1];
		truckBlank[0] = new Texture("others/Train-basket-blank.png");

		grass = new Texture("backgrounds/grass.png");

		sky = new Texture[6];
		sky[0] = new Texture("backgrounds/sky1.png");
		sky[1] = new Texture("backgrounds/sky1reversed.png");
		sky[2] = new Texture("backgrounds/sky2.png");
		sky[3] = new Texture("backgrounds/sky2reversed.png");
		sky[4] = new Texture("backgrounds/sky3.png");
		sky[5] = new Texture("backgrounds/sky3reversed.png");

		truckNoBasket = new Texture[1];
		truckNoBasket[0] = new Texture("spritesheets/Train-blank.png");

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

		elevatorBackground = new Texture[4];
		elevatorBackground[0] = new Texture("backgrounds/elevator/1.png");
		elevatorBackground[1] = new Texture("backgrounds/elevator/2.png");
		elevatorBackground[2] = new Texture("backgrounds/elevator/3.png");
		elevatorBackground[3] = new Texture("backgrounds/elevator/4.png");

		rescueMetro = new Texture[2];
		rescueMetro[0] = new Texture("backgrounds/rescueMetro/1.png");
		rescueMetro[1] = new Texture("backgrounds/rescueMetro/2.png");

		cloudyBackgroundClose = new Texture("backgrounds/rescueCat/close.png");
		cloudyBackgroundFar = new Texture("backgrounds/rescueCat/far.png");

		buzzer = new Texture[3];
		buzzer[0] = new Texture("spritesheets/buzzer/Buzzer1.png");
		buzzer[1] = new Texture("spritesheets/buzzer/Buzzer2.png");
		buzzer[2] = new Texture("spritesheets/buzzer/Buzzer3.png");

		dogWorried = new Texture[2];
		dogWorried[0] = new Texture("spritesheets/dog/worried1.png");
		dogWorried[1] = new Texture("spritesheets/dog/worried2.png");

		food = new Texture[8];
		for (int a = 0; a < 8; a++)
			food[a] = new Texture("food/" + a + ".png");

		dogHappy = new Texture[2];
		dogHappy[0] = new Texture("spritesheets/dog/happy1.png");
		dogHappy[1] = new Texture("spritesheets/dog/happy2.png");

		girlHammer = new Texture[2];
		girlHammer[0] = new Texture("girl/hammer1.png");
		girlHammer[1] = new Texture("girl/hammer2.png");

		girlHammer_16_percent = new Texture[2];
		girlHammer_16_percent[0] = new Texture("girl/hammer1_16_percent.png");
		girlHammer_16_percent[1] = new Texture("girl/hammer2_16_percent.png");
		
		boyHammer = new Texture[2];
		boyHammer[0] = new Texture("boy/boy_hammer/1.png");
		boyHammer[1] = new Texture("boy/boy_hammer/2.png");

		boyHammer_16_percent = new Texture[2];
		boyHammer_16_percent[0] = new Texture("boy/boy_hammer/1_16_percent.png");
		boyHammer_16_percent[1] = new Texture("boy/boy_hammer/2_16_percent.png");
		
		metroDoor = new Texture[2];
		metroDoor[0] = new Texture("backgrounds/rescueMetro/metroDoor1.png");
		metroDoor[1] = new Texture("backgrounds/rescueMetro/metroDoor2.png");

		bushes = new Texture[3];
		bushes[0] = new Texture("backgrounds/bushes1.png");
		bushes[1] = new Texture("backgrounds/bushes2.png");
		bushes[2] = new Texture("backgrounds/Bushes-small.png");

		foodBackground = new Texture[4];
		foodBackground[0] = new Texture("backgrounds/food_bk/1.png");
		foodBackground[1] = new Texture("backgrounds/food_bk/2.png");
		foodBackground[2] = new Texture("backgrounds/food_bk/3.png");
		foodBackground[3] = new Texture("backgrounds/food_bk/4.png");

		trees = new Texture[3];
		trees[0] = new Texture("backgrounds/tree1.png");
		trees[1] = new Texture("backgrounds/tree2.png");
		trees[2] = new Texture("backgrounds/tree3.png");

		girlRunningSuit = new Texture[31];
		for (int a = 0; a < 11; a++) {
			girlRunningSuit[a] = new Texture("girl/running_suit/" + (a + 1)
					+ ".png");
		}

		boyRunningSuit = new Texture[31];
		for (int a = 0; a < 11; a++) {
			boyRunningSuit[10 - a] = new Texture("boy/running_suit/" + (a + 1)
					+ ".png");
		}

		boyRunningSuit[11] = boyRunningSuit[0];
		boyRunningSuit[12] = boyRunningSuit[1];
		boyRunningSuit[13] = boyRunningSuit[2];
		boyRunningSuit[14] = boyRunningSuit[3];
		boyRunningSuit[15] = boyRunningSuit[4];
		boyRunningSuit[16] = boyRunningSuit[5];
		boyRunningSuit[17] = boyRunningSuit[6];
		boyRunningSuit[18] = boyRunningSuit[7];
		boyRunningSuit[19] = boyRunningSuit[9];
		boyRunningSuit[20] = boyRunningSuit[10];

		boyRunningSuit[21] = boyRunningSuit[11];
		boyRunningSuit[22] = boyRunningSuit[12];
		boyRunningSuit[23] = boyRunningSuit[13];
		boyRunningSuit[24] = boyRunningSuit[14];
		boyRunningSuit[25] = boyRunningSuit[15];
		boyRunningSuit[26] = boyRunningSuit[16];
		boyRunningSuit[27] = boyRunningSuit[17];
		boyRunningSuit[28] = boyRunningSuit[18];
		boyRunningSuit[29] = boyRunningSuit[19];
		boyRunningSuit[30] = boyRunningSuit[20];

		girlRunningSuit[11] = girlRunningSuit[0];
		girlRunningSuit[12] = girlRunningSuit[1];
		girlRunningSuit[13] = girlRunningSuit[2];
		girlRunningSuit[14] = girlRunningSuit[3];
		girlRunningSuit[15] = girlRunningSuit[4];
		girlRunningSuit[16] = girlRunningSuit[5];
		girlRunningSuit[17] = girlRunningSuit[6];
		girlRunningSuit[18] = girlRunningSuit[7];
		girlRunningSuit[19] = girlRunningSuit[8];
		girlRunningSuit[20] = girlRunningSuit[10];

		girlRunningSuit[21] = girlRunningSuit[11];
		girlRunningSuit[22] = girlRunningSuit[12];
		girlRunningSuit[23] = girlRunningSuit[13];
		girlRunningSuit[24] = girlRunningSuit[14];
		girlRunningSuit[25] = girlRunningSuit[15];
		girlRunningSuit[26] = girlRunningSuit[16];
		girlRunningSuit[27] = girlRunningSuit[17];
		girlRunningSuit[28] = girlRunningSuit[18];
		girlRunningSuit[29] = girlRunningSuit[19];
		girlRunningSuit[30] = girlRunningSuit[20];

		boyWaving = new Texture[6];
		boyWaving[0] = new Texture("boy/Boy-waving-1.png");
		boyWaving[1] = new Texture("boy/Boy-waving-2.png");
		boyWaving[2] = new Texture("boy/Boy-waving-3.png");
		boyWaving[3] = new Texture("boy/Boy-waving-4.png");
		boyWaving[4] = new Texture("boy/Boy-waving-5.png");
		boyWaving[5] = new Texture("boy/Boy-waving-6.png");

		girlHoseHydrant = new Texture[7];
		girlHoseHydrant[0] = new Texture("girl/hoseHydrant/1.png");
		girlHoseHydrant[1] = new Texture("girl/hoseHydrant/2.png");
		girlHoseHydrant[2] = new Texture("girl/hoseHydrant/3.png");
		girlHoseHydrant[3] = new Texture("girl/hoseHydrant/4.png");
		girlHoseHydrant[4] = new Texture("girl/hoseHydrant/5.png");
		girlHoseHydrant[5] = new Texture("girl/hoseHydrant/6.png");
		girlHoseHydrant[6] = new Texture("girl/hoseHydrant/7.png");

		boyHoseHydrant = new Texture[7];
		for (int a = 0; a < 7; a++)
			boyHoseHydrant[a] = new Texture("boy/hose_hydrant/" + (a + 1)
					+ ".png");

		girlHoseHydrantReversed = new Texture[7];
		for (int a = 0; a < 7; a++) {
			girlHoseHydrantReversed[a] = girlHoseHydrant[6 - a];
		}

		boyHoseHydrantReversed = new Texture[7];
		for (int a = 0; a < 7; a++) {
			boyHoseHydrantReversed[a] = boyHoseHydrant[6 - a];
		}

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
		for (int a = 0; a < 2; a++) {
			dogWorried[a].setFilter(textureFilter, textureFilter);
		}
		grass.setFilter(textureFilter, textureFilter);

		for (int a = 0; a < 6; a++) {
			sky[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			girlHammer[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			girlHammer_16_percent[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			boyHammer[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			boyHammer_16_percent[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			metroDoor[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			bushes[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			trees[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			foodBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			girlRunningSuit[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			boyRunningSuit[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			dogHappy[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 8; a++) {
			food[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			fitnessBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			elevatorBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 6; a++) {
			parkBackgrounds[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 2; a++) {
			rescueMetro[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			wall[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 6; a++) {
			boyWaving[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			buzzer[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 3; a++) {
			clouds[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 4; a++) {
			mainBackground[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 7; a++) {
			girlHoseHydrant[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 7; a++) {
			boyHoseHydrant[a].setFilter(textureFilter, textureFilter);
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
		for (int a = 0; a < 11; a++) {
			spritesheetGirlRunning[a].setFilter(textureFilter, textureFilter);
		}
		for (int a = 0; a < 11; a++) {
			spritesheetBoyRunning[a].setFilter(textureFilter, textureFilter);
		}

		pause.setFilter(textureFilter, textureFilter);
		hoseHydrant.setFilter(textureFilter, textureFilter);
		speedometer.setFilter(textureFilter, textureFilter);
		menu.setFilter(textureFilter, textureFilter);
		barFilled.setFilter(textureFilter, textureFilter);
		grassFlowers.setFilter(textureFilter, textureFilter);
		star.setFilter(textureFilter, textureFilter);
		barNotFilled.setFilter(textureFilter, textureFilter);
		longButton.setFilter(textureFilter, textureFilter);
		foot.setFilter(textureFilter, textureFilter);
		barrel.setFilter(textureFilter, textureFilter);
		arrow.setFilter(textureFilter, textureFilter);
		button.setFilter(textureFilter, textureFilter);
		catHappy.setFilter(textureFilter, textureFilter);
		catSad.setFilter(textureFilter, textureFilter);
		truckMiniature.setFilter(textureFilter, textureFilter);
		rescueMetroSadPeople.setFilter(textureFilter, textureFilter);
		fireStation.setFilter(textureFilter, textureFilter);
		boyButton.setFilter(textureFilter, textureFilter);
		girlButton.setFilter(textureFilter, textureFilter);
		runButton.setFilter(textureFilter, textureFilter);
		runButtonGreen.setFilter(textureFilter, textureFilter);
		runButtonLittle.setFilter(textureFilter, textureFilter);
		cloudyBackgroundFar.setFilter(textureFilter, textureFilter);
		cloudyBackgroundClose.setFilter(textureFilter, textureFilter);
		pointer.setFilter(textureFilter, textureFilter);
		speedBar.setFilter(textureFilter, textureFilter);
		helmet1.setFilter(textureFilter, textureFilter);
		helmet2.setFilter(textureFilter, textureFilter);
		switchButton.setFilter(textureFilter, textureFilter);
		lane.setFilter(textureFilter, textureFilter);

		carRed.setFilter(textureFilter, textureFilter);
		carYellow.setFilter(textureFilter, textureFilter);
		carPink.setFilter(textureFilter, textureFilter);
		carBlue.setFilter(textureFilter, textureFilter);
		carGreen.setFilter(textureFilter, textureFilter);

		carRedButton.setFilter(textureFilter, textureFilter);
		carYellowButton.setFilter(textureFilter, textureFilter);
		carPinkButton.setFilter(textureFilter, textureFilter);
		carBlueButton.setFilter(textureFilter, textureFilter);
		carGreenButton.setFilter(textureFilter, textureFilter);

		retryButton.setFilter(textureFilter, textureFilter);
		playButton.setFilter(textureFilter, textureFilter);
		vibrationsText.setFilter(textureFilter, textureFilter);
		fpsText.setFilter(textureFilter, textureFilter);
		starButton.setFilter(textureFilter, textureFilter);
		starButtonEmpty.setFilter(textureFilter, textureFilter);
		screenAwakeText.setFilter(textureFilter, textureFilter);
		textureFilteringText.setFilter(textureFilter, textureFilter);
		damage.setFilter(textureFilter, textureFilter);
		voiceText.setFilter(textureFilter, textureFilter);
		barFilledBlue.setFilter(textureFilter, textureFilter);
		programmingText.setFilter(textureFilter, textureFilter);
		ground.setFilter(textureFilter, textureFilter);
		graphicsText.setFilter(textureFilter, textureFilter);
		barNotFilledBlue.setFilter(textureFilter, textureFilter);
		sign.setFilter(textureFilter, textureFilter);
		breakTheWallText.setFilter(textureFilter, textureFilter);
		jumpText.setFilter(textureFilter, textureFilter);
		rideTruckText.setFilter(textureFilter, textureFilter);
		eclipseFireText.setFilter(textureFilter, textureFilter);
		basket.setFilter(textureFilter, textureFilter);
		settings.setFilter(textureFilter, textureFilter);
		cockpitPart.setFilter(textureFilter, textureFilter);
		elevatorDoor[0].setFilter(textureFilter, textureFilter);
		elevatorDoor[1].setFilter(textureFilter, textureFilter);
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
		fireStationComplete.setFilter(textureFilter, textureFilter);
		truckBack.setFilter(textureFilter, textureFilter);
		truckBackDoor.setFilter(textureFilter, textureFilter);
		fireBar.setFilter(textureFilter, textureFilter);
		daniel.setFilter(textureFilter, textureFilter);
		nevena.setFilter(textureFilter, textureFilter);
		tushar.setFilter(textureFilter, textureFilter);
		handTruckFront.setFilter(textureFilter, textureFilter);
		handwheelNoHand.setFilter(textureFilter, textureFilter);
		boyHeadCockpit.setFilter(textureFilter, textureFilter);
		girlHeadCockpit.setFilter(textureFilter, textureFilter);
		womanHappy.setFilter(textureFilter, textureFilter);
		girlHandCockpit.setFilter(textureFilter, textureFilter);
		fireBig.setFilter(textureFilter, textureFilter);
		peopleGround.setFilter(textureFilter, textureFilter);
		peopleBuilding.setFilter(textureFilter, textureFilter);
		bigRoadRescue.setFilter(textureFilter, textureFilter);
		training.setFilter(textureFilter, textureFilter);
		trainMiniature.setFilter(textureFilter, textureFilter);
		elevatorMiniature.setFilter(textureFilter, textureFilter);
		truckLed.setFilter(textureFilter, textureFilter);
		meetTheTrucks.setFilter(textureFilter, textureFilter);
		rescueBuilding.setFilter(textureFilter, textureFilter);
		rescueCat.setFilter(textureFilter, textureFilter);
		fitness.setFilter(textureFilter, textureFilter);
		rescueTrain.setFilter(textureFilter, textureFilter);
		yellowSectionUp.setFilter(textureFilter, textureFilter);
		yellowSectionLeft.setFilter(textureFilter, textureFilter);
		yellowSectionMiddle.setFilter(textureFilter, textureFilter);
		dogsGirl.setFilter(textureFilter, textureFilter);
		settingsText.setFilter(textureFilter, textureFilter);
		boyTorso.setFilter(textureFilter, textureFilter);
		boyHeadBigBlonde.setFilter(textureFilter, textureFilter);
		authorsText.setFilter(textureFilter, textureFilter);
		roadPart.setFilter(textureFilter, textureFilter);
		fireMiniature.setFilter(textureFilter, textureFilter);
		elevatorButton.setFilter(textureFilter, textureFilter);
		boyMainMenu.setFilter(textureFilter, textureFilter);
		girlMainMenu.setFilter(textureFilter, textureFilter);
		oilButton.setFilter(textureFilter, textureFilter);
		boltButton.setFilter(textureFilter, textureFilter);
		oil.setFilter(textureFilter, textureFilter);
		tree.setFilter(textureFilter, textureFilter);
		catMiniature.setFilter(textureFilter, textureFilter);
		bolt.setFilter(textureFilter, textureFilter);
		boyMenuHand.setFilter(textureFilter, textureFilter);
		truckNoBasket[0].setFilter(textureFilter, textureFilter);
		truckBlank[0].setFilter(textureFilter, textureFilter);
		authors.setFilter(textureFilter, textureFilter);
	}
}