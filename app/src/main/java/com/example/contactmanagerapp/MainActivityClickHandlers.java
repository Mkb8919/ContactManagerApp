package com.example.contactmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivityClickHandlers {

  private Context context;
    public MainActivityClickHandlers(Context context){
        this.context = context;
    }

    public void onFABClicked(View view){

       // Intent i = new Intent(view.getContext(), AddNewContactActivity.class);
      //  context.startActivity(i);
        Toast.makeText(view.getContext(), "FAB Clicked!", Toast.LENGTH_SHORT).show();

    }
}
