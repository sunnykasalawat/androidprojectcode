package com.e.vechicle_break_downassistance.Strictmode;


import android.os.StrictMode;

public class Strictmode {
   public static void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }
}
