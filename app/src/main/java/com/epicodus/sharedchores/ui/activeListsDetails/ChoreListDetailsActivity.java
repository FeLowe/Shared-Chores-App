package com.epicodus.sharedchores.ui.activeListsDetails;


import android.os.Bundle;

import android.os.RecoverySystem;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.Chore;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.BaseActivity;

import com.epicodus.sharedchores.ui.activeLists.AddChoreListDialogFragment;
import com.epicodus.sharedchores.ui.activeLists.ListViewHolder;
import com.epicodus.sharedchores.utils.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoreListDetailsActivity extends BaseActivity implements View.OnClickListener{
    private DatabaseReference mChoresReference;
    private ListView mListView;
    private Chore mChore;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.addChorebutton)
    FloatingActionButton mAddChoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list_detail);
        ButterKnife.bind(this);

        mAddChoreButton.setOnClickListener(this);

        /*Firebase reference*/
        mChoresReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_USER_CHORES);

        displayData();
    }

    @Override
    public void onClick(View v) {
        if (v == mAddChoreButton) {
            showAddChoreDialog();
        }
    }
    public void showAddChoreDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialog = AddChoreDialogFragment.newInstance(mChore);
        dialog.show(fm, "AddChoreDialogFragment");

    }

    private void displayData(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Chore, ChoreViewHolder>
                (Chore.class, R.layout.single_chore_item, ChoreViewHolder.class, mChoresReference) {

            @Override
            protected void populateViewHolder(ChoreViewHolder viewHolder,
                                              Chore model, int position) {
                viewHolder.bindChore(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
//TODO: FIX IT LATER
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
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

    private void initializeScreen() { /* Link layout elements from XML and setup the toolbar*/
//        mListView = (ListView) findViewById(R.id.choreTitleTextView);
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