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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.Chat;
import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;
import ntu.com.mylife.common.entity.databaseentity.Message;
import ntu.com.mylife.common.service.ChatDao;
import ntu.com.mylife.common.service.ChatDaoImpl;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.ControllerConfiguration;
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

    private String respondentUserId;
    private boolean chatExist;

    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter;
    private ChatDao chatDao;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private String userId;
    private SharedPreferencesService sharedPreferencesService;

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
        userId = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USER);

        Intent intent = getIntent();
        respondentUserId = intent.getStringExtra(ControllerConfiguration.CHAT_RESPONDENTUSERID);

        chatExist = intent.getBooleanExtra(ControllerConfiguration.CHAT_CHATEXIST, true);

        chatDao = new ChatDaoImpl();

        if(!chatExist) {
            Chat c = new Chat(userId, respondentUserId, "", "");

            try {
                chatDao.addData(c);
            } catch (Exception e) {
                Log.e("addData", "Cannot add");
            }
        }

        getSupportActionBar().setTitle(respondentUserId);


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
                mDatabaseReference.child(DatabaseConfiguration.MESSAGE).orderByChild("date")) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                if(model == null)
                    return;

                if ((model.getSenderUserId().equals(userId) && model.getReceiverUserId().equals(respondentUserId)) ||
                                (model.getSenderUserId().equals(respondentUserId) && model.getReceiverUserId().equals(userId)))
                {
                    viewHolder.mLinearLayout.setVisibility(View.VISIBLE);
                    viewHolder.mMessageTextView.setText(model.getMessage());
                    viewHolder.mRespondentTextView.setText(model.getSenderUserId());




                    Date date = new Date(Long.parseLong(model.getDate()));

                    viewHolder.mTimeTextView.setText(date.getHours() + ":" + date.getMinutes());
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


        String numSecond = String.valueOf(System.currentTimeMillis());

        Message m = new Message(respondentUserId, userId, messageContent, numSecond);

        mDatabaseReference.child(DatabaseConfiguration.MESSAGE)
                .push().setValue(m);

        //Add the message to the server (via messageManager)
        //messageController.addOutgoingMessage(respondentUsername, messageContent);
        //Reset the text
        mMessageEditText.setText("");
    }


}
