package com.example.workshopccsit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {
    // Database Name
    public static final  String DATABASE_NAME = "WorkshopCCSIT.DB";

    // Table Names
    public static final  String TABLE_STUDENT = "Student";
    public static final  String TABLE_ORGANIZER = "Organizer";
    public static final  String TABLE_WORKSHOPS = "Workshops";
    public static final  String TABLE_Registration = "registration";

   // TABLE_Registration  Names
    public static String COL_S_ID = "S_ID";
    public static String COL_w_ID = "W_ID";
    public static final  String COL_R_ID = "R_ID";


    // TABLE_STUDENT Names
    public static final  String STD_ID = "ID";
    public static final  String STD_NAME = "Name";
    public static final  String STD_PASSWORD = "Password";

    // TABLE_ORGANIZER Names
    public static final  String ORG_ID = "ID";
    public static final  String ORG_NAME = "Name";
    public static final  String ORG_PASSWORD = "Password";

    // TABLE_WORKSHOP Column Names
    public static final  String WS_ID = "ID";
    public static final  String WS_DATE = "WDate";
    public static final  String WS_TITLE = "Title";
    public static final  String WS_NAME = "OrgName";
    public static final  String WS_SEATSNO = "SeatsNo";
    public static final  String WS_DURATION = "Duration";
    public static final  String WS_LOCATION = "Location";
    public static final  String WS_Image = "Image";

    // Tables creation
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE "+TABLE_STUDENT+"(" + STD_ID +" INTEGER PRIMARY KEY, "+STD_NAME+" TEXT, "+STD_PASSWORD+" TEXT)";

    private static final String CREATE_TABLE_ORGANIZER = "CREATE TABLE "+TABLE_ORGANIZER+"(" + ORG_ID +" INTEGER PRIMARY KEY, "+ORG_NAME+" TEXT, "+ORG_PASSWORD+" TEXT)";

    private static final String CREATE_TABLE_WORKSHOPS = "CREATE TABLE "+TABLE_WORKSHOPS+"("
            + WS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+WS_NAME+" TEXT, "+WS_TITLE+" TEXT, " +WS_DATE+" TEXT, "+WS_SEATSNO+" INTEGER, "+WS_DURATION+" TEXT, "+WS_LOCATION+" TEXT,"+WS_Image+" BLOB )";

    private static final String CREATE_TABLE_Registration  =
            "CREATE TABLE " +TABLE_Registration+"("+ COL_R_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ," + COL_S_ID +" INTEGER , "+COL_w_ID +" INTEGER, attend INTEGER)";

    //Constructor
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }// 34

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_ORGANIZER);
        db.execSQL(CREATE_TABLE_WORKSHOPS);
        db.execSQL(CREATE_TABLE_Registration);



        db.execSQL("INSERT INTO " + TABLE_ORGANIZER + " (ID, Name, Password) VALUES (219000,'Mona saad','123')");
        db.execSQL("INSERT INTO " + TABLE_STUDENT + " (ID, Name, Password) VALUES (216000,'Leena hassen','123')");
        db.execSQL("INSERT INTO " + TABLE_STUDENT + " (ID, Name, Password) VALUES (217000,'Mohammed mosa','456')");
        db.execSQL("INSERT INTO " + TABLE_STUDENT + " (ID, Name, Password) VALUES (218000,'Rowaa sadeq','789')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ORGANIZER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_WORKSHOPS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Registration);
        onCreate(db);

    }

    //insert method
  /*  public void insert(String name, String title, String date, int seatno, int duration, String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValuesWS = new ContentValues();
        contentValuesWS.put(WS_NAME, name);
        contentValuesWS.put(WS_TITLE, title);
        contentValuesWS.put(WS_DATE, date);
        contentValuesWS.put(WS_SEATSNO, seatno);
        contentValuesWS.put(WS_DURATION, duration);
        contentValuesWS.put(WS_LOCATION, location);

        db.insert(TABLE_WORKSHOPS, null, contentValuesWS);

    }*/

    public Cursor view() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery("select * from " + TABLE_WORKSHOPS, null );
        return records;
    }

    public Cursor S_View_Workshops(){

        SQLiteDatabase db = this.getWritableDatabase();

        //create instant of the cursor class
        Cursor records = db.rawQuery("select * from " + TABLE_WORKSHOPS, null );

        return records;
    }


    public Integer delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_WORKSHOPS,"ID = ?" , new String[] {id});
    }

/*    public Integer rowCount() {
        Integer counter = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor recordsCount = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_WORKSHOPS, null );

        if(recordsCount.getCount() > 0)
        {
            recordsCount.moveToFirst();
            counter = recordsCount.getInt(0);
        }

        return counter;
    }*/

