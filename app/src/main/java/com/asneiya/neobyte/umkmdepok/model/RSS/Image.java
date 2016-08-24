package com.asneiya.neobyte.umkmdepok.model.RSS;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Image {

    @Element(required = false)
    public String title;

    @Element(required = false)
    public String url;

    @Element(required = false)
    public String link;
}
