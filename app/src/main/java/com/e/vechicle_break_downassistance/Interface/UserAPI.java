package com.e.vechicle_break_downassistance.Interface;

import com.e.vechicle_break_downassistance.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("Register")
    Call<String> add(@Body User user);
    
}

