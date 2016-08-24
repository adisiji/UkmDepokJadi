package com.asneiya.neobyte.umkmdepok.model.RSS;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

@Root(strict = false)
public class Channel {

    @ElementList(entry = "item", inline = true, required = false)
    public List<FeedItem> items;

    @Element(required = false)
    public String title;

    @Path("link")
    @Text(required=false)
    public String link = "";

    @Path("description")
    @Text(required=false)
    public String description = "";

    @Element(required = false)
    public String language;

    @Element(required = false)
    public String copyright;

    @Element(required = false)
    public String pubDate;

    @Element(required = false)
    public Image image;
}
