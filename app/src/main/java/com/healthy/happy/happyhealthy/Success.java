package com.healthy.happy.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Success extends AppCompatActivity {

    TextView tdate, tcalorie;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        tdate = findViewById(R.id.date);
        tcalorie = findViewById(R.id.calorie);
        name = getIntent().getStringExtra("Name");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        tdate.setText(formattedDate);
        tcalorie.setText(getIntent().getStringExtra("Calorie") + " CALORIES");
    }

    public void Back(View v)  {
        Intent i = new Intent(this, DailyCalorieLog.class);
        i.putExtra("Name", name);
        startActivity(i);
        finish();
    }

    public void PastLogs(View v)  {
        Intent i = new Intent(this, PastLogs.class);
        i.putExtra("Name", name);
        startActivity(i);
        finish();
    }
}
