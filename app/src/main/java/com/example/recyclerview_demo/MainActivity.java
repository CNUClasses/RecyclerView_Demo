package com.example.recyclerview_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);


        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter();

        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);

        // Attach the layout manager to the recycler view
        // Set layout manager to position the items
//      rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setLayoutManager(new GridLayoutManager(this,3)); //3 columns
//      rvContacts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));    //2 columns
    }
}
