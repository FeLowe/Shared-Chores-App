package com.epicodus.sharedchores.model;

import com.epicodus.sharedchores.utils.Constants;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import org.parceler.Parcel;

import java.util.HashMap;

/**
 * Defines the data structure for both Active and Archived ChoreList objects.
 */
@Parcel
@IgnoreExtraProperties
public class ChoreList {
    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private HashMap<String, Object> timestampLastChangedReverse;

    /**
     * Required public constructor
     */
    public ChoreList() {
    }

    /**
     * Use this constructor to create new ChoreLists.
     * Takes Chore list listName and owner. Set's the last
     * changed time to what is stored in ServerValue.TIMESTAMP
     *
     * @param listName
     * @param owner
     */
    public ChoreList(String listName, String owner, HashMap<String, Object> timestampCreated) {
        this.listName = listName;
        this.owner = owner;
        this.timestampCreated = timestampCreated;
        //TODO: LATER
//        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
//        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
//        this.timestampLastChanged = timestampNowObject;
//        this.timestampLastChangedReverse = null;
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

//    public HashMap<String, Object> getTimestampLastChanged() {
//        return timestampLastChanged;
//    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

//    public HashMap<String, Object> getTimestampLastChangedReverse() {
//        return timestampLastChangedReverse;
//    }


//    public long getTimestampLastChangedLong() {
//
//        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
//    }


//    public long getTimestampCreatedLong() {
//        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
//    }


//    public long getTimestampLastChangedReverseLong() {
//
//        return (long) timestampLastChangedReverse.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
//    }

//    public void setTimestampLastChangedToNow() {
//        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
//        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
//        this.timestampLastChanged = timestampNowObject;
//    }


}
