package com.e.vechicle_break_downassistance.Busiiness_logic;

import com.e.vechicle_break_downassistance.Interface.Mechanic.MechanicAPI;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Requestaccept {
    String _id;

    public Requestaccept(String _id) {
        this._id = _id;
    }
    public  Boolean Requestaccept(){
        Boolean istrue=false;
        MechanicAPI mechanicAPI= Url.getInstance().create(MechanicAPI.class);
        Call<String> accept=mechanicAPI.accepthire(_id);
        try {
            Response<String> response=accept.execute();
            if(response.body()=="Request Accepted"){
                istrue=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return istrue;
    }
}
