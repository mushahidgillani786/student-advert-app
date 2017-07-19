package com.example.mushi.advertapp;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class ListItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
   static Button done;
    static Button delete;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Advertisment> advertisment;


    Context context=ListItemActivity.this;
    private String customFetch;
    private String value="false";
    private String searchValue;
    private boolean flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_list_item);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
done=(Button)findViewById(R.id.done);
        delete=(Button)findViewById(R.id.delete);
        done.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        advertisment = new ArrayList<>();

        handleIntent(getIntent());

        Bundle bundle=getIntent().getExtras();
        if (bundle==null) {
Log.d("VALUE",""+value);

        }
        else {


            value=bundle.getString("value");
          //  if (value==null){
          //      value=searchValue;


//            }
        }

        if (MainActivity.USER_TYPE.contains("admin")){

done.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            receivePendingList();

            mAdapter = new MyAdapter(context, advertisment);

            mRecyclerView.setAdapter(mAdapter);

        }

        else{


            if(flag!=true) {

                recieveData();

                mAdapter = new MyAdapter(context, advertisment);

                mRecyclerView.setAdapter(mAdapter);



            }


        }





delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        String[] sintegers={""};
        sintegers=new  String[MyAdapter.list.size()];
        if (!MyAdapter.list.isEmpty()){
            for (int i=0;i<MyAdapter.list.size();i++){


                sintegers[i]=MyAdapter.list.get(i);





            }


            String ssintegers= Arrays.toString(sintegers);
            String[] s1=ssintegers.split(Pattern.quote("["));
            String string1="";
            String string2="";
            for (int i=0;i<s1.length;i++){

                string1+=s1[i];

            }

            String[] s2=string1.split(Pattern.quote("]"));
            for (int i=0;i<s2.length;i++){

                string2+=s2[i];

            }
            StringBuilder _sb = new StringBuilder();
            _sb.insert(0,string2);
            String string3 =","+_sb;
            // int integer =Integer.parseInt(string3);

            deletePost(string3);

            if (advertisment.size()!=0){
                int s=advertisment.size();
                for (int i=0;i<s;i++) {
                    advertisment.remove(0);
                }
            }
            try {
                Thread.sleep(2000);
            }catch (Exception e){

                e.printStackTrace();

            }
            receivePendingList();


            mAdapter = new MyAdapter(context, advertisment);

            mRecyclerView.setAdapter(mAdapter);


            mAdapter.notifyDataSetChanged();




        }


    }
});


done.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

       String[] sintegers={""};
        sintegers=new  String[MyAdapter.list.size()];
if (!MyAdapter.list.isEmpty()){
for (int i=0;i<MyAdapter.list.size();i++){


    sintegers[i]=MyAdapter.list.get(i);





}


String ssintegers= Arrays.toString(sintegers);
    String[] s1=ssintegers.split(Pattern.quote("["));
    String string1="";
    String string2="";
    for (int i=0;i<s1.length;i++){

        string1+=s1[i];

    }

    String[] s2=string1.split(Pattern.quote("]"));
    for (int i=0;i<s2.length;i++){

        string2+=s2[i];

    }
    StringBuilder _sb = new StringBuilder();
_sb.insert(0,string2);
   String string3 =","+_sb;
   // int integer =Integer.parseInt(string3);

    changeStatus(string3);

    if (advertisment.size()!=0){
        int s=advertisment.size();
        for (int i=0;i<s;i++) {
            advertisment.remove(0);
        }
    }
    try {
        Thread.sleep(2000);
    }catch (Exception e){

        e.printStackTrace();

    }
        receivePendingList();


    mAdapter = new MyAdapter(context, advertisment);

    mRecyclerView.setAdapter(mAdapter);


    mAdapter.notifyDataSetChanged();




}



    }
});


    }




    void recieveData(){




        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
       RequestQueue mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        //RequestQueue queue= Volley.newRequestQueue(this);
      //String url="http://192.168.0.145/Advert/fetch.php";
        JsonArrayRequest req = new JsonArrayRequest(config.fetch+"?value="+value,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ListItemActivity", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                           String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                         JSONObject jsonObject=response.getJSONObject(i);



                                advertisment.add(new Advertisment(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("price"),jsonObject.getString("image"),jsonObject.getString("location"),jsonObject.getString("description"),jsonObject.getString("date"),jsonObject.getString("phone")));

                            }
                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ListActivityError", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "No posts to display", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(req);




    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
      mAdapter.notifyDataSetChanged();




    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent2(Intent intent)
    {



    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
if (advertisment.size()!=0){
    int s=advertisment.size();
for (int i=0;i<s;i++) {
    advertisment.remove(0);
}
}
            //Toast.makeText(context, ""+query, Toast.LENGTH_SHORT).show();
searchValue=query;
            if (!searchValue.isEmpty())
            flag=true;
            receiveSearchData();
            mAdapter = new MyAdapter(context, advertisment);

            mRecyclerView.setAdapter(mAdapter);


               mAdapter.notifyDataSetChanged();

            //Intent intent1=new Intent();
            //intent1.putExtra("value",query);



        }
    }

void receiveSearchData(){


    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
    Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
    RequestQueue mRequestQueue = new RequestQueue(cache, network);

// Start the queue
    mRequestQueue.start();

    //RequestQueue queue= Volley.newRequestQueue(this);
    //String url="http://192.168.0.145/Advert/fetch.php";
    JsonArrayRequest req = new JsonArrayRequest(config.search+"?value="+searchValue,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("ListItemActivity", response.toString());

                    try {
                        // Parsing json array response
                        // loop through each json object
                        String jsonResponse = "";
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject jsonObject=response.getJSONObject(i);

                            advertisment.add(new Advertisment(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("price"),jsonObject.getString("image"),jsonObject.getString("location"),jsonObject.getString("description"),jsonObject.getString("date"),jsonObject.getString("phone")));

                        }
                        mAdapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d("ListActivityError", "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),
                    "No posts to display", Toast.LENGTH_SHORT).show();
        }
    });
    mRequestQueue.add(req);







}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) ListItemActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ListItemActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }



    void receivePendingList(){


        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        //RequestQueue queue= Volley.newRequestQueue(this);
        //String url="http://192.168.0.145/Advert/fetch.php";
        JsonArrayRequest req = new JsonArrayRequest(config.pending,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ListItemActivity", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject=response.getJSONObject(i);

                                advertisment.add(new Advertisment(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("price"),jsonObject.getString("image"),jsonObject.getString("location"),jsonObject.getString("description"),jsonObject.getString("date"),jsonObject.getString("phone")));

                            }
                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ListActivityError", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "No pending posts to display", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(req);







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            MainActivity.session.logoutUser();
            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    void changeStatus(final String status){




        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        RequestQueue queue = new RequestQueue(cache, network);

// Start the queue
        queue.start();

        //RequestQueue queue= Volley.newRequestQueue(this);
        //String url="http://192.168.0.145/Advert/fetch.php";
        StringRequest sr = new StringRequest(Request.Method.POST,config.change_status, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//if(response.equals("\nsuccess\n")) {
mAdapter.notifyDataSetChanged();
                Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

              params.put("value",status);



                return params;
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

    void deletePost(final String status){




        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        RequestQueue queue = new RequestQueue(cache, network);

// Start the queue
        queue.start();

        //RequestQueue queue= Volley.newRequestQueue(this);
        //String url="http://192.168.0.145/Advert/fetch.php";
        StringRequest sr = new StringRequest(Request.Method.POST,config.delete_post, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//if(response.equals("\nsuccess\n")) {
                mAdapter.notifyDataSetChanged();
                Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("value",status);



                return params;
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
