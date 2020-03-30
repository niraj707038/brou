package com.info.brochureatmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.info.brochureatmobile.Model.HomeModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    ArrayList<HomeModel.Details> homeview;

    //private Integer[] images = {R.drawable.askonline, R.drawable.hacker, R.drawable.img2, R.drawable.hackker};

    public ViewPagerAdapter(Context context, ArrayList<HomeModel.Details> homeview) {
        this.context = context;
        this.homeview = homeview;
    }

    @Override
    public int getCount() {
        return homeview.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);

        ImageView imageView = view.findViewById(R.id.view_image);
        // imageView.setImageResource(images[position]);

        //imageView.setImageResource(Integer.parseInt(homeview.get(position).banner_image));
        Picasso.get()
                .load(Constant.BANNER_IMG_URL+homeview.get(position).banner_image)
                .error(R.mipmap.ic_launcher)
                .into(imageView);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
