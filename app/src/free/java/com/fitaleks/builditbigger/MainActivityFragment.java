package com.fitaleks.builditbigger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fitaleks.builditbigger.BaseMainFragment;
import com.fitaleks.displayjokes.ActivityJoke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Main fragment for free version of the app
 */
public class MainActivityFragment extends BaseMainFragment {

    private String loadedJoke;

    private InterstitialAd interstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);

        final Button btnTellJoke = (Button) root.findViewById(R.id.btn_tell_joke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });
        progressBar = (ProgressBar)root.findViewById(R.id.main_progress);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                openJokeContentActivity(loadedJoke);
            }
        });
        requestNewInterstitial();

        return root;
    }

    private void requestNewInterstitial() {
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }

    protected void tellJoke(){
        if (!Utils.isConnected(getContext())) {
            showErrorDialog();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask(new IJokeDownloadListener() {
            @Override
            public void onJokeLoaded(final String joke) {
                if (interstitialAd.isLoaded()) {
                    loadedJoke = joke;
                    interstitialAd.show();
                } else {
                    openJokeContentActivity(joke);
                }
            }
        }).execute();
    }

    private void openJokeContentActivity(String joke) {
        progressBar.setVisibility(View.GONE);
        final Intent intent = new Intent(getActivity(), ActivityJoke.class);
        intent.putExtra(ActivityJoke.KEY_JOKE, joke);
        getActivity().startActivity(intent);
    }


}