//W_title W_presenter  w_duration  w_location W_seatNO W_date
    public boolean AddWorkshop(String title, String Presenter, String Duration, String Date, String Location , String seatNo, byte[] image) throws Exception{

        // create your database and table
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newContactValues = new ContentValues();

        newContactValues.put(WS_TITLE,     title);
        newContactValues.put(WS_DATE ,     Date);
        newContactValues.put(WS_NAME,      Presenter);
        newContactValues.put(WS_SEATSNO,   seatNo);
        newContactValues.put(WS_DURATION , Duration);
        newContactValues.put(WS_LOCATION , Location);
        newContactValues.put(WS_Image , image);

        db.beginTransaction();
        db.insert(TABLE_WORKSHOPS, null, newContactValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        long result = db.insert(TABLE_WORKSHOPS, null, newContactValues);

        if (result == -1)
        { return false; }
        else { return true;  }

    }

 public boolean UpdateWorkshop(int Id,String title, String Presenter,String Duration, String Date,String Location ,String seatNo, byte[] image) throws Exception{

        // create your database and table
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newContactValues = new ContentValues();

        newContactValues.put(WS_TITLE, title);
        newContactValues.put(WS_DATE , Date);
        newContactValues.put(WS_NAME,   Presenter);
        newContactValues.put(WS_SEATSNO,   seatNo);
        newContactValues.put(WS_DURATION ,Duration );
        newContactValues.put(WS_LOCATION , Location);
        newContactValues.put(WS_Image , image);


        db.beginTransaction();
        db.update(TABLE_WORKSHOPS,newContactValues, "Id="+Id,null);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        long result = db.update(TABLE_WORKSHOPS,newContactValues, "Id="+Id,null);

        if (result == -1)
        { return false; }
        else { return true;  }

    }



    public Cursor View_Reg_S(int S_id){

        SQLiteDatabase db = this.getWritableDatabase();

        //create instant of the cursor class
        String q= "SELECT * FROM Workshops JOIN registration ON W_ID=ID where S_ID="+S_id;
        Cursor records = db.rawQuery(q,null);

        return records;
    }



    public Cursor View_Reg_W(int Sid){

        SQLiteDatabase db = this.getWritableDatabase();

        //create instant of the cursor class

        Cursor records = db.query("registration", new String[]{"S_ID=?"},Integer.toString(Sid), null, null, null, null);

        return records;
    }



    public Integer deleteReg(String id,int S_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Registration,"W_ID = ? AND S_ID = ?" , new String[] {id,Integer.toString(S_ID)});
    }




    public boolean Reg_Workshop(int s_id , int W_id) throws Exception{

        // create your database and table
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newContactValues = new ContentValues();

        newContactValues.put(COL_S_ID,     Integer.toString(s_id));
        newContactValues.put(COL_w_ID ,    Integer.toString(W_id));
        newContactValues.put("attend",    0);

        db.beginTransaction();
        long result = db.insert(TABLE_Registration, null, newContactValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

        if (result == -1)
        { return false; }
        else { return true;  }

    }


    public Cursor student_login(int S_id,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        //create instant of the cursor class

        String q= "SELECT * FROM Student WHERE ID="+S_id+" AND Password='"+password+"'";
        Cursor records = db.rawQuery(q, null);
        return records;
    }

    public Cursor Organizer_login(int o_id,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        //create instant of the cursor class

        String q= "SELECT * FROM Organizer WHERE ID="+o_id+" AND Password='"+password+"'";
        Cursor records = db.rawQuery(q, null);

        return records;
    }





    public Cursor Check_reg(String id,int S_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String q= "SELECT * FROM registration WHERE W_ID ="+id+" AND S_ID ="+S_ID;
        Cursor records = db.rawQuery(q,null);
        return records;
    }


    public Integer rowCountReceived(int id)
    {
        Integer counter = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor recordsCount = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_Registration+" WHERE "+COL_w_ID+"='"+id+"'", null );

        if(recordsCount.getCount() > 0)
        {
            recordsCount.moveToFirst();
            counter = recordsCount.getInt(0);
        }

        return counter;
    }


    public Integer absent(int id)
    {
       Integer counter = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor abs = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_Registration+" WHERE "+COL_w_ID+"='"+ id +"' AND attend =1 ", null );

        if(abs.getCount() > 0)
        {
            abs.moveToFirst();
            counter = abs.getInt(0);
        }
        return counter;
    }

    public Integer present(int id)
    {
       Integer counter = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor abs = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_Registration+" WHERE "+COL_w_ID+"='"+ id +"' AND attend =0 ", null );

        if(abs.getCount() > 0)
        {
            abs.moveToFirst();
            counter = abs.getInt(0);
        }
        return counter;
    }





 public Integer seat_num(int id)
    {
       Integer counter = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor seatsCount = db.rawQuery(" SELECT "+ WS_SEATSNO +" FROM "+ TABLE_WORKSHOPS +" WHERE "+ WS_ID +"='"+id+"'", null );

        if(seatsCount.getCount() > 0)
        {
            seatsCount.moveToFirst();
            counter = seatsCount.getInt(0);
        }
        return counter;
    }


    public Cursor Attend(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor attend = db.rawQuery("SELECT * FROM registration WHERE W_ID='"+id+"'", null );

        return attend;
    }


    public Cursor Student_name(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor attend = db.rawQuery(" SELECT * FROM Student WHERE ID ='"+ id +"'", null );

        return attend;
    }

    public Cursor Student_ID(String name)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor attend = db.rawQuery(" SELECT * FROM Student WHERE Name ='"+ name +"'", null );

        return attend;
    }

    public boolean check(int num, int Sid, int Wid)
    {

        SQLiteDatabase db = this.getWritableDatabase();

       db.execSQL(" UPDATE registration SET attend ='"+num+"' WHERE S_ID ='"+ Sid +"' AND W_ID ='"+Wid+"'");
       return true;  }





    }





