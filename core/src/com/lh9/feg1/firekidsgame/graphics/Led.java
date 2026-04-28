package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Led {
	boolean normal;
	Sprite led;
	double maxTimer;
	double timer;

	public Led(Texture led, int x, int y) {
		this.led = new Sprite(led);
		this.led.setPosition(x, y);
		maxTimer = MathUtils.random(0.5f, 4f);
	}

	public void render(SpriteBatch batch, float delta) {
		if(led.getColor().a != 0)
		led.draw(batch);
		timer += delta;
		if (normal == true)
			if (timer > maxTimer) {
				timer = 0;
				if (led.getColor().r == 1) {
					led.setColor(0, 1, 1, 1);
				} else {
					led.setColor(1, 0, 0, 1);
				}
			} 
			if(normal == false)
			led.setColor(1, 0, 0, 1);

	}

	public void setAlpha(float alpha) {
		led.setAlpha(alpha);
	}

	public void normal() {
		led.setColor(1, 1, 1, 1);
		normal = true;
	}

	public void red() {
		led.setColor(1, 0, 0, 1);
		normal = false;
	}
}