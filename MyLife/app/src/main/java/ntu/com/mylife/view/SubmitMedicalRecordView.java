package ntu.com.mylife.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import ntu.com.mylife.R;
import ntu.com.mylife.common.service.SharedPreferencesKey;
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
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        //set the name of textView in the fragment as the name that we clicked
        final String nameClicked = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.CURRENT_CLICK_CONTACT);
        final String currentDoctor = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_FULL_NAME);
        nameClick.setText(nameClicked);

        //EditTextDate added
        //showing DatePicket when editText is click
        final Calendar myCalendar = Calendar.getInstance();


        //for DATE
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                editTextDate.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
            }
        };

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date,myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH)+1,
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submitMedicalDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalRecord medicalRecord = new MedicalRecord(editTextDate.getText().toString(),editTextMedicalReport.getText().toString(),currentDoctor);
                controller.submitMedicalReport(medicalRecord);
                //go to the previous fragment
                ft.replace(R.id.fragment_transition_main_page,new ContactOptionsView());
                ft.commit();
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
