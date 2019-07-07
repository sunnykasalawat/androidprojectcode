package com.e.vechicle_break_downassistance.Adapter.Mechanic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Model.Mechanic.hirebusercud;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.R;

import java.util.List;

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

        hiredata_viewholder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
