package com.example.contactmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final ContactDAO contactDAO;
    private final ExecutorService executor;
    private final Handler mainThreadHandler;

    public Repository(Application application) {
        ContactsDatabase database = ContactsDatabase.getInstance(application);
        contactDAO = database.getContactDAO();
        executor = Executors.newSingleThreadExecutor();
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact) {
        executor.execute(() -> contactDAO.insert(contact));
    }

    public void deleteContact(Contacts contact) {
        executor.execute(() -> contactDAO.delete(contact));
    }

    public LiveData<List<Contacts>> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
