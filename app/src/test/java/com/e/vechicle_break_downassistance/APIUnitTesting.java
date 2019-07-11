package com.e.vechicle_break_downassistance;


import com.e.vechicle_break_downassistance.Busiiness_logic.Cancelreq;
import com.e.vechicle_break_downassistance.Busiiness_logic.Hiremechanic;
import com.e.vechicle_break_downassistance.Busiiness_logic.Requestaccept;
import com.e.vechicle_break_downassistance.Busiiness_logic.Userlogin;
import com.e.vechicle_break_downassistance.Busiiness_logic.registeruser;
import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.Loginreq;
import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.URL.Url;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class APIUnitTesting {

    @Test
    public void logintest(){
        UserAPI userApi= Url.getInstance().create(UserAPI.class);
        Call<Loginreq> call=userApi.login("sunny","sunny");
        try {
            Response<Loginreq> response=call.execute();
            Loginreq loginreq=response.body();
            assertThat(loginreq.getMessage(),is("success"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registertest(){
        UserAPI userAPI=Url.getInstance().create(UserAPI.class);
        UserCUD userCUD=new UserCUD("Sunny Kasalawat","Male","9843332358",
                "sunnykasa@gmail.com","Bhaktapur","sunny11","sunny","User","sunny.png","27.6738634",
                "85.4184845"
        );
        Call<String> call=userAPI.add(userCUD);

        try {
            Response<String> response=call.execute();
                assertEquals("Username already exist",response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  Hiremechanictest(){

        Hire_mechanic_cud hire_mechanic_cud=new Hire_mechanic_cud("5d18e6ef4daacd1610f42686","Hire","5d1ee7cd6a52d43ee05e8323");
        UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
        Call<String> hircall=userdashAPI.hiremechanics(hire_mechanic_cud);


        try {
            Response<String> stringResponse=hircall.execute();
            assertEquals("Hired",stringResponse.message());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Cancelreq(){
        Cancelreq cancelreq=new Cancelreq("5d18e6ef4daacd1610f42686");
        assertFalse(cancelreq.Cancelreq());
    }

    @Test
    public  void Reqestaccept(){

        Requestaccept requestaccept=new Requestaccept("5d18e6ef4daacd1610f42686");

        assertFalse(requestaccept.Requestaccept());

    }



}
