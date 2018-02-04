package com.example.dfrank.cryptocoverter;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dfrank on 1/30/18.
 */

public interface Service {
    @GET("data/pricemulti?fsyms=ETH,BTC&tsyms=NGN,USD,GBP,GHS,EUR,XAF,CAD,AFN,ALL,COP,EGP,DKK,KRW,ZAR,SAR,JPY,ARS,BRL,CNY,HKD")
    Call<JsonObject> getCryptoRate();
}
