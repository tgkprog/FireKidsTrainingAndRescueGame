package com.lh9.feg1.firekidsgame.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class AssetsManager extends Thread {

	public Texture lane;
	public Texture arrow;
	public Texture longButton;
	public Texture button;
	public Texture fireStation;
	public Texture pause;
	public Texture carRed;
	public Texture carYellow;
	public Texture carPink;
	public Texture carBlue;
	public Texture carGreen;
	public Texture handTruckFront;
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
	
	public Texture pointer;
	public Texture bar;
	public Texture settings;	
	public Texture boyHeadBig;
	public Texture girlHeadBig;
	public Texture girlHead;
	public Texture boyHead;
	public Texture speedBar;
	public Texture darkScreen;
	public Texture truckCockpit[];
	public Texture truckFront[];
	public Texture rescueMetro[];
	public Texture cockpitPart;
	public Texture ledCockpit;
	public Texture handSpritesheet;
	public Texture barNotFilled;
	public Texture barFilled;
	// Spritesheets
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
	// Buttons
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
	
	// Particles
	public ParticleEffect stars;
	public ParticleEffect leaf;
	public ParticleEffect truckEmissions;
	public ParticleEffect buttonEffect;

	public BitmapFont font;

	boolean assetsLoaded = false;

	public void run() {

		this.setPriority(MAX_PRIORITY);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/comic-andy.regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 90;
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;

		font = generator.generateFont(parameter);
		font.setColor(Color.BLACK);

		if (new Variables().getDebugMode() == true)
			System.out.println("Started loading assets.");

		stars = new ParticleEffect();
		stars.load(Gdx.files.internal("particles/starsGoodYellow180"),
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

		spritesheetBoyRunning = new Texture(
				"spritesheets/spritesheetBoyRunning.png");
		spritesheetGirlRunning = new Texture(
				"spritesheets/spritesheetGirlRunning.png");
		darkScreen = new Texture("backgrounds/blackScreen.png");
		arrowUp = new Texture("buttons/arrow-up.png");
		arrowDown = new Texture("buttons/arrow-down.png");
		runButton = new Texture("buttons/runButton.png");
		runButtonLittle = new Texture("buttons/Button-2.png");
		settings = new Texture("buttons/settings.png");
		pointer = new Texture("others/this.png");
		dialogueWindow = new Texture("windows/windowPlaceholder.png");
		fireStation = new Texture("buttons/fireStation.png");
		pause = new Texture("buttons/Pause.png");
		longButton = new Texture("buttons/greenButtonLong.png");
		arrow = new Texture("others/arrow.png");
		handwheelNoHand = new Texture("others/handwheelNoHand.png");
		button = new Texture("buttons/greenButton.png");
		wheel = new Texture("others/wheel.png");
		girlHead = new Texture("girl/girlHead.png");
		boyHead = new Texture("boy/boyHea" + "d.png");
		speedBar = new Texture("others/speedBar.png");
		lane = new Texture("others/lane.png");
		carRed = new Texture("spritesheets/carRed.png");
		carYellow = new Texture("spritesheets/carYellow.png");
		carPink = new Texture("spritesheets/carPink.png");
		carBlue = new Texture("spritesheets/carBlue.png");
		carGreen = new Texture("spritesheets/carGreen.png");
		handTruckFront = new Texture("boy/handTruckFront.png");
		boyHeadBig = new Texture("boy/boyHeadBig.png");
		girlHeadBig = new Texture("girl/girlHeadBig.png");
		cockpitPart = new Texture("backgrounds/cockpitPart.png");
		ledCockpit = new Texture("others/ledCockpit.png");
		fireBig = new Texture("spritesheets/fireBig.png");
		peopleGround = new Texture("spritesheets/peopleGround.png");
		peopleBuilding = new Texture("spritesheets/peopleBuilding.png");
		barFilled = new Texture("others/barFilled.png");
		barNotFilled = new Texture("others/barNotFilled.png");
		yellowSectionUp = new Texture("buttons/ledRedSection3.png");
		yellowSectionMiddle = new Texture("buttons/yellowSection1.png");
		yellowSectionLeft = new Texture("buttons/yellowSection2.png");
		

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
		
		spritesheetGirlWeights[0] = new Texture("girl/fitnessBenchGirl/Fitness-bench-press-Girl-1.png");
		spritesheetGirlWeights[1] = new Texture("girl/fitnessBenchGirl/Fitness-bench-press-Girl-2.png");
		spritesheetGirlWeights[2] = new Texture("girl/fitnessBenchGirl/Fitness-bench-press-Girl-3.png");
	
		spritesheetBoyWeights[0] = new Texture("boy/fitnessBenchBoy/Fitness-bench-press-boy-1.png");
		spritesheetBoyWeights[1] = new Texture("boy/fitnessBenchBoy/Fitness-bench-press-boy-2.png");
		spritesheetBoyWeights[2] = new Texture("boy/fitnessBenchBoy/Fitness-bench-press-boy-3.png");
		
		spritesheetGirlElliptical[0] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-1.png");
		spritesheetGirlElliptical[1] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-2.png");
		spritesheetGirlElliptical[2] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-3.png");
		spritesheetGirlElliptical[3] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-4.png");
		spritesheetGirlElliptical[4] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-5.png");
		spritesheetGirlElliptical[5] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-6.png");
		spritesheetGirlElliptical[6] = new Texture("girl/fitnessEllipticalGirl/Eliptical-Girl-7.png");
		
		spritesheetBoyElliptical[0] = new Texture("boy/fitnessEllipticalBoy/Eliptical-1.png");
		spritesheetBoyElliptical[1] = new Texture("boy/fitnessEllipticalBoy/Eliptical-2.png");
		spritesheetBoyElliptical[2] = new Texture("boy/fitnessEllipticalBoy/Eliptical-3.png");
		spritesheetBoyElliptical[3] = new Texture("boy/fitnessEllipticalBoy/Eliptical-4.png");
		spritesheetBoyElliptical[4] = new Texture("boy/fitnessEllipticalBoy/Eliptical-5.png");
		spritesheetBoyElliptical[5] = new Texture("boy/fitnessEllipticalBoy/Eliptical-6.png");
		spritesheetBoyElliptical[6] = new Texture("boy/fitnessEllipticalBoy/Eliptical-7.png");
		
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

		fitnessBackground = new Texture[4];
		fitnessBackground[0] = new Texture(
				"backgrounds/fitness/fitness1.png");
		fitnessBackground[1] = new Texture(
				"backgrounds/fitness/fitness2.png");
		fitnessBackground[2] = new Texture(
				"backgrounds/fitness/fitness3.png");
		fitnessBackground[3] = new Texture(
				"backgrounds/fitness/fitness4.png");

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
		
		for (int a = 0; a < 7; a++) {
			spritesheetBoyElliptical[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 7; a++) {
			spritesheetGirlElliptical[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			spritesheetGirlWeights[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			spritesheetBoyWeights[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 9; a++) {
			trainBasketAnimation[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 1; a++) {
			levelBackgrounds[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 4; a++) {
			fitnessBackground[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 6; a++) {
			parkBackgrounds[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 2; a++) {
			rescueMetro[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 6; a++) {
			boyWaving[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			clouds[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		for (int a = 0; a < 4; a++) {
			mainBackground[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 4; a++) {
			truckCockpit[a].setFilter(TextureFilter.Nearest,
					TextureFilter.Nearest);
		}
		for (int a = 0; a < 5; a++) {
			truckFront[a].setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		}
		for (int a = 0; a < 24; a++) {
			bigRoad[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		spritesheetGirlRunning.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		spritesheetBoyRunning.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		pause.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		barFilled.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		barNotFilled.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		longButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		arrow.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		button.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fireStation.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		runButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		runButtonLittle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		pointer.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		speedBar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lane.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		carRed.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		carYellow.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		carPink.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		carBlue.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		carGreen.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		settings.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cockpitPart.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ledCockpit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		handSpritesheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		boyHeadBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		girlHeadBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		glass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		handwheelBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		spritesheetTruck.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		wheel.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		arrowUp.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		arrowDown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		handTruckFront.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		handwheelNoHand.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		boyHeadCockpit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		girlHeadCockpit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		girlHandCockpit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fireBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		peopleGround.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		peopleBuilding.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bigRoadRescue.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		training.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		meetTheTrucks.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		rescueBuilding.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		rescueCat.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fitness.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		rescueTrain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowSectionUp.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowSectionLeft.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowSectionMiddle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		boyMainMenu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		girlMainMenu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		boyMenuHand.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		assetsLoaded = true;
	}

	public boolean getAssetsLoaded() {
		return assetsLoaded;
	}
}