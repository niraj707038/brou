package com.info.brochureatmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.AboutUsModel;
import com.info.brochureatmobile.R;

import java.util.ArrayList;

public class AdapterAboutUs extends RecyclerView.Adapter<AdapterAboutUs.ListViewHolder> {

    Context context;
    ArrayList<AboutUsModel.Data> arrayaboutus;
    RecyclerItemClick click;


    public AdapterAboutUs(Context context, ArrayList<AboutUsModel.Data> arrayaboutus,RecyclerItemClick click) {
        this.context = context;
        this.arrayaboutus = arrayaboutus;
        this.click = click;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.aboutus_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAboutUs.ListViewHolder holder, int position) {

        try {
            holder.tv_title.setText(arrayaboutus.get(position).title);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return arrayaboutus.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        public ListViewHolder(View itemView) {

            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title.setOnClickListener(new View.OnClickListener() {
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



