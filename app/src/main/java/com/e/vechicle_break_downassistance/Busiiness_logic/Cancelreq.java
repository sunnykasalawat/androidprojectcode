package com.e.vechicle_break_downassistance.Busiiness_logic;

import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Cancelreq {
    String _id;

    public Cancelreq(String _id) {
        this._id = _id;
    }
    public Boolean Cancelreq(){
        Boolean istrue=false;

        UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
        Call<String> complete=userdashAPI.deletework(_id);

        try {
            Response<String> response=complete.execute();
            if (response.body().equals("Request Delete")){
                istrue=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return istrue;
    }
}
