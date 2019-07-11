package com.e.vechicle_break_downassistance.Interface;

import com.e.vechicle_break_downassistance.Model.Imagemodel;
import com.e.vechicle_break_downassistance.Model.Loginreq;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.Model.UserCUD;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {
    @Multipart
    @POST("upload")
    Call<Imagemodel> UploadImage(@Part MultipartBody.Part img);


    @POST("Register")
    Call<String> add(@Body UserCUD usercud);

    @FormUrlEncoded
    @POST("login")
    Call<Loginreq> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("profile")
    Call<Mechanic_Data_GET> profile(@Field("_id") String id);

    @PUT("profileup")
    Call<String> profileup(@Body Mechanic_Data_GET mechanic_data_get);

}

