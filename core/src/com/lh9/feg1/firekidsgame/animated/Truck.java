package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Truck extends Human {

	Sprite frameSprite;
	Sprite wheel;
	int lane = 2;
	boolean[] lanesTaken;
	static final int firstLanePosition = 210;
	static final int secondLanePosition = 135;
	static final int animationLanePosition = 80;
	Rectangle truck;
	Rectangle bounds;
	Vector2 maxPositions;
	boolean animation;
	double previousStateTime;
	boolean allowMovingReverse;
	Sprite reversedFrame;
	float scale = 1;

	public void bump() {

		if (speed > 0)
			speed *= -1;

		if (Math.abs(speed) > 3) {
			if (speed < 0)
				speed = -3;
			if (speed > 0)
				speed = 3;
		}
	}

	public boolean checkCollision(float x, float y) {

		truck.setPosition(x, y);
		bounds.setPosition(this.x, this.y);
		if (truck.overlaps(bounds)) {
			bump();
			return true;
		} else
			return false;

	}

	public boolean checkCollision(Rectangle bounds) {

		truck.setPosition(x, y);
		if (truck.overlaps(bounds)) {
			return true;
		} else
			return false;

	}

	public void render(SpriteBatch batch, float delta) {

		if (animation == true && stateTime < animationTime * frameNumber)
			stateTime += delta * 0.05f;

		if (this.fromTextureRegion == true) {

			if (stateTime != previousStateTime || currentFrame == null)
				currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
			if (allowMovingReverse == false)
				batch.draw(currentFrame, x, y);
			else {
				reversedFrame = new Sprite(currentFrame);
				reversedFrame.setPosition(x, y);
				if (speed < 0)
					reversedFrame.flip(true, false);
				reversedFrame.draw(batch);
			}
		} else {

			if (stateTime / animationTime > frameNumber)
				stateTime = animationTime * frameNumber - animationTime * 0.5f;

			if (allowMovingReverse == false) {

				try {
					frameSprite = new Sprite(
							frames[(int) (stateTime / animationTime)]);
				} catch(ArrayIndexOutOfBoundsException e){
					frameSprite = new Sprite(frames[frames.length - 1]);
				}
				frameSprite.setPosition(x, y);
				frameSprite.setScale(scale);
				frameSprite.draw(batch);
			} else {
				reversedFrame = new Sprite(
						frames[(int) (stateTime / animationTime)]);
				reversedFrame.setScale(scale);
				reversedFrame.setPosition(x, y);
				if (speed < 0)
					reversedFrame.flip(true, false);
				reversedFrame.draw(batch);
			}

		}

		batch.setColor(1, 1, 1, 1);
		if (wheel != null) {
			if (allowMovingReverse == false) {
				if (scale == 1) {
					wheel.setPosition(x + 85, y + 10);
					wheel.draw(batch);
					wheel.setPosition(x + 380, y + 10);
					wheel.draw(batch);
					wheel.setPosition(x + 770, y + 10);
					wheel.draw(batch);
					wheel.rotate(delta * 100 * speed);
				} else {
					wheel.setPosition(x - 310, y - 290);
					wheel.draw(batch);
					wheel.setPosition(x + 340, y - 290);
					wheel.draw(batch);
					wheel.setPosition(x + 1200, y - 290);
					wheel.draw(batch);
					wheel.rotate(delta * 100 * speed);
				}
			} else {
				if (speed < 0) {
					if (scale == 1) {
						wheel.setPosition(x + 60, y + 10);
						wheel.draw(batch);
						wheel.setPosition(x + 445, y + 10);
						wheel.draw(batch);
						wheel.setPosition(x + 740, y + 10);
						wheel.draw(batch);
						wheel.rotate(delta * 100 * speed);
					} else {
						wheel.setPosition(x - 370, y - 290);
						wheel.draw(batch);
						wheel.setPosition(x + 485, y - 290);
						wheel.draw(batch);
						wheel.setPosition(x + 1140, y - 290);
						wheel.draw(batch);
						wheel.rotate(delta * 100 * speed);
					}
				} else if (speed >= 0) {
					if (scale == 1) {
						wheel.setPosition(x + 85, y + 10);
						wheel.draw(batch);
						wheel.setPosition(x + 380, y + 10);
						wheel.draw(batch);
						wheel.setPosition(x + 770, y + 10);
						wheel.draw(batch);
						wheel.rotate(delta * 100 * speed);
					} else {
						wheel.setPosition(x - 310, y - 290);
						wheel.draw(batch);
						wheel.setPosition(x + 340, y - 290);
						wheel.draw(batch);
						wheel.setPosition(x + 1200, y - 290);
						wheel.draw(batch);
						wheel.rotate(delta * 100 * speed);
					}
				}
			}
		}
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

		y = 135;
		this.wheel = new Sprite(wheel);
		this.wheel.setScale(2f * scale);
		stateTime = 0;
		lanesTaken = new boolean[3];
		truck = new Rectangle();
		truck.setSize(864, 1);
		bounds = new Rectangle();

		if (this.fromTextureRegion == true)
			bounds.setSize(((TextureRegion) this.walkAnimation.getKeyFrame(stateTime, true))
					.getTexture().getWidth(), 1);
		else
			bounds.setSize(this.frames[0].getWidth(), 1);
		bounds.setPosition(x, y);

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

	public void setAllowReverse(boolean allowMovingReverse) {
		this.allowMovingReverse = allowMovingReverse;
	}

	void manageLanes(float delta) {

		if (y == animationLanePosition) {
			lanesTaken[0] = true;
			lanesTaken[1] = false;
			lanesTaken[2] = false;
		}

		if (y == firstLanePosition) {
			lanesTaken[0] = false;
			lanesTaken[1] = true;
			lanesTaken[2] = false;
		}

		if (y == secondLanePosition) {
			lanesTaken[0] = false;
			lanesTaken[1] = false;
			lanesTaken[2] = true;
		}

		if (y > secondLanePosition && y < firstLanePosition) {
			lanesTaken[0] = false;
			lanesTaken[1] = true;
			lanesTaken[2] = true;
		}

		if (lane == 0) {
			if (y < animationLanePosition)
				y = animationLanePosition;
			if (y > animationLanePosition)
				y -= delta * 300;
		}

		if (Math.abs(speed) > 1) {
			if (lane == 1) {
				if (y < firstLanePosition) {
					y += delta * 50 * Math.abs(speed);
				}
				if (y > firstLanePosition)
					y = firstLanePosition;
			}

			if (lane == 2) {
				if (y > secondLanePosition) {
					y -= delta * 50 * Math.abs(speed);
				}
				if (y < secondLanePosition)
					y = secondLanePosition;
			}
		}
	}

	public boolean getAllowMovingReverse() {
		return allowMovingReverse;
	}

	public void setScale(float scale) {
		this.scale = scale;
		if (wheel != null)
			wheel.setScale(2f * scale);
	}
}