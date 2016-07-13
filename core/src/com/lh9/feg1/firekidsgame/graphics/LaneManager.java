package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LaneManager {

	Lane lanes[];

	public LaneManager(Texture lane, int x, int y) {
		lanes = new Lane[1];
		for (int a = 0; a < 1; a++) {
			lanes[a] = new Lane(lane, x, y - a * 100);
		}
	}

	public void render(SpriteBatch batch, float delta) {
		for (int a = 0; a < 1; a++) {
			lanes[a].render(batch, delta);
		}
	}
	public void setAlpha(float alpha){
		for (int a = 0; a < 1; a++) {
			lanes[a].setAlpha(alpha);
		}
	}
	public void red(){
		for (int a = 0; a < 1; a++) 
			lanes[a].red();
		
	}
	public void normal(){
		for (int a = 0; a < 1; a++) 
			lanes[a].normal();
		
	}
	
}