package com.asneiya.neobyte.umkmdepok.model.RSS;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class MediaContent {

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    @Attribute(required = false)
    public String url;
}
