package com.lh9.feg1.firekidsgame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Button {

	boolean dontRespond;
	int destination;
	double acceleration = 1;
	boolean wentUp;
	float blinkTimer = 0;
	boolean blinking = false;
	float alphaMinimum = 1f;
	float multiplier = 3;
	boolean selected = false;
	float alpha = 1;
	int counter = 0;
	Sprite image;
	Rectangle mouse;
	Rectangle bounds;
	Vector3 color = new Vector3(1, 1, 1);

	public Button(int x, int y, Texture texture) {
		image = new Sprite(texture);
		image.setPosition(x, y);
		mouse = new Rectangle();
		mouse.setSize(1, 1);
		bounds = new Rectangle();
		bounds.setSize(image.getWidth(), image.getHeight());
		bounds.setPosition(x, y);
	}

	public void setTexture(Texture texture) {
		float x = image.getX();
		float y = image.getY();
		image = new Sprite(texture);
		image.setPosition(x, y);
		bounds = new Rectangle();
		bounds.setSize(image.getWidth(), image.getHeight());
		bounds.setPosition(x, y);
	}

	public void render(SpriteBatch batch, float delta) {

		updateTimers(delta);
		if (alpha != 0) {
			if (alpha != image.getColor().a)
				image.setScale(alpha);
			image.setColor(color.x, color.y, color.z, image.getColor().a);
			image.draw(batch);
		}
	}

	public boolean checkCollision(int x, int y) {
		if (dontRespond == false) {
			mouse.setPosition(x, y);

			if (mouse.overlaps(bounds))
				return true;
			else
				return false;
		} else
			return false;
	}

	public void select() {
		selected = true;
	}

	public void deselect() {
		selected = false;
	}

	public boolean getSelection() {
		return selected;
	}

	public void dispose() {
	}

	void updateTimers(float delta) {

		if (image.getY() < destination + 50 && wentUp == false) {
			image.setPosition(image.getX(), image.getY() + (float) acceleration);
			if (acceleration < 15)
				acceleration += delta * 3;
		} else
			wentUp = true;

		if (image.getY() > destination) {
			acceleration -= delta * 10;
		}
		if (wentUp == true)
			acceleration -= delta * 16;

		if (image.getY() < destination && wentUp == true) {
			image.setPosition(image.getX(), destination);
			acceleration = 0;
			wentUp = true;
		}
		this.setPosition(image.getX(), image.getY() + (float) acceleration);

		if (blinking == true) {
			if (blinkTimer < 0.5f) {
				select();
				blinkTimer += delta * 2 * multiplier;
				if (blinkTimer > 0.5f) {
					blinkTimer = 0;
					deselect();
					blinking = false;
				}
			}
		}

		if (selected == true) {

			if (alpha < 1.2f)
				alpha += delta * multiplier;
			if (alpha > 1.2f)
				alpha = 1.2f;
		}
		if (selected == false) {
			if (alpha > alphaMinimum)
				alpha -= delta * multiplier;
			if (alpha < alphaMinimum)
				alpha = alphaMinimum;
		}

	}

	public void setPosition(float x, float y) {
		image.setPosition(x, y);
		bounds.setPosition(x, y);
	}

	public void goUp(int destination) {
		this.destination = destination;
		wentUp = false;
	}

	public void setAlphaMinimum(float alphaMinimum) {
		this.alphaMinimum = alphaMinimum;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

	public void blink() {
		counter++;
		blinking = true;
	}

	public void resetCounter() {
		counter = 0;
	}

	public int getCounter() {
		return counter;
	}

	public void reverseSelection() {
		selected = !selected;
	}

	public boolean notMoving() {
		if (destination == image.getY() && wentUp == true)
			return true;
		else
			return false;
	}

	public void setDontRespond(boolean dontRespond) {
		this.dontRespond = dontRespond;
	}

	public void setAlpha(float alpha) {
		image.setAlpha(alpha);
	}

	public void normal() {
		color = new Vector3(1, 1, 1);
	}

	public void red() {
		color = new Vector3(1, 0, 0);
	}
	public boolean isHighlighted(){
		if(color.z == 0)
			return true;
		else
			return false;
	}

	public float getX() {
		return image.getX();
	}

	public float getY() {
		return image.getY();
	}

	public Rectangle getBounds(){
		return bounds;
	}

	public boolean isBlockedFromInteraction() {
		return dontRespond;
	}

}