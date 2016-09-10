package org.foodrev.direct_sms_example;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by magulo on 9/10/16.
 */
public class SmsSender extends AppCompatActivity{
    private String phoneNumber = null;
    private String message = null;
    private Context context = null;
    private Activity activity = null;

    public SmsSender(String phoneNumber, String message, Context context, Activity activity) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.context = context;
    }

    public void sendSms() {
        int hasSendSMSPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.SEND_SMS);
        if (hasSendSMSPermission!= PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.SEND_SMS};
            ActivityCompat.requestPermissions(activity, permissions, 1);
        }
        else {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumber, null, message, null, null);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(context, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;

    }
}
