package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {
    TextView textViewwin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnerconfirm);

        textViewwin = (TextView) findViewById(R.id.confirmText2);
        Bundle bundleEx = getIntent().getExtras();

        System.out.println((String)bundleEx.get("pickedName"));
        if(textViewwin!=null) {
            textViewwin.setText((String)bundleEx.get("pickedName"));
        }
    }

    public void returnFunction(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
