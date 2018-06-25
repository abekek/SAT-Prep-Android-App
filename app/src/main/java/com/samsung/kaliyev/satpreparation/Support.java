package com.samsung.kaliyev.satpreparation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Support extends AppCompatActivity {
    SharedPref sharedPref;

    LinearLayout adBlock;

    private AdView mAdView;

    //private BillingClient mBillingClient;

    Context mContext;

    //PurchasesUpdatedListener mPurchasesUpdatedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Toolbar toolbar = findViewById(R.id.toolbar); //создаем объект toolbar, которому принадлежит toolbar
        toolbar.setTitle("Support");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adBlock = findViewById(R.id.adBlock);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*mBillingClient = new BillingClient.Builder(Context)
                .setListener(PurchasesUpdatedListener)
                .build();*/

        /*mBillingClient = BillingClient.newBuilder(this)
                .setListener((PurchasesUpdatedListener) Support.this)
                .build();*/

        /*mBillingClient = BillingClient.newBuilder(mContext)
                .setListener(mPurchasesUpdatedListener)
                .build();

        List<String> skuList = new ArrayList<>();
        skuList.add("premiumUpgrade");
        skuList.add("gas");
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        mBillingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {

                    }
                });

        mBillingClient.querySkuDetailsAsync(BillingClient.SkuType.INAPP, skuList, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        String sku = skuDetails.getSku();
                        String price = skuDetails.getPrice();
                    }
                }
            }
        });

        mBillingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished ( int responseCode){
                    if (responseCode == BillingClient.BillingResponse.OK) {

                    }
                }

                @Override
                public void onBillingServiceDisconnected () {

                }
            });*/
        }


    /*@Override
    void onPurchasesUpdated(@BillingClient.BillingResponse int responseCode,
                            List<Purchase> purchases){
            if (responseCode == BillingClient.BillingResponse.OK
                    && purchases != null) {
                for (Purchase purchase : purchases) {
                    handlePurchase(purchase);
                }
            } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
                // Handle an error caused by a user canceling the purchase flow.
            } else {
                // Handle any other error codes.
            }
        };*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
