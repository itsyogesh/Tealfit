package com.droidsize.realmtest;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by yogeshkumar on 17/8/15.
 */
public class Person extends RealmObject {

    private String name;
    @Index private String city;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
