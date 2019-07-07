package com.e.vechicle_break_downassistance.Interface.User;

import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.Model.User.accorcacCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserdashAPI {
    @GET("allmechanic")
    Call<List<Mechanic_Data_GET>> getallmechanic();


    @POST("hiremechanic")
    Call<String> hiremechanics(@Body Hire_mechanic_cud hire_mechanic_cud);


    @FormUrlEncoded
    @POST("accorcacdetail")
    Call<List<accorcacCUD>> accorcacdetail(@Field("Userid") String Userid);

    @FormUrlEncoded
    @PUT("Completework")
    Call<String> completework(@Field("_id") String id);

    @FormUrlEncoded
    @POST("Deletework")
    Call<String> deletework(@Field("_id") String id);


}
