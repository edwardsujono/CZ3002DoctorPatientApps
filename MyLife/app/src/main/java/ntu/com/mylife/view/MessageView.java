package ntu.com.mylife.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.Message;

public class MessageView extends AppCompatActivity {

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView mMessageTextView;
        public TextView mRespondentTextView;
        public TextView mTimeTextView;

        public MessageViewHolder(View v) {
            super(v);
            mMessageTextView = (TextView)v.findViewById(R.id.messageRespondentTextView);
            mRespondentTextView = (TextView) v.findViewById(R.id.respondentTextView);
            mTimeTextView = (TextView) v.findViewById(R.id.timeTextView);
        }
    }

    private EditText mMessageEditText;

    private static String MESSAGE_CHILD = "Message";

    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if (mDatabaseReference == null) {
            Log.e("MessageView", "Error Leh");
        } else {
            Log.d("Inside Reference", mDatabaseReference.toString());
        }

        mMessageRecyclerView = (RecyclerView) findViewById(R.id.messageRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.message,
                MessageViewHolder.class,
                mDatabaseReference.child(MESSAGE_CHILD)) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.mMessageTextView.setText(model.getMessage());
                viewHolder.mRespondentTextView.setText(model.getSenderUsername());
                viewHolder.mTimeTextView.setText(model.getDate());
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

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(firebaseRecyclerAdapter);

        mMessageEditText = (EditText) findViewById(R.id.inputEditText);

    }

}
