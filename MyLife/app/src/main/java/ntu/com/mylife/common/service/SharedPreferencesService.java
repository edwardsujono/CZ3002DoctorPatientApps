package ntu.com.mylife.common.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LENOVO on 11/09/2016.
 */
public class SharedPreferencesService {

    private Context myContext;
    public SharedPreferencesService(Context context){
        this.myContext = context;
    }


    public void saveToSharedPreferences(String preferencesName,String key ,String value){
        SharedPreferences.Editor editor = myContext.getSharedPreferences(preferencesName,myContext.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();
    }


    public String getDataFromSharedPreferences(String preferencesName,String key){
        SharedPreferences prefs = myContext.getSharedPreferences(preferencesName, myContext.MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        return restoredText;
    }


}
