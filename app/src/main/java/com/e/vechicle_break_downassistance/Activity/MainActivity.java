package com.e.vechicle_break_downassistance.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.vechicle_break_downassistance.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signin=findViewById(R.id.btnsignin);
        signin.setOnClickListener(this);


        signup=findViewById(R.id.btnsignup);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){


            case R.id.btnsignin:
                intent=new Intent(this, Login.class);
                startActivity(intent);
            break;

            case R.id.btnsignup:
               intent=new Intent(this, Register.class);
               startActivity(intent);
                break;




        }

    }
}
