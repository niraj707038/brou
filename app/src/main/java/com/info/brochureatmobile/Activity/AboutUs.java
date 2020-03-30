package com.info.brochureatmobile.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.AboutUsModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.AdapterAboutUs;
import com.info.brochureatmobile.fragment.AboutUsCategory;
import com.info.brochureatmobile.util.Constant;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;

public class AboutUs extends Fragment implements View.OnClickListener, OnResponseInterface, AdapterAboutUs.RecyclerItemClick {

    private ImageView view_photo, img_about;
    private TextView tv_about, tv_title;
    private RecyclerView recycler_aboutus;
    private AdapterAboutUs adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us, container, false);
        init(view);
        getData();
        view_photo.setOnClickListener(this);
        //getActivity().findViewById(R.id.btn_callBack).setVisibility(View.GONE);
        return view;
    }

    private void getData() {
        try {
            Call<AboutUsModel> call = APIClient.getInstance().getApiInterface()
                    .getAboutUs("1");
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(View view) {
        view_photo = view.findViewById(R.id.view_photo);
        img_about = view.findViewById(R.id.img_about);
        tv_about = view.findViewById(R.id.tv_about);
        recycler_aboutus = view.findViewById(R.id.recycler_aboutus);
        tv_title = view.findViewById(R.id.tv_title);
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }

    AboutUsModel aboutUsModel;

    ArrayList<AboutUsModel.Data> arrayaboutus = null;

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                aboutUsModel = (AboutUsModel) response;
                Log.d("aboutus", aboutUsModel.toString());
                if (aboutUsModel.data.size() > 0) {
                    arrayaboutus = new ArrayList<>();
                    for (AboutUsModel.Data od : aboutUsModel.data) {
                        if (od.parent_id.equalsIgnoreCase("1")) {
                            arrayaboutus.add(od);
                        }
                        if (od.parent_id.equalsIgnoreCase("0")) {
                            tv_title.setText(HtmlCompat.fromHtml(od.title, 0));
                            tv_about.setText(HtmlCompat.fromHtml(od.content, 0));
                            Picasso.get()
                                    .load(Constant.BASE_IMAGE_URL + Constant.BASE_CMS_URL + od.bannerImg)
                                    .error(R.mipmap.ic_launcher)
                                    .into(img_about);
                        }
                    }
                    adapter = new AdapterAboutUs(getContext(), arrayaboutus, this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recycler_aboutus.setLayoutManager(mLayoutManager);
                    recycler_aboutus.setAdapter(adapter);
                }
                Log.d("", "");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onApiFailure(String message) {
        try {
            Log.d("aboutus", message);

        } catch (Exception e) {

        }
    }


    @Override
    public void itemClick(int position) {

        try {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("title", arrayaboutus.get(position).title);
            bundle.putString("image", arrayaboutus.get(position).bannerImg);
            bundle.putString("content", arrayaboutus.get(position).content);
            AboutUsCategory frag = new AboutUsCategory();
            frag.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_main, frag, "product").addToBackStack(null).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
