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

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data

public class Region {

    //region_id
    @JsonProperty("region_id")
    private int regionId;
    private String region_name;
    private List<Link> links;


}
