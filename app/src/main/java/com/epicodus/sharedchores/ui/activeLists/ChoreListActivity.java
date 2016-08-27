package com.epicodus.sharedchores.ui.activeLists;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.utils.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ChoreListActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private Query mChoreListReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

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
                .getReference(Constants.FIREBASE_CHORE_LIST)
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
        DialogFragment dialog = AddChoreListDialogFragment.newInstance();
        dialog.show(ChoreListActivity.this.getFragmentManager(), "AddChoreListDialogFragment");

    }

private void displayData(){

    mFirebaseAdapter = new FirebaseRecyclerAdapter<ChoreList, ListViewHolder>
            (ChoreList.class, R.layout.single_active_list, ListViewHolder.class, mChoreListReference) {

        @Override
        protected void populateViewHolder(ListViewHolder viewHolder,
                                          ChoreList model, int position) {
            viewHolder.bindList(model);
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

