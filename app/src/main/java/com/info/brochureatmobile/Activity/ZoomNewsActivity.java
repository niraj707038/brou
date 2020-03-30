package com.info.brochureatmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

public class ZoomNewsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_zoomimage, view_photos;
    TextView name, Discription;
    String img, getname, discription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_news_zoom);

            img_zoomimage = findViewById(R.id.img_zoomimage);
            name = findViewById(R.id.name);
            Discription = findViewById(R.id.Discription);
            view_photos = findViewById(R.id.view_photos);
            view_photos.setOnClickListener(this);

            img = getIntent().getStringExtra("img");
            Picasso.get()
                    .load(img)
                    .error(R.mipmap.ic_launcher)
                    .into(img_zoomimage);

            getname = getIntent().getStringExtra("name");
            name.setText(getname);

            discription = getIntent().getStringExtra("discription");
            Discription.setText(Html.fromHtml(discription));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.view_photos:
                    finish();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
