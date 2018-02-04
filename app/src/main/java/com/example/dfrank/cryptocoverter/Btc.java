package com.example.dfrank.cryptocoverter;

/**
 * Created by dfrank on 1/30/18.
 */

public class Btc {
    private String country;
    private String shorcode;
    private double btc;
    private double eth;

    public Btc(String country, String shorcode, double btc, double eth) {
        this.country = country;
        this.shorcode = shorcode;
        this.btc = btc;
        this.eth = eth;
    }

    public String getCountry() {
        return country;
    }

    public String getShorcode() {
        return shorcode;
    }

    public double getBtc() {
        return btc;
    }

    public double getEth() {
        return eth;
    }
}
