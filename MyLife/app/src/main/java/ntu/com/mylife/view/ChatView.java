package ntu.com.mylife.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ntu.com.mylife.R;
import ntu.com.mylife.common.data.Message;
import ntu.com.mylife.controller.MessageRecyclerViewAdapter;

public class ChatView extends AppCompatActivity {

    private Button mSendButton;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    //private FirebaseRecyclerAdapter<Message, MessageRecyclerViewAdapter.ViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;

    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
    }


}
