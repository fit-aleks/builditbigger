package com.fitaleks.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fitaleks.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by alexanderkulikovskiy on 15.12.15.
 */
public class GetJokeAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi mApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
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
        this.context = params[0];

        try {
            return mApiService.sayJoke().execute().getData();
        } catch (IOException ex) {
            return ex.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
