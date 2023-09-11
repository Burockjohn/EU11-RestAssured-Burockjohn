package com.cydeo.pojo;

/*

 {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://54.166.75.122:1000/ords/hr/regions/1"
                }
            ]
        }

 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Region {

    private int region_id;
    private String region_name;
    private List<Link> links;


}
