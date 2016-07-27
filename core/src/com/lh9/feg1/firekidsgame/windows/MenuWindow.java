package com.lh9.feg1.firekidsgame.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lh9.feg1.firekidsgame.ui.Button;

public class MenuWindow extends Dialogue {

	Button menu;
	Button retry;
	Button play;
	String screen;
	
	public MenuWindow(Texture windowTexture, Texture dark, double x, double y, Button menu,
			Button retry, Button play, String screen) {
		super(null, dark, x, y, null);
		this.menu = menu;
		this.retry = retry;
		this.play = play;
		this.screen = screen;
	}

	public void draw(SpriteBatch batch, float delta) {	
		
		menu.goUp((int)this.y+25);
		retry.goUp((int)this.y+25);
		play.goUp((int)this.y+25);
				
		menu.setPosition((int) this.x + 25, (int) this.y + 25);
		retry.setPosition((int) this.x + 125, (int) this.y + 25);
		play.setPosition((int) this.x + 225, (int) this.y + 25);
		
		menu.setAlpha((float) this.scale*0.7f);
		retry.setAlpha((float) this.scale*0.7f);
		play.setAlpha((float) this.scale*0.7f);
		
		super.draw(batch, delta);
		
		menu.render(batch, delta);
		retry.render(batch, delta);
		play.render(batch, delta);

		if (this.visible == true) {
			menu.setDontRespond(false);
			retry.setDontRespond(false);
			play.setDontRespond(false);
		} else {
			menu.setDontRespond(true);
			retry.setDontRespond(true);
			play.setDontRespond(true);
		}

	}
	public Button getRetry(){
		return retry;
	}
	public Button getPlay(){
		return play;
	}
	public Button getMenu(){
		return menu;
	}
	public String getScreen(){
		return screen;
	}
}