package com.cydeo.pojo;

/*

{
    "id": 8,
    "name": "Rodolfo",
    "gender": "Male",
    "phone": 9601637574
}

 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id",allowSetters = true)

public class Spartan {

    // getters & setters
    // to String

    private int id;
    private String name;
    private String gender;
    private long phone;

}
