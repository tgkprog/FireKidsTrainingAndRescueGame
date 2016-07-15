package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Truck extends Human {

	Sprite wheel;

	int lane = 1;

	static final int firstLanePosition = 210;
	static final int secondLanePosition = 135;
	static final int animationLanePosition = 75;

	Vector2 maxPositions;
	boolean animation;
	double previousStateTime;

	public void render(SpriteBatch batch, float delta) {
		if (animation == true && stateTime < animationTime * frameNumber)
			stateTime += delta * 0.05f;

		if (stateTime != previousStateTime || currentFrame == null)
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);

		batch.draw(currentFrame, x, y);

		batch.setColor(1, 1, 1, 1);
		wheel.setPosition(x + 85, y + 10);
		wheel.draw(batch);
		wheel.setPosition(x + 380, y + 10);
		wheel.draw(batch);
		wheel.setPosition(x + 770, y + 10);
		wheel.draw(batch);
		wheel.rotate(delta * 70 * speed);

		if (x < maxPositions.x) {
			speed = 0;
			x = (int) maxPositions.x;
		}
		if (x > maxPositions.y) {
			x = (int) maxPositions.y;
			speed = 0;
		}
		updateSpeed(delta);
		manageLanes(delta);
		previousStateTime = stateTime;
	}

	public void loadWheel(Texture wheel) {
		this.wheel = new Sprite(wheel);
		this.wheel.setScale(2f);
		stateTime = 0;
	}

	public void setMaxPositions(int x1, int x2) {
		maxPositions = new Vector2(x1, x2);
	}

	public void runAnimation() {
		animation = true;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void upLane() {
		lane = 1;
	}

	public void downLane() {
		lane = 2;
	}

	public void animationLane() {
		lane = 0;
	}

	void manageLanes(float delta) {

		System.out.println(y);

		if (lane == 0) {
			if (y < animationLanePosition)
				y = animationLanePosition;
			if (y > animationLanePosition)
				y -= delta * 300;
		}

		if (speed > 1) {

			if (lane == 1) {
				if (y < firstLanePosition) {
					y += delta * 50 * speed;
				}
				if (y > firstLanePosition)
					y = firstLanePosition;
			}
			if (lane == 2) {
				if (y > secondLanePosition) {
					y -= delta * 50 * speed;
				}
				if (y < secondLanePosition)
					y = secondLanePosition;
			}
		}
	}
}