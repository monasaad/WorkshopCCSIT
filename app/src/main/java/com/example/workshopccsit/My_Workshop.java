package com.example.workshopccsit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class My_Workshop extends AppCompatActivity {

    FragmentManager manager;
    EditText ID, Password;
    TextView S_name ;
    int S_ID;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_layout);

        S_name =findViewById(R.id.Name);

        Intent intent = getIntent();
        name = intent.getStringExtra("S_name");
        S_ID = intent.getIntExtra("SId",-1);

        setResult(RESULT_OK, intent);



        S_name.setText("Hello, "+ name );

        manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        MyWorkshops MyWsFragment = new MyWorkshops();


        Bundle bundle = new Bundle();
        bundle.putString("stu_ID", String.valueOf(S_ID));
        bundle.putString("S_name", name);

        // set Fragment class Arguments

        MyWsFragment.setArguments(bundle);

        transaction.replace(R.id.constraintLayout, MyWsFragment);
        transaction.commit();}

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
