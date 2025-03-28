package com.example.contactmanagerapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contacts_id")
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;

    // Constructor for inserting a new contact (without ID)
    @Ignore
    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Room requires an empty constructor
    public Contacts() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

