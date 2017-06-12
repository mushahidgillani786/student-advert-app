package com.example.mushi.advertapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Post extends AppCompatActivity {

    String[] items, items2;
    Spinner spiner, spiner2;
    Button gallery, post;
    ImageView imageView;
    EditText title, description;
    JSONObject postObj;
    Context context=Post.this;
    Bitmap bitmap;
    String encodedImage;

    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    String URL="http://192.168.0.145/Advert/post.php";
    RequestQueue queue;
    private BufferedInputStream bufferedInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        queue = Volley.newRequestQueue(this);
        postObj = new JSONObject();
        items = new String[]{"Mobiles", "Vehicles", "Electronics", "Bikes", "Property sale", "Furniture", "Animals", "Kids", "Fasion & Design"};
        items2 = new String[]{"Multan", "Lahore", "Islamabad", "Rawalpindi", "Karachi", "Faisalabad"};

        spiner = (Spinner) findViewById(R.id.spinner);
        spiner2 = (Spinner) findViewById(R.id.spinner2);
        gallery = (Button) findViewById(R.id.gallery);
        post = (Button) findViewById(R.id.post);
        imageView = (ImageView) findViewById(R.id.imageView2);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spiner.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        spiner2.setAdapter(adapter2);
gallery.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pickImage();
    }
});

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {



                    //  postObj.put("image", "");
                    postObj.put("category", spiner.getSelectedItem().toString());
                    postObj.put("title", title.getText().toString());
                    postObj.put("description", description.getText().toString());
                    postObj.put("location", spiner2.getSelectedItem());

                    sendData();




                } catch (Exception e) {


                }


            }
        });

    }


    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Activityresult","inside");
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode ==RESULT_OK) {




            if (data == null) {
                //Display an error


                return;
            }

            try {


                InputStream inputStream = context.getContentResolver().openInputStream(data.getData());

                //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...


             bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);
                imageView.invalidate();

             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object


                 byte[] imageArray = baos.toByteArray();
                 encodedImage = Base64.encodeToString(imageArray, Base64.DEFAULT);
            }
            catch (Exception e){



            }
        }
            }



    void postRequest() {


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, URL, postObj, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(Post.this, response.toString(), Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsObjRequest);
    }



    void sendData(){

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://192.168.0.145/Advert/Add.php";
        StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Post.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String>postObj = new HashMap<String, String>();
                postObj.put("category", spiner.getSelectedItem().toString());
                postObj.put("title", title.getText().toString());
                postObj.put("description", description.getText().toString());
                postObj.put("location", spiner2.getSelectedItem().toString());
                postObj.put("image", encodedImage);


                return postObj;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);




    }


}