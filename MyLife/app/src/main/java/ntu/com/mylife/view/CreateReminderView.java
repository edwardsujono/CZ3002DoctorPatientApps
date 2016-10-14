package ntu.com.mylife.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import ntu.com.mylife.R;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.DaySchedule;
import ntu.com.mylife.common.entity.databaseentity.TimeSchedule;
import ntu.com.mylife.common.entity.databaseentity.UserSchedule;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.CreateReminderController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateReminderView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateReminderView#} factory method to
 * create an instance of this fragment.
 */
public class CreateReminderView extends Fragment {

    private OnFragmentInteractionListener mListener;
    //declared all the attribute here
    private EditText editTextDate,editTextTime,editTextDescription;
    private TextView nameTextView;
    private Button buttonCreateReminder;
    private SharedPreferencesService sharedPreferencesService;
    private String userId;
    private CreateReminderController reminderController;
    private Context myContext;

    public CreateReminderView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesService = new SharedPreferencesService(getActivity());
        userId = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.CURRENT_CLICK_CONTACT);
        reminderController = new CreateReminderController(getActivity());
        myContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_reminder_view, container, false);
        editTextDate = (EditText)rootView.findViewById(R.id.edit_text_date_createReminder);
        editTextTime = (EditText)rootView.findViewById(R.id.edit_text_time_createReminder);
        editTextDescription = (EditText) rootView.findViewById(R.id.edit_text_description_createReminder);
        buttonCreateReminder = (Button) rootView.findViewById(R.id.create_reminder_button);
        nameTextView = (TextView) rootView.findViewById(R.id.name_click_create_reminder);

        nameTextView.setText(userId);

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
                new DatePickerDialog(myContext,date,myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                myCalendar.set(Calendar.MINUTE,minute);
                String hourAdded = hourOfDay+"";
                String minuteAdded = minute+"";
                if((""+hourOfDay).length() == 1){
                    hourAdded = "0"+hourOfDay;
                }
                if(minuteAdded.length() == 1){
                    minuteAdded = "0"+minute;
                }
                editTextTime.setText(hourAdded+":"+minuteAdded);
            }
        };


        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(myContext,time,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });


        buttonCreateReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<TimeSchedule> listTimeSchedule = new ArrayList<TimeSchedule>();
                    UserSchedule userSchedule = new UserSchedule();
                    userSchedule.setUserId(userId);
                    String date = myCalendar.get(Calendar.DAY_OF_MONTH) + "-" + myCalendar.get(Calendar.MONTH) + "-" + myCalendar.get(Calendar.YEAR);
                    String time = editTextTime.getText().toString();
                    String message = editTextDescription.getText().toString();
                    long futureTimeMillis = myCalendar.getTimeInMillis();
                    DaySchedule daySchedule = new DaySchedule(date,time,userId,message,futureTimeMillis);
                    reminderController.addToDatabaseReminder(daySchedule);

                }catch(Exception e){
                    Log.e("error on create",e.getMessage());
                }
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
