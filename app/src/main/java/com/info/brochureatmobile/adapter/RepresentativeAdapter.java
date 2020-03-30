package com.info.brochureatmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.RepresentativeModel;
import com.info.brochureatmobile.R;

public class RepresentativeAdapter extends RecyclerView.Adapter<RepresentativeAdapter.ListViewHolder> {

    Context context;
    RecyclerItemClick click;
    RepresentativeModel product;

    public RepresentativeAdapter(Context context, RepresentativeModel product, RecyclerItemClick click) {
        this.context = context;
        this.product = product;
        this.click = click;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepresentativeAdapter.ListViewHolder holder, int position) {

        if (product.data.ourrepresentative.get(position).email.equalsIgnoreCase(""))
            holder.tv_email.setText(product.data.ourrepresentative.get(position).email);
        else
            holder.ll_email.setVisibility(View.GONE);
        if (!product.data.ourrepresentative.get(position).office_phone2.equalsIgnoreCase(""))
            holder.tv_phn2.setText(product.data.ourrepresentative.get(position).office_phone2);
        else
            holder.ll_phone2.setVisibility(View.GONE);

        if (!product.data.ourrepresentative.get(position).office_phone1.equalsIgnoreCase(""))
            holder.tv_phn1.setText(product.data.ourrepresentative.get(position).office_phone1);
        else
            holder.ll_phone1.setVisibility(View.GONE);

        if (!product.data.ourrepresentative.get(position).fax.equalsIgnoreCase("")) {
            holder.tv_fax.setText(product.data.ourrepresentative.get(position).fax);
        } else holder.ll_fax.setVisibility(View.GONE);

        if (!product.data.ourrepresentative.get(position).mobile.equalsIgnoreCase("")) {
            holder.tv_mobile.setText(product.data.ourrepresentative.get(position).mobile);
        } else holder.ll_mobile.setVisibility(View.GONE);


        //holder.tv_email.setText(product.data.ourrepresentative.get(position).email);

        holder.tv_address.setText(product.data.ourrepresentative.get(position).address);

        holder.tv_name.setText(product.data.ourrepresentative.get(position).name);

        holder.tv_zone.setText(product.data.ourrepresentative.get(position).territory);

        holder.tv_cmp_name.setText(product.data.ourrepresentative.get(position).company_name);


        //      holder.tv_email.setText(Html.fromHtml("<a href=\"mailto:ask@me.it\">Send Feedback</a>"));
//        feedback.setMovementMethod(LinkMovementMethod.getInstance())

    }


    @Override
    public int getItemCount() {
        return product.data.ourrepresentative.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_email, tv_phn1, tv_phn2, tv_address, tv_name, tv_mobile, tv_zone, tv_cmp_name, tv_fax;
        CardView card_rep;
        LinearLayout ll_fax, ll_mobile, ll_phone1, ll_phone2, ll_email;

        public ListViewHolder(View itemView) {

            super(itemView);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_phn2 = itemView.findViewById(R.id.tv_phn2);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_phn1 = itemView.findViewById(R.id.tv_phn1);
            card_rep = itemView.findViewById(R.id.card_rep);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_zone = itemView.findViewById(R.id.tv_zone);
            tv_cmp_name = itemView.findViewById(R.id.tv_cmp_name);
            tv_fax = itemView.findViewById(R.id.tv_fax);
            ll_email = itemView.findViewById(R.id.ll_email);
            ll_phone2 = itemView.findViewById(R.id.ll_phone2);
            ll_phone1 = itemView.findViewById(R.id.ll_phone1);
            ll_mobile = itemView.findViewById(R.id.ll_mobile);
            ll_fax = itemView.findViewById(R.id.ll_fax);

            tv_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.emailClick(getAdapterPosition());
                }
            });
            tv_mobile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.mobileClick(getAdapterPosition());
                }
            });
            tv_phn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.phn1Click(getAdapterPosition());

                   /* String mobile = tv_phn1.getText().toString();
                    if (mobile.isEmpty()){

                        System.out.println("enter phone Number");
                       // Toast.makeText(getAdapterPosition(),"plese enter call",Toast.LENGTH_SHORT).show();
                    }else {
                        String s = "tel" + mobile;
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(s));
                        getAdapterPosition();
                    }*/

                }
            });
            tv_phn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.phn2Click(getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClick {
        public void emailClick(int pos);

        public void mobileClick(int pos);

        public void phn1Click(int pos);

        public void phn2Click(int pos);
    }
}
