package com.info.brochureatmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

public class webactivityPager extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] image_url;

    public webactivityPager(Context context,String[] image_url) {
        this.context = context;
        this.image_url = image_url;

    }

    @Override
    public int getCount() {
        return image_url.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.view_image);

        Picasso.get()
                .load(image_url[position])
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;

        View view = (View) object;
        viewPager.removeView(view);

    }
}
