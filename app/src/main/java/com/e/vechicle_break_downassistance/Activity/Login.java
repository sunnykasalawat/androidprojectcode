package com.e.vechicle_break_downassistance.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Activity.Mechanic.Mechanicdash;
import com.e.vechicle_break_downassistance.Activity.User.Dashboard;
import com.e.vechicle_break_downassistance.Busiiness_logic.Userlogin;
import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.Loginreq;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;
import com.e.vechicle_break_downassistance.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {
private Button login;
private EditText usernamet,passwordt;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Loginreq loginreq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences=getApplicationContext().getSharedPreferences("app",Context.MODE_PRIVATE);
        editor=preferences.edit();

        initialization(); //initialize  the Button and Edit Text



    }
    public void initialization(){
        usernamet=findViewById(R.id.textemail);
        passwordt=findViewById(R.id.textpassword);
        login=findViewById(R.id.buttonlogin);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonlogin:
                if(Validate()){
                    String User=usernamet.getText().toString();
                    String pass=passwordt.getText().toString();
                    Userlogin userlogin=new Userlogin(User,pass);
                    Strictmode.StrictMode();
                    loginreq=userlogin.loginuser();
                    if(!loginreq.getMessage().equals("Invalid")){
                        editor.putString("userid",loginreq.getUserid()).commit();
                              editor.putString("usertype",loginreq.getUsertype()).commit();
                              editor.putBoolean("status",true).commit();
                              Intent intent;
                        if(loginreq.getUsertype().equals("User")){


                                intent=new Intent(Login.this, Dashboard.class);

                             startActivity(intent);
                             finish();
                             }else if(loginreq.getUsertype().equals("Mechanic")){
                                 intent=new Intent(Login.this, Mechanicdash.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                 startActivity(intent);
                                 finish();
                             }
                         }else
                         {
                             Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_LONG).show();
                        }



                    }

             break;

        }
    }
    private boolean Validate(){

        if(TextUtils.isEmpty(usernamet.getText().toString())){
            usernamet.setError("Enter Your username");
            usernamet.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(passwordt.getText().toString())){
            passwordt.setError("Enter your password");
            passwordt.requestFocus();
            return false;
        }else
        {
            return true;

        }


    }
}
