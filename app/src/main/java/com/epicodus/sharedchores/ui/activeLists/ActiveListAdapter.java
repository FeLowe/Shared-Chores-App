package com.epicodus.sharedchores.ui.activeLists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;


//Populates the list_view_active_lists inside ChoreListsFragment

 public class ActiveListAdapter extends RecyclerView.ViewHolder {
     View mView;
     Context mContext;
 //
 //    /**
 //     * Public constructor that initializes private instance variables when adapter is created
 //     */
 public ActiveListAdapter(View itemView) {
     super(itemView);
     mView = itemView;
     mContext = itemView.getContext();

   }

   public void bindList(final ChoreList list) {
//
//        /**
//         * Grab the needed Textivews and strings
//         */
        TextView textViewListName = (TextView) mView.findViewById(R.id.text_view_list_name);
        final TextView textViewCreatedByUser = (TextView) mView.findViewById(R.id.text_view_created_by_user);
        final TextView textViewUsersChore = (TextView) mView.findViewById(R.id.text_view_people_chore_count);
//
      String ownerEmail = list.getOwner();
//
//        /* Set the list name and owner */
       textViewListName.setText(list.getListName());


    mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(mContext, ChoreListsFragment.class);
//            intent.putExtra("user", Parcels.wrap(user));
            mContext.startActivity(intent);
        }
    });
//        /**
//         * Set "Created by" text to "You" if current user is owner of the list
//         * Set "Created by" text to userName if current user is NOT owner of the list
//         */
//
//
   }
 }

//
//public class ActiveListAdapter extends FirebaseListAdapter<ChoreList> {
//    private Query mQuery;
//    private String mEncodedEmail;
//
//    /**
//     * Public constructor that initializes private instance variables when adapter is created
//     */
//    public ActiveListAdapter(Activity activity, Class<ChoreList> modelClass, int modelLayout,
//                             Query ref, String encodedEmail) {
//        super(activity, modelClass, modelLayout, ref);
//        this.mEncodedEmail = encodedEmail;
//        this.mActivity = activity;
//    }
//
//    /**
//     * Protected method that populates the view attached to the adapter (list_view_active_lists)
//     * with items inflated from single_active_list.xml
//     * populateView also handles data changes and updates the listView accordingly
//     */
//    @Override
//    protected void populateView(View view, ChoreList list) {
//
//        /**
//         * Grab the needed Textivews and strings
//         */
//        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
//        final TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);
//        final TextView textViewUsersChore = (TextView) view.findViewById(R.id.text_view_people_chore_count);
//
//        String ownerEmail = list.getOwner();
//
//        /* Set the list name and owner */
//        textViewListName.setText(list.getListName());
//        /**
//         * Set "Created by" text to "You" if current user is owner of the list
//         * Set "Created by" text to userName if current user is NOT owner of the list
//         */
//
//
//    }
//}
