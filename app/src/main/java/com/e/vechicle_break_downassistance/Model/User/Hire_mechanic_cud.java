package com.e.vechicle_break_downassistance.Model.User;

public class Hire_mechanic_cud {
    String mechanicid,hiredetail,Userid;

    public Hire_mechanic_cud(String mechanicid, String hiredetail, String userid) {
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

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }
}
