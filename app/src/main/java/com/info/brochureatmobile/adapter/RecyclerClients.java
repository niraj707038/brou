package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.info.brochureatmobile.Model.ClientsModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class RecyclerClients extends RecyclerView.Adapter<RecyclerClients.ListViewHolder> {

    Context context;
    ClientsModel clients;
    RecyclerItemClick click;

    public RecyclerClients(Context context, ClientsModel clients, RecyclerItemClick click) {
        this.context = context;
        this.clients = clients;
        this.click = click;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service, parent, false);
        return new RecyclerClients.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerClients.ListViewHolder holder, int position) {
        holder.service_names.setText(clients.data.get(position).name);

        Picasso.get()
                .load(Constant.BASE_IMAGE_URL + Constant.BASE_CLIENTS_URL + clients.data.get(position).logo)
                .error(R.mipmap.ic_launcher)
                .into(holder.service_images);

    }

    @Override
    public int getItemCount() {
        return clients.data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView service_images;
        TextView service_names;

        public ListViewHolder(View itemView) {
            super(itemView);

            service_images = itemView.findViewById(R.id.service_images);
            service_names = itemView.findViewById(R.id.service_names);
        }
    }

    public interface RecyclerItemClick {
        public void itemClick();
    }
}
