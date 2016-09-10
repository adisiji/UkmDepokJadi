package com.asneiya.neobyte.umkmdepok.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.model.RSS.FeedItem;
import com.asneiya.neobyte.umkmdepok.model.umkm.ItemKategori;
import com.asneiya.neobyte.umkmdepok.ui.Search_act;
import com.asneiya.neobyte.umkmdepok.ui.util.aturKlik;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by neobyte on 8/24/2016.
 */
public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {

        private final List<ItemKategori> item;
        private final Context context;
        private final aturKlik.kategori onKatItemClickListener;

        public AdapterKategori(final Context context, final List<ItemKategori> item, aturKlik.kategori itemclick) {
            // TODO Auto-generated constructor stub
            this.item = item;
            this.context=context;
            onKatItemClickListener = itemclick;
        }


    @Override
    public AdapterKategori.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_kategori, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterKategori.ViewHolder holder, int i) {
        holder.bindItem(item.get(i), onKatItemClickListener);
        Picasso.with(context).load(item.get(i).getImage())
                .resize(250,200)
                .centerInside()
                .into(holder.img_kat);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final View rootView;
        private final TextView nama_kat;
        private final ImageView img_kat;

        ViewHolder (View view){
            super(view);
            rootView = view;
            nama_kat = (TextView)view.findViewById(R.id.nama_kategori);
            img_kat = (ImageView) view.findViewById(R.id.imgKategori);

        }

        /**
         * Bind a FeedItem data model to the View
         */
        void bindItem(final ItemKategori feedItem, final aturKlik.kategori clickListener) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    clickListener.onItemClicked(feedItem);
                }
            });
            nama_kat.setText(feedItem.getNama());
        }
    }

}
