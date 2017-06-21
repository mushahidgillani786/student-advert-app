package com.example.mushi.advertapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegActivity extends AppCompatActivity {

    EditText fname, lname, email, password, cpassword;
    Button button;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fname = (EditText) findViewById(R.id.editText);
        lname = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        cpassword = (EditText) findViewById(R.id.editText5);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFieldSet()) {
                    sendData();
                    startActivity(new Intent(RegActivity.this, MainActivity.class));


                } else {

                    Toast.makeText(RegActivity.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();


                }


            }
        });


    }

    boolean isFieldSet() {

        if (fname.getText().toString().equals("") || lname.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || cpassword.getText().toString().equals("")) {
            return false;
        } else {


            if (validate(email.getText().toString())) {
                if (password.length() < 5) {
                    Toast.makeText(this, "Minimum password length is 5", Toast.LENGTH_SHORT).show();
                } else {
                    return true;
                }
            } else {
                Toast.makeText(this, "Please type valid Email Address", Toast.LENGTH_SHORT).show();
                return false;
            }


        }
return false;
    }




    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    void sendData(){

        RequestQueue queue= Volley.newRequestQueue(this);
       // String url="http://192.168.0.143/Advert/registeration.php";
        StringRequest sr = new StringRequest(Request.Method.POST,config.REGISTRATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(RegActivity.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegActivity.this,MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("firstname",fname.getText().toString());
                params.put("lastname",lname.getText().toString());
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                params.put("cpassword",cpassword.getText().toString());


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
