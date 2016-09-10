package com.asneiya.neobyte.umkmdepok.model.RSS;

/**
 * Created by neobyte on 8/23/2016.
 */
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

@Root(strict = false)
public class FeedItem {

    @Element(required = false)
    private String title;

    @Element(required = false)
    private String pubDate;

    @Path("link")
    @Text(required=false)
    private String link = "";

    @ElementList(entry = "category", inline = true, required = false)
    public List<String> categories;

    @Path("description")
    @Text(required=false)
    private String description = "";

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}
