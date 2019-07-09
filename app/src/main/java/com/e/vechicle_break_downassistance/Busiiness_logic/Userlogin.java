package com.e.vechicle_break_downassistance.Busiiness_logic;

import com.e.vechicle_break_downassistance.Activity.Login;
import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.Loginreq;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Userlogin {

    String username,password;
    Loginreq loginreq;

    public Userlogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Loginreq loginuser(){

        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<Loginreq> loginreqCall=userAPI.login(username,password);

        try {
            Response<Loginreq>   loginreqResponse = loginreqCall.execute();
             loginreq=loginreqResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return loginreq;
    }
}
