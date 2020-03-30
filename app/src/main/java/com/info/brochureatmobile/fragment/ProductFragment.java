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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.info.brochureatmobile.Model.CategoryModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.RecyclerProduct;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class ProductFragment extends Fragment implements OnResponseInterface, RecyclerProduct.RecyclerProductItemClick {

    ImageView view_photo;
    TextView tv_product;

    private RecyclerProduct adapter;
    private RecyclerView staggerd;
    private StaggeredGridLayoutManager manger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.recyclerproduct, container, false);


            view_photo = view.findViewById(R.id.view_photo);
            tv_product = view.findViewById(R.id.tv_product);

            staggerd = view.findViewById(R.id.recycler_list);
            manger = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            staggerd.setLayoutManager(manger);


            getData();

            view_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    fragmentManager.popBackStack();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    public void itemClick(int position) {
        if (category.data.get(position).subcategoey_data.size() > 0) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("category_id",category.data.get(position).id);
            bundle.putSerializable("subcategory_data",category.data.get(position).subcategoey_data);
            bundle.putString("cat_name",category.data.get(position).categoryname);
            FragmentSubCategory frag=new FragmentSubCategory();
            frag.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_main,frag, "product").addToBackStack(null).commit();
        } else {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("category_id",category.data.get(position).id);
            ProductList frag=new ProductList();
            frag.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_main, frag, "product").addToBackStack(null).commit();
        }
    }

    public void getData() {
        try {
            Call<CategoryModel> call = APIClient.getInstance().getApiInterface()
                    .getCategoryData();
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    CategoryModel category = new CategoryModel();

    @Override
    public void onApiResponse(Object response) {

        try {
            if (response != null) {
                if (response instanceof CategoryModel) {
                    category = (CategoryModel) response;
                    Log.d("", "");
                    adapter = new RecyclerProduct(getContext(), category, this);
                    staggerd.setAdapter(adapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiFailure(String message) {

    }
}
