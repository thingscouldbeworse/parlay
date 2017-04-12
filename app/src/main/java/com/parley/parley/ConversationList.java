package com.parley.parley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ConversationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
        //listOfMessages.setClickable(true);
    }
}
