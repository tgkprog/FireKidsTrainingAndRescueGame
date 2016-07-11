package com.lh9.feg1.firekidsgame.files.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dialogue extends Window {

		BitmapFont font;
	
	
	public Dialogue(Texture windowTexture, double x, double y,
			Texture buttonTexture) {
		super(windowTexture, x, y);
	}

	public void draw(SpriteBatch batch, double delta) {
		
		super.draw(batch, delta);
	}

	public void checkCollision(int x, int y) {
			this.hide();
		
	}
}