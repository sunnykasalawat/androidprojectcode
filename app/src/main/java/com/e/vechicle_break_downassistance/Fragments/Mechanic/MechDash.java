package com.e.vechicle_break_downassistance.Fragments.Mechanic;


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
import com.e.vechicle_break_downassistance.Interface.Mechanic.MechanicAPI;
import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MechDash extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public MechDash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mech_dash, container, false);
        init(view);
        Load();
        return view;
    }
    private void init(View view) {

        recyclerView = view.findViewById(R.id.hirelistdatarv);
        preferences=getContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    private void Load(){
        String mechanicid=preferences.getString("userid", "0");
        Toast.makeText(getContext(),mechanicid,Toast.LENGTH_LONG).show();

        MechanicAPI mechanicAPI= Url.getInstance().create(MechanicAPI.class);
        Call<List<hirebusercud>> datas=mechanicAPI.hiredata(mechanicid);

        datas.enqueue(new Callback<List<hirebusercud>>() {
            @Override
            public void onResponse(Call<List<hirebusercud>> call, Response<List<hirebusercud>> response) {

                List<hirebusercud> data=response.body();

                mechanic_hire_adapter mechanic_hire_adapters=new mechanic_hire_adapter(data,getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(mechanic_hire_adapters);
            }

            @Override
            public void onFailure(Call<List<hirebusercud>> call, Throwable t) {

            }
        });
    }

}
