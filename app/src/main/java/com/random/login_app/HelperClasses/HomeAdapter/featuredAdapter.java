package com.random.login_app.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.random.login_app.R;

import java.util.ArrayList;

public class featuredAdapter extends RecyclerView.Adapter<featuredAdapter.FeaturedViewHolder> {
    //for values or text
    ArrayList<featuredHelperClass> featuredLocations;



    public featuredAdapter(ArrayList<featuredHelperClass> featuredLocation) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    // to hold the value from java
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        featuredHelperClass featuredHelperClass = featuredLocations.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    // for hold the design
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_description);

        }
    }

}
