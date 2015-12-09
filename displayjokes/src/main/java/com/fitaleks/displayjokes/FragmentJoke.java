package com.fitaleks.displayjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alexanderkulikovskiy on 09.12.15.
 */
public class FragmentJoke extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        final Intent intent = getActivity().getIntent();
        final String joke = intent.hasExtra(ActivityJoke.KEY_JOKE)
                ? intent.getStringExtra(ActivityJoke.KEY_JOKE)
                : getString(R.string.joke_stub);
        final TextView jokeText = (TextView)rootView.findViewById(R.id.joke_text);
        jokeText.setText(joke);
        return rootView;
    }
}
