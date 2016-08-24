package com.asneiya.neobyte.umkmdepok;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by neobyte on 8/23/2016.
 */
public class singleVolley {
    public static final String TAG = singleVolley.class.getName();
    private RequestQueue mRequestQueue;
    private static singleVolley mInstance;
    private static Context mContext;

    private singleVolley(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized singleVolley getInstance(Context context) {
        if (mInstance==null){
            mInstance=new singleVolley(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel() {
        mRequestQueue.cancelAll(TAG);
    }
}
