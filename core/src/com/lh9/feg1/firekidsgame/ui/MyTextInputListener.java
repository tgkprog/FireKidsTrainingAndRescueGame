package com.lh9.feg1.firekidsgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class MyTextInputListener implements TextInputListener {

	String input;

	@Override
	public void input(String text) {
		if (text != null && text.length() != 0) {
		if(text.length() > 20)
			input = text.substring(0, 20);
			else
			input = text;
		}
	}

	public void start() {
		Gdx.input.getTextInput(this, "Fire Kids Game",
				"Click to type", "");
	}

	public String getInput() {
		if (input == null)
			return "";
		else
			return input;
	}

	@Override
	public void canceled() {
	}
}