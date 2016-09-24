package ntu.com.mylife.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.controller.ContactController;
import ntu.com.mylife.controller.ContactRecyclerViewAdapter;

public class ContactView extends Fragment implements MyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mContactRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ContactRecyclerViewAdapter contactRecyclerViewAdapter;

    private ArrayList<Contact> contactList;

    private ContactController contactController;


    public ContactView() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_view, container, false);

        mContactRecyclerView = (RecyclerView) v.findViewById(R.id.contactRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        contactList = new ArrayList<>();

        contactRecyclerViewAdapter = new ContactRecyclerViewAdapter(contactList, getActivity().getBaseContext(),getActivity());
        mContactRecyclerView.setLayoutManager(linearLayoutManager);
        mContactRecyclerView.setAdapter(contactRecyclerViewAdapter);

        contactController  = new ContactController(contactList, getActivity(), this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactController.fetchContact();
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

    @Override
    public void callbackFunction(Object object) {
        contactList.clear();
        for(Contact c: (ArrayList<Contact>)object) {
            contactList.add(c);
            Log.d("ContactView", c.getContactName());
        }
        contactRecyclerViewAdapter.notifyDataSetChanged();
    }
}
