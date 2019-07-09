package com.e.vechicle_break_downassistance.Adapter.Mechanic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Activity.Mechanic.Mechanicdash;
import com.e.vechicle_break_downassistance.Busiiness_logic.Requestaccept;
import com.e.vechicle_break_downassistance.Fragments.Mechanic.MechDash;
import com.e.vechicle_break_downassistance.Interface.Mechanic.MechanicAPI;
import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mechanic_hire_adapter extends RecyclerView.Adapter<mechanic_hire_adapter.hiredata_viewholder>{
    List<hirebusercud> hirebusercuds;
    Context context;


    public mechanic_hire_adapter(List<hirebusercud> hirebusercuds, Context context) {
        this.hirebusercuds = hirebusercuds;
        this.context = context;
    }

    @NonNull
    @Override
    public hiredata_viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_mechanic_hire_view,viewGroup,false);
        return new hiredata_viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull hiredata_viewholder hiredata_viewholder, int i) {
    final hirebusercud hirebusercudlist=hirebusercuds.get(i);

        Toast.makeText(context,hirebusercudlist.getHiredetail(),Toast.LENGTH_LONG).show();
        hiredata_viewholder.fullname.setText(hirebusercudlist.getUserid().getFullname());
        hiredata_viewholder.address.setText(hirebusercudlist.getUserid().getAddress());
        hiredata_viewholder.phone.setText(hirebusercudlist.getUserid().getPhone());
        Strictmode.StrictMode();
        String imgpath= Url.BASE_URL+"uploads/"+hirebusercudlist.getUserid().getProfilepic();

        try {
            URL url = new URL(imgpath);
            Bitmap bitmap= BitmapFactory.decodeStream((InputStream)url.getContent());
           hiredata_viewholder.imageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hiredata_viewholder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=hirebusercudlist.get_id();

                Strictmode.StrictMode();

                Requestaccept requestaccept=new Requestaccept(id);
                if(requestaccept.Requestaccept()){
                    Toast.makeText(context,"Succesfull",Toast.LENGTH_LONG).show();
                }




            }
        });

        hiredata_viewholder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=hirebusercudlist.get_id();

                final MechanicAPI mechanicAPI= Url.getInstance().create(MechanicAPI.class);
                Call<String> cancel=mechanicAPI.cancelhire(id);
                cancel.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context,response.body(),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context, Mechanicdash.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return hirebusercuds.size();
    }


    public class hiredata_viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView fullname,address,phone;
        Button accept,cancel;

        public hiredata_viewholder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.img_smhv);
            fullname=itemView.findViewById(R.id.tv_flnm_smhv);
            address=itemView.findViewById(R.id.tv_ad_smhv);
            phone=itemView.findViewById(R.id.tv_ph_smhv);
            accept=itemView.findViewById(R.id.btn_Accept_smhv);
            cancel=itemView.findViewById(R.id.btn_Cancel_smhv);
        }
    }
}
