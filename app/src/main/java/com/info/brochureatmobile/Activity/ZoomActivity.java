package com.info.brochureatmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class ZoomActivity extends AppCompatActivity {

    ImageView img_zoomimage;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_zoom);

            img_zoomimage = findViewById(R.id.img_zoomimage);
            img = getIntent().getStringExtra("img");

            DisplayMetrics dm=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width=dm.widthPixels;
            int height=dm.heightPixels;

            getWindow().setLayout((int)(width*1.0),(int)(height*.8));

            Picasso.get()
                    .load(Constant.BASE_IMAGE_URL + Constant.BASE_OTHERS_URL + img)
                    .error(R.mipmap.ic_launcher)
                    .into(img_zoomimage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
