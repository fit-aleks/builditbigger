package com.fitaleks.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.fitaleks.displayjokes.ActivityJoke;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressBar progressBar;

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

        return root;
    }

    private void tellJoke(){
        progressBar.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask(new IJokeDownloadListener() {
            @Override
            public void onJokeLoaded(final String joke) {
                progressBar.setVisibility(View.GONE);
                final Intent intent = new Intent(getActivity(), ActivityJoke.class);
                intent.putExtra(ActivityJoke.KEY_JOKE, joke);
                getActivity().startActivity(intent);
            }
        }).execute();
    }
}
