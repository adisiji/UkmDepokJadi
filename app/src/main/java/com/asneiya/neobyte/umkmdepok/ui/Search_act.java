package com.asneiya.neobyte.umkmdepok.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.model.umkm.ContentUmkm;
import com.asneiya.neobyte.umkmdepok.ui.util.searchRetro;
import com.asneiya.neobyte.umkmdepok.ui.adapter.AdapterSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Search_act extends AppCompatActivity {

    private static final String url = "http://umkmdepok.16mb.com/show/";
    private ProgressDialog mProgressDialog;
    private RecyclerView rvSearch;
    private AdapterSearchResult adapt;
    private static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);
        mProgressDialog = new ProgressDialog(this);
        rvSearch = (RecyclerView) findViewById(R.id.RV_search_Act);
        Intent i = getIntent();
        x = i.getIntExtra("idKat", 0);
        Log.d("Intent = ", Integer.toString(x));
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        if (x != 0) {
            retroResult();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void retroResult() {
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        Gson gson = new GsonBuilder().create();
        //default network calls to be asynchronous, you need to use createWithScheduler()
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        searchRetro sR = retrofit.create(searchRetro.class);
        Observable<List<ContentUmkm>> rxobs = sR.getKategori(x);
        rxobs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ContentUmkm>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Sukses di rx ","good");
                        rvSearch.setAdapter(adapt);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        rvSearch.setLayoutManager(layoutManager);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        Log.e("Error ",e.getMessage());
                    }

                    @Override
                    public void onNext(List<ContentUmkm> contentUmkms) {
                        List<ContentUmkm> konten = new ArrayList<>(contentUmkms);
                        adapt = new AdapterSearchResult(getApplicationContext(), konten);
                    }
                });
    }
}
