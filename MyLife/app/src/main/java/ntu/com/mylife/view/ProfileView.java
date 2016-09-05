package ntu.com.mylife.view;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ntu.com.mylife.R;


public class ProfileView extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextView todaySchedule,todayNumberSchedule,todayMonthSchedule;

    public ProfileView() {
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
        View rootView  =  inflater.inflate(R.layout.fragment_profile_view, container, false);
        todaySchedule = (TextView) rootView.findViewById(R.id.today_fragment_day_main_page);
        todayNumberSchedule = (TextView) rootView.findViewById(R.id.today_fragment_number_day_main_page);
        todayMonthSchedule = (TextView) rootView.findViewById(R.id.today_fragment_month_main_page);
        todaySchedule.setTextColor(Color.parseColor("#009688"));
        todayNumberSchedule.setTextColor(Color.parseColor("#009688"));
        todayMonthSchedule.setTextColor(Color.parseColor("#009688"));
        instanstiateTodaySchedule();
        return rootView;

    }


    private  void instanstiateTodaySchedule(){

        Calendar rightNow = Calendar.getInstance();
        String month =  new SimpleDateFormat("MMMM").format(rightNow.getTime());
        String weekDay = new SimpleDateFormat("EEEE", Locale.US).format(rightNow.getTime());
        int dayNumber = rightNow.getTime().getDate();
        todaySchedule.setText(weekDay);
        todayNumberSchedule.setText(dayNumber+"");
        todayMonthSchedule.setText(month);

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
