package busbooking.kulendran.com.busbooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBusDetails extends AppCompatActivity {
    Button bt_addBus,bt_viewBus,bt_updateBus,bt_deleteBus,bt_pay;
    EditText BusFrom,BusTo,BusTime,BusTicPrice,BusSeats;
     DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_details);
        bt_addBus = findViewById(R.id.bt_addBus);
        bt_deleteBus=findViewById(R.id.bt_delete);
        bt_pay=findViewById(R.id.bt_pay);
        bt_updateBus=findViewById(R.id.bt_addBus3);
        bt_viewBus=findViewById(R.id.bt_addBus2);
        BusFrom = findViewById(R.id.txt_busStart);
        BusTo = findViewById(R.id.txt_busEnd);
        BusTime = findViewById(R.id.txt_bustime);
        BusTicPrice = findViewById(R.id.txt_BusPrice);
        BusSeats = findViewById(R.id.txt_BusSeats);
        myDb = new DatabaseHelper(this);

        bt_addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String busfrom = String.valueOf(BusFrom.getText());
                String busto = String.valueOf(BusTo.getText());
                String bustime = String.valueOf(BusTime.getText());
                String ticketPrice  = String.valueOf(BusTicPrice.getText());
                String busSeats = String.valueOf(BusSeats.getText());

                boolean isInserted = myDb.AddBus(busfrom,busto,bustime,ticketPrice,busSeats);
                if(isInserted == true) {
                    Toast.makeText(getApplication(), "Bus Added", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                } else{
                    Toast.makeText(getApplication(),"Bus Not Added", Toast.LENGTH_LONG).show();
                }
            }
        });



        bt_viewBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res =myDb.getAllData();

                if(res.getCount() == 0){
                   showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("Bus Id: "+res.getString(0)+"\n");
                    buffer.append("From: "+res.getString(1)+"\n");
                    buffer.append("To: "+res.getString(2)+"\n");
                    buffer.append("Time: "+res.getString(3)+"\n ");
                    buffer.append("Ticket Price: "+res.getString(4)+"\n ");
                    buffer.append("No of seats: "+res.getString(5)+"\n\n ");
                }

                showMessage("Data",buffer.toString());

            }
        });

        bt_updateBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UpdateBus.class);
                startActivity(intent);
                finish();
            }
        });

        bt_deleteBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),deleteBus.class);
                startActivity(intent);
                finish();

            }
        });



          bt_pay.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Cursor res =myDb.getAllPayment();

                  if(res.getCount() == 0){
                      showMessage("Error","Nothing found");
                      return;
                  }

                  StringBuffer buffer = new StringBuffer();

                  while(res.moveToNext()){
                      buffer.append("Payment Id: "+res.getString(0)+"\n");
                      buffer.append("Bus ID: "+res.getString(1)+"\n");
                      buffer.append("User Name: "+res.getString(2)+"\n");
                      buffer.append("Number of seats: "+res.getString(3)+"\n ");
                      buffer.append("Total Price: "+res.getString(4)+"\n\n ");

                  }

                  showMessage("Data",buffer.toString());
              }
          });

        }
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
