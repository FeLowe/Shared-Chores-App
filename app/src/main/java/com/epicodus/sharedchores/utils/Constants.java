package com.epicodus.sharedchores.utils;

import com.epicodus.sharedchores.BuildConfig;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants for Firebase URL
     */

    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_USER_CHORES = "userChores";
     public static final String FIREBASE_USERS = "users";
     public static final String FIREBASE_USER_CHORE_LIST = "userChoreList";
    public static final String FIREBASE_CHORE_LIST = "Lists";
     public static final String FIREBASE_USER_FRIENDS = "userFriends";
     public static final String FIREBASE_LISTS_SHARED_WITH = "sharedWith";
     public static final String FIREBASE_UID_MAPPINGS = "uidMappings";
     public static final String FIREBASE_LOCATION_OWNER_MAPPINGS = "ownerMappings";



    /**
     * Constants for Firebase object properties
     */
     public static final String FIREBASE_PROPERTY_DONE = "done";
     public static final String FIREBASE_PROPERTY_DONE_BY = "doneBy";
     public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
     public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
     public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
     public static final String FIREBASE_PROPERTY_CHORE_TITLE = "choreTitle";
     public static final String FIREBASE_PROPERTY_EMAIL = "email";
     public static final String FIREBASE_PROPERTY_USER_CHORES = "usersChores";
     public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";
     public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED_REVERSE = "timestampLastChangedReverse";


    /**
     * Constants for Firebase URL
     */
//    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
////    public static final String FIREBASE_URL_USER_CHORES = FIREBASE_LOCATION_CHORES;
//    public static final String FIREBASE_URL_USERS = FIREBASE_LOCATION_USERS;
//    public static final String FIREBASE_URL_USER_CHORE_LISTS = FIREBASE_LOCATION_USER_CHORE_LIST;
//    public static final String FIREBASE_URL_USER_FRIENDS = FIREBASE_URL + FIREBASE_LOCATION_USER_FRIENDS;
//    public static final String FIREBASE_URL_LISTS_SHARED_WITH = FIREBASE_URL + FIREBASE_LOCATION_LISTS_SHARED_WITH;
//    public static final String FIREBASE_URL_ACTIVE_LIST = FIREBASE_LOCATION_ACTIVE_LIST;


    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_LIST_OWNER = "LIST_OWNER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
    public static final String KEY_PREF_SORT_ORDER_LISTS = "PERF_SORT_ORDER_LISTS";
    public static final String KEY_SHARED_WITH_USERS = "SHARED_WITH_USERS";


    /**
     * Constants for Firebase login
     */
    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";


    /**
     * Constant for sorting
     */
    public static final String ORDER_BY_KEY = "orderByPushKey";
    public static final String ORDER_BY_OWNER_EMAIL = "orderByOwnerEmail";



}
