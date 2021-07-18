package busbooking.kulendran.com.busbooking;



import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateBus extends AppCompatActivity {

    EditText BusFrom,BusTo,BusTime,BusTicPrice,BusSeats,BusID;
    TextView  BusFrom1,BusTo1,BusTime1,BusTicPrice1,BusSeats1,BusID1;
    DatabaseHelper myDb;
    Button bt_updateBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus);
        BusID=findViewById(R.id.txt_busId);
        BusFrom = findViewById(R.id.txt_busStart);
        BusTo = findViewById(R.id.txt_busEnd);
        BusTime = findViewById(R.id.txt_bustime);
        BusTicPrice = findViewById(R.id.txt_BusPrice);
        BusSeats = findViewById(R.id.txt_BusSeats);
        myDb = new DatabaseHelper(this);
        bt_updateBus=findViewById(R.id.bt_update3);



        bt_updateBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String busId =String.valueOf(BusID.getText());

                String busfrom = String.valueOf(BusFrom.getText());
                String busto = String.valueOf(BusTo.getText());
                String bustime = String.valueOf(BusTime.getText());
                String ticketPrice  = String.valueOf(BusTicPrice.getText());
                String busSeats = String.valueOf(BusSeats.getText());

                boolean isUpdate=myDb.updateBus(busId,busfrom,busto,bustime,ticketPrice,busSeats);

                if(isUpdate == true) {
                    Toast.makeText(getApplication(), "Bus Details Updated", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                } else{
                    Toast.makeText(getApplication(),"Bus Details not Updated", Toast.LENGTH_LONG).show();
                }

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
