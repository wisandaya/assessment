package com.example.assessment.Global;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GlobalFunctions {


    public static boolean EmailValidaion(EditText email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() || email.getText().toString().isEmpty()) {
            email.setError("Invalid Email Address");
            return false;
        } else {
            return true;
        }
    }

    public static boolean PhoneValidaion(EditText phone) {
        if (!phone.getText().toString().startsWith("09") || phone.getText().toString().length() != 11 || phone.getText().toString().isEmpty()) {
            phone.setError("Invalid Phone Number");
            return false;
        } else {
            return true;
        }
    }

    public static String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    public static boolean ageValidaion(EditText age) {
        if (age.getText().toString().isEmpty()||Integer.parseInt(age.getText().toString())<18) {
            age.setError("Age 18 Above is Accepted");
            return false;
        } else {
            return true;
        }
    }


}

