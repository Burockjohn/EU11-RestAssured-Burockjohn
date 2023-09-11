package com.cydeo.pojo;

/*

                {
                    "rel": "self",
                    "href": "http://54.166.75.122:1000/ords/hr/regions/1"
                }


 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Link {

    private String rel;
    private String href;


}
