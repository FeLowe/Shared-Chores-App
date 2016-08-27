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

import butterknife.Bind;
import butterknife.ButterKnife;


//Populates the list_view_active_lists inside ChoreListsFragment

// public class FirebaseViewHolder extends RecyclerView.Adapter<FirebaseViewHolder.ChoreListViewHolder> {
//
//     private ArrayList<ChoreList> mChoreList = new ArrayList<>();
//
//     private Context mContext;
//
// //     * Public constructor that initializes private instance variables when adapter is created
//
//     public FirebaseViewHolder(Context context, ArrayList<ChoreList> choreList) {
//         mContext = context;
//         mChoreList = choreList;
//     }
//
//public FirebaseViewHolder.ChoreListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_active_list, parent, false);
//    ChoreListViewHolder viewHolder = new ChoreListViewHolder(view);
//    return viewHolder;
//}
//
//     @Override
//     public void onBindViewHolder(FirebaseViewHolder.ChoreListViewHolder holder, int position) {
//         holder.bindChoreList(mChoreList.get(position));
//     }
//
//     @Override
//     public int getItemCount() {
//         return mChoreList.size();
//     }
//
//     public class ChoreListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//         @Bind(R.id.listNameTextView) TextView mListNameTextView;
//         @Bind(R.id.createdByTextView) TextView mCreatedByTextView;
//         @Bind(R.id.createdByUserTextView) TextView mCreatedByUserTextView;
//
//         private Context mContext;
//
//         public ChoreListViewHolder(View itemView) {
//             super(itemView);
//             ButterKnife.bind(this, itemView);
//
//             mContext = itemView.getContext();
//             itemView.setOnClickListener(this);
//         }
//
//         public void bindChoreList(ChoreList choreList) {
//             mListNameTextView.setText(choreList.getListName());
//             mCreatedByTextView.setText("placeholder");
//             mCreatedByUserTextView.setText("placeholder");
//
//         }
//
//         @Override
//         public void onClick(View v) {
//             Log.d("click listener", "working!");
//             int itemPosition = getLayoutPosition();
//             Intent intent = new Intent(mContext, ChoreListDetailsActivity.class);
//             intent.putExtra("position", itemPosition + "");
//             intent.putExtra("choreLists", Parcels.wrap(mChoreList));
//             mContext.startActivity(intent);
//         }
//     }
// }


public class FirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    //     * Public constructor that initializes private instance variables when adapter is created

    public FirebaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindChoreList(final ChoreList choreList) {
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
                .getReference(Constants.FIREBASE_URL_ACTIVE_LIST);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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
