package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenTransition {

	Texture texture;
	double alpha;
	boolean brighten;

	public ScreenTransition(Texture texture) {
		this.texture = texture;
	}

	public void render(SpriteBatch batch, double delta) {

		if (alpha != 0) {
			batch.setColor(1, 1, 1, (float) alpha);
			batch.draw(texture, 0, 0);
			batch.setColor(1, 1, 1, 1);
		}
		if (brighten == false) {
			if (alpha < 1)
				alpha += 3 * delta;
			if (alpha > 1)
				alpha = 1;
		} else {
			if (alpha > 0)
				alpha -= 3 * delta;
			if (alpha < 0)
				alpha = 0;
		}
	}

	public void renderBrighten(SpriteBatch batch, double delta) {
		batch.setColor(1, 1, 1, (float) alpha);
		batch.draw(texture, 0, 0);
		batch.setColor(1, 1, 1, 1);

		if (alpha > 0)
			alpha -= 3.5f * delta;
		if (alpha < 0)
			alpha = 0;

	}

	public void renderDarken(SpriteBatch batch, double delta) {
		batch.setColor(1, 1, 1, (float) alpha);
		batch.draw(texture, 0, 0);
		batch.setColor(1, 1, 1, 1);

		if (alpha < 1)
			alpha += delta;
		if (alpha > 1)
			alpha = 1;
	}

	public void setAlphaZero() {
		alpha = 0;
	}

	public void setAlphaOne() {
		alpha = 1;

	}

	public double getAlpha() {
		return alpha;
	}

	public void brighten() {
		brighten = true;
	}

	public void darken() {
		brighten = false;
	}
}