package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LedManager {

	Led leds[];

	public LedManager(Texture led) {
		leds = new Led[3];
		leds[0] = new Led(led, 1180, 670);
		leds[1] = new Led(led, 1330, 670);
		leds[2] = new Led(led, 1480, 670);
	}

	public void render(SpriteBatch batch, float delta) {
		for (int a = 0; a < 3; a++) {
			leds[a].render(batch, delta);
		}
		
	}
	public void setAlpha(float alpha){
		for (int a = 0; a < 1; a++) {
			leds[a].setAlpha(alpha);
		}
	}
	public void red(){
		for (int a = 0; a < 1; a++) 
			leds[a].red();
		
	}
	public void normal(){
		for (int a = 0; a < 1; a++) 
			leds[a].normal();
		
	}
	
}
