package com.example.dfrank.cryptocoverter;

/**
 * Created by dfrank on 1/30/18.
 */

public class Data {
    public static String[] country = {"Nigeria","United State","United Kingdom",
            "Ghana","Germany","Congo","Canada","Afganistan","Albania","Colombia","Egypt","Denmark",
            "South Korea","South Africa","Saudi Arabia","Japan","Argentina","Brazil",
            "China","Hong Kong"};

    public static String[] shortcode = {"NGN","USD","GBP","GHS","EUR","XAF","CAD","AFN","ALL",
            "COP","EGP","DKK","KRW","ZAR","SAR","JPY","ARS","BRL","CNY","HKD"};


    public static boolean[] getChecked() {
        boolean[] checked = new boolean[20];
        checked[0] = true;
        return checked;
    }
}
