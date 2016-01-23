package com.tyrion.plugin.imgutil;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * This class echoes a string called from JavaScript.
 */
public class ImageUtil extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("toBinary")) {
            String path = args.getString(0);
//            Log.e("path", path);
            byte[] imageByn = Image2Bytes(path);

            PluginResult result = new PluginResult(PluginResult.Status.OK, imageByn);
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);
            return true;
        }
        return false;
    }

    public static byte[] Image2Bytes(String path) {
//        Log.e("path", path);
        File f = new File(path);
        byte b[] = null;
        try {
            InputStream in = new FileInputStream(f);
            b=new byte[(int)f.length()];
            in.read(b);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

}
