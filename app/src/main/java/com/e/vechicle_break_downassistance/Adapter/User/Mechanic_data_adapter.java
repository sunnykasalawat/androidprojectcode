package com.e.vechicle_break_downassistance.Adapter.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Model.User.Hire_mechanic_cud;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.URL.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mechanic_data_adapter extends RecyclerView.Adapter<Mechanic_data_adapter.mechanic_data_viewholder> {
    List<Mechanic_Data_GET> mechaniclistitem;
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Mechanic_data_adapter(List<Mechanic_Data_GET> mechaniclistitem, Context context) {
        this.mechaniclistitem = mechaniclistitem;
        this.context = context;
    }

    @NonNull
    @Override
    public Mechanic_data_adapter.mechanic_data_viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_mechanic_data_view,viewGroup,false);
        preferences=context.getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
        return new mechanic_data_viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Mechanic_data_adapter.mechanic_data_viewholder mechanic_data_viewholder, int i) {
        final Mechanic_Data_GET mechanic_data_get=mechaniclistitem.get(i);

        mechanic_data_viewholder.fullname.setText(mechanic_data_get.getFullname());
        mechanic_data_viewholder.address.setText(mechanic_data_get.getAddress());
        mechanic_data_viewholder.phone.setText(mechanic_data_get.getPhone());

        mechanic_data_viewholder.Hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        String mechanicid=mechanic_data_get.getId();
        String userid=preferences.getString("userid", "0");

                Hire_mechanic_cud hire_mechanic_cud=new Hire_mechanic_cud(mechanicid,"Hire",userid);


                final UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
                Call<String> hircall=userdashAPI.hiremechanics(hire_mechanic_cud);

                hircall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Toast.makeText(context,response.body(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

                    }
                });



            }
        });

    }

    @Override
    public int getItemCount() {
        return mechaniclistitem.size();
    }
public class mechanic_data_viewholder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView fullname,address,phone;
    Button Hire;
    public mechanic_data_viewholder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.img_smdv);
        fullname=itemView.findViewById(R.id.tv_flnm_smdv);
        address=itemView.findViewById(R.id.tv_ad_smdv);
        phone=itemView.findViewById(R.id.tv_ph_smdv);
        Hire=itemView.findViewById(R.id.btn_hire_smdv);
    }
}
}
