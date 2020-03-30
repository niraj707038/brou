package com.info.brochureatmobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.github.clans.fab.FloatingActionButton;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.Widget.AutoScrollViewPager;
import com.info.brochureatmobile.adapter.webactivityPager;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    WebView web_link;
    String url = "", image_url = "", name = "", pid = "";
    AutoScrollViewPager mViewPager;
    LinearLayout sliderdots;
    private int discount;
    private ImageView[] dots;
    private webactivityPager viewPagerAdapter;
    ImageView img_back;
    TextView tv_title;
    String[] image_list;

    FloatingActionButton fabButton, fabButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.webviewactivity);


            init();
            getValue();
            img_back.setOnClickListener(this);
            if (!url.equalsIgnoreCase(""))
                web_link.loadUrl(url);


            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    for (int i = 0; i < discount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(WebViewActivity.this, R.drawable.nonactive_dot));

                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(WebViewActivity.this, R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getValue() {
        try {
            url = getIntent().getStringExtra("url");
            image_url = getIntent().getStringExtra("image");
            name = getIntent().getStringExtra("name");
            pid = getIntent().getStringExtra("pid");
            if (!name.equalsIgnoreCase(""))
                tv_title.setText(name);
            if (!image_url.equalsIgnoreCase("")) {
                image_list = image_url.split(",");
            }
            setViewPager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewPager() {
        try {
            viewPagerAdapter = new webactivityPager(this, image_list);
            mViewPager.startAutoScroll();
            mViewPager.setInterval(4000);
            mViewPager.setCycle(true);
            mViewPager.setStopScrollWhenTouch(true);
            mViewPager.setAdapter(viewPagerAdapter);
            discount = viewPagerAdapter.getCount();
            dots = new ImageView[discount];

            for (int i = 0; i < discount; i++) {
                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nonactive_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 0, 5, 0);

                sliderdots.addView(dots[i], params);
            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            web_link = findViewById(R.id.web_link);
            mViewPager = findViewById(R.id.mViewPager);
            sliderdots = findViewById(R.id.sliderdots);
            img_back = findViewById(R.id.img_back);
            tv_title = findViewById(R.id.tv_title);
            fabButton = findViewById(R.id.fabButton);
            fabButton1 = findViewById(R.id.fabButton1);

            fabButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(WebViewActivity.this,"Call back request",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String pName = name;
                    String pUrl = url;
                    String shareSub = "Product info";
                    intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    intent.putExtra(Intent.EXTRA_TEXT, pName);
                    intent.putExtra(Intent.EXTRA_TEXT,pUrl);
                    startActivity(Intent.createChooser(intent, "Share using"));
                }
            });

            fabButton1.setOnClickListener(new View.OnClickListener() {
                // private boolean massage;


                @Override
                public void onClick(View v) {
                    try {

                        Intent intent = new Intent(WebViewActivity.this, EnquiryOption.class);
                        intent.putExtra("name", name);
                        intent.putExtra("pid", pid);
                        startActivity(intent);
                        Log.d("", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        this.finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}

