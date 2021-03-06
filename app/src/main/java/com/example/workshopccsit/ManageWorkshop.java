package com.example.workshopccsit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ManageWorkshop extends AppCompatActivity {
    FragmentManager manager;
    TextView O_name;
    String o_name;
    int O_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizer_activity_layout);


        Intent intent = getIntent();
         o_name = intent.getStringExtra("O_name");
         O_ID = intent.getIntExtra("OId",-1);

        O_name =findViewById(R.id.Name);
        O_name.setText("Hello, "+o_name);


        manager = getSupportFragmentManager();

        //
        FragmentTransaction transaction = manager.beginTransaction();
        ManageWorkshops orgnizerFragment = new ManageWorkshops();

        Bundle bundle = new Bundle();
        bundle.putString("OId", String.valueOf(O_ID));
        bundle.putString("O_name", o_name);

        // set Fragment class Arguments

        orgnizerFragment.setArguments(bundle);

        transaction.replace(R.id.constraintLayout, orgnizerFragment);
        transaction.commit();

        //

    }


   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.sub_menu, menu);
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item2_menu:
                logOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void logOut(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}

