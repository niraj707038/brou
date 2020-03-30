package com.info.brochureatmobile.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {

    public static boolean checkNetwork(Context context) {
        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        {
            if (networkInfo != null && networkInfo.isConnected() == true)
                return true;
        }
        return false;
    }
}
