package com.epicodus.sharedchores.ui.activeLists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.activeListsDetails.ChoreListDetailsActivity;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    //  * Public constructor that initializes private instance variables when adapter is created
    public ListViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindList(final ChoreList choreList) {
        TextView listNameTextView = (TextView) mView.findViewById(R.id.listNameTextView);
        TextView createdByTextView = (TextView) mView.findViewById(R.id.createdByTextView);
        TextView createdByUserTextView = (TextView) mView.findViewById(R.id.createdByUserTextView);

        listNameTextView.setText(choreList.getListName());
        createdByTextView.setText(choreList.getOwner());
        createdByUserTextView.setText("createdByUserFB-PLACEHOLDER");

    }

    @Override
    public void onClick(View view) {
        final ArrayList<ChoreList> choreLists = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_USER_CHORE_LIST);
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("DATA HAS CHAGED", snapshot + "");
                    choreLists.add(snapshot.getValue(ChoreList.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ChoreListDetailsActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("choreLists", Parcels.wrap(choreLists));
                mContext.startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
