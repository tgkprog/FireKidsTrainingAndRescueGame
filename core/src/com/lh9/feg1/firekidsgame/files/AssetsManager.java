package com.lh9.feg1.firekidsgame.files;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.lh9.feg1.firekidsgame.utils.Variables;

public class AssetsManager extends Thread {

	public Texture mainBackground;
	public Texture longButton;
	public Texture button;
	public Texture levelBackgrounds[];

	boolean assetsLoaded = false;

	public void run() {
		this.setPriority(MAX_PRIORITY);

		if (new Variables().getDebugMode() == true)
			System.out.println("Started loading assets.");

		mainBackground = new Texture("backgrounds/Marketing-1.png");
		longButton = new Texture("buttons/greenButtonLong.png");
		button = new Texture("buttons/greenButton.png");
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

		for (int a = 0; a < 8; a++) {
			levelBackgrounds[a].setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
		}
		mainBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		longButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		button.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		assetsLoaded = true;
	}

	public boolean getAssetsLoaded() {
		return assetsLoaded;
	}
}