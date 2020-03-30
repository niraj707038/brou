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

import java.util.ArrayList;

public class RecyclerSubCategory extends RecyclerView.Adapter<RecyclerSubCategory.ListViewHolder> {

    Context context;
    ArrayList<CategoryModel.Data.Subcategory> mdata;
    RecyclerItemClick click;


    public RecyclerSubCategory(Context context, ArrayList<CategoryModel.Data.Subcategory> mdata, RecyclerItemClick click) {
        this.context = context;
        this.mdata = mdata;
        this.click=click;
    }

    @Override
    public RecyclerSubCategory.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_subcategory,parent,false);
        return new RecyclerSubCategory.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerSubCategory.ListViewHolder holder, int position) {

        //holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_home));
        Picasso.get()
                .load(/*Constant.BASE_IMAGE_URL+Constant.BASE_CATEGORY_URL+*/mdata.get(position).feature_image)
                .error(R.mipmap.ic_launcher)
                .into(holder. image);

        holder.tv_names.setText(mdata.get(position).sub_categoryname);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tv_names;

        public ListViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imagess);
            tv_names = itemView.findViewById(R.id.tv_names);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClick(getAdapterPosition());
                }
            });

        }
    }

   public interface RecyclerItemClick {
        public void itemClick(int position);
    }
}
