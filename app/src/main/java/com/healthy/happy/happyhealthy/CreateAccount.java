package com.healthy.happy.happyhealthy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    EditText etfullname, etemail, etpass, etpassAgain, etbirthday;
    RadioGroup genderRadioGroup;
    Button createButton;
    DatePickerDialog datePickerDialog;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        helper = new DBHelper(this);
        etfullname = findViewById(R.id.fullname);
        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.password);
        etpassAgain = findViewById(R.id.password_again);
        etbirthday = findViewById(R.id.birthday);
        genderRadioGroup = findViewById(R.id.gender_radiogroup);

        etbirthday.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etbirthday.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void Back(View v)  {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void CreateUser(View v){
        RadioButton selectedRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String fullname = etfullname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        String passagain = etpassAgain.getText().toString().trim();
        String birthday = etbirthday.getText().toString().trim();
        String gender = selectedRadioButton.getText().toString();

        if(!fullname.equals("")&&!email.equals("")&&!password.equals("")&&!passagain.equals("")&&!birthday.equals("")&&!gender.equals("")){
            if(password.equals(passagain)){
                helper.insert(fullname, email, password, birthday, gender);
                Toast.makeText(this, "User Created...",Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, LogIn.class);
                startActivity(i);
            }
            else
                Toast.makeText(this,"Password and password again field must be the same",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"No field can be empty",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.birthday){
                datePickerDialog.show();
        }
    }
}
