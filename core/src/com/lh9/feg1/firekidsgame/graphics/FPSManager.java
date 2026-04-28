package com.lh9.feg1.firekidsgame.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FPSManager {

	FPSLogger fpsLogger;
	BitmapFont font;
	boolean draw;

	public FPSManager(BitmapFont font, boolean draw) {
		fpsLogger = new FPSLogger();
		this.font = font;
		this.draw = draw;
	}

	public void render(SpriteBatch batch) {
		if(draw == true)
		font.draw(batch, Integer.toString(Gdx.graphics.getFramesPerSecond()),
				625, 460);
	}
}