package com.example.mushi.advertapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
TextView signup;
    EditText email,password;
    Button signin;

    Context myContext=MainActivity.this;
    static LoginSession session;
    static String USER_TYPE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
email=(EditText)findViewById(R.id.editText6);
        password=(EditText)findViewById(R.id.editText7);
        signin=(Button)findViewById(R.id.button2);
signup=(TextView)findViewById(R.id.textView2);
        session=new LoginSession(MainActivity.this);
        HashMap<String, String> user = new HashMap<String, String>();
     user=session.getUserDetails();
        USER_TYPE=user.get(LoginSession.KEY_TYPE);

//String check="\nadmin\n";
  //      USER_TYPE=check;
        if (session.isLoggedIn()){
            if (USER_TYPE.contains("admin")) {

                startActivity(new Intent(MainActivity.this, ListItemActivity.class));
                finish();


            }else{

                startActivity(new Intent(MainActivity.this, AddPost.class));
                finish();


            }

        }



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(myContext,RegActivity.class));


            }
        });

signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (email.getText().toString().equals("")||password.getText().toString().equals("")){

            Toast.makeText(myContext, "Please enter the Email and Password", Toast.LENGTH_SHORT).show();


        }else{

            sendData();



        }


    }
});






    }


    void sendData(){

        RequestQueue queue= Volley.newRequestQueue(this);
       // String url="http://192.168.0.143/Advert/login.php";
        StringRequest sr = new StringRequest(Request.Method.POST,config.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//if(response.equals("\nsuccess\n")) {
if(!response.contains("fail")){

    Toast.makeText(myContext, response + " === Login success", Toast.LENGTH_SHORT).show();
                session.createLoginSession("",email.getText().toString(),response);
                USER_TYPE=response;
                if (response.contains("admin")){

                    startActivity(new Intent(MainActivity.this, ListItemActivity.class));
                    finish();


                }
                else {

                    startActivity(new Intent(MainActivity.this, AddPost.class));
                    finish();



                }

}


else{
   Toast.makeText(myContext, "Invalid Email/Password", Toast.LENGTH_SHORT).show();


}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());



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
