package com.example.contactmanagerapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

@Database(entities = {Contacts.class}, version = 3, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

    private static volatile ContactsDatabase dbInstance;

    public static synchronized ContactsDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactsDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();
        }

        return dbInstance;
    }
}



