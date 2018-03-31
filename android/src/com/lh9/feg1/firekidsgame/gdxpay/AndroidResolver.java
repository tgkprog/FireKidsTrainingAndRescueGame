package com.lh9.feg1.firekidsgame.gdxpay;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.lh9.feg1.firekidsgame.Starter;

public class AndroidResolver extends PlatformResolver {

	private final static String GOOGLEKEY = "..............................";
    static final int RC_REQUEST = 10001;	// (arbitrary) request code for the purchase flow
    
	public AndroidApplication androidApplication;
	public Starter feg;
	
	public AndroidResolver(Starter feg, AndroidApplication androidApplication) {
		super(feg.fegPurchaseObserver);
		this.feg = feg;
		this.androidApplication = androidApplication;
		
		PurchaseManagerConfig config = feg.purchaseManagerConfig;
		config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);
		
		initializeIAP(null, feg.fegPurchaseObserver, config);
	}
}