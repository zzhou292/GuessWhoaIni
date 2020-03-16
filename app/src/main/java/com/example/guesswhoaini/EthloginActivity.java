package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EthloginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethlogin);
    }

    public void returnFunction(View view)
    {



        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }

    public void ethloginFunction(View view){
        EditText myTextField =  findViewById(R.id.editText2);
        String str = myTextField.getText().toString();

        Intent intent = new Intent(this,EthlogintestActivity.class);
        intent.putExtra("message",str);
        startActivity(intent);
    }
}
