package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class CollidableObject {

	String type;

	int x;
	int y;
	float scale;
	float timerLiving;
	boolean active;
	boolean toDestroy;
	Sprite sprite;
	Rectangle collidableBounds;
	float maxScale;

	public CollidableObject(int x, int y, Texture texture, String type) {
		this.x = x;
		this.y = y;
		sprite = new Sprite(texture);
		if (type == "Bolt") {
			sprite.setPosition(x, y - 130);
			maxScale = 0.4f;
		}
		if (type == "Oil") {
			sprite.setPosition(x, y - 30);
			maxScale = 0.6f;
		}
		active = true;
		collidableBounds = new Rectangle();
		collidableBounds.setSize(100, 1);
		this.type = type;
	}

	public void render(SpriteBatch batch, float delta) {

		timerLiving += delta;
		if (timerLiving > 10)
			toDestroy();

		if (active == true) {
			if (scale < maxScale)
				scale += delta;
			if (scale > maxScale)
				scale = maxScale;
		} else {
			if (scale > 0)
				scale -= delta;
			if (scale < 0)
				scale = 0;
		}
		if (toDestroy == true) {
			active = false;
		}

		sprite.setScale(scale);
		sprite.draw(batch);
	}

	public boolean getToDestroy() {
		return toDestroy;
	}

	public boolean isReadyToDestroy() {
		if (toDestroy == true && scale == 0)
			return true;
		else
			return false;
	}

	public Rectangle getBounds() {
		collidableBounds.setPosition(x, y);
		return collidableBounds;
	}

	public void toDestroy() {
		toDestroy = true;
	}

	public String getType() {
		return type;
	}

	public float getPercentScale() {
		return scale / maxScale;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setTimerLiving(float timerLiving){
		this.timerLiving = timerLiving;
	}
}