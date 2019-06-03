package com.e.vechicle_break_downassistance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.e.vechicle_break_downassistance.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
private Button login;
private EditText usernamet,passwordt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                Intent intent=new Intent(this,Dashboard.class);
                startActivity(intent);
                //Validate();
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
