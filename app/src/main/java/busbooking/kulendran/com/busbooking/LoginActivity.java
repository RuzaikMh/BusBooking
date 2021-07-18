package busbooking.kulendran.com.busbooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper myDb;
    EditText username,password;
    Button Login,Signin,adminLogin;
    public static String user1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        username = findViewById(R.id.login_user);
        password = findViewById(R.id.login_pass);
        Login = findViewById(R.id.bt_login);
        Signin = findViewById(R.id.bt_signin);
        adminLogin = findViewById(R.id.Login_as_Admin);


        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SigninActivityy.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = String.valueOf(username.getText());
                String passwrd = String.valueOf(password.getText());
                boolean isInserted = myDb.CheckAccount(user,passwrd);


                if(isInserted == true) {
                    user1=user;
                    Intent intent = new Intent(getApplicationContext(),BusLists.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplication(),"Login fail !", Toast.LENGTH_LONG).show();
                }

            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = String.valueOf(username.getText());
                String passwrd = String.valueOf(password.getText());

                if((user.equals("admin")&&passwrd.equals("admin"))){
                    Intent intent = new Intent(getApplicationContext(),AddBusDetails.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplication(),"Admin Login invalid !", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
