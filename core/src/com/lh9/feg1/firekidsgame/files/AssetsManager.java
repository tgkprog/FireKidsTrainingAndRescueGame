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
	public Texture levelBackgrounds[];
	public Texture clouds[];
	public Texture dialogueWindow;
	public Texture parkBackgrounds[];
	public Texture runButton;
	public Texture spritesheetBoyRunning;
	public Texture spritesheetBoyWeights;	
	public Texture spritesheetBoyElliptical;	
	public Texture spritesheetGirlRunning;
	public Texture spritesheetGirlElliptical;
	public Texture spritesheetGirlWeights;	
	public Texture pointer;
	public Texture bar;
	public Texture boyHeadBig;
	public Texture girlHeadBig;
	public Texture girlHead;
	public Texture boyHead;
	public Texture speedBar;
	public Texture darkScreen;
	public Texture truckCockpit[];
	public Texture truckFront[];
	public Texture cockpitPart;
	public Texture ledCockpit;
	public Texture handSpritesheet;
	public Texture glass;
	public Texture handwheelBig;
	
	public Texture mainBackground[];
	
	
	public ParticleEffect stars;
	public ParticleEffect leaf;
	
	public BitmapFont font;
	
	boolean assetsLoaded = false;

	public void run() {

		this.setPriority(NORM_PRIORITY);
		
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
	     stars.load(Gdx.files.internal("particles/starsGoodYellow180"),Gdx.files.internal("particles/"));
	     leaf = new ParticleEffect();
	     leaf.load(Gdx.files.internal("particles/leaf"),Gdx.files.internal("particles/"));
	  
		spritesheetBoyRunning = new Texture(
				"spritesheets/spritesheetBoyRunning.png");
		spritesheetGirlRunning = new Texture(
				"spritesheets/spritesheetGirlRunning.png");
		darkScreen = new Texture("backgrounds/blackScreen.png");
		runButton = new Texture("buttons/runButton.png");
		pointer = new Texture("others/this.png");
		dialogueWindow = new Texture("windows/windowPlaceholder.png");
		fireStation = new Texture("buttons/fireStation.png");
		pause = new Texture("buttons/Pause.png");
		longButton = new Texture("buttons/greenButtonLong.png");
		arrow = new Texture("others/arrow.png");
		button = new Texture("buttons/greenButton.png");
		girlHead = new Texture("girl/girlHead.png");
		boyHead = new Texture("boy/boyHead.png");
		speedBar = new Texture("others/speedBar.png");
		lane = new Texture("others/lane.png");
		boyHeadBig = new Texture("boy/boyHeadBig.png");
		girlHeadBig = new Texture("girl/girlHeadBig.png");
		cockpitPart = new Texture("backgrounds/cockpitPart.png");
		ledCockpit = new Texture("others/ledCockpit.png");
		handSpritesheet = new Texture("others/spritesheetPress.png");	
		spritesheetGirlWeights = new Texture("girl/spritesheetGirlWeights.png");
		spritesheetGirlElliptical = new Texture("girl/spritesheetEllipticalGirl.png");
		spritesheetBoyElliptical = new Texture("boy/spritesheetEllipticalBoy.png");
		spritesheetBoyWeights = new Texture("boy/spritesheetBoyWeights.png");
		bar = new Texture("others/bar.png");
		glass = new Texture("backgrounds/glass.png");
		handwheelBig = new Texture("others/handwheelBig.png");

		truckFront = new Texture[5];
		
		truckFront[0] = new Texture("backgrounds/truckFront1.png");
		truckFront[1] = new Texture("backgrounds/truckFront2.png");
		truckFront[2] = new Texture("backgrounds/truckFront3.png");
		truckFront[3] = new Texture("backgrounds/truckFront4.png");
		truckFront[4] = new Texture("backgrounds/truckFront5.png");
		
		
		truckCockpit = new Texture[4];
		
		truckCockpit[0] = new Texture("backgrounds/truckCockpit1.png");
		truckCockpit[1] = new Texture("backgrounds/truckCockpit2.png");
		truckCockpit[2] = new Texture("backgrounds/truckCockpit3.png");
		truckCockpit[3] = new Texture("backgrounds/truckCockpit4.png");
		
		
		levelBackgrounds = new Texture[8];
		levelBackgrounds[0] = new Texture(
				"backgrounds/Station-meet-trucks_0.png");
		levelBackgrounds[1] = new Texture("backgrounds/Fitness-bk-2.jpg");
		levelBackgrounds[2] = new Texture("backgrounds/fitness-park-bk.png");
		levelBackgrounds[3] = new Texture("backgrounds/rescue-tree-bk.png");
		levelBackgrounds[4] = new Texture("backgrounds/Rescue-building-bk.png");
		levelBackgrounds[5] = new Texture("backgrounds/Rescue-metro-bk.png");
		levelBackgrounds[6] = new Texture(
				"backgrounds/Rescue-metro-people-2.png");
		levelBackgrounds[7] = new Texture("backgrounds/8 Big Road Rescue.png");
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

		
		for (int a = 0; a < 8; a++) {
			levelBackgrounds[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 6; a++) {
			parkBackgrounds[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		for (int a = 0; a < 3; a++) {
			clouds[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		for (int a = 0; a < 4; a++) {
			mainBackground[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		for (int a = 0; a < 4; a++) {
			truckCockpit[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		for (int a = 0; a < 5; a++) {
			truckFront[a].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		spritesheetGirlRunning.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		spritesheetBoyRunning.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		pause.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		longButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		arrow.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		button.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fireStation.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		runButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		pointer.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		speedBar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		spritesheetGirlWeights.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		spritesheetBoyWeights.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lane.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cockpitPart.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ledCockpit.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		handSpritesheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		boyHeadBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		girlHeadBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		glass.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		handwheelBig.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		assetsLoaded = true;
	}

	public boolean getAssetsLoaded() {
		return assetsLoaded;
	}
}