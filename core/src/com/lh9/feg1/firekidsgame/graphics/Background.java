package com.lh9.feg1.firekidsgame.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Background {

	FrameBuffer fbo;
	SpriteBatch batch;

	Texture fboTexture;
	Sprite fboSprite;

	ArrayList<Vector2> types;
	ArrayList<Arrow> arrows;
	ArrayList<Texture> textures;
	ArrayList<Sprite> sprites;
	ArrayList<Vector2> texturesPositions;
	LaneManager laneManager;
	LedManager ledManager;

	public Background() {
		fbo = new FrameBuffer(Format.RGBA8888, 2550, 1530, true);
		arrows = new ArrayList<Arrow>();
		textures = new ArrayList<Texture>();
		sprites = new ArrayList<Sprite>();
		texturesPositions = new ArrayList<Vector2>();
		types = new ArrayList<Vector2>();
	}

	public void add(Texture texture, float x, float y) {
		textures.add(texture);
		types.add(new Vector2(0, 1));
		texturesPositions.add(new Vector2(x, y));
	}

	public void add(Arrow arrow) {
		arrows.add(arrow);
		types.add(new Vector2(0, 2));
	}

	public void add(Sprite sprite) {
		sprites.add(sprite);
		types.add(new Vector2(0, 3));
	}

	public void addLaneManager(LaneManager laneManager) {
		this.laneManager = laneManager;
		types.add(new Vector2(0, 5));
	}

	public void addLedManager(LedManager ledManager) {
		this.ledManager = ledManager;
		types.add(new Vector2(0, 6));
	}

	public void render(SpriteBatch batch, float delta, float alpha,
			Vector3 color) {

		fbo.begin();
		
		int tex = 0;
		int spr = 0;
		int arr = 0;

		for (int a = 0; a < types.size(); a++) {
			if (types.get(a).y == 1) {
				// Textures
				batch.setColor(1, 1, 1, 1);
				batch.draw(textures.get(tex), texturesPositions.get(tex).x,
						texturesPositions.get(tex).y);
				tex++;
			}

			if (types.get(a).y == 2) {
				// Arrows
				arrows.get(arr).render(batch, delta);
				arr++;
			}

			if (types.get(a).y == 3) {
				// Sprites
				sprites.get(spr).draw(batch);
				spr++;
			}

			if (types.get(a).y == 5) {
				// LaneManager
				laneManager.render(batch, delta);
				batch.setColor(color.x,color.y,color.z,1);
			}

			if (types.get(a).y == 6) {
				// LedManager
				ledManager.render(batch, delta);
				batch.setColor(color.x,color.y,color.z,1);
			}

		}

		fbo.end();

		fboTexture = fbo.getColorBufferTexture();
		fboTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		fboSprite = new Sprite(fboTexture);
		fboSprite.flip(false, true);
		fboSprite.setColor(color.x, color.y, color.z, alpha);
		fboSprite.draw(batch);
	}
}