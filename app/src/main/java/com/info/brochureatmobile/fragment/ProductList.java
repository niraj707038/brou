package com.info.brochureatmobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Activity.WebViewActivity;
import com.info.brochureatmobile.Model.ProductModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.RecyclerCategory;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class ProductList extends Fragment implements RecyclerCategory.RecyclerItemClick, OnResponseInterface {

    ImageView view_photos;
    TextView tv_products;
    RecyclerView recycl_list;
    RecyclerCategory adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_categ, container, false);

        view_photos = view.findViewById(R.id.view_photos);
        tv_products = view.findViewById(R.id.tv_products);
        recycl_list = view.findViewById(R.id.recycl_list);

        getBundleData();
        getData();

        view_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    private String subcategory_id = "";
    private String category_id = "", sub_cat_name;

    private void getBundleData() {
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                category_id = bundle.getString("category_id");
                subcategory_id = bundle.getString("sub_category_id"); // Key, default value
                sub_cat_name = bundle.getString("sub_category_name"); // Key, default value
                tv_products.setText(sub_cat_name);
                Log.d("Bundle", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getData() {
        try {
            if (subcategory_id == null) {
                subcategory_id = "";
            }
            Call<ProductModel> call = APIClient.getInstance().getApiInterface().getProductList(category_id, subcategory_id);
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ProductModel productModel;

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                productModel = (ProductModel) response;
                adapter = new RecyclerCategory(getContext(),productModel, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recycl_list.setLayoutManager(mLayoutManager);
                recycl_list.setAdapter(adapter);
                Log.d("products:", productModel.toString());
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onApiFailure(String message) {
        try {
            Log.d("failure message", message);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void itemClick(int pos) {
        Intent ittt = new Intent(ProductList.super.getContext(), WebViewActivity.class);
        ittt.putExtra("url", productModel.data.get(pos).productDetailUrl);
        ittt.putExtra("name", productModel.data.get(pos).productname);
        ittt.putExtra("image", productModel.data.get(pos).productImage);
        ittt.putExtra("pid", productModel.data.get(pos).id);

        startActivity(ittt);
    }

}

