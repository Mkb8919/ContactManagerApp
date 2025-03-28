package com.example.contactmanagerapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.contactmanagerapp.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        // ✅ Correct way to use Data Binding
     //   binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);

        binding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contacts = new Contacts();

        // ViewModel initialization
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Initialize ClickHandler
        handler = new AddNewContactClickHandler(contacts, this, myViewModel);

        // ✅ Binding data to layout
        binding.setContact(contacts);
        binding.setClickHandler(handler);


        // Fix window insets issue
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }
}
