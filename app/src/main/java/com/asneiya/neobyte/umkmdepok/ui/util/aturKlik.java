package com.asneiya.neobyte.umkmdepok.ui.util;

import com.asneiya.neobyte.umkmdepok.model.RSS.FeedItem;
import com.asneiya.neobyte.umkmdepok.model.umkm.ItemKategori;

/**
 * Created by neobyte on 9/10/2016.
 */
public interface aturKlik {
    interface itemfeed{
        void onItemClicked(FeedItem item);
    }
    interface kategori{
        void onItemClicked(ItemKategori item);
    }
}
