package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        text = (TextView) findViewById(R.id.confirmText);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnerconfirm);


        Bundle bundleEx = getIntent().getExtras();

        if(bundleEx!=null) {
            text.setText((String)bundleEx.get("pickedName"));
        }
    }

    public void returnFunction(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
