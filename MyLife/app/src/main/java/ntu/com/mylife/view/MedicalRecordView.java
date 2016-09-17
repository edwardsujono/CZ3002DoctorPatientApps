package ntu.com.mylife.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.CurrentScheduleRecyclerViewAdaptor;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecord;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecordImpl;
import ntu.com.mylife.common.service.DatabaseUserScheduleDao;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.controller.MedicalRecordController;
import ntu.com.mylife.controller.MedicalRecordRecyclerViewAdaptor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MedicalRecordView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MedicalRecordView#} factory method to
 * create an instance of this fragment.
 */
public class MedicalRecordView extends Fragment implements MyCallback {

    //this class simply will show recycler view

    private OnFragmentInteractionListener mListener;
    private MedicalRecordController medicalRecordController;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;



    public MedicalRecordView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicalRecordController = new MedicalRecordController(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_medical_record_view, container, false);
        // instanstiate
        medicalRecordController.getListMedicalRecord();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.medical_record_recycler_view);
        MedicalRecordRecyclerViewAdaptor adaptor;
        ArrayList<MedicalRecord> ls = new ArrayList<MedicalRecord>();
        ls.add((new MedicalRecord("12:30 thursday","wow it is coughing","Edward Sujono")));
        adaptor = new MedicalRecordRecyclerViewAdaptor(ls);
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
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

    @Override
    public void callbackFunction(Object object) {
        //fill the recycler view after finish the execution
        ArrayList<MedicalRecord> listMedicalRecord =(ArrayList<MedicalRecord>) object;
        MedicalRecordRecyclerViewAdaptor adaptor;
        adaptor = new MedicalRecordRecyclerViewAdaptor(listMedicalRecord);
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
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
