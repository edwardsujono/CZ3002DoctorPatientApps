package ntu.com.mylife.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;


/**
 * Created by LENOVO on 13/08/2016.
 */
public class NavigationDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerViewAdapter.ViewHolder>{

    private Context myContext;
    private ArrayList<String> listText;
    private ArrayList<Bitmap> listImageView;


    public NavigationDrawerRecyclerViewAdapter(Context context, ArrayList<String> listText, ArrayList<Bitmap> listImageView){
        this.myContext= myContext;
        this.listText = listText;
        this.listImageView = listImageView;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_row,parent,false);
        return new ViewHolder(itemView);
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iconImage;
        public TextView textIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = (ImageView) itemView.findViewById(R.id.icon_navigation_drawer);
            textIcon  = (TextView) itemView.findViewById(R.id.text_navigation_drawer);
        }
    }

}
