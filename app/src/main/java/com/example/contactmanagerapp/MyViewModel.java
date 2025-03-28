package com.example.contactmanagerapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private final Repository myRepository;
    private final LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new Repository(application);
        allContacts = myRepository.getAllContacts();
    }

    public LiveData<List<Contacts>> getAllContacts() {
        return allContacts;
    }

    public void addNewContact(Contacts contact) {
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact) {
        myRepository.deleteContact(contact);
    }
}
