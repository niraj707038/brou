package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.ProductModel;
import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

public class RecyclerCategory extends RecyclerView.Adapter<RecyclerCategory.ListViewHolder> {

    Context context;
    ProductModel product;
    RecyclerItemClick click;

    public RecyclerCategory(Context context, ProductModel product, RecyclerItemClick click) {
        this.context = context;
        this.product = product;
        this.click = click;
    }

    @Override
    public RecyclerCategory.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_categery, parent, false);
        return new RecyclerCategory.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerCategory.ListViewHolder holder, int position) {

        holder.tv_prod_name.setText(product.data.get(position).productname);

      //  holder.tv_categery.setText(product.data.get(position).feature_description);
       holder.tv_categery.setText(HtmlCompat.fromHtml(product.data.get(position).feature_description, 0));
        //HtmlCompat.fromHtml(htmlText, 0)

        Picasso.get()
                .load(/*Constant.BASE_IMAGE_URL + Constant.BASE_CATEGORY_URL +*/ product.data.get(position).feature_image)
                .error(R.mipmap.ic_launcher)
                .into(holder.images);
    }


    @Override
    public int getItemCount() {
        return product.data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView images, newpage;
        TextView tv_prod_name, tv_categery;
        CardView card_product;

        public ListViewHolder(View itemView) {

            super(itemView);
            images = itemView.findViewById(R.id.imaqge);
            newpage = itemView.findViewById(R.id.newpage);
            tv_prod_name = itemView.findViewById(R.id.tv_prod_name);
            tv_categery = itemView.findViewById(R.id.tv_categery);
            card_product = itemView.findViewById(R.id.card_product);

            card_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClick {
        public void itemClick(int pos);
    }
}
