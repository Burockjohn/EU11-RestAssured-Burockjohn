package com.cydeo.pojo;

/*

                {
                    "rel": "self",
                    "href": "http://54.166.75.122:1000/ords/hr/regions/1"
                }


 */

public class Link {

    private String rel;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String toString() {
        return "Link{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
