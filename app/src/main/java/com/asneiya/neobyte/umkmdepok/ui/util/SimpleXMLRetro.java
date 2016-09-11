package com.asneiya.neobyte.umkmdepok.ui.util;

import android.app.Application;

import com.asneiya.neobyte.umkmdepok.model.RSS.Rss;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

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

    //default network calls to be asynchronous, you need to use createWithScheduler()
    private RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

    private void initializeFeedApi(){
        Retrofit feedRestAdapter = new Retrofit.Builder()
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("http://ukmdepok.co.id/")
                .build();

        siapi = feedRestAdapter.create(SiteAPI.class);
    }


    public interface SiteAPI{
        @GET("feed")
        Observable<Rss> getfeeedItems();
    }

}
