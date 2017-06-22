package com.example.mushi.advertapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddPost extends AppCompatActivity implements View.OnClickListener{
Button add;
    Intent intent;
    ImageButton mobiles,electronics,vehicles,bikes,propery,furniture,animals,fashion,
    kids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        electronics=(ImageButton)findViewById(R.id.electronics);
        vehicles=(ImageButton)findViewById(R.id.vehicles);
        bikes=(ImageButton)findViewById(R.id.bikes);
        propery=(ImageButton)findViewById(R.id.property);
        furniture=(ImageButton)findViewById(R.id.furniture);
        animals=(ImageButton)findViewById(R.id.animals);
        fashion=(ImageButton)findViewById(R.id.fashion);
        kids=(ImageButton)findViewById(R.id.kids);

        mobiles=(ImageButton)findViewById(R.id.mobiles);
        electronics.setOnClickListener(this);
        vehicles.setOnClickListener(this);
        bikes.setOnClickListener(this);
        propery.setOnClickListener(this);
        furniture.setOnClickListener(this);
        animals.setOnClickListener(this);
        fashion.setOnClickListener(this);
        kids.setOnClickListener(this);
        mobiles.setOnClickListener(this);
intent=new Intent(AddPost.this,ListItemActivity.class);
add=(Button)findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddPost.this,Post.class));



            }
        });







    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.animals:
                intent.putExtra("value",7);
                startActivity(intent);

                break;

            case R.id.bikes:
                intent.putExtra("value",4);
                startActivity(intent);

                break;

            case R.id.property:

                intent.putExtra("value",5);
                startActivity(intent);
                break;

            case R.id.kids:
                intent.putExtra("value",9);
                startActivity(intent);

                break;

            case R.id.fashion:
                intent.putExtra("value",8);
                startActivity(intent);

                break;

            case R.id.mobiles:
                intent.putExtra("value",1);
                startActivity(intent);

                break;

            case R.id.furniture:
                intent.putExtra("value",6);
                startActivity(intent);

                break;

            case R.id.vehicles:
                intent.putExtra("value",3);
                startActivity(intent);

                break;

            case R.id.electronics:
                intent.putExtra("value",2);
                startActivity(intent);

                break;




        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) AddPost.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(AddPost.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }


}
