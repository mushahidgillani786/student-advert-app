package com.example.mushi.advertapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Advertisment> advertisments;
   static Context context;
  static   List<String> list;
    Intent intent;





public MyAdapter(Context context,List<Advertisment> advertisments){
    this.context=context;
    this.advertisments=advertisments;
    list=new ArrayList<>();


}


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        CardView cv,cv2;
        TextView Name;
        TextView Price;
        ImageView Photo;
        TextView Location;
        TextView date;
        CheckBox check;


        public ViewHolder(View v) {

            super(v);

            cv = (CardView)itemView.findViewById(R.id.cv);
            cv2 = (CardView)itemView.findViewById(R.id.cv2);
            Name = (TextView)itemView.findViewById(R.id.name);
            Price = (TextView)itemView.findViewById(R.id.price);
           Photo = (ImageView)itemView.findViewById(R.id.photo);
            Location = (TextView) itemView.findViewById(R.id.location);
date=(TextView) itemView.findViewById(R.id.date);
            check=(CheckBox)itemView.findViewById(R.id.check);


        }



    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context,String[] advertisments) {
        //this.advertisments = advertisments;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        // create a new view
        if (MainActivity.USER_TYPE.contains("admin")||MainActivity.USER_TYPE.contains("adminau")) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.checkbox_layout, parent, false);

        }else{

             v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_view_layout, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Name.setText(advertisments.get(i).name);
        holder.Price.setText(advertisments.get(i).price);
        Log.d("Image",advertisments.get(i).getPhotoId());
        Picasso.with(context)
                .load(advertisments.get(i).getPhotoId()).into(holder.Photo);


       // holder.Photo.setImageResource(advertisments.get(i).photoId);
holder.Location.setText(advertisments.get(i).location);
holder.date.setText(advertisments.get(i).date);

if (MainActivity.USER_TYPE.contains("admin")){
holder.check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (holder.check.isChecked())
        {


            list.add(Integer.toString(advertisments.get(i).getId()));
         //   Toast.makeText(context, ""+advertisments.get(i).getId(), Toast.LENGTH_SHORT).show();

        }
        else
        {



                list.remove(Integer.toString(advertisments.get(i).getId()));



        }



    }
});}
        if (MainActivity.USER_TYPE.contains("admin")||MainActivity.USER_TYPE.contains("adminau")) {

            holder.cv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent = new Intent(context, DetailView.class);
                    intent.putExtra("title", advertisments.get(i).name);
                    intent.putExtra("location", advertisments.get(i).getLocation());
                    intent.putExtra("id", advertisments.get(i).getId());
                    intent.putExtra("price", advertisments.get(i).getPrice());
                    intent.putExtra("description", advertisments.get(i).getDescription());
                    context.startActivity(intent);
                 //   Toast.makeText(context, "item clicked= " + i, Toast.LENGTH_SHORT).show();

                }
            });


        }
        else{

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent = new Intent(context, DetailView.class);
                    intent.putExtra("title", advertisments.get(i).name);
                    intent.putExtra("location", advertisments.get(i).getLocation());
                    intent.putExtra("id", advertisments.get(i).getId());
                    intent.putExtra("price", advertisments.get(i).getPrice());
                    intent.putExtra("description", advertisments.get(i).getDescription());
                    context.startActivity(intent);
                 //   Toast.makeText(context, "item clicked= " + i, Toast.LENGTH_SHORT).show();

                }
            });




        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return advertisments.size() ;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


