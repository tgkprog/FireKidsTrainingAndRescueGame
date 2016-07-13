package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Human extends Animated {

	float speed;
	float stateTime = 0.05f;
	float maxSpeed = 8;
	boolean animationOnly;

	int counter;
	
	public void render(SpriteBatch batch, float delta) {
		stateTime += delta * speed * 0.1f;
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		
		batch.draw(currentFrame, x, y);
		
		if(stateTime > animationTime && speed > 1.5f){
			frameBefore = walkAnimation.getKeyFrame(stateTime - animationTime, true);
			batch.setColor(1, 1, 1, 0.3f);
			batch.draw(frameBefore, x, y);
			batch.setColor(1, 1, 1, 1f);
		}
		
		if (speed >= 5 && animationOnly == false) {
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
		if(speed < maxSpeed)
		speed += 0.5f;
		else
			speed += (1/speed);
			
		if (speed > maxSpeed + 5)
			speed = maxSpeed + 5;
	
	counter++;
	}

	public void setSpeed(float s) {
		speed = s;
	}

	void updateSpeed(float delta) {

		if (animationOnly == false)
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

	public void setAnimationOnly(boolean animationOnly) {
		this.animationOnly = animationOnly;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getCounter(){
		return counter;
	}
}