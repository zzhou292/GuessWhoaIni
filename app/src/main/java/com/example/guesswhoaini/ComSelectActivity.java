package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ComSelectActivity extends AppCompatActivity{

    @Override
    protected void onCreate  (Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comselect);
    }


    public void returnFunction(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btloginFunction(View view)
    {
        Intent intent = new Intent(this,BtloginActivity.class);
        startActivity(intent);
    }

    public void ethloginFunction(View view)
    {
        Intent intent = new Intent(this,EthloginActivity.class);
        startActivity(intent);
    }
}
