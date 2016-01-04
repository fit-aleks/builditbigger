package com.fitaleks.builditbigger;

import android.os.AsyncTask;

import com.fitaleks.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by alexanderkulikovskiy on 15.12.15.
 */
public class GetJokeAsyncTaskTest extends AsyncTask<Void, Void, String> {
    private static MyApi mApiService = null;
    private final IJokeDownloadListener downloadListener;

    public GetJokeAsyncTaskTest(IJokeDownloadListener listener) {
        this.downloadListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (mApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 192.168.187.57 is my Mac at home network address
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.187.57:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            mApiService = builder.build();
        }

        try {
//            JokeBean jokeBean = mApiService.sayJoke().execute();
//            return jokeBean.getData() != null ? jokeBean.getData() : "";
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
