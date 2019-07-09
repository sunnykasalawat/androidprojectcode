package com.e.vechicle_break_downassistance.Busiiness_logic;

import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Hiremechanic {
    Hire_mechanic_cud hire_mechanic_cud;

    public Hiremechanic(Hire_mechanic_cud hire_mechanic_cud) {
        this.hire_mechanic_cud = hire_mechanic_cud;
    }

    public Boolean Hiremechanic(){
        Boolean istrue=false;
        UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
        Call<String> hircall=userdashAPI.hiremechanics(hire_mechanic_cud);

        try {
            Response<String> stringResponse=hircall.execute();
            if(stringResponse.body().equals("Hired"))
            {
                istrue=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return istrue;


    }
}
