package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.NewsModel;
import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListViewHolder> {

    Context context;
    ArrayList<NewsModel.Data.News> nmodel;
    private NewsItemClick click;

    public NewsAdapter(Context context, ArrayList<NewsModel.Data.News> nmodel,NewsItemClick click) {
        try {
            this.context = context;
            this.nmodel = nmodel;
            this.click=click;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        try {
            view = LayoutInflater.from(context).inflate(R.layout.news, parent, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new NewsAdapter.ListViewHolder(view);
    }


    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.tv_prod_name.setText(nmodel.get(position).getName());
        holder.tv_categery.setText(nmodel.get(position).getFrom_date());
        holder.tv_date.setText(Html.fromHtml(nmodel.get(position).getDescription()));

        Picasso.get()
                .load(/*Constant.BASE_NEWS_EVENT_URL + Constant.BASE_NEWS_EVENT_URL +*/ nmodel.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .into(holder.imaqge);
    }

    @Override
    public int getItemCount() {
        return nmodel.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imaqge;
        TextView tv_prod_name;
        TextView tv_categery;
        TextView tv_date;
        TextView tv_ZECO;
        CardView card_product;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_prod_name = itemView.findViewById(R.id.tv_prod_name);
            tv_categery = itemView.findViewById(R.id.tv_categery);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_ZECO = itemView.findViewById(R.id.tv_ZECO);
            imaqge = itemView.findViewById(R.id.imaqge);
            card_product = itemView.findViewById(R.id.card_product);

            card_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface NewsItemClick {
        public void itemClick(int pos);

    }

}

