package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class CloudManager {

	Cloud[] clouds;

	boolean allScalesEqualZero;
	boolean allScalesEqualOne;
	boolean loaded;

	public void load(Texture[] cloudTextures) {
		clouds = new Cloud[13];

		clouds[0] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), -50, -50);
		clouds[1] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 150, -150);
		clouds[2] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 350, -80);
		clouds[3] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 550, -120);
		clouds[4] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), -50, 125);
		clouds[5] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), -100, 250);
		clouds[6] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 120, 290);
		clouds[7] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 100, 70);
		clouds[8] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 280, 105);
		clouds[9] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 375, 290);
		clouds[10] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 465, 75);
		clouds[11] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0f, 1f), 560, 250);
		clouds[12] = new Cloud(cloudTextures[MathUtils.random(0, 2)],
				MathUtils.random(0, 1f), 700, 65);

		loaded = true;
	}

	public void render(SpriteBatch batch, double delta) {
		if (allScalesEqualZero == false && loaded == true) {

			for (int a = 0; a < 13; a++)
				clouds[a].draw(batch, delta);

			for (int a = 0; a < 13; a++) {
				if (clouds[a].getScale() < 1) {
					allScalesEqualOne = false;
					break;
				} else
					allScalesEqualOne = true;
			}

			for (int a = 0; a < 13; a++) {
				if (clouds[a].getScale() > 0f) {
					{
						allScalesEqualZero = false;
						break;
					}
				}

			}
		}
	}

	public void start() {
		for (int a = 0; a < 13; a++)
			clouds[a].popUp();
		allScalesEqualZero = false;
	}

	public void stop() {
		for (int a = 0; a < 13; a++)
			clouds[a].hide();

	}

	public boolean getAllScalesEqualOne() {
		return allScalesEqualOne;
	}

	public boolean isLoaded() {
		return loaded;
	}
}
