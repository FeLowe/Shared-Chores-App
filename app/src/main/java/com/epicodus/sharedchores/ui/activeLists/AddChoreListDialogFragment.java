package com.epicodus.sharedchores.ui.activeLists;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;


import java.util.HashMap;

/**
 * Adds a new chore list
 */
public class AddChoreListDialogFragment extends DialogFragment {
    String mEncodedEmail;
    EditText mListNameEditText;
    private DatabaseReference mDatabaseReference;

    /**
     * Public static constructor that creates fragment and
     * passes a bundle with data into it when adapter is created
     */
    public static AddChoreListDialogFragment newInstance() {
        AddChoreListDialogFragment addListDialogFragment = new AddChoreListDialogFragment();
        Bundle bundle = new Bundle();
//        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        addListDialogFragment.setArguments(bundle);
        return addListDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
    }

    /**
     * Open the keyboard automatically when the dialog fragment is opened
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("create a chore list");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list, null);
        mListNameEditText = (EditText) rootView.findViewById(R.id.listNameEditText);

        /**
         * Call addChoreList() when user taps "Done" keyboard action
         */
        mListNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addChoreList();
                }
                return true;
            }
        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout*/
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton("create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addChoreList();
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

    public void addChoreList() {
        String listName = mListNameEditText.getText().toString().trim();
        String listOwner = " Anonymous Owner";
//        If EditText input is not empty
        if (!listName.equals("")) {

            DatabaseReference userListsRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_USER_CHORE_LIST).push();

            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            /* Build the chore list */
            ChoreList newChoreList = new ChoreList(listName, "Anonymous Owner", timestampCreated);
            String listId = userListsRef.getKey();
            userListsRef.setValue(newChoreList);

            /* HashMap for data to update */
            HashMap<String, Object> updateChoreListData = new HashMap<>();

//            ObjectMapper objectMapper =  new ObjectMapper();
//
//            HashMap<String, Object> choreListMap = (HashMap<String, Object>)
//
//            HashMap<String, Object> map =   objectMapper.convertValue(newChoreList, Map.class);

            updateChoreListData.put("/" + Constants.FIREBASE_LOCATION_OWNER_MAPPINGS + "/" + listId,
                    mEncodedEmail);
                }

            /* Close the dialog fragment */
            AddChoreListDialogFragment.this.getDialog().cancel();
        }
    }

