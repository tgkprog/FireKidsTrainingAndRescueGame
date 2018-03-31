package com.lh9.feg1.firekidsgame.desktop.gdxpay;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.lh9.feg1.firekidsgame.Starter;
import com.lh9.feg1.firekidsgame.gdxpay.PlatformResolver;

public class DesktopResolver extends PlatformResolver {

	public LwjglApplication desktopApplication;
	public Starter feg;
	
	public DesktopResolver(Starter feg, LwjglApplication desktopApplication) {
		super(feg.fegPurchaseObserver);
		this.feg = feg;
		this.desktopApplication = desktopApplication;
	}
}