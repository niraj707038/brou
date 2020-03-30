package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.CategoryModel;
import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

public class RecyclerProduct extends RecyclerView.Adapter<RecyclerProduct.ListViewHolder> {

    Context context;
    CategoryModel category;
    RecyclerProductItemClick click;

    public RecyclerProduct(Context context, CategoryModel category, RecyclerProductItemClick click) {
        this.context = context;
        this.category = category;
        this.click = click;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        try {
            view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        try {
            holder.tv_name.setText(category.data.get(position).categoryname);
            Picasso.get()
                    .load(/*Constant.BASE_IMAGE_URL+Constant.BASE_CATEGORY_URL+*/category.data.get(position).feature_image)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.images);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return category.data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView images;
        TextView tv_name;

        public ListViewHolder(View itemView) {

            super(itemView);
            images = itemView.findViewById(R.id.images);
            tv_name = itemView.findViewById(R.id.tv_name);

            images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface RecyclerProductItemClick {
        public void itemClick(int position);
    }
}

