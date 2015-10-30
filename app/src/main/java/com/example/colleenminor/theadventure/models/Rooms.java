package com.example.colleenminor.theadventure.models;

/**
 * Created by colleenminor on 10/29/15.
 */
public class Rooms {
    private boolean mMermaidVisited;
    private boolean mOceanVisited;
    private boolean mTwistyVisited;
    private boolean mMoanerVisited;

    public Rooms(){
        mMermaidVisited = false;
        mOceanVisited = false;
        mTwistyVisited = false;
        mMermaidVisited = false;
    }

    public boolean isMermaidVisited() {
        return mMermaidVisited;
    }

    public void setMermaidVisited() {
        this.mMermaidVisited = true;
    }

    public boolean isOceanVisited() {
        return mOceanVisited;
    }

    public void setOceanVisited() {
        this.mOceanVisited = true;
    }

    public boolean isTwistyVisited() {
        return mTwistyVisited;
    }

    public void setTwistyVisited() {
        this.mTwistyVisited = true;
    }

    public boolean isMoanerVisited() {
        return mMoanerVisited;
    }

    public void setMoanerVisited() {
        this.mMoanerVisited = true;
    }
}
