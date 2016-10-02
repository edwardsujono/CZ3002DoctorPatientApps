package ntu.com.mylife.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.ContactOptionsController;

public class SubmitMedicalRecordView extends Fragment {

    private OnFragmentInteractionListener mListener;

    //declare your attribute here
    private EditText editTextMedicalReport,editTextDate;
    private TextView nameClick;
    private SharedPreferencesService sharedPreferencesService;
    private Button submitMedicalDescriptionButton;
    private ContactOptionsController controller;

    public SubmitMedicalRecordView() {
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
        View rootView =  inflater.inflate(R.layout.fragment_submit_medical_record_view, container, false);
        editTextMedicalReport = (EditText) rootView.findViewById(R.id.edit_text_medical_record_description);
        editTextDate = (EditText) rootView.findViewById(R.id.edit_text_date_medical_record);
        submitMedicalDescriptionButton = (Button) rootView.findViewById(R.id.submit_medical_report_button);
        nameClick = (TextView) rootView.findViewById(R.id.name_click_medical_record);
        sharedPreferencesService = new SharedPreferencesService(getActivity().getBaseContext());
        controller = new ContactOptionsController(getActivity());

        //set the name of textView in the fragment as the name that we clicked
        final String nameClicked = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.CURRENT_CLICK_CONTACT);
        final String currentDoctor = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_FULL_NANE);
        nameClick.setText(nameClicked);

        submitMedicalDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalRecord medicalRecord = new MedicalRecord(editTextDate.getText().toString(),editTextMedicalReport.getText().toString(),currentDoctor);
                controller.submitMedicalReport(medicalRecord);
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
