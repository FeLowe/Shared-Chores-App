package com.epicodus.sharedchores.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.sharedchores.R;
import com.epicodus.sharedchores.model.ChoreList;
import com.epicodus.sharedchores.ui.activeLists.AddListDialogFragment;
import com.epicodus.sharedchores.ui.activeLists.ChoreListsFragment;

import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener{
    private DatabaseReference mUserRef;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ValueEventListener mUserRefListener;
    private Toolbar toolbar;
    private Query mChoreListReference;
    private ArrayAdapter mAdapter;

    ArrayList<String> mChoreListArray = new ArrayList<>();

    @Bind(R.id.addListButton) Button mAddListButton;
    @Bind(R.id.listView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAddListButton.setOnClickListener(this);

//        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);

        initializeScreen();


        mAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.single_active_list, mChoreListArray);
        mListView.setAdapter(mAdapter);


        mChoreListReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_URL_USER_LISTS)
                .limitToLast(1);

        mChoreListReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Log.d("DATACHANGED", snapshot + " ");
                    String listName = snapshot.getValue(ChoreList.class).getListName();
                    mChoreListArray.add(listName);
//                    Long timestamp = (Long)(snapshot.getValue());
                    Log.d("TIMESTAMP", listName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**
         * Add ValueEventListeners to Firebase references
         * to control get data and control behavior and visibility of elements
         */

//        //LOOPS THRU USERS IN DATABASE
//        mUserRefListener = mUserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                User user = snapshot.getValue(User.class);
//
//                /**
//                 * Set the activity title to current user name if user is not null
//                 */
//                if (user != null) {
//                    /* Assumes that the first word in the user's name is the user's first name. */
//                    String firstName = user.getName().split("\\s+")[0];
//                    String title = firstName + "'s Lists";
//                    setTitle(title);
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Log.e(LOG_TAG,
//                        getString(R.string.log_error_the_read_failed) +
//                                firebaseError.getMessage());
//            }
//        });
//
//    }

    }
    @Override
    public void onClick(View v) {
        if (v == mAddListButton) {
            showAddListDialog();
        }
        }



        /**
         * Override onOptionsItemSelected to use main_menu instead of BaseActivity menu
         *
         * @param menu
         */
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
        /* Inflate the menu; this adds items to the action bar if it is present. */
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        /**
         * Override onOptionsItemSelected to add action_settings only to the MainActivity
         *
         * @param
         */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        /**
//         * Open SettingsActivity with sort options when Sort icon was clicked
//         */
//        if (id == R.id.action_sort) {
//            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mUserRef.removeEventListener(mUserRefListener);
    }

    /**
     * Link layout elements from XML and setup the toolbar
     */
    public void initializeScreen() {
//        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
////        TabLayout tabLayout = (TabLayoutbLayout) findViewById(R.id.tab_layout);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(toolbar);
        /**
         * Create SectionPagerAdapter, set it as adapter to viewPager with setOffscreenPageLimit(2)
         **/
//        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
//        viewPager.setOffscreenPageLimit(2);
//        viewPager.setAdapter(adapter);
        /**
         * Setup the mTabLayout with view pager
         */
//        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Create an instance of the AddList dialog fragment and show it
     */
    public void showAddListDialog() {
        /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = AddListDialogFragment.newInstance("");
//        DialogFragment dialog = AddListDialogFragment.newInstance(mEncodedEmail);
        dialog.show(MainActivity.this.getFragmentManager(), "AddListDialogFragment");
    }



    /**
     * SectionPagerAdapter class that extends FragmentStatePagerAdapter to save fragments state
     */
//    public class SectionPagerAdapter extends FragmentStatePagerAdapter {
//
//        public SectionPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        /**
//         * Use positions (0 and 1) to find and instantiate fragments with newInstance()
//         *
//         * @param position
//         */
//        @Override
//        public Fragment getItem(int position) {
//
//            Fragment fragment = null;
//
//            /**
//             * Set fragment to different fragments depending on position in ViewPager
//             */
//            switch (position) {
//                case 0:
//                    fragment = ChoreListsFragment.newInstance("");
////                    fragment = ChoreListsFragment.newInstance(mEncodedEmail);
//
//                    break;
////                case 1:
////                    fragment = MealsFragment.newInstance();
////                    break;
//                default:
//                    fragment = ChoreListsFragment.newInstance("");
////                    fragment = ChoreListsFragment.newInstance(mEncodedEmail);
//
//                    break;
//            }
//
//            return fragment;
//        }
//
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        /**
//         * Set string resources as titles for each fragment by it's position
//         *
//         * @param position
//         */
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                default:
//                case 0:
//                    return ("Chore Lists");
//
//
//            }
//        }
//    }
}
