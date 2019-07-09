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

import com.e.vechicle_break_downassistance.Adapter.Mechanic.Workcompleted_adapter;
import com.e.vechicle_break_downassistance.Interface.Mechanic.MechanicAPI;
import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Completedwork extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Completedwork() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_completedwork, container, false);
        init(view);
        Load();
        return view;
    }
    private void init(View view) {

        recyclerView = view.findViewById(R.id.workcomlisdatarv);
        preferences=getContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

    private void Load() {
        String mechanicid=preferences.getString("userid", "0");


        MechanicAPI mechanicAPI= Url.getInstance().create(MechanicAPI.class);
        Call<List<hirebusercud>> datas=mechanicAPI.getcompletework(mechanicid);
        datas.enqueue(new Callback<List<hirebusercud>>() {
            @Override
            public void onResponse(Call<List<hirebusercud>> call, Response<List<hirebusercud>> response) {

                List<hirebusercud> data=response.body();

               Workcompleted_adapter workcompleted_adapter=new Workcompleted_adapter(data,getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(workcompleted_adapter);

            }

            @Override
            public void onFailure(Call<List<hirebusercud>> call, Throwable t) {

            }
        });
    }

}
