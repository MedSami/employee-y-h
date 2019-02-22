package com.yosra.employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yosra.employee.API.ApiRequest;
import com.yosra.employee.API.RetrofitServer;
import com.yosra.employee.Model.ResponseDataModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnvoyerCertif extends AppCompatActivity {
String idEmploye;
EditText imageTitle;
Button btnChoose,btnUpload;
ImageView img;
private static  final int img_request=777;
private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer_certif);

        imageTitle=findViewById(R.id.edtTitle);
        btnChoose=findViewById(R.id.btnChoisir);
        btnUpload=findViewById(R.id.btnUpload);
        img=findViewById(R.id.imageView2);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            idEmploye = data.getString("idEmploye");

        }


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    selectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private  void selectImage(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,img_request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==img_request && resultCode==RESULT_OK && data !=null){
            Uri path= data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
                imageTitle.setVisibility(View.VISIBLE);
                btnChoose.setEnabled(false);
                btnUpload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        String image=imageToString();
        String image_title=imageTitle.getText().toString();

        ApiRequest api= RetrofitServer.getClient().create(ApiRequest.class);
        Call<ResponseDataModel> upload=api.UploadCertif(image_title,image,idEmploye);
        upload.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                Toast.makeText(EnvoyerCertif.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                img.setVisibility(View.GONE);
                imageTitle.setText("");
                imageTitle.setVisibility(View.GONE);

                btnChoose.setEnabled(true);
                btnUpload.setEnabled(false);
            }

            @Override
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {
                Toast.makeText(EnvoyerCertif.this, "Problem Connexion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);

    }
}
