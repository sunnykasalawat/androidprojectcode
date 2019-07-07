package com.e.vechicle_break_downassistance.Model.User;

public class accorcacCUD {
   private String _id,hiredetail,Userid;
   private Mechanic_Data_GET mechanicid;

    public accorcacCUD(String _id, String hiredetail, String userid, Mechanic_Data_GET mechanicid) {
        this._id = _id;
        this.hiredetail = hiredetail;
        Userid = userid;
        this.mechanicid = mechanicid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Mechanic_Data_GET getMechanicid() {
        return mechanicid;
    }

    public void setMechanicid(Mechanic_Data_GET mechanicid) {
        this.mechanicid = mechanicid;
    }
}
