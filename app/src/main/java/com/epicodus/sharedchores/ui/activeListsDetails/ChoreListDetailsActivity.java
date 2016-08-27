package com.epicodus.sharedchores.ui.activeListsDetails;


import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.BaseActivity;

import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChoreListDetailsActivity extends BaseActivity {
    private DatabaseReference mDatabaseReference;
    private ListView mListView;
    private ChoreList mChoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list_detail);

        /*Firebase reference*/
        mDatabaseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_URL_ACTIVE_LIST);

        showAddChoreDialog();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ChoreList choreList = snapshot.getValue(ChoreList.class);

                if (choreList == null) {
                     /*Make sure to call return, otherwise the rest of the method will execute, even after calling finish*/

                    return;
                }
                mChoreList = choreList;

                /* Calling invalidateOptionsMenu causes onCreateOptionsMenu to be called */
                invalidateOptionsMenu();

                /* Set title appropriately. */
                setTitle(choreList.getListName());
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* Check that the view is not the empty footer item */
                if (view.getId() != R.id.list_view_footer_empty) {
                    showEditChoreDialog();
                }
            }
        });
    }

//    @Override
//    public void onClick(View v) {
////        if (v == mAddListButton) {
////            showAddListDialog();
////        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_list_details, menu);

        /**
         * Get menu items
         */
        MenuItem remove = menu.findItem(R.id.action_remove_list);
        MenuItem edit = menu.findItem(R.id.action_edit_list_name);
        MenuItem share = menu.findItem(R.id.action_share_list);
        MenuItem archive = menu.findItem(R.id.action_archive);

        /* Only the edit and remove options are implemented */
        remove.setVisible(true);
        edit.setVisible(true);
        share.setVisible(false);
        archive.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /**
         * Show edit list dialog when the edit action is selected
         */
        if (id == R.id.action_edit_list_name) {
            showEditChoreListNameDialog();
            return true;
        }

//        //TODO: Try to implement this functions later(remove)
//        /**
//         * removeList() when the remove action is selected
//         */
//        if (id == R.id.action_remove_list) {
//            removeList();
//            return true;
//        }

        /*** Eventually we'll add this actions - FERNANDA*/
        if (id == R.id.action_share_list) {
            return true;
        }


        if (id == R.id.action_archive) {
            archiveList();/*** archiveList() when the archive action is selected*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {/*** Cleanup when the activity is destroyed.*/
        super.onDestroy();
    }

    private void initializeScreen() { /* Link layout elements from XML and setup the toolbar*/
        mListView = (ListView) findViewById(R.id.listNameTextView);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
//        toolbar.setTitle("TOOLBAR-CLDETAILS");
//        setSupportActionBar(toolbar);
//
//        if (getSupportActionBar() != null) { /* Add back button to the action bar */
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        View footer = getLayoutInflater().inflate(R.layout.footer_empty, null); /* Inflate the footer, set root layout to null*/
        mListView.addFooterView(footer);
    }


    /* Archive current list when user selects "Archive" menu item*/
    public void archiveList() {}

    //TODO: Try to implement this functions later(remove)
//     Remove current shopping list and its items from all nodes
//    public void removeList() {
//        /* Create an instance of the dialog fragment and show it */
//        DialogFragment dialog = RemoveListDialogFragment.newInstance(mShoppingList);
//        dialog.show(getFragmentManager(), "RemoveListDialogFragment");
//    }

    /* Show the edit dialogs after fab click*/
    public void showAddChoreDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = AddChoreDialogFragment.newInstance(mChoreList);
        dialog.show(fm, "AddChoreDialogFragment");
    }

    public void showEditChoreListNameDialog() {
//        FragmentManager fm = getSupportFragmentManager();
//        DialogFragment dialog = EditChoreListNameDialogFragment.newInstance();
//        dialog.show(fm, "EditChoreListNameDialog");
    }

    public void showEditChoreDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = EditChoreDialogFragment.newInstance();
        dialog.show(fm, "EditListItemNameDialogFragment");
    }

    /**
     * This method is called when user taps "Start/Stop shopping" button
     */
    public void toggleShopping(View view) {

    }
}

//mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//@Override
//public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//        getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
//        //part I - display welcome message
//        } else {
//        }
//        }
//        };