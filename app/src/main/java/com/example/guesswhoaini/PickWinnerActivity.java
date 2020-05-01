package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class PickWinnerActivity extends AppCompatActivity {
    EditText pickedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickwinner);

    }


    public void confirmFunction(View view)
    {
        pickedName = findViewById(R.id.pickedName);
        FirebaseDatabase.getInstance("https://guesswhoa-322a1-414eb.firebaseio.com/")
                .getReference()
                .push()
                .setValue(pickedName.getText().toString()
                );



        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }



}