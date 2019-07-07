package com.e.vechicle_break_downassistance.Model.Mechanic;

import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;

import java.util.List;

public class hirebusercud {
   private String mechanicid,hiredetail;
   private Mechanic_Data_GET Userid;

    public hirebusercud(String mechanicid, String hiredetail, Mechanic_Data_GET userid) {
        this.mechanicid = mechanicid;
        this.hiredetail = hiredetail;
        Userid = userid;
    }

    public String getMechanicid() {
        return mechanicid;
    }

    public void setMechanicid(String mechanicid) {
        this.mechanicid = mechanicid;
    }

    public String getHiredetail() {
        return hiredetail;
    }

    public void setHiredetail(String hiredetail) {
        this.hiredetail = hiredetail;
    }

    public Mechanic_Data_GET getUserid() {
        return Userid;
    }

    public void setUserid(Mechanic_Data_GET userid) {
        Userid = userid;
    }
}
