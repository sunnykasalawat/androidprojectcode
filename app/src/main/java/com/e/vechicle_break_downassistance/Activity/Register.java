package com.e.vechicle_break_downassistance.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements  View.OnClickListener {
private EditText fullname,phone,email,address,username,password;
private RadioGroup gender,usertype;



private Button btnregister;
private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        fullname=findViewById(R.id.fullname);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.gender);

        usertype=findViewById(R.id.usertype);
        btnregister=findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);

        login=findViewById(R.id.alhaveaccnt);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
            }
        });

        usertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnregister:

            if(Validate()){
                RadioButton gend = (RadioButton) gender.findViewById(gender.getCheckedRadioButtonId());
                RadioButton usertyp=(RadioButton) usertype.findViewById(usertype.getCheckedRadioButtonId());

                String name=fullname.getText().toString();
                String Phone=phone.getText().toString();
                String Email=email.getText().toString();
                String Address=address.getText().toString();
                String uname=username.getText().toString();
                String pass=password.getText().toString();


                UserCUD userCUD=new UserCUD(name,gend.getText().toString(),Phone,Email,Address,uname,pass,usertyp.getText().toString());


                final UserAPI userAPI= Url.getInstance().create(UserAPI.class);
                Call<String> voidcall=userAPI.add(userCUD);
                voidcall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),response.errorBody().toString(),Toast.LENGTH_LONG).show();
                            return;
                        }
                        String result=response.body();

                        if(result.equals("Username already exist")){

                            Toast.makeText(getApplicationContext(),"Username already exist",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


            }
        }

    }


    private boolean Validate() {

        if(TextUtils.isEmpty(fullname.getText().toString())){
            fullname.setError("Enter yout Fullname");
            fullname.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("Enter your Phone");
            phone.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Enter your Address");
            address.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Enter your Phone");
            email.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Enter Your Username");
            username.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Enter your Password");
            password.requestFocus();
            return false;
        }else {
            return true;

        }
    }
}
