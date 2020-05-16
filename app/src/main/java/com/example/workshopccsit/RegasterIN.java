package com.example.workshopccsit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class RegasterIN extends AppCompatActivity {
TextView title,duration,present,date,location;
    int w_Id,S_ID;
    String S_name;
    DBHelper myDB;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regaster_in_layout);

        title = findViewById(R.id.wTitle) ;
        location=  findViewById(R.id.w_loc) ;
        present= findViewById(R.id.Oname) ;
        date= findViewById(R.id.date) ;
        duration= findViewById(R.id.duration) ;
        image= findViewById(R.id.imageView);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("iTitle"));
        location.setText(intent.getStringExtra("iLocation"));
        duration.setText(intent.getStringExtra("iDuration"));
        present.setText(intent.getStringExtra("iPresenter"));
        date.setText(intent.getStringExtra("iDate"));

        byte[] Image =  intent.getByteArrayExtra("iImage");
        Bitmap bmp= BitmapFactory.decodeByteArray(Image, 0 , Image.length);
        image.setImageBitmap(bmp);
    }

    public void  regButton(View v) throws Exception {
        Intent intent = getIntent();
        w_Id = intent.getIntExtra("iID", -1);
        S_ID = intent.getIntExtra("S_ID", -1);
        S_name = intent.getStringExtra("S_name");
        myDB = new DBHelper(this);
        int seat = myDB.seat_num(w_Id);
        int CountReg = myDB.rowCountReceived(w_Id);
        if (! (seat == CountReg)){
            Cursor result1 = myDB.Check_reg(Integer.toString(w_Id),S_ID);

            if (result1.getCount() == 0) {
                boolean isInserted = myDB.Reg_Workshop(S_ID, w_Id);

                if (isInserted == true) {
                    Toast.makeText(this, "register successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this,StudentActivity.class);
                    i.putExtra("SId",S_ID);
                    i.putExtra("S_name",S_name);
                    this.startActivity(i);
                } else {
                    Toast.makeText(this, "Can not register", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "You are already registered in this workshop", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(this, "No available seats. ", Toast.LENGTH_LONG).show();
        }
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
                Toast.makeText(this,"logout selected",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOut(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


