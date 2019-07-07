package com.e.vechicle_break_downassistance.Interface.User;

import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserdashAPI {
    @GET("allmechanic")
    Call<List<Mechanic_Data_GET>> getallmechanic();


    @POST("hiremechanic")
    Call<String> hiremechanics(@Body Hire_mechanic_cud hire_mechanic_cud);
}
