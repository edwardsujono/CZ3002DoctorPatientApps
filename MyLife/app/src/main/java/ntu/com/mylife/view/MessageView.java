package ntu.com.mylife.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.Chat;
import ntu.com.mylife.common.entity.databaseentity.Message;
import ntu.com.mylife.common.service.DatabaseDaoChat;
import ntu.com.mylife.common.service.DatabaseDaoChatImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.MessageCallback;
import ntu.com.mylife.controller.MessageController;
import ntu.com.mylife.controller.MessageRecyclerViewAdapter;

public class MessageView extends AppCompatActivity implements MessageCallback {

    private ImageButton mSendButton;
    //private FirebaseRecyclerAdapter<Message, MessageRecyclerViewAdapter.ViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;
    private EditText mMessageEditText;

    private ArrayList<Message> messageList;
    private MessageRecyclerViewAdapter mMessageAdapter;
    private MessageController messageController;

    private String respondentUsername;
    private boolean chatExist;

    private static String MESSAGE_CHILD = "Message";
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private String userId;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView mMessageTextView;
        public TextView mRespondentTextView;
        public TextView mTimeTextView;
        public LinearLayout mLinearLayout;

        public MessageViewHolder(View v) {
            super(v);
            mMessageTextView = (TextView)v.findViewById(R.id.messageRespondentTextView);
            mRespondentTextView = (TextView) v.findViewById(R.id.respondentTextView);
            mTimeTextView = (TextView) v.findViewById(R.id.timeTextView);
            mLinearLayout = (LinearLayout) v.findViewById(R.id.bubble);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);//disable title

        sharedPreferencesService = new SharedPreferencesService(this);
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);

        Intent intent = getIntent();
        respondentUsername = intent.getStringExtra("respondentUsername");

        chatExist = intent.getBooleanExtra("chatExist", true);

        if(!chatExist) {
            Chat c = new Chat(userId, respondentUsername, "", "");

            DatabaseDaoChat db = new DatabaseDaoChatImpl();
            try {
                db.addData(c);
            } catch (Exception e) {
                Log.e("addData", "Cannot add");
            }
        }

        getSupportActionBar().setTitle(respondentUsername);


        //Initialize all variable
        //Firebase.setAndroidContext(this);
        //messageList = new ArrayList<>();
        mMessageRecyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        // mMessageAdapter = new MessageRecyclerViewAdapter(messageList);

        //Setup the layout manager
        mLinearLayoutManager = new LinearLayoutManager(this);
        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mMessageRecyclerView.setAdapter(mMessageAdapter);

        mLinearLayoutManager.setStackFromEnd(true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        if (mDatabaseReference == null) {
            Log.e("MessageView", "Error Leh");
        } else {
            Log.d("Inside Reference", mDatabaseReference.toString());
        }

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.message,
                MessageViewHolder.class,
                mDatabaseReference.child(MESSAGE_CHILD).orderByChild("date")) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                if ((model.getSenderUsername().equals(userId) && model.getReceiverUsername().equals(respondentUsername)) ||
                                (model.getSenderUsername().equals(respondentUsername) && model.getReceiverUsername().equals(userId)))
                {
                    viewHolder.mLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mMessageTextView.setText(model.getMessage());
                    viewHolder.mRespondentTextView.setText(model.getSenderUsername());
                    viewHolder.mTimeTextView.setText(model.getDate());
                }
                else {
                    viewHolder.mLinearLayout.setVisibility(View.GONE);
                }
            }


        };

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int messageCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisiblePosition == -1 ||
                        (positionStart >= (messageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setAdapter(firebaseRecyclerAdapter);

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
        //messageList.clear();
        //Construct a new chat controller
        //messageController = new MessageController(respondentUsername, messageList, getApplicationContext(), this);
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

        Date date = new Date();

        Message m = new Message(respondentUsername, userId, messageContent, date.getHours() + ":" + date.getMinutes());

        mDatabaseReference.child(MESSAGE_CHILD)
                .push().setValue(m);

        //Add the message to the server (via messageManager)
        //messageController.addOutgoingMessage(respondentUsername, messageContent);
        //Reset the text
        mMessageEditText.setText("");
    }


}
