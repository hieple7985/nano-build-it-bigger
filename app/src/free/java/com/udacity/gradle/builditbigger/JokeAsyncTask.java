package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.hieple.backend.jokeApi.JokeApi;
import com.hieple.backend.jokeApi.model.JokeBean;
import com.hieple.droid.JokesDisplayActivity;

import java.io.IOException;

/**
 * Created by Hiep Le on 18/03/2016.
 */
class JokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static final String LOG_TAG = "JokeAsyncTask";

    private static JokeApi mJokeApi = null;

    private ProgressBar mProgressBar;
    private Context mContext;
    private String mResult;
    private InterstitialAd mInterstitialAd;

    public JokeAsyncTask(ProgressBar progressBar, Context context) {
        this.mProgressBar = progressBar;
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (mJokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(mContext.getString(R.string.root_url_api));
            mJokeApi = builder.build();
        }

        try {
            return mJokeApi.putJoke(new JokeBean()).execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;

        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(mContext.getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }

                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                startJokeActivity();
            }

            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });
        AdRequest ar = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(mContext.getString(R.string.device_id))
                .build();

        try {
            mInterstitialAd.loadAd(ar);
        } catch(java.lang.NoClassDefFoundError error) {
            error.printStackTrace();
            Log.d(LOG_TAG, "ERROR LOADING AD");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void startJokeActivity() {
        Intent intent = new Intent(mContext, JokesDisplayActivity.class);
        intent.putExtra(JokesDisplayActivity.JOKE_INTENT, mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}