package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow {

	Sprite arrow;
	double rotation;
	double rotationMin;
	double rotationMax;

	double acceleration = 12;

	boolean changeRotation;

	public Arrow(int x, int y, Texture arrow, double arrowRotationMin,
			double arrowRotationMax) {
		this.arrow = new Sprite(arrow);
		this.arrow.setScale(1.8f);
		this.arrow.setPosition(x, y);
		this.rotationMin = arrowRotationMin;
		this.rotationMax = arrowRotationMax;
		this.setAlpha(0);
	}

	public void render(SpriteBatch batch, float delta) {
		arrow.setRotation((float) rotation);
		arrow.draw(batch);
		updateRotation(delta);		
	}

	void updateRotation(float delta) {
		if (changeRotation == false) {
			if (acceleration < 12)
				acceleration += delta*20;
			if(acceleration > 0)
			if (rotation < rotationMax)
				rotation += delta * acceleration*10;
			if (rotation > rotationMax) {
				rotation = rotationMax;
				changeRotation = true;
			}
		} else {
			if (changeRotation == true) {
				if (acceleration > -12)
					acceleration -= delta*20;
				if (rotation > rotationMin)
					rotation += delta * acceleration*10;
				if (rotation < rotationMin) {
					rotation = rotationMin;
					changeRotation = false;
				}
			}

		}
	}
	public void setAlpha(float alpha){
		arrow.setAlpha(alpha);
	}
	public void normal() {
		arrow.setColor(1, 1, 1, 1);
	}

	public void red() {
		arrow.setColor(1, 0, 0, 1);
	}
}
