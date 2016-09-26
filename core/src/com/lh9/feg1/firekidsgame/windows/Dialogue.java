package com.lh9.feg1.firekidsgame.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dialogue extends Window {

	Sprite starCollectible;
	Sprite cog;
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

	public void drawLevelSummary(Texture cog, Texture starCollectible, Texture starSummary, Texture starSummaryDesaturated,
			int goldenStars, int starsCollected, boolean victory) {
		this.cog = new Sprite(cog);
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
					if(a == 0)
					font.draw(batch, dialogueText[a], (float) x + 75,
							(float) y + 130 - a * 37);
					else
						font.draw(batch, dialogueText[a], (float) x + 15,
								(float) y + 130 - a * 37);
							
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
						
				font.draw(batch, starsCollectedInString, (float) x + 230,
						(float) y + 35);
				font.draw(batch, "1", (float) x + 163,
						(float) y + 35);
			
				
				font.setColor(1, 1, 1, fontPreviousAlpha);
				for (int a = 0; a < 3; a++) {
					if (a < goldenStars) {
						starSummary.setScale((float) (this.scale)*1f);
						starSummary.setPosition((int) x + 90 + a*75, (int) y + 50);
						starSummary.draw(batch);
					} else {
						starSummaryDesaturated.setScale((float) (this.scale)*1f);
						starSummaryDesaturated
								.setPosition((int) x + 90 + a*75, (int) y + 50);
						starSummaryDesaturated.draw(batch);
					}
				}
				cog.setScale((float)this.scale*0.75f);
				cog.setPosition((int)x+120, (int)y);
				cog.draw(batch);
				
				starCollectible.setScale((float)this.scale*0.65f);
				starCollectible.setPosition((int)x+175, (int)y-2);
				starCollectible.draw(batch);
			
			}
		}
	}

	public void checkCollision(int x, int y) {
		this.hide();

	}
}