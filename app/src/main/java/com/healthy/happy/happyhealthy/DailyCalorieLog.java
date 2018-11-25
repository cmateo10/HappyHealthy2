package com.healthy.happy.happyhealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyCalorieLog extends AppCompatActivity {

    EditText edate, ecalorie;
    DBHelper helper;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calorie_log);

        helper = new DBHelper(this);
        name = getIntent().getStringExtra("Name");

        edate = findViewById(R.id.date);
        ecalorie = findViewById(R.id.calorieintake);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        edate.setText(formattedDate);
    }

    public void CalorieInput(View v)  {
        try{
            Intent i = new Intent(this, Success.class);
            int calories = Integer.parseInt(ecalorie.getText().toString().trim());
            String fcalories = Integer.toString(calories);
            helper.insertCalorie(name, fcalories, edate.getText().toString());
            i.putExtra("Calorie", fcalories);
            i.putExtra("Name", name);
            startActivity(i);
            finish();
        } catch(Exception e) {
            Toast.makeText(this, "Input not an Integer",Toast.LENGTH_LONG).show();
        }
    }
}
