package busbooking.kulendran.com.busbooking;
import static busbooking.kulendran.com.busbooking.LoginActivity.user1;
import static busbooking.kulendran.com.busbooking.BookBusActivity.id1;
import static busbooking.kulendran.com.busbooking.BookBusActivity.no;
import static busbooking.kulendran.com.busbooking.BookBusActivity.total;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    TextView userr,amount,No,bNo;
    EditText cdNo,ccv;
    Button b1;
    private DatabaseHelper myDb;
    int bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        myDb = new DatabaseHelper(this);

        userr = findViewById(R.id.editText);
        No = findViewById(R.id.editText2);
        amount = findViewById(R.id.editText3);
        cdNo=findViewById(R.id.editText5);
        ccv=findViewById(R.id.editText6);
        b1=findViewById(R.id.button1);





        userr.setText(user1);
        No.setText(Integer.toString(no));
        amount.setText(Double.toString( total));


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cno=Integer.parseInt(cdNo.getText().toString());
                int Ccv=Integer.parseInt(ccv.getText().toString());
                String name=userr.getText().toString();


                boolean isInserted=myDb.addpay(id1,name,no,total);
                if(isInserted == true) {
                    Toast.makeText(getApplication(), "Payment Success", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                    finish();
                } else{
                    Toast.makeText(getApplication(),"Payment not Success", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
