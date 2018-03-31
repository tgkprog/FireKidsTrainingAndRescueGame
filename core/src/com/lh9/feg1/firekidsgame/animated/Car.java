package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Car extends Truck {

	boolean goAutomatically;
	boolean dontCheckCollision;
	double timerGoAutomatically;
	double wait = 4;

	public void render(SpriteBatch batch, float delta) {

		if (animation == true && stateTime < animationTime * frameNumber)
			stateTime += delta * 0.05f;

		if (this.fromTextureRegion == true) {
			if (stateTime != previousStateTime || currentFrame == null)
				currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

			batch.draw(currentFrame, x, y);
		} else {
			if (stateTime / animationTime > frameNumber)
				stateTime = animationTime * frameNumber - animationTime;

			batch.draw(frames[(int) (stateTime / animationTime)], x, y);
		}
		batch.setColor(1, 1, 1, 1);
		wheel.setPosition(x + 35, y);
		wheel.draw(batch);
		wheel.setPosition(x + 240, y);
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

		if (wait >= 4)
			updateSpeed(delta);

		previousStateTime = stateTime;
	}

	public void goAutomatically(boolean goAutomatically) {
		this.goAutomatically = goAutomatically;
	}

	public void manageGoAutomatically(float delta) {
		manageLanes(delta);
		if (wait >= 4) {

			if (goAutomatically == true) {
				timerGoAutomatically += delta;
				if (timerGoAutomatically > 0.3f) {
					move();
					timerGoAutomatically = 0;
				}
			}
		} else
			wait += delta;

	}

	public void waitSec() {
		wait = 0;
		speed = 0;
	}

	public void dontCheckCollision() {
		dontCheckCollision = true;
	}

	public boolean checkCollision(float x, float y) {
		if (dontCheckCollision == false) {
			truck.setPosition(x, y);
			bounds.setPosition(this.x, this.y);
			if (truck.overlaps(bounds)) {
				bump();
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean checkCollision(Rectangle bounds) {
		if (dontCheckCollision == false) {
			truck.setPosition(x, y);
			if (truck.overlaps(bounds)) {
				return true;
			} else
				return false;
		} else
			return false;

	}

}