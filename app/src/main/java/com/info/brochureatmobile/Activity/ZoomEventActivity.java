package com.info.brochureatmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.info.brochureatmobile.R;
import com.squareup.picasso.Picasso;

public class ZoomEventActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_zoomimage,view_photos;
    TextView name, Discription, from_date, to_date;
    String img, getname, getDescription, getFrom_date, getTo_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_event);

        try {
            img_zoomimage = findViewById(R.id.img_zoomimage);
            name = findViewById(R.id.name);
            Discription = findViewById(R.id.Discription);
            from_date = findViewById(R.id.from_date);
            to_date = findViewById(R.id.to_date);
            view_photos = findViewById(R.id.view_photos);
            view_photos.setOnClickListener(this);
            img = getIntent().getStringExtra("img");


            Picasso.get()
                    .load(img)
                    .error(R.mipmap.ic_launcher)
                    .into(img_zoomimage);

            getname = getIntent().getStringExtra("name");
            name.setText(getname);

            getDescription = getIntent().getStringExtra("discription");
            Discription.setText(Html.fromHtml(getDescription));

            getFrom_date = getIntent().getStringExtra("from_date");
            from_date.setText(getFrom_date);

            getTo_date = getIntent().getStringExtra("to_date");
            to_date.setText(getTo_date);

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
