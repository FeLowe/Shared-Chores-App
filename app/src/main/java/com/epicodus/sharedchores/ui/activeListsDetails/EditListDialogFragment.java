package com.epicodus.sharedchores.ui.activeListsDetails;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;

public abstract class EditListDialogFragment extends DialogFragment {
    EditText mEditTextForList;
    int mResource;

    protected static Bundle newInstanceHelper(ChoreList choreList, int resource) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_LAYOUT_RESOURCE, resource);
        return bundle;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResource = getArguments().getInt(Constants.KEY_LAYOUT_RESOURCE);
    }

    /**
     * Open the keyboard automatically when the dialog fragment is opened
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    protected Dialog createDialogHelper(int stringResourceForPositiveButton) {
        /* Use the Builder class for convenient dialog construction */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("Edit Chore list name");
        /* Get the layout inflater */
        LayoutInflater inflater = getActivity().getLayoutInflater();
        /* Inflate the layout, set root ViewGroup to null*/
        View rootView = inflater.inflate(mResource, null);
        mEditTextForList = (EditText) rootView.findViewById(R.id.edit_text_list_dialog);

        /**
         * Call doListEdit() when user taps "Done" keyboard action
         */
        mEditTextForList.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    editList();

                    /**
                     * Close the dialog fragment when done
                     */
                    EditListDialogFragment.this.getDialog().cancel();
                }
                return true;
            }
        });
        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout */
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(stringResourceForPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        editList();

                        /**
                         * Close the dialog fragment
                         */
                        EditListDialogFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        /**
                         * Close the dialog fragment
                         */
                        EditListDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    /**
     * Set the EditText text to be the inputted text
     * and put the pointer at the end of the input
     *
     * @param defaultText
     */
    protected void helpSetDefaultValueEditText(String defaultText) {
        mEditTextForList.setText(defaultText);
        mEditTextForList.setSelection(defaultText.length());
    }

    /**
     * Method to be overriden with whatever edit is supposed to happen to the list
     */
    protected abstract void editList();
}


