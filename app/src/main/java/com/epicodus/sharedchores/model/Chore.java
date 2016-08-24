package com.epicodus.sharedchores.model;

import org.parceler.Parcel;

@Parcel
public class Chore {
    private String choreTitle;
    private String choreDoer;
    private String choreDescription;
    private long choreDueDate;
    private String choreDoneBy;
    private boolean choreDone;

    Chore() {
    }

    public Chore (String choreTitle, String choreDoer, String choreDescription, long choreDueDate){
        this.choreTitle = choreTitle;
        this.choreDoer = choreDoer;
        this.choreDescription = choreDescription;
        this.choreDueDate = choreDueDate;
        this.choreDoneBy = null;
        this.choreDone = false;


    }

    public String getChoreTitle() {
        return choreTitle;
    }

    public String getChoreDoer() {
        return choreDoer;
    }

    public String getChoreDescription() {
        return choreDescription;
    }

    public long getChoreDueDate() {
        return choreDueDate;
    }

    public String getChoreDoneBy() {
        return choreDoneBy;
    }

    public boolean isChoreDone() {
        return choreDone;
    }

}
