package com.e.vechicle_break_downassistance.Fragments.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Adapter.User.Mechanic_data_adapter;
import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mechanic_data extends Fragment {
    RecyclerView recyclerView;
    List<Mechanic_Data_GET> mechanic_data_gets = new ArrayList<>();

    public Mechanic_data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mechanic_data, container, false);
        init(view);
        LoadData();
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.mechanicdatarv);
    }

    private void LoadData() {
        UserdashAPI userdashAPI = Url.getInstance().create(UserdashAPI.class);
        Call<List<Mechanic_Data_GET>> datas = userdashAPI.getallmechanic();
        datas.enqueue(new Callback<List<Mechanic_Data_GET>>() {
            @Override
            public void onResponse(Call<List<Mechanic_Data_GET>> call, Response<List<Mechanic_Data_GET>> response) {
            List<Mechanic_Data_GET> data=response.body();
            Mechanic_data_adapter mechanic_data_adapter=new Mechanic_data_adapter(data,getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(mechanic_data_adapter);
            }

            @Override
            public void onFailure(Call<List<Mechanic_Data_GET>> call, Throwable t) {
                Toast.makeText(getContext(), "Could no load data", Toast.LENGTH_SHORT).show();

            }
        });

    }
}