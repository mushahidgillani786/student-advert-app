package com.example.mushi.advertapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailView extends AppCompatActivity {

    ImageView image;
    Button call,text;
    TextView item,price,location,description;
    Intent intent=new Intent();
    String titlee;
    String descriptionn;
    int idd;
    String locationn;
    String pricee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        image = (ImageView) findViewById(R.id.imageView);
        call = (Button) findViewById(R.id.button4);
        text = (Button) findViewById(R.id.button5);
        item = (TextView) findViewById(R.id.item);
        price = (TextView) findViewById(R.id.price);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();
        titlee = bundle.getString("title");
        descriptionn = bundle.getString("description");
        pricee = bundle.getString("price");
        idd = bundle.getInt("id");
        locationn = bundle.getString("location");


        Toast.makeText(this, "" + idd, Toast.LENGTH_SHORT).show();

        recieveData();
    }



    void recieveData(){


        final HashMap<String, String> params=new HashMap<>();

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        //RequestQueue queue= Volley.newRequestQueue(this);
        //String url="http://192.168.0.145/Advert/fetch.php";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,config.fetchobject+"?id="+idd,null,new Response.Listener<JSONArray>(){


            @Override
            public void onResponse(JSONArray response) {

                Toast.makeText(DetailView.this, ""+response.toString(), Toast.LENGTH_SHORT).show();

         if(response.length()==0){

                    Toast.makeText(DetailView.this, "No data", Toast.LENGTH_SHORT).show();


                }else{

                    try {

                        for (int i = 0; i < response.length(); i++) {

                            JSONObject obj = response.getJSONObject(i);

                            Log.d("Price", obj.getString("title"));
                            price.setText(obj.getString("price"));
                            description.setText(obj.getString("description"));
                            item.setText(obj.getString("title"));
                            location.setText(obj.getString("location"));
                            Picasso.with(DetailView.this)
                                    .load(obj.getString("image")).into(image);

                        }

                    }catch (Exception e){
e.printStackTrace();



                    }

                }


            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailView.this, "That did't work"+error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("That didn't",""+error);


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                params.put("id",""+idd);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                params.put("Content-Type", "application/json");

                return params;
            }
        };
        mRequestQueue.add(req);




    }


}
