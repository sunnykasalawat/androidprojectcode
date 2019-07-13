package com.e.vechicle_break_downassistance.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL="http://192.168.100.5:9000/";

    public static Retrofit getInstance() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Url.BASE_URL).
                addConverterFactory(GsonConverterFactory.
                        create()).build();
        return retrofit;
    }
}
