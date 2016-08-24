package com.asneiya.neobyte.umkmdepok.model.RSS;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Rss {

    @Attribute(required = false)
    public String version;

    @Element
    public Channel channel;
}
