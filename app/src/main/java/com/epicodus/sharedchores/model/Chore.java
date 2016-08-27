package com.epicodus.sharedchores.model;

import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.HashMap;

@Parcel
@IgnoreExtraProperties
public class Chore {
    private String title;
    private String doer;
    private String description;
    private String dueDate;
    private String doneBy;
    private boolean done;
    private HashMap<String, Object> timestampCreated;

    Chore() {
    }

    public Chore (String title, String doer, String description, String dueDate, HashMap<String, Object> timestampCreated){
        this.title = title;
        this.doer = doer;
        this.description = description;
        this.dueDate = dueDate;
        this.timestampCreated = timestampCreated;
        this.doneBy = null;
        this.done = false;


    }

    public String getTitle() {
        return title;
    }

    public String getDoer() {
        return doer;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public boolean isChoreDone() {
        return done;
    }

}
