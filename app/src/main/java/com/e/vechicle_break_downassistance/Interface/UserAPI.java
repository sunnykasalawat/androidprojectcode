package com.e.vechicle_break_downassistance.Interface;

import com.e.vechicle_break_downassistance.Model.UserCUD;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("Register")
    Call<String> add(@Body UserCUD usercud);

    @FormUrlEncoded
    @POST("login")
    Call<String> login(@Field("username") String username, @Field("password") String password);

}

