package com.e.vechicle_break_downassistance.Adapter.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Activity.User.Dashboard;
import com.e.vechicle_break_downassistance.Busiiness_logic.Cancelreq;
import com.e.vechicle_break_downassistance.Fragments.User.accept_or_cancel_view;
import com.e.vechicle_break_downassistance.Interface.User.UserdashAPI;
import com.e.vechicle_break_downassistance.Model.User.accorcacCUD;
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

public class accept_or_cancel_adapter extends  RecyclerView.Adapter<accept_or_cancel_adapter.accocac_viewholder>{
    List<accorcacCUD> accorcacCUDList;
    Context context;

    public accept_or_cancel_adapter(List<accorcacCUD> accorcacCUDList, Context context) {
        this.accorcacCUDList = accorcacCUDList;
        this.context = context;
    }

    @NonNull
    @Override
    public accocac_viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_acc_or_cac_view,viewGroup,false);
        return new accocac_viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull accocac_viewholder accocac_viewholder, int i) {
        final accorcacCUD accorcacCUD=accorcacCUDList.get(i);

        accocac_viewholder.fullname.setText(accorcacCUD.getMechanicid().getFullname());
        accocac_viewholder.address.setText(accorcacCUD.getMechanicid().getAddress());
        accocac_viewholder.phone.setText(accorcacCUD.getMechanicid().getPhone());
        Strictmode.StrictMode();
        String imgpath= Url.BASE_URL+"uploads/"+accorcacCUD.getMechanicid().getProfilepic();

        try {
            URL url = new URL(imgpath);
            Bitmap bitmap= BitmapFactory.decodeStream((InputStream)url.getContent());
            accocac_viewholder.imageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        accocac_viewholder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=accorcacCUD.get_id();

                final UserdashAPI userdashAPI= Url.getInstance().create(UserdashAPI.class);
                Call<String> complete=userdashAPI.completework(id);
                complete.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(context,response.body(),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context, Dashboard.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });
        accocac_viewholder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=accorcacCUD.get_id();

                Strictmode.StrictMode();
                Cancelreq cancelreq=new Cancelreq(id);
                if(cancelreq.Cancelreq()){
                    Toast.makeText(context,"canceled",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return accorcacCUDList.size();
    }

    public class accocac_viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView fullname,address,phone;
        Button accept,cancel;
        public accocac_viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_sacv);
            fullname=itemView.findViewById(R.id.tv_flnm_sacv);
            address=itemView.findViewById(R.id.tv_ad_sacv);
            phone=itemView.findViewById(R.id.tv_ph_sacv);
            accept=itemView.findViewById(R.id.btn_Accept_sacv);
            cancel=itemView.findViewById(R.id.btn_Cancel_sacv);
        }
    }
}
