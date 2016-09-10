package com.asneiya.neobyte.umkmdepok.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.model.RSS.FeedItem;
import com.asneiya.neobyte.umkmdepok.ui.util.aturKlik;

import java.util.List;

/**
 * Created by neobyte on 8/23/2016.
 */
public class ItemFeedAdapter extends RecyclerView.Adapter<ItemFeedAdapter.ViewHolder> {

    private final List<FeedItem> dataset;
    private final aturKlik.itemfeed onFeedItemClickListener;

    public ItemFeedAdapter(final List<FeedItem> dataset,
                           aturKlik.itemfeed onFeedItemClickListener) {
        this.dataset = dataset;
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // Create a new View instances from the XML layout definition
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_feed_item, parent, false);
        return new ViewHolder(view);
    }

    // Bind a View (new or recycled) to a data item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(dataset.get(position), onFeedItemClickListener);
    }

    // Returns the size of the dataset
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     * A recycleable representation of a View entity
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        ViewHolder(View view) {
            super(view);
            rootView = view;
            titleTextView = (TextView) view.findViewById(R.id.textView_title);
            descriptionTextView = (TextView) view.findViewById(R.id.textView_description);
        }

        /**
         * Bind a FeedItem data model to the View
         */
        void bindItem(final FeedItem feedItem, final aturKlik.itemfeed clickListener) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    clickListener.onItemClicked(feedItem);
                }
            });
            titleTextView.setText(feedItem.getTitle());
            descriptionTextView.setText(feedItem.getDescription());
        }
    }
}