package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import javax.xml.soap.Text;

public class StaticAnimation extends Animated {

	float rotation;
	boolean continous = true;
	boolean start;
	boolean withPreviousFrame;
	Vector2 scale = new Vector2(1, 1);
	Sprite frameSprite;

	Vector3 color = new Vector3(1, 1, 1);

	public void render(SpriteBatch batch, float delta) {

		if (this.fromTextureRegion == true) {
			if (start == true) {
				if (continous == true)
					stateTime += delta;
				else if (stateTime < animationTime * frameNumber) {
					stateTime += delta;
					if (stateTime > animationTime * frameNumber)
						stateTime = animationTime * frameNumber;
				}
			}

			frameSprite = new Sprite((TextureRegion) walkAnimation.getKeyFrame(stateTime, true));
			frameSprite.setRotation(rotation);
			frameSprite.setPosition(x, y);
			frameSprite.setColor(color.x, color.y, color.z, 1);
			frameSprite.setScale(scale.x, scale.y);
			frameSprite.draw(batch);

			if (withPreviousFrame == true && stateTime > animationTime) {
				frameSprite = new Sprite((TextureRegion) walkAnimation.getKeyFrame(stateTime
						- animationTime, true));
				frameSprite.setRotation(rotation);
				frameSprite.setColor(color.x, color.y, color.z, 0.5f);
				frameSprite.setPosition(x, y);
				frameSprite.setScale(scale.x, scale.y);
				frameSprite.draw(batch);
			}
		} else {

			stateTime += delta;

			if (continous == true) {
				if (stateTime / animationTime > frameNumber)
					stateTime = animationTime * frameNumber - animationTime
							* 0.5f;
			} else if (stateTime > frameNumber * animationTime)
				stateTime = 0;

			frameSprite = new Sprite(frames[(int) (stateTime / animationTime)]);
			frameSprite.setPosition(x, y);
			frameSprite.setRotation(rotation);
			frameSprite.setScale(scale.x, scale.y);
			frameSprite.draw(batch);
		}
	}

	public void setContinous(boolean continous) {
		this.continous = continous;
	}

	public void start() {
		start = true;
	}

	public void setWithPreviousFrame(boolean withPreviousFrame) {
		this.withPreviousFrame = withPreviousFrame;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void normal() {
		color = new Vector3(1, 1, 1);
	}

	public void red() {
		color = new Vector3(1, 0, 0);
	}
	public void setRotation(float rotation){
		this.rotation = rotation;
	}

}