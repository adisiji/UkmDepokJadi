package com.asneiya.neobyte.umkmdepok.ui.TabPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.ui.kategori.AdapterKategori;

/**
 * Created by neobyte on 8/24/2016.
 */

public class TabHotUkm extends Fragment {
    private View rv;
    private static final String [] prgmNameList={
            "Kuliner",
            "Jasa",
            "Handycraft",
            "Fashion",
            "Perbengkelan",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"};
    private static final String [] prgmImages={
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rv = inflater.inflate(R.layout.list_hot_ukm, container, false);
        initViews();
        return rv;
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)rv.findViewById(R.id.cardKategori);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        AdapterKategori adapter = new AdapterKategori(this.getContext(),prgmNameList,prgmImages);
        recyclerView.setAdapter(adapter);
    }
}