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
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        siapi().getfeeedItems().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rss>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        Log.e(TabHomeFeed.class.getSimpleName(),"Error > "+e.getMessage());
                    }

                    @Override
                    public void onNext(Rss rss) {
                        ItemFeedAdapter ifa = new ItemFeedAdapter(rss.channel.items,
                                onFeedItemClickListener);
                        // add more items to the list
                        feedItems.addAll(rss.channel.items);
                        feedRecyclerView.setAdapter(ifa);
                        ifa.notifyDataSetChanged();
                    }
                });
    }

    private SimpleXMLRetro.SiteAPI siapi(){
        SimpleXMLRetro.SiteAPI x =  ((SimpleXMLRetro)getActivity().getApplication()).getSiteApi();
        if(x==null)
            Log.d("error coi","di siapi");
        return x;
    }

}
