package com.cydeo.utilities;

import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class SpartanUtils {

    public static Map<String, Object> createSpartan() {

        Map<String, Object> spartaAsMap = new LinkedHashMap<>();
        Faker faker = new Faker();
        Random random = new Random();
        Spartan spartan = new Spartan();

        int phoneLength = random.nextInt(4)+10; //  int phoneLength = faker.number().numberBetween(10, 14);
        String phoneNumber = "";

        int randomN = random.nextInt(2); // int randomN = faker.number().numberBetween(0, 2);

        spartan.setName(faker.name().firstName());
        spartaAsMap.put("name", spartan.getName());

        for (int i = 0; i < phoneLength; i++) {
            int digit = random.nextInt(10);
            phoneNumber += digit;
        }
        spartan.setPhone(Long.valueOf(phoneNumber));
        spartaAsMap.put("phone", spartan.getPhone());

        String gender = "";
        if (randomN == 0) gender = "Female";
        else gender = "Male";

        spartan.setGender(gender);
        spartaAsMap.put("gender", spartan.getGender());

        return spartaAsMap;


    }

    public static void main(String[] args) {

        for (Map.Entry<String, Object> entry : createSpartan().entrySet()) {
            System.out.println("entry.getValue() = " + entry.getValue());
        }

    }

}
