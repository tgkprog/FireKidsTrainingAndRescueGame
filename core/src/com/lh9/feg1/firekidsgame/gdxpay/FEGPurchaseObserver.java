package com.lh9.feg1.firekidsgame.gdxpay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FEGPurchaseObserver implements PurchaseObserver {

    public boolean fullVersionBuyError = false;
    public boolean fullVersionBuyAttempt = false;

    public void clear() {
        fullVersionBuyAttempt = false;
        fullVersionBuyError = false;
    }

    @Override
    public void handleRestore(Transaction[] transactions) {

        Gdx.app.log("FEG PURCHASE OBSERVER", "Restoring previous transactions");
        boolean bought = false;
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i].getIdentifier().equals(FEG_PRODUCT_ID_FULL_VERSION)) {
                bought = checkTransaction(transactions[i].getOrderId());
                break;
            }
        }

        Gdx.app.log("FEG PURCHASE OBSERVER", "Found already done transaction? " + bought);
        if (bought)
            fullVersionBuyAttempt = true;


    }

    @Override
    public void handleRestoreError(Throwable e) {
        // getPlatformResolver().showToast("FEGPurchaseObserver: handleRestoreError!");
        Gdx.app.log("ERROR", "FEGPurchaseObserver: handleRestoreError!: " + e.getMessage());
        throw new GdxRuntimeException(e);
    }

    @Override
    public void handleInstall() {
        // getPlatformResolver().showToast("FEGPurchaseObserver: installed successfully...");
        Gdx.app.log("handleInstall: ", "successfully..");
    }

    @Override
    public void handleInstallError(Throwable e) {
        // getPlatformResolver().showToast("FEGPurchaseObserver: handleInstallError!");
        Gdx.app.log("ERROR", "FEGPurchaseObserver: handleInstallError!: " + e.getMessage());
        throw new GdxRuntimeException(e);
    }

    @Override
    public void handlePurchase(Transaction transaction) {
        checkTransaction(transaction.getIdentifier());
        fullVersionBuyAttempt = true;
    }

    @Override
    public void handlePurchaseError(Throwable e) {
        if (e.getMessage().equals("There has been a Problem with your Internet connection. Please try again later")) {

            // this check is needed because user-cancel is a handlePurchaseError too)
            // getPlatformResolver().showToast("handlePurchaseError: " + e.getMessage());
        }
        fullVersionBuyError = true;
        throw new GdxRuntimeException(e);
    }

    @Override
    public void handlePurchaseCanceled() {
    }

    public static final String FEG_PRODUCT_ID_FULL_VERSION = "feg1";

    protected boolean checkTransaction(String ID) {
        boolean returnbool = false;

        if (FEG_PRODUCT_ID_FULL_VERSION.equals(ID)) {
            Gdx.app.log("checkTransaction", "full version found!");
            returnbool = true;
        }
        return returnbool;
    }

}
