package com.epicodus.sharedchores.ui.activeListsDetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.Chore;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

//public class AddChoreDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//    EditText mChoreTitle;
//    EditText mChoreDescription;
//    EditText mChoreDoer;
//    EditText mChoreDueDate;
//
//
//    private DatabaseReference mDatabaseReference;
//
//
////  constructor that creates fragment and passes a bundle with data into it when adapter is created
//
//    public static AddChoreDialogFragment newInstance(Chore chore) {
//        AddChoreDialogFragment addChoreDialog = new AddChoreDialogFragment();
//        Bundle bundle = new Bundle();
//        addChoreDialog.setArguments(bundle);
//        return addChoreDialog;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        getDialog().getWindow().setSoftInputMode(WindowManager
//                .LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("add a chore");
//        // Get the layout inflater
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View rootView = inflater.inflate(R.layout.dialog_add_chore, null);
//
//        // Use the current date as the default date in the picker
//        final Calendar calender = Calendar.getInstance();
//        int year = calender.get(Calendar.YEAR);
//        int month = calender.get(Calendar.MONTH);
//        int day = calender.get(Calendar.DAY_OF_MONTH);
//
//        // Create a new instance of DatePickerDialog and return it
//        return new DatePickerDialog(getActivity(), this, year, month, day);
//
//
//        mChoreTitle = (EditText) rootView.findViewById(R.id.choreTitleTextView);
//        mChoreDescription = (EditText) rootView.findViewById(R.id.choreDescriptionTextView);
//        mChoreDoer = (EditText) rootView.findViewById(R.id.choreDoerTextView);
//        mChoreDueDate = (EditText) rootView.findViewById(R.id.choreDueDateEditText);
//
//        /**
//         * Call addChoreList() when user taps "Done" keyboard action
//         */
//        mChoreDueDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                    addChore();
//                }
//                return true;
//            }
//
//        });
//
//        /* Inflate and set the layout for the dialog */
//        /* Pass null as the parent view because its going in the dialog layout*/
//        builder.setView(rootView)
//                /* Add action buttons */
//                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        addChore();
//                    }
//                })
//
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                dialog.dismiss();
//                            }
//                        });
//
//
//        return builder.create();
//    }
//
//
//    public void onDateSet(DatePicker view, int year, int month, int day) {
//
//        populateSetDate(year, month + 1, day);
//    }
//        public void populateSetDate(int year, int month, int day) {
//            mChoreDueDate.setText(month+"/"+day+"/"+year);
//        }
//
//        // Do something with the date chosen by the user
//
//    public void addChore() {
//        String choreTitle = mChoreTitle.getText().toString();
//        String choreDescription = mChoreDescription.getText().toString();
//        String choreDoer = mChoreDoer.getText().toString();
//        String choreDueDate = mChoreDueDate.getText().toString();
//
//
//            DatabaseReference userChoresRef = FirebaseDatabase
//                    .getInstance()
//                    .getReference(Constants.FIREBASE_USER_CHORES)
//                    .push();
//
//            HashMap<String, Object> timestampCreated = new HashMap<>();
//            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
//
//            Chore newChore = new Chore(choreTitle, choreDescription, choreDoer, choreDueDate, timestampCreated);
//            String choreId = userChoresRef.getKey();
//            userChoresRef.setValue(newChore);
//
//        }
//
//}

public class AddChoreDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText mChoreTitle;
    EditText mChoreDescription;
    EditText mChoreDoer;
    EditText mChoreDueDate;

    Calendar calendar = Calendar.getInstance();
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int year = calendar.get(Calendar.YEAR);

    public static AddChoreDialogFragment newInstance(Chore chore) {
        AddChoreDialogFragment addChoreDialog = new AddChoreDialogFragment();
        Bundle bundle = new Bundle();
        addChoreDialog.setArguments(bundle);
        return addChoreDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View rootView = inflater.inflate(R.layout.dialog_add_chore, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle("add a chore");
            builder.setView(rootView);

        mChoreTitle = (EditText) rootView.findViewById(R.id.choreTitleTextView);
        mChoreDescription = (EditText) rootView.findViewById(R.id.choreDescriptionTextView);
        mChoreDoer = (EditText) rootView.findViewById(R.id.choreDoerTextView);
        mChoreDueDate = (EditText) rootView.findViewById(R.id.choreDueDateEditText);


// Show a datepicker when the dateButton is clicked
            mChoreDueDate.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     showDatePicker();
                                                 }
                                             });

    builder.setCancelable(false)

            .setPositiveButton("add",new DialogInterface.OnClickListener() {
        @Override
        public void onClick (DialogInterface dialog,int id){
        String choreTitle = mChoreTitle.getText().toString();
        String choreDescription = mChoreDescription.getText().toString();
        String choreDoer = mChoreDoer.getText().toString();
        String choreDueDate = mChoreDueDate.getText().toString();


            if (choreTitle.isEmpty()) {
                Toast.makeText(getActivity(), " please enter chore title", Toast.LENGTH_LONG).show();
            } else {

               DatabaseReference userChoresRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_USER_CHORES)
                    .push();

            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            Chore newChore = new Chore(choreTitle, choreDescription, choreDoer, choreDueDate, timestampCreated);
            String choreId = userChoresRef.getKey();
            userChoresRef.setValue(newChore);

                dialog.dismiss();

            }
        }
    }

    ).setNegativeButton("cancel",
                              new DialogInterface.OnClickListener() {
        public void onClick (DialogInterface dialog,int id){
            dialog.dismiss();
        }
    }

    );

    // create an alert dialog
    return builder.create();
    }

    public void showDatePicker() {

        // Use the current date as the default date in the picker
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        populateSetDate(year, month + 1, day);
    }


    public void populateSetDate(int year, int month, int day) {
        mChoreDueDate.setText(month+"/"+day+"/"+year);


    }
}




