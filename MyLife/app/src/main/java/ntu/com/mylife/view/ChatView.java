package ntu.com.mylife.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Chat;

import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.ChatCallback;
import ntu.com.mylife.controller.ChatController;
import ntu.com.mylife.controller.ChatRecyclerViewAdapter;


public class ChatView extends Fragment implements MyCallback {

    private ArrayList<Chat> chatList;
    private RecyclerView chatRecyclerView;
    private ChatRecyclerViewAdapter chatAdapter;
    private ChatController chatController;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";
    private String userId;
    private String userType;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_view, container, false);
        setHasOptionsMenu(false);

        //Initialize all variables
        chatList = new ArrayList<>();
        chatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        chatAdapter = new ChatRecyclerViewAdapter(chatList, getContext());

        //Setup the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        chatRecyclerView.setLayoutManager(layoutManager);
        chatRecyclerView.setItemAnimator(new DefaultItemAnimator());
        chatRecyclerView.setAdapter(chatAdapter);

        sharedPreferencesService = new SharedPreferencesService(getContext());
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, USER_TYPE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Reset chatlist
        chatList.clear();
        //Construct a new chat controller
        chatController = new ChatController(chatList, getContext(), this);
    }

    @Override
    public void callbackFunction(Object object) {
        ArrayList<ntu.com.mylife.common.entity.databaseentity.Chat> tmp = (ArrayList<ntu.com.mylife.common.entity.databaseentity.Chat>) object;

        Log.e("callbackFunction", tmp.get(0).getUsername1() + " " + userId);

        chatList.clear();

        for(Object o: tmp) {
            ntu.com.mylife.common.entity.databaseentity.Chat c1 = (ntu.com.mylife.common.entity.databaseentity.Chat)o;

            String username1 = c1.getUsername1();
            String username2 = c1.getUsername2();

            if(username1.equals(userId) || username2.equals(userId)) {
                String latestMessage = c1.getLatestMessage();
                String latestMessageTime = c1.getLatestMessageTime();
                String respondentName;

                if(username1.equals(userId)) {
                    respondentName = username2;
                }
                else {
                    respondentName = username1;
                }

                Chat c2 = new Chat(null, respondentName, respondentName, latestMessage, latestMessageTime);
                chatList.add(c2);
            }
        }

        Log.e("length", chatList.size() +"");

        chatAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
