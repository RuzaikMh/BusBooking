package busbooking.kulendran.com.busbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivityy extends AppCompatActivity {
    private DatabaseHelper myDb;
    EditText user,password;
    Button creatAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_activityy);

        myDb = new DatabaseHelper(this);
        user = findViewById(R.id.signuser);
        password = findViewById(R.id.signpass);
        creatAccount = findViewById(R.id.bt_create_user);


        creatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(user.getText());
                String passwrd = String.valueOf(password.getText());

                if(username.equals("") || passwrd.equals("")){
                    Toast.makeText(getApplication(),"Invalid !", Toast.LENGTH_LONG).show();

                }else {
                    boolean isInserted = myDb.insertData(username,passwrd);
                    if(isInserted == true) {
                        Toast.makeText(getApplication(), "Account Created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else{
                        Toast.makeText(getApplication(),"Account not Created", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
