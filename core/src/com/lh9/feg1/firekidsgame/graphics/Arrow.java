package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow {

	// This is used for rotating of the objects in some order

	int x;
	int y;

	Sprite arrow;
	double rotation;
	double rotationMin;
	double rotationMax;

	double acceleration = -20;

	boolean changeRotation;

	public Arrow(int x, int y, Texture arrow, float rotationMin,
			float rotationMax) {
		this.arrow = new Sprite(arrow);
		this.arrow.setScale(1.8f);
		this.arrow.setPosition(x, y);
		this.rotationMin = rotationMin;
		this.rotationMax = rotationMax;
		this.setAlpha(0);
		this.arrow.setRotation(0f);
		rotation = 0f;
		this.x = x;
		this.y = y;

	}

	public void render(SpriteBatch batch, float delta) {
		if (arrow.getColor().a != 0) {
			arrow.setRotation((float) rotation);
			arrow.draw(batch);
			updateRotation(delta);
		}
	}

	void updateRotation(float delta) {
		if (changeRotation == false) {
			if (acceleration < 12)
				acceleration += delta * 20;
			if (acceleration > 0)
				if (rotation < rotationMax)
					rotation += delta * acceleration * 10;
			if (rotation > rotationMax) {
				rotation = rotationMax;
				changeRotation = true;
			}
		} else {
			if (changeRotation == true) {
				if (acceleration > -12)
					acceleration -= delta * 20;
				if (rotation > rotationMin)
					rotation += delta * acceleration * 10;
				if (rotation < rotationMin) {
					rotation = rotationMin;
					changeRotation = false;
				}
			}

		}

	}

	public void setAlpha(float alpha) {
		arrow.setAlpha(alpha);
	}

	public void normal() {
		arrow.setColor(1, 1, 1, 1);
	}

	public void red() {
		arrow.setColor(1, 0, 0, 1);
	}

	public void setScale(float scale) {
		arrow.setScale(scale);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		arrow.setPosition(x, y);
	};

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
