package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Human extends Animated {

	float speed;
	float stateTime = 0.05f;

	public void render(SpriteBatch batch, float delta) {
		stateTime += delta * speed * 0.1f;
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, x, y);
		if (speed >= 5) {
			batch.setColor(1, 1, 1, 0.3f);
			batch.draw(currentFrame, x - 3, y);
			batch.setColor(1, 1, 1, 0.25f);
			batch.draw(currentFrame, x - 6, y);
			batch.setColor(1, 1, 1, 0.20f);
			batch.draw(currentFrame, x - 9, y);
			batch.setColor(1, 1, 1, 0.15f);
			batch.draw(currentFrame, x - 15, y);
			batch.setColor(1, 1, 1, 0.1f);
			batch.draw(currentFrame, x - 21, y);

			batch.setColor(1, 1, 1, 1);
		}
		updateSpeed(delta);
	}

	public void move() {
		speed += 0.5f;
		if (speed > 8)
			speed = 8;
	}

	public void setSpeed(float s) {
		speed = s;
	}

	void updateSpeed(float delta) {

		this.x += speed;

		if (speed > 0)
			speed -= delta;
		if (speed < 0)
			speed = 0;
	}

	public float getX() {
		return x;
	}

	public float getSpeed() {
		return speed;
	}
}