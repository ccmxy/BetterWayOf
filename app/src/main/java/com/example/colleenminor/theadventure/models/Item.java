package com.example.colleenminor.theadventure.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by colleenminor on 10/28/15.
 */
@Table(name = "items", id = "_id")
public class Item extends Model {
    @Column(name = "item")
    private String mItem;

    @Column(name = "user")
    private User mUser;

    public Item() {
        super();
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String mItem) {
        this.mItem = mItem;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public Item(String item, User user) {
        mItem = item;
        mUser = user;
    }

    public static List<Item> all() { //maybe send user thru to onle get some...
        return new Select()
                .from(Item.class)
                .execute();
//                .where("Name = ?", username)
//        34	                .executeSingle();
//        35
    }

}