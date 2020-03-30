package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.ServiceModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class RecyclerService extends RecyclerView.Adapter<RecyclerService.ListViewHolder> {

    Context context;
    ServiceModel services;
    private RecyclerItemClick click;

    public RecyclerService(Context context, ServiceModel services, RecyclerItemClick click) {
        this.context = context;
        this.services = services;
        this.click = click;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service, parent, false);
        return new RecyclerService.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerService.ListViewHolder holder, int position) {

        holder.service_names.setText(services.data.get(position).name);

        Picasso.get()
                .load(Constant.BASE_IMAGE_URL + Constant.BASE_OTHERS_URL + services.data.get(position).image)
                .error(R.mipmap.ic_launcher)
                .into(holder.service_images);

    }

    @Override
    public int getItemCount() {
        return services.data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView service_images;
        TextView service_names;
        CardView card_product;

        public ListViewHolder(View itemView) {
            super(itemView);
            service_images = itemView.findViewById(R.id.service_images);
            service_names = itemView.findViewById(R.id.service_names);
            card_product = itemView.findViewById(R.id.card_product);

            card_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.itemClick(getAdapterPosition());
                }
            });
        }


    }

    public interface RecyclerItemClick {
        public void itemClick(int getAdapterPosition);
    }
}
