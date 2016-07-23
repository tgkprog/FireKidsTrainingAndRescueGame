package com.lh9.feg1.firekidsgame.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dialogue extends Window {

	BitmapFont font;
	Texture dark;

	public Dialogue(Texture windowTexture, Texture dark, double x, double y,
			Texture buttonTexture) {
		super(windowTexture, x, y);
		this.dark = dark;
	}

	public void draw(SpriteBatch batch, float delta) {
		if ((float) this.scale * 0.5f != 0) {
			batch.setColor(1, 1, 1, (float) this.scale * 0.5f);
			batch.draw(dark, 0, 0);
			batch.setColor(1, 1, 1, 1);
		}
		super.draw(batch, delta);
	}

	public void checkCollision(int x, int y) {
		this.hide();

	}
}