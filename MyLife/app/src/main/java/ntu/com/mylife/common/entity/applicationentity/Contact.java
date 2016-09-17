package ntu.com.mylife.common.entity.applicationentity;

import android.graphics.Bitmap;

/**
 * Created by micha on 9/17/2016.
 */
public class Contact {

    private String contactName;
    private Bitmap contactBitmap;

    public Contact(String contactName, Bitmap imageBitmap) {
        this.contactName = contactName;
        this.contactBitmap = imageBitmap;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Bitmap getContactBitmap() {
        return contactBitmap;
    }

    public void setContactBitmap(Bitmap contactBitmap) {
        this.contactBitmap = contactBitmap;
    }
}
