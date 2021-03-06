package com.example.mushi.advertapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;



public class AddPost extends AppCompatActivity implements View.OnClickListener{
Button add;
    Intent intent;
    ImageButton mobiles,electronics,vehicles,bikes,property,furniture,animals,fashion,
    kids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        electronics=(ImageButton)findViewById(R.id.electronics);
        vehicles=(ImageButton)findViewById(R.id.vehicles);
        bikes=(ImageButton)findViewById(R.id.bikes);
        property=(ImageButton)findViewById(R.id.property);
        furniture=(ImageButton)findViewById(R.id.furniture);
        animals=(ImageButton)findViewById(R.id.animals);
        fashion=(ImageButton)findViewById(R.id.fashion);
        kids=(ImageButton)findViewById(R.id.kids);

        mobiles=(ImageButton)findViewById(R.id.mobiles);
        electronics.setOnClickListener(this);
        vehicles.setOnClickListener(this);
        bikes.setOnClickListener(this);
        property.setOnClickListener(this);
        furniture.setOnClickListener(this);
        animals.setOnClickListener(this);
        fashion.setOnClickListener(this);
        kids.setOnClickListener(this);
        mobiles.setOnClickListener(this);
intent=new Intent(AddPost.this,ListItemActivity.class);
add=(Button)findViewById(R.id.button3);

        if (MainActivity.USER_TYPE.contains("auser")){
            add.setVisibility(View.INVISIBLE);

        }


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
                intent.putExtra("value","Animals");
                startActivity(intent);

                break;

            case R.id.bikes:
                intent.putExtra("value","Bikes");
                startActivity(intent);

                break;

            case R.id.property:

                intent.putExtra("value","PropertySale");
                startActivity(intent);
                break;

            case R.id.kids:
                intent.putExtra("value","Kids");
                startActivity(intent);

                break;

            case R.id.fashion:
                intent.putExtra("value","FashionDesign");
                startActivity(intent);

                break;

            case R.id.mobiles:
                intent.putExtra("value","Mobiles");
                startActivity(intent);

                break;

            case R.id.furniture:
                intent.putExtra("value","Furniture");
                startActivity(intent);

                break;

            case R.id.vehicles:
                intent.putExtra("value","Vehicles");
                startActivity(intent);

                break;

            case R.id.electronics:
                intent.putExtra("value","Electronics");
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

}
