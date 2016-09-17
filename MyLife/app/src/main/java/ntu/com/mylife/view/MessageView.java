package ntu.com.mylife.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ntu.com.mylife.R;

public class MessageView extends AppCompatActivity {

    private Button mSendButton;
    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    //private FirebaseRecyclerAdapter<Message, MessageRecyclerViewAdapter.ViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;

    private EditText mMessageEditText;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if(mDatabaseReference == null) {
            Log.e("MessageView", "Error Leh");
        }
        else {
            Log.d("Inside Reference", mDatabaseReference.toString());
        }





    }


}
