package busbooking.kulendran.com.busbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteBus extends AppCompatActivity {

    EditText BusId;

    Button bt_dlt;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bus);
        myDb = new DatabaseHelper(this);


        BusId=findViewById(R.id.busId);
        bt_dlt=findViewById(R.id.bt_delet2);




        bt_dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // int no=Integer.parseInt(BusId.getText().toString());

                Integer deletedRows = myDb.deleteData(BusId.getText().toString());

                if (deletedRows > 0){
                    Toast.makeText(getApplication(), "Bus Details Deleted", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                } else{
                    Toast.makeText(getApplication(),"Bus Details not deleted", Toast.LENGTH_LONG).show();
                }


            }
        });
    }




}
