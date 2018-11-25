package com.healthy.happy.happyhealthy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

public class PastLogs extends AppCompatActivity {

    DBHelper helper;
    Cursor cur;
    cursorAdapter curAdapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_logs);

        helper = new DBHelper(this);
        cur = helper.selectRecords();
        name = getIntent().getStringExtra("Name");

        ListView lvItems = findViewById(R.id.listView);
        curAdapter = new cursorAdapter(this, cur);
        lvItems.setAdapter(curAdapter);
    }

    public class cursorAdapter extends CursorAdapter implements Filterable {
        public cursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.logs_layout, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView date = view.findViewById(R.id.textviewDate);
            TextView calorie = view.findViewById(R.id.textviewCalorie);

            String tdate= cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String tcalorie = cursor.getString(cursor.getColumnIndexOrThrow("calorie"));

            date.setText(tdate);
            calorie.setText(tcalorie);
        }
    }

    public void Home(View v)  {
        Intent i = new Intent(this, Home.class);
        i.putExtra("Name", name);
        startActivity(i);
        finish();
    }
}
