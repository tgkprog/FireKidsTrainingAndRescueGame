package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Star {

	int x;
	int y;
	Sprite star;
	float alpha = 1;
	boolean hit;

	public Star(Texture tex, int x, int y, float scale) {
		this.x = x;
		this.y = y;
		star = new Sprite(tex);
		star.setPosition(x, y);
		star.setScale(scale);
	}

	public void draw(Batch batch, float delta) {
		manageTimers(delta);
		star.setPosition(x, y);
		if (alpha != 0) {
			star.setAlpha(alpha);
			star.draw(batch);
		}
	}

	void manageTimers(float delta) {
		if (hit == true) {
			if (alpha > 0)
				alpha -= delta;
			star.rotate(alpha * 900);
			y += delta * 900;
		}
		if (alpha < 0)
			alpha = 0;
	}

	public boolean getHit() {
		return hit;
	}

	public void setHit() {
		hit = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getAlpha() {
		return alpha;
	}

	public float getWidth() {
		return star.getWidth();
	}
}