package com.mark.colorpickerdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 *
 */

public class ColorPickerDialogFragment extends DialogFragment{

    // This data is the text to be displayed in the dialog's list.
    final CharSequence[] COLOR_CHOICES = {"Red", "Green", "Blue"};

    // Parallel array with the color values as int value constants.
    final int[] COLOR_VALUES = {Color.RED, Color.GREEN, Color.BLUE};

    // Interface for the listener.
    interface ColorDialogSelectionListener {
        void colorSelected(int color);
    }

    private ColorDialogSelectionListener mListener;

    // New instance - no arguments
    public static ColorPickerDialogFragment newInstance() {
        ColorPickerDialogFragment fragment = new ColorPickerDialogFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ColorDialogSelectionListener) {
            mListener = (ColorDialogSelectionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement ColorDialogSelectionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a background color")
                .setItems(COLOR_CHOICES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.colorSelected(COLOR_VALUES[i]);
                    }
                });
        return builder.create();
    }
}
