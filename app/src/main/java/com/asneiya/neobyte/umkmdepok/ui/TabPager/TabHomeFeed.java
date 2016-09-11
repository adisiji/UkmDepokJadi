package com.asneiya.neobyte.umkmdepok.ui.TabPager;

/**
 * Created by neobyte on 8/24/2016.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.ui.util.SimpleXMLRetro;
import com.asneiya.neobyte.umkmdepok.model.RSS.FeedItem;
import com.asneiya.neobyte.umkmdepok.model.RSS.Rss;
import com.asneiya.neobyte.umkmdepok.ui.adapter.ItemFeedAdapter;
import com.asneiya.neobyte.umkmdepok.ui.isi_artikel_rss;
import com.asneiya.neobyte.umkmdepok.ui.util.aturKlik;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TabHomeFeed extends Fragment {

    public static final String TAG = TabHomeFeed.class.getSimpleName();
    private RecyclerView feedRecyclerView;
    private ProgressDialog mProgressDialog;
    private List<FeedItem> feedItems = new ArrayList<FeedItem>();
    private final aturKlik.itemfeed onFeedItemClickListener = new aturKlik.itemfeed() {
        @Override
        public void onItemClicked(FeedItem item) {
            isi_artikel_rss.launch(getActivity(),item.getLink(),item.getTitle());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        feedRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_FeedView);
        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        retroFeed();
        return rootView;
    }

    private void retroFeed(){
        siapi().getfeeedItems().enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, retrofit2.Response<Rss> response) {
                Rss respon = response.body();
                Log.d(TAG, response.toString());
                hasilok(respon);
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.d(TAG, "Error: " + t.getMessage());
                mProgressDialog.dismiss();
            }
        });
    }

    private SimpleXMLRetro.SiteAPI siapi(){
        SimpleXMLRetro.SiteAPI x =  ((SimpleXMLRetro)getActivity().getApplication()).getSiteApi();
        if(x==null)
            Log.d("error coi","di siapi");
        return x;
    }

    private void hasilok(Rss respon){
        mProgressDialog.dismiss();
        if ( respon.channel != null && respon.channel.items != null) {
            ItemFeedAdapter ifa = new ItemFeedAdapter(respon.channel.items,
                    onFeedItemClickListener);
            // add more items to the list
            feedItems.addAll(respon.channel.items);
            feedRecyclerView.setAdapter(ifa);
            ifa.notifyDataSetChanged();
        }
    }
}
