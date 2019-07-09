package com.e.vechicle_break_downassistance.Adapter.Mechanic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Workcompleted_adapter extends RecyclerView.Adapter<Workcompleted_adapter.workcompleted_view> {
    List<hirebusercud> hirebusercuds;
    Context context;

    public Workcompleted_adapter(List<hirebusercud> hirebusercuds, Context context) {
        this.hirebusercuds = hirebusercuds;
        this.context = context;
    }

    @NonNull
    @Override
    public workcompleted_view onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_work_complete_view,viewGroup,false);
        return new workcompleted_view(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull workcompleted_view workcompleted_view, int i) {
        final hirebusercud hirebusercudlist=hirebusercuds.get(i);

        Toast.makeText(context,hirebusercudlist.getHiredetail(),Toast.LENGTH_LONG).show();
       workcompleted_view.fullname.setText(hirebusercudlist.getUserid().getFullname());
        workcompleted_view.address.setText(hirebusercudlist.getUserid().getAddress());
        workcompleted_view.phone.setText(hirebusercudlist.getUserid().getPhone());
        Strictmode.StrictMode();
        String imgpath= Url.BASE_URL+"uploads/"+hirebusercudlist.getUserid().getProfilepic();

        try {
            URL url = new URL(imgpath);
            Bitmap bitmap= BitmapFactory.decodeStream((InputStream)url.getContent());
            workcompleted_view.imageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return hirebusercuds.size();
    }

    public class workcompleted_view extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView fullname,address,phone;
        public workcompleted_view(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_swcv);
            fullname=itemView.findViewById(R.id.tv_flnm_swcv);
            address=itemView.findViewById(R.id.tv_ad_swcv);
            phone=itemView.findViewById(R.id.tv_ph_swcv);
        }
    }
}
