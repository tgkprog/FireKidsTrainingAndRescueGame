package com.lh9.feg1.firekidsgame.animated;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animated {

	int FRAME_COLS;
	int FRAME_ROWS;

	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	TextureRegion currentFrame;
	TextureRegion frameBefore;

	int x;
	int y;

	float stateTime;
	int frameNumber;
	float animationTime = 0.025f;
	boolean animationTimeSet;
	boolean fromTextureRegion = true;
	Texture[] frames;

	public void create(Texture walkSheet, int FRAME_COLS, int FRAME_ROWS,
			int frameNumber, int x, int y, float animationTime) {
		this.animationTime = animationTime;
		this.frameNumber = frameNumber;
		this.x = x;
		this.y = y;
		this.FRAME_COLS = FRAME_COLS;
		this.FRAME_ROWS = FRAME_ROWS;

		this.walkSheet = walkSheet;
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);

		walkFrames = new TextureRegion[frameNumber];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i * FRAME_COLS + j < frameNumber)
					walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(animationTime, walkFrames);
		stateTime = 0f;
	}

	public void create(Texture walkSheet[], int FRAME_COLS, int FRAME_ROWS,
			int frameNumber, int x, int y) {

		this.x = x;
		this.y = y;
		
		fromTextureRegion = false;
		this.frameNumber = frameNumber;
		frames = walkSheet;
		stateTime = 0f;
	}

	public void create(Texture walkSheet, int FRAME_COLS, int FRAME_ROWS,
			int frameNumber, int x, int y) {

		this.frameNumber = frameNumber;
		this.x = x;
		this.y = y;
		this.FRAME_COLS = FRAME_COLS;
		this.FRAME_ROWS = FRAME_ROWS;

		this.walkSheet = walkSheet;
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);

		walkFrames = new TextureRegion[frameNumber];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i * FRAME_COLS + j < frameNumber)
					walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(animationTime, walkFrames);
		stateTime = 0f;
	}

	public void render(SpriteBatch batch, float delta) {
		stateTime += delta;
		if (fromTextureRegion == true)
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);

		batch.begin();
		if (fromTextureRegion == true)
			batch.draw(currentFrame, x, y);
		else {


			if (stateTime / animationTime > frameNumber)
				stateTime = 0;
			batch.draw(frames[(int) (stateTime / animationTime)], x, y);

		}
		batch.end();

	}
	public void setAnimationTime(float animationTime){
		this.animationTime = animationTime;
		animationTimeSet = true;
	}
}