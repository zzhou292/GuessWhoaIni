package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BtlogintestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btlogintest);

        TextView textView2 = (TextView) findViewById(R.id.usernameBT);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView2.setText("Hello "+str);
    }


}
