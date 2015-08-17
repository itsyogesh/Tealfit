package com.droidsize.realmtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class EditActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mCity;
    private String personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        personId = getIntent().getStringExtra("person");
        mName = (EditText) findViewById(R.id.mName);
        mCity = (EditText) findViewById(R.id.mCity);

        Realm realm = Realm.getInstance(this);
        Person toEdit = realm.where(Person.class)
                .equalTo("id", personId)
                .findFirst();

        mName.setText(toEdit.getName());
        mCity.setText(toEdit.getCity());
    }

    public void update(View view){
        Realm realm = Realm.getInstance(getApplicationContext());
        Person toEdit = realm.where(Person.class)
                .equalTo("id", personId)
                .findFirst();
        realm.beginTransaction();
        toEdit.setName(mName.getText().toString());
        toEdit.setCity(mCity.getText().toString());
        realm.commitTransaction();
        Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        Realm realm = Realm.getInstance(getApplicationContext());
        Person toEdit = realm.where(Person.class)
                .equalTo("id", personId)
                .findFirst();

        realm.beginTransaction();
        toEdit.removeFromRealm();
        realm.commitTransaction();
        finish();
    }




}
