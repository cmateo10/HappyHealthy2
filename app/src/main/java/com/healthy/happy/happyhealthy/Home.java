package com.healthy.happy.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView tname, tbirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tname = findViewById(R.id.name);
        tbirthday = findViewById(R.id.birthday);
        String name = getIntent().getStringExtra("Name");
        String birthday = getIntent().getStringExtra("Birthday");
        tname.setText(name);
        tbirthday.setText(birthday);
    }

    public void CalorieLog(View v)  {
        Intent i = new Intent(this, DailyCalorieLog.class);
        i.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(i);
    }

    public void PastLogs(View v)  {
        Intent i = new Intent(this, PastLogs.class);
        i.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(i);
    }

    public void SignOut(View v)  {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
