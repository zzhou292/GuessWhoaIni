package com.example.guesswhoaini;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class BtlogintestActivity extends AppCompatActivity {
    // use bluetooth adapter to connect other devices
    // following tutorial from https://www.tutorialspoint.com/android/android_bluetooth.htm
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btlogintest);

        TextView textView2 = (TextView) findViewById(R.id.usernameBT);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView2.setText("Hello "+str);

        // initialize bluetooth adapter
        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listview);
    }


}
