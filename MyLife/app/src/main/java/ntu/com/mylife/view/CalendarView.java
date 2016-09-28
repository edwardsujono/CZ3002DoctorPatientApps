package ntu.com.mylife.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.CurrentScheduleRecyclerViewAdaptor;
import ntu.com.mylife.common.entity.databaseentity.DaySchedule;
import ntu.com.mylife.common.service.DatabaseDaoUserScheduleImpl;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.controller.MedicalRecordRecyclerViewAdaptor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarView extends Fragment implements MyCallback, OnDateSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use library from https://github.com/prolificinteractive/material-calendarview
     */
    private MaterialCalendarView mCalendarView;

    private RecyclerView mRecyclerView;
    private CurrentScheduleRecyclerViewAdaptor adaptor;
    private LinearLayoutManager mLayoutManager;
    private DatabaseDaoUserScheduleImpl dbSchedule;
    private SharedPreferencesService sharedPreferencesService;

    public CalendarView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarView.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarView newInstance(String param1, String param2) {
        CalendarView fragment = new CalendarView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesService   = new SharedPreferencesService(getActivity());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.current_schedule_recyler_view);

        mCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);

        Date date = new Date();
        mCalendarView.setDateSelected(date, true);

        ArrayList<String> t = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();

        s.add("Coding");
        s.add("Coding");
        s.add("Die die Coding");

        t.add("00:00");
        t.add("01:00");
        t.add("02:00");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.current_schedule_recyler_view);
        adaptor = new CurrentScheduleRecyclerViewAdaptor(t, s);
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    /**
     * This is callback when a date is selected
     * @param widget
     * @param date
     * @param selected
     */
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

    @Override
    public void callbackFunction(Object object) {
        HashMap hashReturned = (HashMap) object;
        CurrentScheduleRecyclerViewAdaptor adaptor;
        adaptor = new CurrentScheduleRecyclerViewAdaptor((ArrayList)hashReturned.get("listTime"),(ArrayList)hashReturned.get("listMessage"));
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
