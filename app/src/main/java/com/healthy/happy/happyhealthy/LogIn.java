package com.healthy.happy.happyhealthy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    Button loginbutton;
    EditText epassword, eemail;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        helper = new DBHelper(this);
        loginbutton = findViewById(R.id.button4);
        eemail = findViewById(R.id.email);
        epassword = findViewById(R.id.password);
    }

    public void Back(View v)  {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Login(View v)  {
        String email = eemail.getText().toString().trim();
        String password = epassword.getText().toString().trim();
        Cursor check = helper.checkUser(email,password);
        if(check == null){
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_LONG).show();
        } else {
            check.moveToFirst();
            String name = check.getString(1);
            String birthday = check.getString(4);
            Intent i = new Intent(getBaseContext(), Home.class);
            i.putExtra("Name", name);
            i.putExtra("Birthday", birthday);
            startActivity(i);
            finish();
        }
    }

}
