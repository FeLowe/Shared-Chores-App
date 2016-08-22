package com.epicodus.sharedchores.ui.activeLists;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.MainActivity;
import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass that shows a list of all chore lists a user can see.
 * Use the {@link ChoreListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoreListsFragment extends Fragment {
    private String mEncodedEmail;
    private ActiveListAdapter mActiveListAdapter;
    private ListView mListView;
    private DatabaseReference mUserReference;
    private DatabaseReference mDatabaseReference;
    private ArrayAdapter mArrayAdapter;
    private Query mUserListReference;
    ArrayList<String> mChoreListArray = new ArrayList<>();

    public ChoreListsFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ChoreListsFragment newInstance(String encodedEmail) {
        ChoreListsFragment fragment = new ChoreListsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
        }

        mArrayAdapter = new ArrayAdapter<String> (getContext(), R.layout.single_active_list, mChoreListArray);
        mListView.setAdapter(mArrayAdapter);
//
// Query orderedActiveUserListsRef;

        mUserListReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_URL_USER_LISTS)
//                .child(mEncodedEmail)
                .limitToLast(1);
//        /**
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
//
        mUserListReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String listName = snapshot.getValue(ChoreList.class).getListName();
                    Log.d("list name is:", listName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * Initialize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_chore_lists, container, false);
        initializeScreen(rootView);

        /**
         * Set interactive bits, such as click events and adapters
         */

        // CLICK ON ITEM TO TAKE IT TO ITS DETAILS
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ChoreList selectedList = mActiveListAdapter.getItem(position);
//                if (selectedList != null) {
//                    Intent intent = new Intent(getActivity(), ActiveListDetailsActivity.class);
//                    /* Get the list ID using the adapter's get ref method to get the Firebase
//                     * ref and then grab the key.
//                     */
//                    String listId = mActiveListAdapter.getRef(position).getKey();
//                    intent.putExtra(Constants.KEY_LIST_ID, listId);
//                    /* Starts an active showing the details for the selected list */
//                    startActivity(intent);
//                }
//            }
//        });


        return rootView;
    }
    /**
     * Updates the order of mListView onResume to handle sortOrderChanges properly
     */
    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = sharedPref.getString(Constants.KEY_PREF_SORT_ORDER_LISTS, Constants.ORDER_BY_KEY);

//
//
//
// * Sort active lists by "date created"
//         * if it's been selected in the SettingsActivity
//         */
//        if (sortOrder.equals(Constants.ORDER_BY_KEY)) {
//            orderedActiveUserListsRef = activeListsRef.orderByKey();
//        } else {
//
//            /**
//             * Sort active by lists by name or datelastChanged. Otherwise
//             * depending on what's been selected in SettingsActivity
//             */
//
//            orderedActiveUserListsRef = activeListsRef.orderByChild(sortOrder);


        /**
         * Create the adapter with selected sort order
         *
         *
         */


//            mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
////
////            @Override
////            protected void populateView(ActiveListAdapter view, ChoreList model, int position){
////                view.bindList(model);
////
////
////            }
//        };
//        /**
//         * Set the adapter to the mListView


    }

    /**
     * Cleanup the adapter when activity is paused.
     */
    @Override
    public void onPause() {
        super.onPause();
//        mArrayAdapter.cleanup();
    }

    /**
     * Link list view from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
    }
}
