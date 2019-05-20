package com.e.vechicle_break_downassistance.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.e.vechicle_break_downassistance.R;

public class splashing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashing);
        final Intent intent=new Intent(splashing.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },2000); //delaymills: After 2 seconds MainActivity class will open from Splashing
    }

}

