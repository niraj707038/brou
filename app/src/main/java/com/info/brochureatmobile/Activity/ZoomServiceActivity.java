package com.info.brochureatmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class ZoomServiceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_zoomimage,view_photos;
    TextView tvdiscription;
    String img, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_service);

        try {
            img_zoomimage = findViewById(R.id.img_zoomimage);
            tvdiscription = findViewById(R.id.tvdiscription);
            view_photos = findViewById(R.id.view_photos);
            view_photos.setOnClickListener(this);
            img = getIntent().getStringExtra("img");

            Picasso.get()
                    .load(Constant.BASE_IMAGE_URL + Constant.BASE_OTHERS_URL + img)
                    .error(R.mipmap.ic_launcher)
                    .into(img_zoomimage);


            description = getIntent().getStringExtra("disc");
            tvdiscription.setText(Html.fromHtml(description));

            //tvdiscription.setText(getDiscription);




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
