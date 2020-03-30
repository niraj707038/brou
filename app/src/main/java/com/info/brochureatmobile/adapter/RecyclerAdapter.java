package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.info.brochureatmobile.Model.HomeModel;
import com.info.brochureatmobile.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListViewHolder> {

    Context context;
    ArrayList<HomeModel.Details> home;
    RecyclerItemClick click;

    private static final int TYPE_FULL = 0;
    private static final int TYPE_HALF = 1;
    private static final int TYPE_QUARTER = 2;
    private ArrayList<Drawable> images = new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<HomeModel.Details> home, RecyclerItemClick click) {
        this.context = context;
        this.home = home;
        this.click = click;
        for (int i = 0; i < 7; i++) {
            images.add(context.getResources().getDrawable(R.drawable.about_us));
            images.add(context.getResources().getDrawable(R.drawable.products));
            images.add(context.getResources().getDrawable(R.drawable.service));
            images.add(context.getResources().getDrawable(R.drawable.about_us));
            images.add(context.getResources().getDrawable(R.drawable.clients));
            images.add(context.getResources().getDrawable(R.drawable.representative));
            images.add(context.getResources().getDrawable(R.drawable.news));
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        //return new ListViewHolder(view);
        final View itemView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final int type = viewType;
                final ViewGroup.LayoutParams lp = itemView.getLayoutParams();
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
                    switch (type) {
                        case TYPE_FULL:
                            sglp.setFullSpan(true);
                            break;
                        case TYPE_HALF:
                            //sglp.setFullSpan(false);
                            sglp.width = itemView.getWidth();
                            sglp.height = itemView.getHeight() * 2;
                            break;
                        case TYPE_QUARTER:
                            sglp.setFullSpan(false);
                            sglp.width = itemView.getWidth();
                            sglp.height = itemView.getHeight();
                            break;
                    }

                    itemView.setLayoutParams(sglp);
                    final StaggeredGridLayoutManager lm =
                            (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
                    lm.invalidateSpanAssignments();
                }
                itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        ListViewHolder holder = new ListViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        try {
            if (home.get(position).id.equals("6")) {

                holder.tv_title.setText(home.get(position).title);
                if (!home.get(position).favcolor.equalsIgnoreCase("#000000")) {
                    holder.tv_title.setBackgroundColor(Color.parseColor(home.get(position).favcolor));
                } else {
                    holder.tv_title.setBackground(images.get(position));
                    //holder.tv_title.setBackground(context.getResources().getDrawable(R.drawable.background));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);

        final int modeThree = position % 4;
        switch (modeThree) {
            case 0:
                return TYPE_QUARTER;

            case 1:
                return TYPE_HALF;

            case 2:
                return TYPE_QUARTER;
        }
        return TYPE_FULL;
    }

    @Override
    public int getItemCount() {
        return home.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        // ImageView image_views;
        TextView tv_title;

        public ListViewHolder(View itemView) {

            super(itemView);
            // image_views = itemView.findViewById(R.id.image_views);
            tv_title = itemView.findViewById(R.id.tv_title);


            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClick(home.get(getAdapterPosition()).typeid);
                }
            });

        }
    }

    public interface RecyclerItemClick {
        public void itemClick(String typeid);
    }
}

