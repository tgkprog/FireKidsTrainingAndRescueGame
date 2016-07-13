package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticAnimation extends Animated {

	boolean continous = true;

	public void render(SpriteBatch batch, float delta) {
		if (continous == true)
			stateTime += delta;
		else if (stateTime < animationTime * frameNumber) {
			stateTime += delta;
			if (stateTime > animationTime * frameNumber)
				stateTime = animationTime * frameNumber;
		}

		currentFrame = walkAnimation.getKeyFrame(stateTime, true);

		batch.draw(currentFrame, x, y);
		batch.setColor(1, 1, 1, 1);
	}

	public void setContinous(boolean continous) {
		this.continous = continous;
	}
}
