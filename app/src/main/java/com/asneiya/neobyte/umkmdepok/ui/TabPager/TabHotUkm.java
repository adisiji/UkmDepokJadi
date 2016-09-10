package com.asneiya.neobyte.umkmdepok.ui.TabPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.model.umkm.ItemKategori;
import com.asneiya.neobyte.umkmdepok.ui.Search_act;
import com.asneiya.neobyte.umkmdepok.ui.adapter.AdapterKategori;
import com.asneiya.neobyte.umkmdepok.ui.util.aturKlik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neobyte on 8/24/2016.
 */

public class TabHotUkm extends Fragment {
    private View rv;
    private List<ItemKategori> ik = new ArrayList<>();
    private final aturKlik.kategori kliKat = new aturKlik.kategori() {
        @Override
        public void onItemClicked(ItemKategori item) {
            Intent i = new Intent(getActivity(), Search_act.class);
            i.putExtra("idKat",item.getId());
            startActivity(i);
        }
    };

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
    private static final int[] idKategori={1,2,3,4,5,9,9,9,9};
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
        addData();
        initViews();
        return rv;
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)rv.findViewById(R.id.cardKategori);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        AdapterKategori adapter = new AdapterKategori(this.getContext(), ik, kliKat);
        recyclerView.setAdapter(adapter);
    }

    private void addData(){
        for (int i=0;i<=8;i++){
            ItemKategori item = new ItemKategori();
            item.setNama(prgmNameList[i]);
            item.setId(idKategori[i]);
            item.setImage(prgmImages[i]);
            ik.add(item);
        }
    }
}