package com.example.workshopccsit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class Attendance extends AppCompatActivity {

    RecyclerView ListView;
    rowAdapter adapter;
    ArrayList<Country> items;
    DBHelper myDB;

  //  private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend);
        ListView = findViewById(R.id.list_view);




       Intent i = getIntent();
       int wid =i.getIntExtra("WId",-1);
       TextView name =findViewById(R.id.WorkTitle);
       name.setText(i.getStringExtra("iTitle"));


        myDB = new DBHelper(this);

        Cursor result1 = myDB.Attend(wid);
        Cursor result2;

        if(result1.getCount() == 0)
        {
            ShowMsg("Oh! Hold on!", "There is no one register on this workshop.");
        }
        else {
            items = new ArrayList<>();
            while (result1.moveToNext()) {
                result2 = myDB.Student_name(result1.getInt(1));

                if (result2.getCount() == 0) {
                    ShowMsg("Oh! Hold on!", "NO STUDENT.");
                } else {
                    result2.moveToNext();
                    items.add(new Country(result2.getString(1),result1.getInt(3)) );
                }


                ListView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new rowAdapter(this, items, wid) ;
                ListView.setAdapter(adapter);
            }
        }
        /*
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectdCountry = rowAdapter.getSelectedCountry();
                StringBuilder str = new StringBuilder();
                if(selectdCountry.size() != 0){
                    Iterator iterator = selectdCountry.iterator();
                    while (iterator.hasNext()){
                        str.append(iterator.next().toString());
                    }
                    Toast.makeText(Attendance.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        });


         */
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


        public void ShowMsg(String title, String msg){

            //builder use to set our title and message to the user
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // to cancel it after it has been used
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(msg);

            // show the dialog
            builder.show();


        }
}