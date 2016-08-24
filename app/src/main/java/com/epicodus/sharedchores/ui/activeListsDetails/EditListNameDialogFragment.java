package com.epicodus.sharedchores.ui.activeListsDetails;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;

public class EditListNameDialogFragment extends EditListDialogFragment {

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static EditListNameDialogFragment newInstance(ChoreList choreList) {
        EditListNameDialogFragment editListNameDialogFragment = new EditListNameDialogFragment();
        Bundle bundle = EditListDialogFragment.newInstanceHelper(choreList, R.layout.dialog_base_edit_list);
        // TODO add any values you need here from the shopping list to make this change.
        // Once you put a value in the bundle, it available to you in onCreate
        editListNameDialogFragment.setArguments(bundle);
        return editListNameDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Extract any arguments you put in the bundle when the newInstance method
        // created the dialog. You can store these in an instance variable so that they
        // are available to you.
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /** {@link EditListDialogFragment#createDialogHelper(int)} is a
         * superclass method that creates the dialog
         **/
        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);
        // TODO You can use the helper method in the superclass I made (EditListDialogFragment)
        // called helpSetDefaultValueEditText. This will allow you to set what text the
        // user sees when the dialog opens.

        return dialog;
    }

    /**
     * Changes the list name in all copies of the current list
     */
    protected void editList() {
        // TODO Do the actual edit operation here.
        // Remember, you need to update the timestampLastChanged for
        // the shopping list.

    }

}
