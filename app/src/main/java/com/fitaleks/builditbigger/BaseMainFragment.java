package com.fitaleks.builditbigger;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

/**
 * Created by alexanderkulikovskiy on 04.01.16.
 */
public abstract class BaseMainFragment extends Fragment {
    protected ProgressBar progressBar;

    abstract protected void tellJoke();

    protected void showErrorDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.error_no_network_title)
                .setMessage(R.string.error_no_network_description)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
