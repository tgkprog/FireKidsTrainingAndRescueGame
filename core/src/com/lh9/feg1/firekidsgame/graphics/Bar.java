package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bar {

	Sprite speedometerSprite;
	boolean speedometer;
	boolean visibility;
	float alpha;

	int x;
	int y;
	float maximum;

	Texture barNotFilled;
	Texture barFilled;

	public Bar(Texture barNotFilled, Texture barFilled, int x, int y,
			float maximum) {
		this.barNotFilled = barNotFilled;
		this.barFilled = barFilled;
		this.x = x;
		this.y = y;
		this.maximum = maximum;
	}

	public void render(SpriteBatch batch, float delta, float speed) {
		
		if (visibility == true) {
			if (alpha < 1)
				alpha += delta * 3;
			if (alpha > 1)
				alpha = 1;
		} else {
			if (alpha > 0)
				alpha -= delta * 3;
			if (alpha < 0)
				alpha = 0;
		}

		batch.setColor(1, 1, 1, alpha);
		batch.draw(barFilled, x, y);

		if (speed > 0) {
			if (barFilled.getWidth() * speed / maximum > barFilled.getWidth())
				batch.draw(barNotFilled, x, y, barFilled.getWidth(),
						barFilled.getHeight());
			else
				batch.draw(barNotFilled, x, y, barFilled.getWidth() * speed
						/ maximum, barFilled.getHeight());
		}

		batch.setColor(1, 1, 1, 1);
		
		if(speedometer == true){
			speedometerSprite.setAlpha(alpha);
			speedometerSprite.draw(batch);
		}
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setSpeedometer(Texture speedometerTex) {
	//	speedometer = true;
	//	speedometerSprite = new Sprite(speedometerTex);
	//	speedometerSprite.setPosition(x-100, y-10);
	}
}