package ntu.com.mylife.common.service;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class AlertDialogService {

    private Context myContext;


    public AlertDialogService(Context context){
        this.myContext =  context;
    }

    public void showDialog(String messageToShown){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(myContext);
        builder1.setMessage(messageToShown);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
