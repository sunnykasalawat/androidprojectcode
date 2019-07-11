package com.e.vechicle_break_downassistance.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Busiiness_logic.registeruser;
import com.e.vechicle_break_downassistance.Interface.UserAPI;
import com.e.vechicle_break_downassistance.Model.Imagemodel;
import com.e.vechicle_break_downassistance.Model.UserCUD;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Sensor.Accelerometer;
import com.e.vechicle_break_downassistance.Strictmode.Strictmode;
import com.e.vechicle_break_downassistance.URL.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class Register extends AppCompatActivity implements  View.OnClickListener{
private EditText fullname,phone,email,address,username,password;
private RadioGroup gender,usertype;
private ImageView profileimage;
private Button btnregister,btngetlocation;
private TextView login;
public String imagepath="";

public String imagenames="";
    private SensorManager manager;
    private Sensor mAccelerometer;
    private Accelerometer accelerometer;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
  public  String lattitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        manager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        mAccelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometer=new Accelerometer(getApplicationContext());

        accelerometer.setOnShakeListener(new Accelerometer.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Intent intent=new Intent(Register.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        fullname=findViewById(R.id.fullname);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.gender);

        usertype=findViewById(R.id.usertype);
        btnregister=findViewById(R.id.btnregister);
        profileimage=findViewById(R.id.proimage_reg);
        profileimage.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        btngetlocation=findViewById(R.id.btnlocation);
        btngetlocation.setOnClickListener(this);
        login=findViewById(R.id.alhaveaccnt);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
            }
        });

        usertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregister:

                if (Validate()) {
                    RadioButton gend = (RadioButton) gender.findViewById(gender.getCheckedRadioButtonId());
                    RadioButton usertyp = (RadioButton) usertype.findViewById(usertype.getCheckedRadioButtonId());

                    String name = fullname.getText().toString();
                    String Phone = phone.getText().toString();
                    String Email = email.getText().toString();
                    String Address = address.getText().toString();
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();
                    SaveimageOnly();
                    UserCUD userCUD = new UserCUD(name, gend.getText().toString(), Phone, Email, Address, uname, pass, usertyp.getText().toString(),imagenames,lattitude,longitude);
                    Strictmode.StrictMode();
                    registeruser reg = new registeruser(userCUD);
                   if( reg.registerusers()){
                       Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                   }else{

                       Toast.makeText(getApplicationContext(),"Error in Login",Toast.LENGTH_LONG).show();
                   }
                }
                break;
            case  R.id.proimage_reg:
                int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

                if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                open();

                    break;
            case R.id.btnlocation:
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
                break;
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(Register.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (Register.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Toast.makeText(Register.this,lattitude+" "+longitude,Toast.LENGTH_LONG).show();;

            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                Toast.makeText(Register.this,lattitude+" "+longitude,Toast.LENGTH_LONG).show();;


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                Toast.makeText(Register.this,lattitude+" "+longitude,Toast.LENGTH_LONG).show();;

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }



    private void open(){
        Intent gallery=new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(gallery,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode==RESULT_OK && requestCode==0){

            if(data==null){
                Toast.makeText(this,"Please enter an image",Toast.LENGTH_LONG).show();

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
        CursorLoader loader=new CursorLoader(this,uri,
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
            profileimage.setImageBitmap(myBitmap);

        }
    }

    private boolean Validate(){

        if(TextUtils.isEmpty(fullname.getText().toString())){
            fullname.setError("Enter yout Fullname");
            fullname.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("Enter your Phone");
            phone.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Enter your Address");
            address.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Enter your Phone");
            email.requestFocus();
            return false;
        }

        else if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Enter Your Username");
            username.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Enter your Password");
            password.requestFocus();
            return false;
        }else  if (TextUtils.isEmpty(lattitude)) {
            Toast.makeText(Register.this,"locate the adress",Toast.LENGTH_LONG).show();
        return false;
    }else if (TextUtils.isEmpty(longitude)) {
            Toast.makeText(Register.this,"locate the adress",Toast.LENGTH_LONG).show();

            return false;
    }else{
            return true;

        }
    }
    private void SaveimageOnly(){
        if(imagepath.isEmpty()){
            Toast.makeText(this, "please select image", Toast.LENGTH_SHORT).show();

        }else {
            File file = new File(imagepath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

            System.out.println(imagepath);
            UserAPI userAPI = Url.getInstance().create(UserAPI.class);
            Call<Imagemodel> responseBodycall = userAPI.UploadImage(body);

            Strictmode.StrictMode();

            try {
                Response<Imagemodel> imagemodelResponse = responseBodycall.execute();

                imagenames = imagemodelResponse.body().getFilename();


            } catch (IOException e) {
                Toast.makeText(this, "Error uploading image", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }

}
