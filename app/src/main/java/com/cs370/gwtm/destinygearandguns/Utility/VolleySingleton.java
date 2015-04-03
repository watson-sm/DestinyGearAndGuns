package com.cs370.gwtm.destinygearandguns.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
/**
 * Created by Kiladre on 4/2/15.
 * sdfsdfs
 */
public class VolleySingleton {

    // Prototypes

    private static VolleySingleton ourInstance = null;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    private ImageLoader mImageLoader;


    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                }); // end mImageLoader = new ImageLoader
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if(ourInstance == null) {
            ourInstance = new VolleySingleton(context);
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            // do stuff
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
