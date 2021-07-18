package busbooking.kulendran.com.busbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "user_table";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String SEAT = "SEATS";
    public static final String TOTAMOUTN = "AMOUNT";
    public static final String BUSERNAME = "USERNAME";
    public static final String BUSID = "BUSID";
    public static final String BUS_TABLE_NAME = "bus_table";
    public static final String Payment_TABLE_NAME = "payment_table";
    public static final String BOOK_TABLE_NAME = "bus_book";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");
        sqLiteDatabase.execSQL("create table "+BUS_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BUSFROM TEXT ,BUSTO TEXT,BUSTIME TEXT,BUSTICKET TEXT,BUSSEATS TEXT)");
        sqLiteDatabase.execSQL("create table "+Payment_TABLE_NAME+"(PID INTEGER PRIMARY KEY AUTOINCREMENT,BID INTEGER,USERNAME TEXT,NUM INTEGER,TOTAL DOUBLE)");
        sqLiteDatabase.execSQL("create table "+BOOK_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , SEATS INTEGER,AMOUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BUS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Payment_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BOOK_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(PASSWORD,password);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean CheckAccount(String user,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM user_table where USERNAME ='"+user+"' and PASSWORD ='"+pass+"'", null );

        if(res.getCount()==0){
            return false;
        }else {
            return true;
        }
    }

    public boolean Book(String seats,String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SEAT,seats);
        contentValues.put(TOTAMOUTN,amount);
        long result = db.insert(BOOK_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public  boolean AddBus(String busfrom, String busto, String busTime,String busTicket,String busSeats){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSFROM",busfrom);
        contentValues.put("BUSTO",busto);
        contentValues.put("BUSTIME",busTime);
        contentValues.put("BUSTICKET",busTicket);
        contentValues.put("BUSSEATS",busSeats);
        long result = db.insert(BUS_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addpay(int bid,String user,int num,double total){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("BID",bid);
        contentValues.put("USERNAME",user);
        contentValues.put("NUM",num);
        contentValues.put("TOTAL",total);

        long result=db.insert(Payment_TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;


    }

    //To view All the appointment for searching the appointment
    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> array_list = new ArrayList<Appointment>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM bus_table ", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Integer id = res.getInt(res.getColumnIndex("ID"));
            String Busfrom = res.getString(res.getColumnIndex("BUSFROM"));
            String Busto = res.getString(res.getColumnIndex("BUSTO"));
            String BusTime = res.getString(res.getColumnIndex("BUSTIME"));
            String BusPrice = res.getString(res.getColumnIndex("BUSTICKET"));
            String BusSeats = res.getString(res.getColumnIndex("BUSSEATS"));
            Appointment appointment = new Appointment(id, Busfrom, Busto, BusTime, BusPrice,BusSeats);
            array_list.add(appointment);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Appointment> getdata(String id) {
        ArrayList<Appointment> array_list = new ArrayList<Appointment>();
        ArrayList<Appointment> allAppointments = getAllAppointments();

        for (Appointment appoint: allAppointments) {
            if(appoint.getId().equals(Integer.valueOf(id))) {
                array_list.add(appoint);
            }
        }
        return array_list;
    }

    public Cursor getAllData(){

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res= (Cursor) db.rawQuery("select * from " +BUS_TABLE_NAME,null);

        return res;
    }


    public  boolean updateBus(String id,String busfrom, String busto, String busTime,String busTicket,String busSeats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("BUSFROM", busfrom);
        contentValues.put("BUSTO", busto);
        contentValues.put("BUSTIME", busTime);
        contentValues.put("BUSTICKET", busTicket);
        contentValues.put("BUSSEATS", busSeats);
        db.update(BUS_TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;

    }

    public Cursor getAllPayment(){

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res= (Cursor) db.rawQuery("select * from " +Payment_TABLE_NAME,null);

        return res;
    }

    public Integer deleteData(String id){

        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(BUS_TABLE_NAME,"ID = ?",new String[]{id});

    }}