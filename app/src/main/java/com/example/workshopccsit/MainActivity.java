package com.example.workshopccsit;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    DBHelper myDB;
    EditText ID, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        transaction.replace(R.id.constraintLayout, welcomeFragment);
        transaction.commit();
        //
        myDB = new DBHelper(this);
        ID = findViewById(R.id.idEditText);
        Password = findViewById(R.id.passwordEditText);
    }

    public void StudentFragment(View view){
        FragmentTransaction transaction = manager.beginTransaction();
        StudentFragment studentFragment = new StudentFragment();

        if(view == findViewById(R.id.studentButton)){
            transaction.replace(R.id.constraintLayout, studentFragment);
            transaction.commit();
        }
    }

    public void OrgnizerFragment(View view){
        FragmentTransaction transaction = manager.beginTransaction();
        OrgnizerFragment orgnizerFragment = new OrgnizerFragment();

        if(view == findViewById(R.id.orgnizerButton)){
            transaction.replace(R.id.constraintLayout, orgnizerFragment);
            transaction.commit();
        }
    }
}
















