package ntu.com.mylife.controller;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.OnItemClickListener;
import ntu.com.mylife.common.service.SharedPreferencesService;
import ntu.com.mylife.view.CalendarView;
import ntu.com.mylife.view.ContactContainerView;
import ntu.com.mylife.view.ContactView;
import ntu.com.mylife.view.HomeView;
import ntu.com.mylife.view.MedicalRecordView;
import ntu.com.mylife.view.ProfileView;


/**
 * Created by LENOVO on 13/08/2016.
 */
public class NavigationDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerViewAdapter.ViewHolder>{

    private Context myContext;
    private ArrayList<String> listText;
    private ArrayList<Bitmap> listImageView;
    private AppCompatActivity myActivity;
    private SharedPreferencesService sharedPreferencesService;
    private String type = "";


    public NavigationDrawerRecyclerViewAdapter(AppCompatActivity activity, ArrayList<String> listText, ArrayList<Bitmap> listImageView){
        this.listText = listText;
        this.listImageView = listImageView;
        this.myActivity = activity;
        sharedPreferencesService = new SharedPreferencesService(activity);
        type = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,"userType");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_row,parent,false);
        return new ViewHolder(itemView,myContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = listText.get(position);
        Bitmap bitmap = listImageView.get(position);
        holder.textIcon.setText(text);
        holder.iconImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return listText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView iconImage;
        public TextView textIcon;
        private Context context;
        public ViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            iconImage = (ImageView) itemView.findViewById(R.id.icon_navigation_drawer);
            textIcon  = (TextView) itemView.findViewById(R.id.text_navigation_drawer);
        }

        @Override
        public void onClick(View v) {
            Log.i("position: ",""+getPosition());
            final FragmentTransaction ft = myActivity.getSupportFragmentManager().beginTransaction();
           if(type.equals(UserType.Type.PATIENT))
            switch(getPosition()){
                case 0:
                    ft.replace(R.id.fragment_transition_main_page, new HomeView());
                    break;
                case 1:
                    ft.replace(R.id.fragment_transition_main_page, new ProfileView());
                    break;
                case 2:
                    ft.replace(R.id.fragment_transition_main_page, new MedicalRecordView());
                    break;
                case 3:
                    //ft.replace(R.id.fragment_transition_main_page, new ContactView());
                    Intent intent = new Intent(myActivity, ContactContainerView.class);
                    myActivity.startActivity(intent);

                    break;
                case 4:
                    ft.replace(R.id.fragment_transition_main_page, new CalendarView());

            }
            else
               switch(getPosition()){
                   case 0:
                       ft.replace(R.id.fragment_transition_main_page, new HomeView());
                       break;
                   case 1:
                       ft.replace(R.id.fragment_transition_main_page, new ProfileView());
                       break;
                   case 2:
                       ft.replace(R.id.fragment_transition_main_page, new ContactView());
                       break;
                   case 3:
                       ft.replace(R.id.fragment_transition_main_page, new CalendarView());
                       break;

               }
            ft.commit();
            View mainDrawerView = myActivity.findViewById(R.id.drawer_navigation_drawer);
            DrawerLayout drawer = (DrawerLayout) mainDrawerView;
            drawer.closeDrawer(GravityCompat.START);
        }
    }

}
