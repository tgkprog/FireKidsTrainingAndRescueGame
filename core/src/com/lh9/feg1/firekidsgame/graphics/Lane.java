package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lane {

	double timer;

	Sprite lane;
	int y;
	int x;

	float acceleration;

	public Lane(Texture lane, int x, int y) {
		this.lane = new Sprite(lane);
		this.lane.setPosition(x, y);
		this.lane.setScale(0, 0);
		this.y = y;
		this.x = x;
		this.lane.setAlpha(0);
	}

	public void render(SpriteBatch batch, float delta) {
		if(lane.getColor().a != 0)
		lane.draw(batch);
		updateTimers(delta);
	}

	void updateTimers(float delta) {

		acceleration += delta * 10;
		timer += delta;

		if (timer > 0.1f) {
			timer = 0;
			acceleration *= 1.15f;
		}
		lane.setPosition(lane.getX() - delta * 20, lane.getY() - acceleration);
		lane.setScale(lane.getScaleX() + delta, lane.getScaleY() + delta);
		if (lane.getY() < 900) {
			goUpAgain();
		}
	}

	public void goUpAgain() {
		lane.setPosition(x, y);
		this.lane.setScale(0, 0);
		acceleration = 0;
	}

	public void setAlpha(float alpha) {
		lane.setAlpha(alpha);
	}

	public void normal() {
		lane.setColor(1, 1, 1, 1);
	}

	public void red() {
		lane.setColor(1, 0, 0, 1);
	}
}
