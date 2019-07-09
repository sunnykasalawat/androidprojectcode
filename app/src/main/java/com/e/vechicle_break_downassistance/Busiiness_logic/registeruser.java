package com.e.vechicle_break_downassistance.Busiiness_logic;

import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class registeruser {
    UserCUD userCUD;

    public registeruser(UserCUD userCUD) {
        this.userCUD = userCUD;
    }

    public Boolean registerusers(){
        Boolean valreturn=false;
        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<String> registerres=userAPI.add(userCUD);

        try {
            Response<String> registerResponse=registerres.execute();
            if(registerResponse.body().equals("Username already exist")){
                valreturn=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return  valreturn;
    }
}
