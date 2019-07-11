package com.e.vechicle_break_downassistance.Fragments.User;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.Imagemodel;
import com.e.vechicle_break_downassistance.Model.User.Mechanic_Data_GET;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class profile extends Fragment implements  View.OnClickListener {
private ImageView imageview;
private EditText Name,address,phone,email,username,password;
private Button btnup;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public String imagepath="";
    public String imagename,Gend,type,lat,lon;
    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        initialization(view);
        Loaddata();

        return view;
    }

    private void Loaddata() {
        String userid=preferences.getString("userid", "0");

        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<Mechanic_Data_GET> data=userAPI.profile(userid);
        data.enqueue(new Callback<Mechanic_Data_GET>() {
            @Override
            public void onResponse(Call<Mechanic_Data_GET> call, Response<Mechanic_Data_GET> response) {
                Mechanic_Data_GET value=response.body();
                Name.setText(value.getFullname());
                address.setText(value.getAddress());
                phone.setText(value.getPhone());
                email.setText(value.getEmail());
                username.setText(value.getUsername());
                password.setText(value.getPassword());
                imagename=value.getProfilepic();
                Strictmode.StrictMode();
                String imgpath= Url.BASE_URL+"uploads/"+value.getProfilepic();
                Gend=value.getGender();
                type=value.getUsertype();
                lat=value.getLattitude();
                lon=value.getLongitude();

                try {
                    URL url = new URL(imgpath);
                    Bitmap bitmap= BitmapFactory.decodeStream((InputStream)url.getContent());
                    imageview.setImageBitmap(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Mechanic_Data_GET> call, Throwable t) {

            }
        });
    }

    private void initialization(View view) {
        imageview=view.findViewById(R.id.pivprofile);
        Name=view.findViewById(R.id.ptvname);
        address=view.findViewById(R.id.address);
        phone=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.emailaddress);
        username=view.findViewById(R.id.Usernamea);
        password=view.findViewById(R.id.passworda);
        btnup=view.findViewById(R.id.btnupdate);
        preferences=getContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
        btnup.setOnClickListener(this);
        imageview.setOnClickListener(this);

    }

    private void open(){
        Intent gallery=new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(gallery,0);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pivprofile:
                open();
                break;
            case R.id.btnupdate:

                SaveimageOnly();
                String userid=preferences.getString("userid", "0");
                Mechanic_Data_GET mechanic_data_get= new Mechanic_Data_GET(userid,Name.getText().toString(), Gend, phone.getText().toString(), email.getText().toString(), address.getText().toString(), username.getText().toString(), password.getText().toString(), type,imagename,lat,lon);
                UserAPI userAPI=Url.getInstance().create(UserAPI.class);
                Call<String> call=userAPI.profileup(mechanic_data_get);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("success")) {
                            Toast.makeText(getContext(), "User updated", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                break;
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode==RESULT_OK && requestCode==0){

            if(data==null){
                Toast.makeText(getContext(),"Please enter an image",Toast.LENGTH_LONG).show();

            }
            else {

                Uri uri=data.getData();
                imagepath=getRealPathfromURI(uri);
                previewImage(imagepath);
            }
        }

    }
    private String getRealPathfromURI(Uri uri) {
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getContext(),uri,
                projection,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int colindex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(colindex);
        cursor.close();
        return result;
    }
    private void previewImage(String imagepath) {
        File imgfile = new File(imagepath);
        if (imgfile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
            imageview.setImageBitmap(myBitmap);

        }
    }
    private void SaveimageOnly() {
        if (imagepath.isEmpty()) {
            Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();

        } else {
            File file = new File(imagepath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

            System.out.println(imagepath);
            UserAPI userAPI = Url.getInstance().create(UserAPI.class);
            Call<Imagemodel> responseBodycall = userAPI.UploadImage(body);

            Strictmode.StrictMode();

            try {
                Response<Imagemodel> imagemodelResponse = responseBodycall.execute();

                imagename = imagemodelResponse.body().getFilename();


            } catch (IOException e) {
                Toast.makeText(getContext(), "Error uploading image", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        }
    }


}
