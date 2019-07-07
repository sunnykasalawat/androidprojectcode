package com.e.vechicle_break_downassistance.Interface.Mechanic;

import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MechanicAPI {
    @FormUrlEncoded
    @POST("hiredata")
    Call<List<hirebusercud>> hiredata(@Field("mechanicid") String mechanicid);

}
