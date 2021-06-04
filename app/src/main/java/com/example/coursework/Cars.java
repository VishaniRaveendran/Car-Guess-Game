package com.example.coursework;

import java.util.Random;

public class Cars {


    private final int no_cars = 59;//(no of cars -1)
    private static int lastRandomIndex;
    //drawable cars
    Integer[] cars = {
            R.drawable.alfa_1,R.drawable.alfa_2,R.drawable.amg_1,R.drawable.amg_2,R.drawable.audi_1,R.drawable.audi_2,R.drawable.bentley_1,R.drawable.bentley_2,
            R.drawable.bmw_1,R.drawable.bmw_2,R.drawable.bugatti_1,R.drawable.bugatti_2,R.drawable.daihatsu_1,R.drawable.daihatsu_2,
            R.drawable.ferrari_1,R.drawable.ferrari_2,R.drawable.ford_1,R.drawable.ford_2,R.drawable.genesis_1,R.drawable.genesis_2,
            R.drawable.hyundai_1,R.drawable.hyundai_2,R.drawable.jaguar_1,R.drawable.jaguar_2,R.drawable.kia_1,R.drawable.kia_2,R.drawable.lexus_1,R.drawable.lexus_2,
            R.drawable.lincoln_1,R.drawable.lincoln_2,R.drawable.lotus_1,R.drawable.lotus_2,R.drawable.maserati_1,R.drawable.maserati_2,
            R.drawable.mercedesbenz_1,R.drawable.mercedesbenz_2,R.drawable.mini_1,R.drawable.mini_2,R.drawable.opel_1,R.drawable.opel_2,R.drawable.peugeot_1,R.drawable.peugeot_2,
            R.drawable.proton_1,R.drawable.proton_2, R.drawable.rangerover_1,R.drawable.rangerover_2,R.drawable.rollsroyce_1,R.drawable.rollsroyce_2,R.drawable.smart_1,R.drawable.smart_2,
            R.drawable.tata_1,R.drawable.tata_2, R.drawable.toyota_1,R.drawable.toyota_2,R.drawable.troller_1,R.drawable.troller_2,R.drawable.vauxhall_1,R.drawable.vauxhall_2,R.drawable.volvo_1,R.drawable.volvo_2
    };

    String [] brands = {
            "alfa","alfa", "amg","amg", "audi","audi", "bentley","bentley",
            "bmw","bmw", "bugatti","bugatti","daihatsu","daihatsu",
            "ferrari", "ferrari", "ford", "ford", "genesis","genesis",
            "hyundai","hyundai", "jaguar",  "jaguar", "kia","kia", "lexus","lexus",
            "lincoln","lincoln", "lotus", "lotus", "maserati","maserati",
            "mercedesbenz","mercedesbenz", "mini", "mini", "opel","opel", "peugeot", "peugeot",
            "proton","proton", "rangerover","rangerover", "rollsroyce", "rollsroyce", "smart","smart",
            "tata","tata", "toyota","toyota", "troller","troller", "vauxhall","vauxhall", "volvo","volvo"
    };

    public Cars(){

    }


    //-----------takes index to return associated brand name---------//
    public String getBrandName(int indexInArray){
        //----------Preventing array index out of bounds error------------//
        if ((indexInArray <= no_cars) && (indexInArray >= 0)){
            return brands[indexInArray];
        }
        else {
            return "Index out of bounds <-- Database.class";
        }
    }

    //-----------returns random flag--------------//
    public Integer getRandomCar(){
        Random rand = new Random();
        int randomNumber = rand.nextInt((no_cars) + 1);
        lastRandomIndex = randomNumber;
        return cars[randomNumber];
    }


    public static int getLastRandomIndex(){
        return lastRandomIndex;
    }



}
