package yang.com.style.main;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdScreen  {

    private InterstitialAd mInterstitial;
    private Context context;

    public void AdsCreation(final Context context) {
        this.context=context;
        mInterstitial = new InterstitialAd(context);
        mInterstitial.setAdUnitId("ca-app-pub-4141254649449903/7131397446");
        mInterstitial.loadAd(new AdRequest.Builder().build());
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                showInterstitial();
            }
        });


    }

    private void showInterstitial() {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
            //optionally load again if you plan to show another one later
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitial.loadAd(adRequest);

        }
    }

  /*  @Override
    public void onBackPressed() {
        startActivity(new Intent(AdScreen.this, PickImageActivity.class));
        finish();
    }
*/

}
