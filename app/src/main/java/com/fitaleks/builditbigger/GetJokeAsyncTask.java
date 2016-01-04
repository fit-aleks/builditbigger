package com.fitaleks.builditbigger;

import android.os.AsyncTask;

import com.fitaleks.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by alexanderkulikovskiy on 15.12.15.
 */
public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi mApiService = null;
    private final IJokeDownloadListener downloadListener;

    public GetJokeAsyncTask(IJokeDownloadListener listener) {
        this.downloadListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (mApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1168.appspot.com/_ah/api/");
            mApiService = builder.build();
        }

        try {
            return mApiService.sayJoke().execute().getData();
        } catch (IOException ex) {
            return ex.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        this.downloadListener.onJokeLoaded(s);
    }
}
