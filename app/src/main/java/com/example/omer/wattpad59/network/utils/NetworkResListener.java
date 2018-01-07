package com.example.omer.wattpad59.network.utils;

import android.graphics.Bitmap;

import org.json.JSONObject;

/**
 * Created by Yarden-PC on 07-Jan-18.
 */

public interface NetworkResListener {

    /**
     * callback method which called when the resources update is started
     */
    public void onPreUpdate();

    /**
     * callback method which called after resources update is finished
     * @param  res  - the data
     * @param status - the status of the update process
     */
    public void onBookUpdate(byte[] res, ResStatus status);

    public void onBookUpdate(JSONObject res, ResStatus status);

    public void onBookUpdate(Bitmap res, ResStatus status);

}
