package com.epicodus.sharedchores.ui.activeLists;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoreListsActivity extends Activity implements View.OnClickListener{
    private DatabaseReference mUserRef;
    private ValueEventListener mUserRefListener;
    private Toolbar toolbar;
    private Query mChoreListReference;
//    private ArrayAdapter mAdapter;
    private FirebaseViewHolder mAdapter;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    ArrayList<ChoreList> mChoreListArray = new ArrayList<>();

    @Bind(R.id.addListButton) Button mAddListButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_lists);

        ButterKnife.bind(this);

        mAddListButton.setOnClickListener(this);


        mChoreListReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_URL_USER_LISTS)
                .limitToLast(1);
        displayData();
    }

    @Override
    public void onClick(View v) {
        if (v == mAddListButton) {
            showAddListDialog();
        }
    }

    public void showAddListDialog() {
       /* Create an instance of the dialog fragment and show it */
     DialogFragment dialog = AddListDialogFragment.newInstance("");
//       DialogFragment dialog = AddListDialogFragment.newInstance(mEncodedEmail);
     dialog.show(ChoreListsActivity.this.getFragmentManager(), "AddListDialogFragment");
}

private void displayData(){

    mFirebaseAdapter = new FirebaseRecyclerAdapter<ChoreList, FirebaseViewHolder>
            (ChoreList.class, R.layout.single_active_list, FirebaseViewHolder.class,
                    mChoreListReference) {

        @Override
        protected void populateViewHolder(FirebaseViewHolder viewHolder,
                                          ChoreList model, int position) {
            viewHolder.bindChoreList(model);
        }
    };
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(mFirebaseAdapter);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

