package com.asneiya.neobyte.umkmdepok.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.intface.OnItemClickListener;
import com.asneiya.neobyte.umkmdepok.model.umkm.ContentUmkm;
import com.asneiya.neobyte.umkmdepok.ui.search.Search_act;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neobyte on 8/24/2016.
 */
public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolder> {

        List<ContentUmkm> konten = new ArrayList<>();
        Context context;

        private OnItemClickListener listener;

        public AdapterSearchResult(Context context, List<ContentUmkm> data) {
            konten = data;
            this.context=context;
        }


    @Override
    public AdapterSearchResult.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_search_result, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSearchResult.ViewHolder viewHolder, int i) {

        viewHolder.nama_umkm.setText(konten.get(i).getNama());
        viewHolder.nama_pemilik.setText(konten.get(i).getPemilik());
        Picasso.with(context).load(konten.get(i).getLogo())
                .resize(250,200)
                .centerInside()
                .error(R.drawable.close)
                .into(viewHolder.img_umkm);

    }

    @Override
    public int getItemCount() {
        return konten.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_umkm,nama_pemilik;
        private ImageView img_umkm;
        public ViewHolder(View view) {
            super(view);
            nama_umkm = (TextView)view.findViewById(R.id.nama_umkm);
            nama_pemilik = (TextView)view.findViewById(R.id.nama_pemilik);
            img_umkm = (ImageView) view.findViewById(R.id.imgUmkm);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getLayoutPosition();
                    Toast.makeText(context,"Anda klik "+konten.get(pos).getNama(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
