package com.lh9.feg1.firekidsgame.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Window {

	boolean visible;
	boolean close;
	boolean fullscale;

	Sprite window;

	double scale;
	double alpha;
	double x;
	double y;

	boolean readyToClose;

	public Window(Texture windowTexture, double x, double y) {

		if(windowTexture != null){
		window = new Sprite(windowTexture);
		window.setPosition((float) x, (float) y);
		}
		this.x = x;
		this.y = y;

	}

	public void draw(SpriteBatch batch, double delta) {
		updateTimers(delta);
		if (window != null) {
			window.setScale((float) scale);
			window.draw(batch);
		}
	}

	public void updateTimers(double delta) {
		if (visible == true) {
			if (fullscale == false) {
				if (scale < 1.2f) {
					scale += delta * 2;
				}
				if (scale > 1.2f) {
					scale = 1.2f;
					fullscale = true;
				}
			} else if (scale > 1.0f) {
				scale -= delta;
				if (scale < 1) {
					scale = 1;
					readyToClose = true;
				}
			}

		}

		if (readyToClose == true && close == true) {
			if (scale > 0)
				scale -= delta * 3;
			if (scale < 0) {
				scale = 0;
				visible = false;
			}
		}
	}

	public void popUp() {
		visible = true;
		readyToClose = false;
		close = false;
		fullscale = false;
	}

	public void hide() {
		close = true;
	}

	public double getScale() {
		return scale;
	}

	public boolean isVisibile() {
		return visible;
	}

}