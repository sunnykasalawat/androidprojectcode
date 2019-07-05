package com.e.vechicle_break_downassistance.Model;

public class Loginreq {
    private String message,usertype,userid;

    public Loginreq(String message, String usertype, String userid) {
        this.message = message;
        this.usertype = usertype;
        this.userid = userid;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
