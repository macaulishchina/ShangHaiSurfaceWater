package com.sinoyd.Code.Until;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

/**
 * Created by shenchuanjiang on 2017/4/11.
 * 获取手机权限  sdk23+  需要使用。
 */

public class Getpermission {
    // Storage Permissions

    private static boolean isPermissionRequested = false;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 8      * Checks if the app has permission to write to device storage
     * 9      *
     * 10      * If the app does not has permission then the user will be prompted to
     * 11      * grant permissions
     * 12      *
     * 13      * @param activity
     * 14
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permissionint
        int permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permission1 != PackageManager.PERMISSION_GRANTED
                || permission2 != PackageManager.PERMISSION_GRANTED
                ) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }


    public static void requestPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {
            isPermissionRequested = true;
            ArrayList<String> permissions = new ArrayList<>();
            if (ActivityCompat.checkSelfPermission(activity,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() == 0) {
                return;
            } else {
                activity.requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
            }
        }
    }



}
