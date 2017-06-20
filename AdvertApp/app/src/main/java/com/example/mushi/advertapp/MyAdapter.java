package com.example.mushi.advertapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Advertisment> advertisments;
    Context context;
public MyAdapter(Context context,List<Advertisment> advertisments){
    this.context=context;
    this.advertisments=advertisments;

}


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView Name;
        TextView Price;
        ImageView Photo;
        TextView Location;


        public ViewHolder(View v) {

            super(v);

            cv = (CardView)itemView.findViewById(R.id.cv);
            Name = (TextView)itemView.findViewById(R.id.name);
            Price = (TextView)itemView.findViewById(R.id.price);
           Photo = (ImageView)itemView.findViewById(R.id.photo);
            Location = (TextView) itemView.findViewById(R.id.location);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Advertisment> advertisments) {
        this.advertisments = advertisments;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Name.setText(advertisments.get(i).name);
        holder.Price.setText("60000");
        Log.d("Image",advertisments.get(i).getPhotoId());
        Picasso.with(context)
                .load(advertisments.get(i).getPhotoId()).into(holder.Photo);
       // holder.Photo.setImageResource(advertisments.get(i).photoId);
holder.Location.setText(advertisments.get(i).location);
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


