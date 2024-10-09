package com.coinguess.coin.Objects;

public class Coin {
    public String country;
    public String obverse;
    public String reverse;
    public String liscense;

    public Coin(String country, String reverse, String obverse, String liscense){
        this.country = country;
        this.obverse = obverse;
        this.reverse = reverse;
        this.liscense = liscense;
    }

    public String getCountry(){
        return country;
    }

    public String getObverse(){
        return obverse;
    }

    public String getReverse(){
        return reverse;
    }

    public String getLiscense(){
        return liscense;
    }
}