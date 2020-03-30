package com.info.brochureatmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.CategoryModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.RecyclerSubCategory;

import java.util.ArrayList;

public class FragmentSubCategory extends Fragment implements RecyclerSubCategory.RecyclerItemClick {
    ImageView view_photos;
    RecyclerView recyclerView;
    RecyclerSubCategory adapter;
    TextView tv_products;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_subcat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        view_photos = view.findViewById(R.id.view_photos);
        tv_products = view.findViewById(R.id.tv_products);

        getBundleData();


        adapter = new RecyclerSubCategory(getContext(), subcategories, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


        view_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();

            }
        });

        return view;
    }


    private ArrayList<CategoryModel.Data.Subcategory> subcategories = new ArrayList<>();

    private void getBundleData() {
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                category_id = bundle.getString("category_id");
                subcategories = (ArrayList<CategoryModel.Data.Subcategory>) bundle.getSerializable("subcategory_data"); // Key, default value
                cat_name = bundle.getString("cat_name");
                tv_products.setText(cat_name);
                Log.d("Bundle", subcategories.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String category_id, cat_name;


    @Override
    public void itemClick(int position) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", category_id);
        bundle.putString("sub_category_id", subcategories.get(position).subcategory_id);
        bundle.putString("sub_category_name", subcategories.get(position).sub_categoryname);
        ;
        ProductList frag = new ProductList();
        frag.setArguments(bundle);
        fragmentTransaction.addToBackStack(null).replace(R.id.frame_main, frag, "home").commit();
    }
}
