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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.SimpleXmlVolleyRequest;
import com.asneiya.neobyte.umkmdepok.model.RSS.FeedItem;
import com.asneiya.neobyte.umkmdepok.model.RSS.Rss;
import com.asneiya.neobyte.umkmdepok.singleVolley;
import com.asneiya.neobyte.umkmdepok.ui.RSS.feed.ItemFeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabHomeFeed extends Fragment {

    public static final String TAG = TabHomeFeed.class.getSimpleName();
    private RecyclerView feedRecyclerView;
    private ProgressDialog mProgressDialog;
    private final static String url = "http://ukmdepok.co.id/feed/";
    private List<FeedItem> feedItems = new ArrayList<FeedItem>();
    private final ItemFeedAdapter.OnFeedItemClickListener onFeedItemClickListener
            = new ItemFeedAdapter.OnFeedItemClickListener() {
        @Override
        public void onItemClicked(final FeedItem item) {
            Log.d(TAG,item.getTitle());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        feedRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_FeedView);
        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressDialog = new ProgressDialog(getActivity());
        requestFeed();
        return rootView;
    }


    private void requestFeed() {

        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();

        SimpleXmlVolleyRequest<Rss> strReq = new SimpleXmlVolleyRequest(Request.Method.GET, url, Rss.class, new Response.Listener<Rss>() {

            @Override
            public void onResponse(Rss response) {
                Log.d(TAG, response.toString());
                mProgressDialog.hide();

                if (response != null && response.channel != null && response.channel.items != null) {
                    ItemFeedAdapter ifa = new ItemFeedAdapter(response.channel.items,
                            onFeedItemClickListener);
                    // add more items to the list
                    feedItems.addAll(response.channel.items);
                    feedRecyclerView.setAdapter(ifa);
                    ifa.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                mProgressDialog.hide();
            }
        });

        // Adding request to request queue
        singleVolley.getInstance(getActivity()).add(strReq);
    }
}
