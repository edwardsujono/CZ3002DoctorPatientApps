package ntu.com.mylife.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Message;
import ntu.com.mylife.controller.MessageCallback;
import ntu.com.mylife.controller.MessageController;
import ntu.com.mylife.controller.MessageRecyclerViewAdapter;

public class MessageView extends AppCompatActivity implements MessageCallback {

    private ImageButton mSendButton;
    //private FirebaseRecyclerAdapter<Message, MessageRecyclerViewAdapter.ViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;
    private EditText mMessageEditText;

    private ArrayList<Message> messageList;
    private RecyclerView mMessageRecyclerView;
    private MessageRecyclerViewAdapter mMessageAdapter;
    private MessageController messageController;

    private String respondentUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        Intent intent = getIntent();
        respondentUsername = intent.getStringExtra("respondentUsername");

        //Initialize all variable
        Firebase.setAndroidContext(this);
        messageList = new ArrayList<>();
        mMessageRecyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        mMessageAdapter = new MessageRecyclerViewAdapter(messageList);

        //Setup the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mMessageRecyclerView.setLayoutManager(layoutManager);
        mMessageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMessageRecyclerView.setAdapter(mMessageAdapter);

        mMessageEditText = (EditText) findViewById(R.id.input_edittext);
        mSendButton = (ImageButton) findViewById(R.id.send_button);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //Reset chatlist
        messageList.clear();
        //Construct a new chat controller
        messageController = new MessageController(respondentUsername, messageList, getApplicationContext(), this);
    }

    public void messageCallback() {
        mMessageAdapter.notifyDataSetChanged();
    }

    private void sendMessage() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mMessageEditText.getWindowToken(), 0);

        String messageContent = mMessageEditText.getText().toString();
        if (messageContent.length() == 0) {
            return;
        }

        //Add the message to the server (via messageManager)
        messageController.addOutgoingMessage(respondentUsername, messageContent);
        //Reset the text
        mMessageEditText.setText(null);
    }


}
