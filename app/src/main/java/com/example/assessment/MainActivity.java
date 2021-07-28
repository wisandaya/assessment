package com.example.assessment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.assessment.Global.GlobalFunctions;
import com.example.assessment.presenter.GetResponse;
import com.example.assessment.presenter.GetResponsePresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetResponse.view {

    GetResponsePresenter presenter;
    TextInputEditText et_name;
    TextInputEditText et_email;
    TextInputEditText et_phoneNumber;
    TextInputEditText et_dateOfBirth;
    TextInputEditText et_age;
    Button btn_submit;
    ArrayList<Boolean> isAllValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new GetResponsePresenter(this, this);
        isAllValid = new ArrayList<>();
        et_name = findViewById(R.id.et_fullName);
        et_email = findViewById(R.id.et_emailAdd);
        et_phoneNumber = findViewById(R.id.et_mobileNumber);
        et_dateOfBirth = findViewById(R.id.et_dateOfBirth);
        et_age = findViewById(R.id.et_age);
        btn_submit = findViewById(R.id.btnSubmit);
        et_dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                et_dateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                et_age.setText(GlobalFunctions.getAge(year, monthOfYear, dayOfMonth));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllValid.clear();
                if(Validation()) {
                    presenter.getResponse();
                }else{
                    showErrorDialog("Please Enter Valid Information to Proceed");
                }
            }
        });


    }

    private boolean Validation() {
        isAllValid.add(GlobalFunctions.EmailValidaion(et_email));
        isAllValid.add(GlobalFunctions.PhoneValidaion(et_phoneNumber));
        isAllValid.add(GlobalFunctions.ageValidaion(et_age));
        if (isAllValid.contains(false)){
            return false;
        }
        else {
            return true;
        }
    }


    @Override
    public void showSuccessDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void showErrorDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}