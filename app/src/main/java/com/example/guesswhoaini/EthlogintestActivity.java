package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EthlogintestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethlogintest);

        TextView textView2 = (TextView) findViewById(R.id.usernameETH);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView2.setText("Hello "+str);
    }
}
