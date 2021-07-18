package busbooking.kulendran.com.busbooking;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import static busbooking.kulendran.com.busbooking.BookBusActivity.id1;
import static busbooking.kulendran.com.busbooking.LoginActivity.user1;
import java.util.ArrayList;

import static busbooking.kulendran.com.busbooking.BusLists.SELECTID;



public class BookBusActivity extends AppCompatActivity {
    private DatabaseHelper myDb;
    TextView noSeats,Busfrom,Busto,BusTime,Busprice,totalamount,dg_total,dg_seats,dg_place;
    NumberPicker selectSeats=null;
    public Double Ticketprice,totalAmount;
    Button booknow,bt_done;
    Dialog dialog;
    String fromplace,toplace,seats;
    public static double total;
    public static  int id1,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);
        noSeats = findViewById(R.id.totbusSeats);
        Busfrom = findViewById(R.id.tottxtfrom);
        Busto = findViewById(R.id.tottxtto);
        BusTime = findViewById(R.id.tottxtDate);
        Busprice = findViewById(R.id.tottxtprice);
        selectSeats = findViewById(R.id.np_seats);
        myDb = new DatabaseHelper(this);
        totalamount = findViewById(R.id.totalamount);
        booknow = findViewById(R.id.bt_bookBus);
        dialog = new Dialog(this);

        ArrayList<Appointment> array_list = myDb.getdata(SELECTID);
        Ticketprice = Double.valueOf(array_list.get(0).getBus_Tprice());
        fromplace = array_list.get(0).getBus_from();
        toplace = array_list.get(0).getBus_to();
        seats = array_list.get(0).getBus_Seats();
        id1=array_list.get(0).getId();


        noSeats.setText("Toatal No of Seats : "+seats);
        Busfrom.setText(fromplace);
        Busto.setText(toplace);
        Busprice.setText("Ticket Price :"+array_list.get(0).getBus_Tprice()+"/=");
        BusTime.setText("Depature Time "+array_list.get(0).getBus_time());

        selectSeats.setMaxValue(Integer.valueOf(array_list.get(0).getBus_Seats()));
        selectSeats.setMinValue(1);
        selectSeats.setWrapSelectorWheel(false);
        totalAmount =selectSeats.getValue()*Ticketprice;
        totalamount.setText(String.valueOf("Total Amonut : "+totalAmount+"/="));




        selectSeats.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                no=selectSeats.getValue();

                totalAmount =selectSeats.getValue()*Ticketprice;
                total=totalAmount;
                totalamount.setText(String.valueOf("Total Amonut : "+totalAmount+"/="));

            }
        });


        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String seats1 = String.valueOf(Integer.toString(no));
                String amount1 = String.valueOf(totalamount.getText());


                boolean isInserted = myDb.Book(seats1,amount1);

                if(isInserted == true) {
                    Toast.makeText(getApplication(), "Proceed to payment", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                } else{
                    Toast.makeText(getApplication(),"Error", Toast.LENGTH_LONG).show();
                }

                dialog.setContentView(R.layout.dialog_view);

                dg_place =dialog.findViewById(R.id.book_place);
                dg_seats = dialog.findViewById(R.id.book_seats);
                dg_total = dialog.findViewById(R.id.book_total);
                bt_done = dialog.findViewById(R.id.bt_Done);

                dg_place.setText(fromplace+"-"+toplace);
                dg_total.setText(String.valueOf("Total : "+totalAmount+"/="));
                dg_seats.setText(selectSeats.getValue()+" Seats");

                bt_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),Payment.class);
                        startActivity(intent);
                        finish();

                    }
                });

                dialog.show();
            }
        });


    }
}
