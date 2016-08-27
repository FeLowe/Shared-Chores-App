package com.epicodus.sharedchores.ui.activeListsDetails;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.utils.Constants;

public class EditChoreDialogFragment extends DialogFragment {
    //from EditListDialogFragment
    //This fragment is used as base for doing ALL THE EDITING
    String mChoreTitle;
    EditText mEditChoreEditText;
    EditText mEditTextForList;
    int mResource;


    public static EditChoreDialogFragment newInstance() {
        EditChoreDialogFragment editChoreDialogFragment = new EditChoreDialogFragment();
        Bundle bundle = new Bundle();
        editChoreDialogFragment.setArguments(bundle);
        return editChoreDialogFragment;
    }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mChoreTitle = getArguments().getString(Constants.FIREBASE_PROPERTY_CHORE_TITLE);
        }


    /* Open the keyboard automatically when the dialog fragment is opened*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("edit chore title");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_base_edit_list, null);
        mEditChoreEditText = (EditText) rootView.findViewById(R.id.edit_text_list_dialog);

        mEditChoreEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    editChoreTitle();
                }
                return true;
            }
        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout*/
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        editChoreTitle();

                        dialog.dismiss();   /* Close the dialog fragment */
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        });

        return builder.create();
    }

    /*Set the EditText text to be the inputted text and put the pointer at the end of the input*/
    protected void helpSetDefaultValueEditText(String defaultText) {
        mEditChoreEditText.setText(defaultText);
        mEditChoreEditText.setSelection(defaultText.length());
    }

    /*Method to be overriden with whatever edit is supposed to happen to the list*/
    public void editChoreTitle() {
    }

}



