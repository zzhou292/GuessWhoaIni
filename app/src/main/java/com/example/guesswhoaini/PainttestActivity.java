package com.example.guesswhoaini;
import com.example.guesswhoaini.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class PainttestActivity extends AppCompatActivity {

    private PaintView paintView;
    private int counter = 0;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painttest);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        displayChatMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.red:
                paintView.red();
                return true;
            case R.id.green:
                paintView.green();
                return true;
            case R.id.blue:
                paintView.blue();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onClickColorButton(){
        openColorDialog();
    }

    private void openColorDialog(){

    }

    private void displayChatMessages() {
        final ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages1);

        listOfMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override

            protected void populateView(View v, ChatMessage model, int position) {

                if (model.getType() != 1 && model != null) {
                    Log.d("out", String.valueOf(model.getType()));
                    // Get references to the views of message.xml
                    TextView messageText = (TextView) v.findViewById(R.id.message_text);
                    TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                    // Set their text
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());

                    // Format the date before showing it
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                            model.getMessageTime()));


                    scrollMyListViewToBottom(listOfMessages, adapter);
                }
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    private void scrollMyListViewToBottom(final ListView myListView, final Adapter myListAdapter) {
        if(counter == 0)
        {
            myListView.post(new Runnable() {
                @Override
                public void run() {
                    // Select the last row so it will scroll into view...
                    myListView.setSelection(myListAdapter.getCount() - 1);
                }
            });
            counter++;
        }
    }
}