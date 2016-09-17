package ntu.com.mylife.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ntu.com.mylife.R;
import ntu.com.mylife.common.service.DatabaseDaoUser;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileView#  } factory method to
 * create an instance of this fragment.
 */
public class ProfileView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private OnFragmentInteractionListener mListener;
    private EditText textFullName,textAge,textDob,textBloodType,textAllergy,textMedicalRecord;
    private Button buttonSave;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName",KEY_FULL_NANE= "fullName",KEY_AGE = "age", KEY_DOB = "dob" , KEY_BLOOD = "blood" , KEY_ALLERGY = "allergy" , KEY_MEDICAL_RECORD= "medicalRecord", NAME_SHARED_PREFERENCES = "UserSharedPreferences";

    public ProfileView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesService = new SharedPreferencesService(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  =  inflater.inflate(R.layout.fragment_profile_view, container, false);
        //all attribute is defined here
        textFullName = (EditText) rootView.findViewById(R.id.fullname_profile_editText);
        textAge      = (EditText) rootView.findViewById(R.id.age_profile_editText);
        textDob     = (EditText) rootView.findViewById(R.id.dob_profile_editText);
        textBloodType = (EditText) rootView.findViewById(R.id.bloodType_profile_editText);
        textAllergy = (EditText) rootView.findViewById(R.id.allergy_profile_editText);
        textMedicalRecord = (EditText) rootView.findViewById(R.id.medicalCondition_profile_editText);
        buttonSave        =  (Button) rootView.findViewById(R.id.button_save_profile);

        String currentUserName =   sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);
        loadEditText();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textFullName.setText(textFullName.getText().toString());
                textAge.setText(textAge.getText());
                textDob.setText(textDob.getText());
                textBloodType.setText(textBloodType.getText());
                textAllergy.setText(textAllergy.getText());
                textMedicalRecord.setText(textMedicalRecord.getText());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_FULL_NANE,textFullName.getText().toString());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_AGE,textAge.getText().toString());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_DOB,textDob.getText().toString());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_BLOOD,textBloodType.getText().toString());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_ALLERGY,textAllergy.getText().toString());
                sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_MEDICAL_RECORD,textMedicalRecord.getText().toString());
                Toast.makeText(getActivity(),"Contact is updated",Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


    public void loadEditText(){
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_FULL_NANE) != null){
            textFullName.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_FULL_NANE));
        }
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_DOB) != null){
            textDob.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_DOB));
        }
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_AGE) != null){
            textAge.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_AGE));
        }
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_BLOOD) != null){
            textBloodType.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_BLOOD));
        }
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_ALLERGY) != null){
            textAllergy.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_ALLERGY));
        }
        if(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_MEDICAL_RECORD) != null){
            textMedicalRecord.setText(sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_MEDICAL_RECORD) );
        }

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
