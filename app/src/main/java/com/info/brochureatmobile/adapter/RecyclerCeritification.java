package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.R;
import com.info.brochureatmobile.Model.CertificationModel;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class RecyclerCeritification extends RecyclerView.Adapter<RecyclerCeritification.ListViewHolder> {

    Context context;
    CertificationModel certification;
    RecyclerItemClick click;

    public RecyclerCeritification(Context context, CertificationModel certification,RecyclerItemClick click) {
        this.context = context;
        this.certification = certification;
        this.click=click;
    }

    @Override
    public ListViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service, parent, false);
        return new RecyclerCeritification.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerCeritification.ListViewHolder holder, int position) {

        holder.service_names.setText(certification.data.get(position).name);

        Picasso.get()
                .load(Constant.BASE_IMAGE_URL+Constant.BASE_OTHERS_URL+certification.data.get(position).image)
                .error(R.mipmap.ic_launcher)
                .into(holder.service_images);

    }

    @Override
    public int getItemCount() {
        return certification.data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView service_images;
        TextView service_names;

        public ListViewHolder(View itemView){
            super(itemView);

            service_images = itemView.findViewById(R.id.service_images);
            service_names=itemView.findViewById(R.id.service_names);

            service_images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.itemClick(getAdapterPosition());
                }
            });
        }


    }

    public interface RecyclerItemClick {
        public void itemClick(int pos);
    }
}
