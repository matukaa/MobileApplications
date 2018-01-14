package com.freakz.matukaa.lab4;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Matukaa on 2018-01-15.
 */

public class MShareFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMInstanceIDService", "Refreshed token: " + refreshedToken);
    }
}
