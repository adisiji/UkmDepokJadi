package com.asneiya.neobyte.umkmdepok.ui.util;

import com.asneiya.neobyte.umkmdepok.model.umkm.ContentUmkm;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neobyte on 9/11/2016.
 */
public interface searchRetro {

        @GET("search.php")
        Observable<List<ContentUmkm>> getKategori(@Query("kat") int kategori);
}
