package com.example.guesswhoaini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BtloginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btlogin);
    }

    public void returnFunction(View view)
    {


        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btloginFunction(View view){
        EditText myTextField =  findViewById(R.id.editText);
        String str = myTextField.getText().toString();

        Intent intent = new Intent(this,BtlogintestActivity.class);
        intent.putExtra("message",str);
        startActivity(intent);
    }
}
