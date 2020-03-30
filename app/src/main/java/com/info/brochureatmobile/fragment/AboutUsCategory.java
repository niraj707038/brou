package com.info.brochureatmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Constant;
import com.squareup.picasso.Picasso;

public class AboutUsCategory extends Fragment {

    ImageView view_photo, img_about;
    TextView tv_product;
    WebView tv_about;
    String title, image, content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_us_category, container, false);
        init(v);
        getBundleData();
        setData();

        view_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return v;
    }

    private void getBundleData() {
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                title = bundle.getString("title");
                image = bundle.getString("image"); // Key, default value
                content = bundle.getString("content"); // Key, default value
                Log.d("Bundle", "");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        try {
            /*Gson gson=new Gson();
            gson.fromJson(content,null);*/

            //tv_about.setText(HtmlCompat.fromHtml(content, 0));
            tv_about.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
            tv_product.setText(title);
            Picasso.get()
                    .load(Constant.BASE_IMAGE_URL + Constant.BASE_CMS_URL + image)
                    .error(R.mipmap.ic_launcher)
                    .into(img_about);
        } catch (Exception e) {
            Log.d("error:", e.getMessage());
        }
    }

    private void init(View v) {
        view_photo = v.findViewById(R.id.view_photo);
        img_about = v.findViewById(R.id.img_about);
        tv_product = v.findViewById(R.id.tv_product);
        tv_about = v.findViewById(R.id.tv_about);
    }
}
