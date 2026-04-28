package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FallingText {

	Sprite text;
	float alpha;
	float scale = 2;
	boolean visible;

	public FallingText(Texture texture, int x, int y) {
		text = new Sprite(texture);
		text.setPosition(x, y);
		text.setAlpha(0);
		text.setScale(scale);
	}

	public void render(SpriteBatch batch, float delta) {

		text.setScale(scale);
		text.setAlpha(alpha);

		if (alpha != 0 && scale != 0)
			text.draw(batch);

		if (visible == true) {

			if (scale > 1)
				scale -= delta * 2;
			if (scale < 1)
				scale = 1;
			
			if (alpha < 1)
				alpha += delta * 2;
			if (alpha > 1)
				alpha = 1;
		} else {

			if (scale < 2)
				scale += delta * 2;
			if (scale > 2)
				scale = 2;
			
			if (alpha > 0)
				alpha -= delta * 2;
			if (alpha < 0)
				alpha = 0;
		
		}

	}

	public void start() {
		visible = true;
	}

	public void stop() {
		visible = false;
	}
}
