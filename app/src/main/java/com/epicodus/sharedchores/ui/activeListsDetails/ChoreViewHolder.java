package com.epicodus.sharedchores.ui.activeListsDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.Chore;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ChoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    //  * Public constructor that initializes private instance variables when adapter is created
    public ChoreViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindChore(final Chore chore) {
        TextView choreTitle = (TextView) mView.findViewById(R.id.choreTitleTextView);
        TextView choreDescription = (TextView) mView.findViewById(R.id.choreDescriptionTextView);
        TextView choreDoer = (TextView) mView.findViewById(R.id.choreDoerTextView);
        TextView choreDueDate = (TextView) mView.findViewById(R.id.choreDueDateTextView);

        choreTitle.setText(chore.getTitle());
        choreDescription.setText(chore.getDoer());
        choreDoer.setText(chore.getDescription());
        choreDueDate.setText(chore.getDueDate());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Chore> chores = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_USER_CHORES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chores.add(snapshot.getValue(Chore.class));
                }
//TODO: COMMENT IT BACK IF THERE IS A INTENT
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, ChoreListDetailsActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("chores", Parcels.wrap(chores));
//                mContext.startActivity(intent);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
