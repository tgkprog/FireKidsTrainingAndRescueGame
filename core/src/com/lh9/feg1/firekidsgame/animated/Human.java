package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Human extends Animated {

	Rectangle bounds;
	float accelerationJump;
	float friction = 1;
	float speed;
	float stateTime = 0.05f;
	float maxSpeed = 8;
	boolean animationOnly;
	boolean left;
	int counter;
	float speedAdder = 0.5f;

	boolean onceOnly;

	public void render(SpriteBatch batch, float delta) {

		if (bounds != null) {
			bounds.setPosition(x, y);
		}

		if (onceOnly == false) {
			stateTime += delta * speed * 0.1f;
			if (stateTime / animationTime > frameNumber)
				stateTime = 0;

		} else {
			stateTime += delta * speed * 0.1f;
			if (stateTime / animationTime > frameNumber)
				stateTime = animationTime * frameNumber - animationTime;
		}

		if (this.fromTextureRegion == true) {
			if (animationTimeSet == false) {
				currentFrame = walkAnimation.getKeyFrame(stateTime, true);
				batch.draw(currentFrame, x, y);
			} else {
				currentFrame = walkAnimation.getKeyFrame(stateTime*animationTime, true);
				batch.draw(currentFrame, x, y);
			}
		} else {
			if (stateTime / animationTime < frameNumber)
				batch.draw(frames[(int) (stateTime / animationTime)], x, y);
		}
		if (this.fromTextureRegion == true) {
			if (stateTime > animationTime && speed > 1.5f) {
				frameBefore = walkAnimation.getKeyFrame(stateTime
						- animationTime, true);
				// batch.setColor(1, 1, 1, 0.3f);
				// batch.draw(frameBefore, x, y);
				// batch.setColor(1, 1, 1, 1f);
			}
		}

		else if (stateTime > animationTime && speed > 1.5f) {
			// batch.setColor(1, 1, 1, 0.3f);
			// batch.draw(
			// frames[(int) ((stateTime / animationTime) - animationTime)],
			// x, y);
			// batch.setColor(1, 1, 1, 1f);
		}

		if (this.fromTextureRegion == true) {
			if (speed >= 5 && animationOnly == false) {
				// batch.setColor(1, 1, 1, 0.3f);
				// batch.draw(currentFrame, x - 3, y);
				// batch.setColor(1, 1, 1, 0.25f);
				// batch.draw(currentFrame, x - 6, y);
				// batch.setColor(1, 1, 1, 0.20f);
				// / batch.draw(currentFrame, x - 9, y);
				// batch.setColor(1, 1, 1, 0.15f);
				// batch.draw(currentFrame, x - 15, y);
				// batch.setColor(1, 1, 1, 0.1f);
				// batch.draw(currentFrame, x - 21, y);
				// batch.setColor(1, 1, 1, 1);
			}
		} else if (speed >= 2 && animationOnly == false
				&& stateTime - animationTime > 0) {
			/*
			 * batch.setColor(1, 1, 1, 0.3f); batch.draw( frames[(int)
			 * (stateTime - animationTime / animationTime)], x - 3, y);
			 * batch.setColor(1, 1, 1, 0.25f); batch.draw( frames[(int)
			 * (stateTime - animationTime / animationTime)], x - 6, y);
			 * batch.setColor(1, 1, 1, 0.20f); batch.draw( frames[(int)
			 * (stateTime - animationTime / animationTime)], x - 9, y);
			 * batch.setColor(1, 1, 1, 0.15f); batch.draw( frames[(int)
			 * (stateTime - animationTime / animationTime)], x - 15, y);
			 * batch.setColor(1, 1, 1, 0.1f); batch.draw( frames[(int)
			 * (stateTime - animationTime / animationTime)], x - 21, y);
			 * batch.setColor(1, 1, 1, 1);
			 */
		}

		updateSpeed(delta);
	}

	public void move() {
		if (speed < maxSpeed)
			speed += speedAdder;
		else
			speed += (1 / speed);

		if (speed >= 0)
			if (speed > maxSpeed + 5)
				speed = maxSpeed + 5;
		if (speed < 0)
			if (speed < -maxSpeed - 5)
				speed = -maxSpeed - 5;

		counter++;
	}

	public void moveReverse() {
		if (speed > -maxSpeed)
			speed -= speedAdder;
		else
			speed -= (1 / speed);

		if (speed < -maxSpeed - 5)
			speed = -maxSpeed - 5;

		counter++;
	}

	public void setSpeed(float s) {
		speed = s;
	}

	void updateSpeed(float delta) {
		if (delta != 0) {

			if (animationOnly == false) {
				if (left == false)
					this.x += speed;
				else
					this.x -= speed;
			}
		}
		if (speed > 0)
			speed -= delta * friction;
		if (speed < 0)
			speed += delta * friction;

		if (Math.abs(speed) < 0.1f) {
			speed = 0;
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getSpeed() {
		return speed;
	}

	public void createBounds() {
		bounds = new Rectangle();
		if (fromTextureRegion == false)
			bounds.setSize(this.frames[0].getWidth(),
					this.frames[0].getHeight());
		else
			bounds.setSize(walkAnimation.getKeyFrame(stateTime, true)
					.getTexture().getWidth(),
					walkAnimation.getKeyFrame(stateTime, true).getTexture()
							.getHeight());

	}

	public Rectangle getRectangle() {
		return bounds;
	}

	public void setAnimationOnly(boolean animationOnly) {
		this.animationOnly = animationOnly;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getCounter() {
		return counter;
	}

	public void goLeft() {
		left = true;
	}

	public void goRight() {
		left = false;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void resetStateTime() {
		stateTime = 0;
		speed = 0;
	}

	public void setOnceOnly() {
		onceOnly = true;
	}

	public void setSpeedAdder(float speedAdder) {
		this.speedAdder = speedAdder;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public void setAccelerationJump(float accelerationJump) {
		this.accelerationJump = accelerationJump;
	}

	public float getAccelerationJump() {
		return accelerationJump;
	}
}