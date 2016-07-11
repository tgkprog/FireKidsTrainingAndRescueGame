package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpeedBar {

	int x;
	int y;
	double speed;
	Texture bar;

	public SpeedBar(Texture bar, int x, int y) {
		this.bar = bar;
		this.x = x;
		this.y = y;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void render(SpriteBatch batch) {
		batch.draw(bar, x, y, 0, 0, (int)speed * 20, 20);
	}
}