package ntu.com.mylife.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.ContactOptionsController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactOptionsView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactOptionsView#} factory method to
 * create an instance of this fragment.
 */
public class ContactOptionsView extends Fragment {

    private OnFragmentInteractionListener mListener;

    //declare our own attributes
    private Button submitMedicalReportButton,createReminderButton,startChatButton;

    public ContactOptionsView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_options, container, false);
        submitMedicalReportButton = (Button) rootView.findViewById(R.id.submit_medical_report_button);
        createReminderButton = (Button) rootView.findViewById(R.id.create_reminder_button);
        startChatButton = (Button) rootView.findViewById(R.id.start_chat_button);

        final FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        submitMedicalReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.layout.activity_main_page_view, new SubmitMedicalRecordView());
                ft.commit();
            }
        });

        createReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        startChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
        public void onFragmentInteraction(Uri uri);
    }

}
