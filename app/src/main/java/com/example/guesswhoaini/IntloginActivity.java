package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IntloginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intlogin);
    }


    public void returnFunction(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void regFunction(View view)
    {
        Intent intent = new Intent(this,RegActivity.class);
        startActivity(intent);
    }
}
