package com.example.mushi.advertapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by mu on 7/20/17.
 */

public class AdminMain_Activity   extends AppCompatActivity {

Button view,delete,approve;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.adminmain_activity);


view=(Button)findViewById(R.id.button6);
        approve=(Button)findViewById(R.id.button7);
        delete=(Button)findViewById(R.id.button8);



    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

MainActivity.USER_TYPE="auser";
            startActivity(new Intent(AdminMain_Activity.this, AddPost.class));


        }
    });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.USER_TYPE="adminau";
                startActivity(new Intent(AdminMain_Activity.this, ListItemActivity.class));

            }
        });


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
MainActivity.USER_TYPE="admin";
                 startActivity(new Intent(AdminMain_Activity.this, ListItemActivity.class));



            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);


        SearchManager searchManager = (SearchManager) AdminMain_Activity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(AdminMain_Activity.this.getComponentName()));
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
