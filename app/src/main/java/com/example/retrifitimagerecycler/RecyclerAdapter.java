package com.example.retrifitimagerecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Anime> mData;
    private Context context;
    private RequestOptions options;

    public RecyclerAdapter(List<Anime> mData, Context context) {
        this.mData = mData;
        this.context = context;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anime_row,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AnimeActivity.class);

                intent.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                intent.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                intent.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                intent.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                intent.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                intent.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                context.startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategorie());

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(context).load(mData.get(position).getImage_url()).apply(options).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_rating, tv_studio, tv_category ;
        ImageView img_thumbnail;
        CardView view_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
