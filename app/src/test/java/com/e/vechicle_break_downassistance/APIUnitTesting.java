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
       Userlogin userlogin=new Userlogin("sunny","sunny");
       Loginreq log=userlogin.loginuser();
       String message=log.getMessage();
       assertEquals("success",message);
    }

    @Test
    public void registertest(){

        UserCUD userCUD=new UserCUD("Sunny Kasalawat","Male","9843332358",
                "sunnykasa@gmail.com","Bhaktapur","sunny11","sunny","User","sunny.png","27.6738634",
                "85.4184845"
        );
       registeruser reg=new registeruser(userCUD);
       Boolean istrue=reg.registerusers();
       assertFalse(istrue);

          }

    @Test
    public void  Hiremechanictest(){

        Hire_mechanic_cud hire_mechanic_cud=new Hire_mechanic_cud("5d18e6ef4daacd1610f42686","Hire","5d1ee7cd6a52d43ee05e8323");
        Hiremechanic hiremechanic=new Hiremechanic(hire_mechanic_cud);
        Boolean istrue=hiremechanic.Hiremechanic();
        assertFalse(istrue);

    }

    @Test
    public void Cancelreq(){
        Cancelreq cancelreq=new Cancelreq("5d18e6ef4daacd1610f42686");
        assertTrue(cancelreq.Cancelreq());
    }

    @Test
    public  void Reqestaccept(){

        Requestaccept requestaccept=new Requestaccept("5d18e6ef4daacd1610f42686");

        assertFalse(requestaccept.Requestaccept());

    }



}
