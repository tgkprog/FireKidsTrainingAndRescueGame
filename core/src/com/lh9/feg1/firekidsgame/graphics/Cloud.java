package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cloud {

	Sprite cloudSprite;
	double popUpTimer;
	double popUpDelayTime;
	double x;
	double y;
	double scale;

	boolean visible;

	public Cloud(Texture cloudTexture, double popUpDelayTime, double x, double y) {
		cloudSprite = new Sprite(cloudTexture);
		this.popUpDelayTime = popUpDelayTime;
		this.x = x;
		this.y = y;
		cloudSprite.setPosition((float) x, (float) y);
	}
	public void draw(SpriteBatch batch, double delta) {
		updateTimers(delta);
		cloudSprite.setScale((float) scale);
		if(cloudSprite.getScaleX() > 0f)
		cloudSprite.draw(batch);
	}

	public void updateTimers(double delta) {
		randomlyMove(delta);

		if (visible == true) {
			if(Gdx.graphics.getRawDeltaTime() < 0.05f && Gdx.graphics.getDeltaTime() < 0.05f)
			if (popUpTimer < popUpDelayTime) {
				popUpTimer += delta;
			}
			if (popUpTimer > popUpDelayTime)
				popUpTimer = popUpDelayTime;
		} else {
			if (popUpTimer > 0)
				popUpTimer -= delta;
			if (popUpTimer < 0)
				popUpTimer = 0;
		}

		if (visible == true && popUpTimer == popUpDelayTime && Gdx.graphics.getRawDeltaTime() < 0.05f) {
			if (scale < 1)
				scale += delta;
			if (scale > 1)
				scale = 1;
		}
		if (visible == false && popUpTimer == 0) {
			if (scale > 0)
				scale -= delta;
			if (scale < 0)
				scale = 0;
		}
	}

	public void popUp() {
		visible = true;
		scale = 0;
		popUpTimer = 0;
	}

	public void hide() {
		visible = false;
	}

	public void randomlyMove(double delta) {
	}

	public double getScale() {
		return scale;
	}

	public double getPopUpTimer() {
		return popUpTimer;
	}

	public boolean isVisibile() {
		return visible;
	}
}