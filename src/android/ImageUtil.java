package com.tyrion.plugin.imgutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

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

            PluginResult result = new PluginResult(PluginResult.Status.OK, imageByn, true);
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);
            return true;
        }
        return false;
    }

    public static byte[] Image2Bytes(String path) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(path,newOpts);
        return Bitmap2Bytes(bitmap);
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] img64 =  Base64.encode(baos.toByteArray(), Base64.DEFAULT);
        return img64;
    }
}
