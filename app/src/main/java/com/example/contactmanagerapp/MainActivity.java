package com.example.contactmanagerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Data Source
    private ContactsDatabase contactsDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    // Adapter
    private MyAdapter myAdapter;

    // Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Data Binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);

        // Enable Edge-to-Edge Layout
        EdgeToEdge.enable(this);

        // RecyclerView Setup
        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // Database Initialization
        contactsDatabase = ContactsDatabase.getInstance(this);

        // ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Inserting a New Contact (For Testing)
        Contacts c1 = new Contacts("Mohit", "mohit@gmail.com");
        Contacts c2 = new Contacts("Jack", "jack@gmail.com");

        viewModel.addNewContact(c1);
        viewModel.addNewContact(c2);

        // Load Data from ROOM DB
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contactList) {
                contactsArrayList.clear(); // Clear existing list
                contactsArrayList.addAll(contactList); // Add new data
                myAdapter.notifyDataSetChanged(); // Notify adapter of changes

                for (Contacts c : contactList) {
                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);

                }


                myAdapter.notifyDataSetChanged();
            }
        });

        // Adapter Initialization
        myAdapter = new MyAdapter(contactsArrayList);

        // Linking the RecyclerView with the Adapter
        recyclerView.setAdapter(myAdapter);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // if you swipe the item to the left
                Contacts c =  contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContact(c);

            }
        }).attachToRecyclerView(recyclerView);



        // Apply Window Insets for Edge-to-Edge Layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
