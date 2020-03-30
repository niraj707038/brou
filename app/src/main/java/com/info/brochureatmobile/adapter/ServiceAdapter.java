package com.info.brochureatmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.ServiceModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ListViewHolder> {

    Context context;
    ServiceModel serviceModel;
    ServiceItemClick click;

    public ServiceAdapter(Context context, ServiceModel serviceModel, ServiceItemClick click) {
        try {
            this.context = context;
            this.serviceModel = serviceModel;
            this.click = click;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            holder.tv_name.setText(serviceModel.data.get(position).name);
            Picasso.get()
                    .load(Constant.BASE_IMAGE_URL + Constant.BASE_OTHERS_URL + serviceModel.data.get(position).image)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.images);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return serviceModel.data.size();
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

    public interface ServiceItemClick {
        public void itemClick(int position);
    }
}

