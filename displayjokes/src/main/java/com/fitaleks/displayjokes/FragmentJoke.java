package com.fitaleks.displayjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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

        displayJoke(rootView, joke);

        return rootView;
    }

    private void displayJoke(final View rootView, final String joke) {
        if (joke.startsWith("http")) {
            final ImageView jokeImg = (ImageView) rootView.findViewById(R.id.joke_img);
            final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.img_loading_progress);
            jokeImg.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(joke)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(jokeImg);
        } else {
            final TextView jokeText = (TextView)rootView.findViewById(R.id.joke_text);
            jokeText.setVisibility(View.VISIBLE);
            jokeText.setText(joke);
        }
    }
}
