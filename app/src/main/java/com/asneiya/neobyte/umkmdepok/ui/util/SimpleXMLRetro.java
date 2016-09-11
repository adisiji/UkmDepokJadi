package com.asneiya.neobyte.umkmdepok.ui.util;

import android.app.Application;

import com.asneiya.neobyte.umkmdepok.model.RSS.Rss;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

/**
 * Created by neobyte on 9/10/2016.
 */
public class SimpleXMLRetro extends Application {
    private SiteAPI siapi;

    public SiteAPI getSiteApi() {
        return siapi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeFeedApi();
    }

    private void initializeFeedApi(){
        Retrofit feedRestAdapter = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("http://ukmdepok.co.id/")
                .build();

        siapi = feedRestAdapter.create(SiteAPI.class);
    }


    public interface SiteAPI{
        @GET("feed")
        Call<Rss> getfeeedItems();
    }

}
