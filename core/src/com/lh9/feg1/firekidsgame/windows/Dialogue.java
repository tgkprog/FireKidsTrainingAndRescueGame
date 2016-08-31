package com.lh9.feg1.firekidsgame.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dialogue extends Window {

	Sprite starCollectible;
	Sprite starSummary;
	Sprite starSummaryDesaturated;

	BitmapFont font;
	Texture dark;

	String[] dialogueText;
	String starsCollectedInString;
	
	boolean victory;
	boolean drawLevelSummary;
	int goldenStars;
	int starsCollected;
	

	public void setDialogueText(String[] dialogueText) {
		this.dialogueText = dialogueText;
	}

	public void drawLevelSummary(Texture starCollectible, Texture starSummary, Texture starSummaryDesaturated,
			int goldenStars, int starsCollected, boolean victory) {

		this.starCollectible = new Sprite(starCollectible);
		this.starSummary = new Sprite(starSummary);
		this.starSummaryDesaturated = new Sprite(starSummaryDesaturated);
		this.goldenStars = goldenStars;
		this.starsCollected = starsCollected;
		this.victory = victory;
		drawLevelSummary = true;
		starsCollectedInString = Integer.toString(starsCollected);
		}

	public Dialogue(Texture windowTexture, Texture dark, double x, double y,
			String[] dialogueText, BitmapFont font) {
		super(windowTexture, x, y);
		this.dark = dark;
		this.dialogueText = dialogueText;
		this.font = font;
	}

	public void draw(SpriteBatch batch, float delta) {
		if ((float) this.scale * 0.5f != 0) {
			batch.setColor(1, 1, 1, (float) this.scale * 0.5f);
			batch.draw(dark, 0, 0);
			batch.setColor(1, 1, 1, 1);
		}

		super.draw(batch, delta);

		if (drawLevelSummary == false) {
			if (font != null && dialogueText != null) {
				float fontPreviousAlpha = font.getColor().a;
				if (this.scale * this.scale < 1)
					font.setColor(1, 1, 1, (float) (this.scale * this.scale));
				else
					font.setColor(1, 1, 1, 1);
				for (int a = 0; a < dialogueText.length; a++)
					font.draw(batch, dialogueText[a], (float) x + 100,
							(float) y + 130 - a * 40);

				font.setColor(1, 1, 1, fontPreviousAlpha);
			}
		} else {
			if (font != null && dialogueText != null) {
				float fontPreviousAlpha = font.getColor().a;
				if (this.scale * this.scale < 1)
					font.setColor(1, 1, 1, (float) (this.scale * this.scale));
				else
					font.setColor(1, 1, 1, 1);
				if(victory == true)
				font.draw(batch, "   Victory!", (float) x + 100,
						(float) y + 140);
				else
					font.draw(batch, "     Ooops...", (float) x + 100,
							(float) y + 140);
						
				font.draw(batch, starsCollectedInString, (float) x + 195,
						(float) y + 35);
			
				font.setColor(1, 1, 1, fontPreviousAlpha);
				for (int a = 0; a < 3; a++) {
					if (a < goldenStars) {
						starSummary.setScale((float) (this.scale)*1f);
						starSummary.setPosition((int) x + 115 + a*55, (int) y + 50);
						starSummary.draw(batch);
					} else {
						starSummaryDesaturated.setScale((float) (this.scale)*1f);
						starSummaryDesaturated
								.setPosition((int) x + 115 + a*55, (int) y + 50);
						starSummaryDesaturated.draw(batch);
					}
				}
				starCollectible.setScale((float)this.scale*0.75f);
				starCollectible.setPosition((int)x+140, (int)y);
				starCollectible.draw(batch);
			
			}
		}
	}

	public void checkCollision(int x, int y) {
		this.hide();

	}
}