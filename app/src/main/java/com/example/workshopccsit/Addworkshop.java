package com.example.workshopccsit;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Addworkshop extends AppCompatActivity {
    final int REQUEST_CODE_GALLARY=999;
    ImageView image;
    EditText W_title,W_seatNO,W_presenter,w_duration,w_location;
    TextView W_date;
    DBHelper myDB;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;

    int O_ID;
    String o_name ;


    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add);

        W_title =findViewById(R.id.AddTitle);
        W_date  = findViewById(R.id.Adddate);
        W_seatNO = findViewById(R.id.AddseatsNo);
        W_presenter = findViewById(R.id.AddOName);
        w_duration =findViewById(R.id.Adddura);
        w_location  = findViewById(R.id.AddLocation);
        image= findViewById(R.id.imageView);

        Intent intent = getIntent();
        O_ID   = intent.getIntExtra("OId", -1);
        o_name = intent.getStringExtra("O_name");

// Calender
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Show current date
     /*   W_date.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
*/

    }

    //  IMAGE
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLARY) {
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLARY);
            }
            else {
                Toast.makeText(getApplicationContext(),"sorry, you don't have a permission to access the file location",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if (requestCode == REQUEST_CODE_GALLARY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick1(View v) {
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLARY);
    }




private byte[] imageviewtobyte(ImageView image ){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArry= stream.toByteArray();
        return byteArry ;}




public void AddButton (View v) throws Exception {

    myDB = new DBHelper(this);


if(! (     isNullOrEmpty(W_title.getText().toString())
        || isNullOrEmpty(W_presenter.getText().toString())
        || isNullOrEmpty(w_duration.getText().toString())
        || isNullOrEmpty(W_seatNO.getText().toString())
        || isNullOrEmpty(W_date.getText().toString())
        || isNullOrEmpty(w_location.getText().toString()))
    ) {

      boolean isInserted = myDB.AddWorkshop(  W_title.getText().toString(),
                                              W_presenter.getText().toString(),
                                              w_duration.getText().toString(),
                                              W_date.getText().toString(),
                                              w_location.getText().toString(),
                                              W_seatNO.getText().toString(),
                                              imageviewtobyte(image));

        if (isInserted == true) {

            Toast.makeText(this, "New contact added to the Database", Toast.LENGTH_LONG).show();
            Intent i= new Intent(this, OrganizerActivity.class);

            i.putExtra("OId",O_ID );
            i.putExtra("O_name",o_name);

            this.startActivity(i);

        } else {
            Toast.makeText(this, "No contact has been added to the Database", Toast.LENGTH_LONG).show();
        }
    } else {
        Toast.makeText(this, "please fill all text fields", Toast.LENGTH_LONG).show();
    }

}




    public void cancel_Button (View v){
        Intent i= new Intent(this, OrganizerActivity.class);
        i.putExtra("OId",O_ID );
        i.putExtra("O_name",o_name);
        this.startActivity(i);
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }



    public void onClick(View v) {
        showDialog(DATE_PICKER_ID);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            W_date.setText(new StringBuilder().append(month + 1).append("-").append(day)
                    .append("-").append(year).append(" "));
        }
    };

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
