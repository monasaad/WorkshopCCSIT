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
public class Modify extends AppCompatActivity {
    int O_ID;
    String o_name;
    TextView title,duration,present,Date,location,seatNO;
    DBHelper myDB;
    int Id;
    int year, month, day;
    static final int DATE_PICKER_ID = 1111;
    final int REQUEST_CODE_GALLARY=999;
    ImageView image;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_activity_layout);



        title = findViewById(R.id.edTitle) ;
        location=  findViewById(R.id.edLocation) ;
        present= findViewById(R.id.AddOName) ;
        Date= findViewById(R.id.DisplayDate) ;
        duration= findViewById(R.id.eddura) ;
        seatNO= findViewById(R.id.edNoseats) ;
        image= findViewById(R.id.imageView);


      Intent intent = getIntent();
        title.setText(intent.getStringExtra("iTitle"));
        location.setText(intent.getStringExtra("iLocation"));
        duration.setText(intent.getStringExtra("iDuration"));
        present.setText(intent.getStringExtra("iPresenter"));
        Date.setText(intent.getStringExtra("iDate"));
        seatNO.setText(intent.getStringExtra("iSeatNo"));
        Id=(intent.getIntExtra("iID",-1));


        O_ID   = intent.getIntExtra("OId", -1);
        o_name = intent.getStringExtra("O_name");



        byte[] Image =  intent.getByteArrayExtra("iImage");
        Bitmap bmp= BitmapFactory.decodeByteArray(Image, 0 , Image.length);
        image.setImageBitmap(bmp);


// Calender
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Show current date
     /*   Date.setText(new StringBuilder()
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



    public void cancel_But (View v){
        Intent i= new Intent(this,OrganizerActivity.class);
        i.putExtra("OId",O_ID );
        i.putExtra("O_name",o_name);
        this.startActivity(i);
    }




    public void Update_Button (View v) throws Exception {
        myDB = new DBHelper(this);

      if (! (    isNullOrEmpty(title.getText().toString())
              || isNullOrEmpty(present.getText().toString())
              || isNullOrEmpty(duration.getText().toString())
              || isNullOrEmpty(seatNO.getText().toString())
              || isNullOrEmpty(Date.getText().toString())
              || isNullOrEmpty(location.getText().toString()))
      ) {

            boolean isInserted = myDB.UpdateWorkshop(  Id,
                    title.getText().toString(),
                    present.getText().toString(),
                    duration.getText().toString(),
                    Date.getText().toString(),
                    location.getText().toString(),
                    seatNO.getText().toString(),
                    imageviewtobyte(image));

            if (isInserted == true)
             {  Toast.makeText(this, "Updated in the Database", Toast.LENGTH_LONG).show();
                 Intent i= new Intent(this, OrganizerActivity.class);

                 i.putExtra("OId",O_ID );
                 i.putExtra("O_name",o_name);

                 this.startActivity(i);
             }
            else
                {
                Toast.makeText(this, "can not Updated the Database", Toast.LENGTH_LONG).show();
                }
       } else {
            Toast.makeText(this, "please fill all text fields", Toast.LENGTH_LONG).show();
        }

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
            Date.setText(new StringBuilder().append(month + 1).append("-").append(day)
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
