package com.e.vechicle_break_downassistance;

import com.e.vechicle_break_downassistance.Busiiness_logic.Cancelreq;
import com.e.vechicle_break_downassistance.Busiiness_logic.Hiremechanic;
import com.e.vechicle_break_downassistance.Busiiness_logic.Requestaccept;
import com.e.vechicle_break_downassistance.Busiiness_logic.Userlogin;
import com.e.vechicle_break_downassistance.Busiiness_logic.registeruser;
import com.e.vechicle_break_downassistance.Model.Loginreq;
import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class APIUnitTesting {
    public class logincons{
        private String message,usertype,userid;

        public logincons(String message, String usertype, String userid) {
            this.message = message;
            this.usertype = usertype;
            this.userid = userid;
        }
    }
    @Test
    public void logintest(){
        Userlogin userlogin=new Userlogin("sunny","sunny");
         Loginreq loginreq=userlogin.loginuser();
        logincons logincons=new logincons("success","User","5d1ee7cd6a52d43ee05e8323");
            assertEquals(logincons,loginreq);
    }

    @Test
    public void registertest(){
        UserCUD userCUD=new UserCUD("Sunny Kasalawat","Male","9843332358",
                "sunnykasa@gmail.com","Bhaktapur","sunny","sunny","User","sunny.png");
        registeruser reg = new registeruser(userCUD);
        assertFalse(reg.registerusers());

    }

    @Test
    public void  Hiremechanictest(){
        Hire_mechanic_cud hire_mechanic_cud=new Hire_mechanic_cud("5d18e6ef4daacd1610f42686","Hire","5d1ee7cd6a52d43ee05e8323");

        Hiremechanic hiremechanic=new Hiremechanic(hire_mechanic_cud);
        Boolean actual_result=hiremechanic.Hiremechanic();

        assertEquals(true,actual_result);
    }

    @Test
    public void Cancelreq(){
        Cancelreq cancelreq=new Cancelreq("5d18e6ef4daacd1610f42686");
        Boolean expeter_result=cancelreq.Cancelreq();
        assertEquals(false,expeter_result);
    }

    @Test
    public  void Reqestaccept(){

        Requestaccept requestaccept=new Requestaccept("5d18e6ef4daacd1610f42686");
        Boolean Acutal_result=requestaccept.Requestaccept();
        assertEquals(false,Acutal_result);

    }



}
