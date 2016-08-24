package com.asneiya.neobyte.umkmdepok.ui.kategori;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asneiya.neobyte.umkmdepok.R;
import com.squareup.picasso.Picasso;

/**
 * Created by neobyte on 8/24/2016.
 */
public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {

        String [] nama;
        Context context;
        String [] imageId;

        public AdapterKategori(Context context, String[] catNameList, String[] prgmImages) {
            // TODO Auto-generated constructor stub
            nama=catNameList;
            this.context=context;
            imageId=prgmImages;
        }

    @Override
    public AdapterKategori.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_kategori, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterKategori.ViewHolder viewHolder, int i) {

        viewHolder.nama_kat.setText(nama[i]);
        Picasso.with(context).load(imageId[i])
                .resize(250,200)
                .centerInside()
                .into(viewHolder.img_kat);
    }

    @Override
    public int getItemCount() {
        return nama.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_kat;
        private ImageView img_kat;
        public ViewHolder(View view) {
            super(view);
            nama_kat = (TextView)view.findViewById(R.id.nama_kategori);
            img_kat = (ImageView) view.findViewById(R.id.imgKategori);
        }
    }

}
