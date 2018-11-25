package com.healthy.happy.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View v)  {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }

    public void CreateAccount(View v)  {
        Intent i = new Intent(this, CreateAccount.class);
        startActivity(i);
    }

}


