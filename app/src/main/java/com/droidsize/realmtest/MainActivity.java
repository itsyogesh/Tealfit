package com.droidsize.realmtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    ArrayList<Person> persons = new ArrayList<>();
    private ListView listView;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.mListView);
        listAdapter = new MyListAdapter(persons, this);
        loadData();
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person p = persons.get(position);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("person", p.getId());
                startActivity(intent);
            }
        });
    }

    public void loadData(){
        Realm realm = Realm.getInstance(this);
        RealmResults<Person> query = realm.where(Person.class).findAll();
        for (Person p: query){
            persons.add(p);
        }
    }

    public void add(View view){
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        Person p = realm.createObject(Person.class);
        p.setCity("New City");
        p.setName("New Name");
        p.setId(UUID.randomUUID().toString());
        realm.commitTransaction();
        persons.add(p);
        listAdapter.notifyDataSetChanged();
    }


}
