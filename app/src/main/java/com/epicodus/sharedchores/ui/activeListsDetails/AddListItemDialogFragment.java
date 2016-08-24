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
import com.epicodus.sharedchores.model.Chore;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class AddListItemDialogFragment extends DialogFragment {
    EditText mItemNameEditText;
    private DatabaseReference mDatabaseReference;


//  constructor that creates fragment and passes a bundle with data into it when adapter is created

    public static AddListItemDialogFragment newInstance(ChoreList choreList) {
        AddListItemDialogFragment addListItemDialogFragment = new AddListItemDialogFragment();
        Bundle bundle = new Bundle();
//        Bundle bundle = newInstanceHelper(choreList, R.layout.dialog_add_list_item);
        addListItemDialogFragment.setArguments(bundle);
        return addListItemDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("add a chore to this list");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list_item, null);
        mItemNameEditText = (EditText) rootView.findViewById(R.id.itemNameEditText);

        /**
         * Call addChoreList() when user taps "Done" keyboard action
         */
        mItemNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addChore();
                }
                return true;
            }

        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout*/
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addChore();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        });


        return builder.create();
    }

    public void addChore() {
        String choreTitle = mItemNameEditText.getText().toString();
//        If EditText input is not empty
        if (!choreTitle.equals("")) {

            DatabaseReference userChoresRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_URL_USER_CHORES)
                    .push();

            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            Chore newChore = new Chore(" ", " ", " ", 1829209388);
            String choreId = userChoresRef.getKey();
            userChoresRef.setValue(newChore);

        }
            /* Close the dialog fragment */
        AddListItemDialogFragment.this.getDialog().cancel();
    }


}
