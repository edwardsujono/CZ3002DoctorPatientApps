package ntu.com.mylife.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Chat;
import ntu.com.mylife.controller.ChatCallback;
import ntu.com.mylife.controller.ChatController;
import ntu.com.mylife.controller.ChatRecyclerViewAdapter;

public class ChatView extends Fragment implements ChatCallback {

    private ArrayList<Chat> chatList;
    private RecyclerView chatRecyclerView;
    private ChatRecyclerViewAdapter chatAdapter;
    private ChatController chatController;

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

    public void chatCallback() {
        chatAdapter.notifyDataSetChanged();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
