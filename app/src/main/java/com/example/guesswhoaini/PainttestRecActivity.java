package com.example.guesswhoaini;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class PainttestRecActivity extends AppCompatActivity {
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painttest);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    //database connection code
     /*FirebaseDatabase.getInstance("https://guesswhoa-322a1-58abe.firebaseio.com/")
             .getReference()
             .push()
            .setValue(new LocDBMes(x,y,colorIndicator
                      )*/

}
