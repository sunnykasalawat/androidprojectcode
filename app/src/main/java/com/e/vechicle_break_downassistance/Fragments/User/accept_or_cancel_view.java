package com.e.vechicle_break_downassistance.Fragments.User;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Adapter.Mechanic.mechanic_hire_adapter;
import com.e.vechicle_break_downassistance.Adapter.User.accept_or_cancel_adapter;
import com.e.vechicle_break_downassistance.Interface.Mechanic.MechanicAPI;
import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.Model.User.accorcacCUD;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class accept_or_cancel_view extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public accept_or_cancel_view() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_accept_or_cancel_view, container, false);
        init(view);
        Load();
        return view;
    }
    private void init(View view) {

        recyclerView = view.findViewById(R.id.acocalistdatarv);
        preferences=getContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    private void Load(){
        String Userid=preferences.getString("userid", "0");

        Toast.makeText(getContext(),Userid,Toast.LENGTH_LONG).show();
        UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
        Call<List<accorcacCUD>> datas=userdashAPI.accorcacdetail(Userid);

        datas.enqueue(new Callback<List<accorcacCUD>>() {
            @Override
            public void onResponse(Call<List<accorcacCUD>> call, Response<List<accorcacCUD>> response) {

                List<accorcacCUD> data=response.body();

               accept_or_cancel_adapter acc_or_can_adapter=new accept_or_cancel_adapter(data,getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(acc_or_can_adapter);
            }

            @Override
            public void onFailure(Call<List<accorcacCUD>> call, Throwable t) {

            }
        });
    }


}
