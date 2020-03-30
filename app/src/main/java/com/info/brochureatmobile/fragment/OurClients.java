package com.info.brochureatmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.info.brochureatmobile.Model.ClientsModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.RecyclerClients;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class OurClients extends Fragment implements RecyclerClients.RecyclerItemClick, OnResponseInterface {

    private RecyclerView staggerd;
    private RecyclerClients adapter;
    private StaggeredGridLayoutManager manger;

    ImageView view_photos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clients, container, false);


        view_photos = view.findViewById(R.id.view_photos);

        staggerd = view.findViewById(R.id.recycl_list);
        manger = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggerd.setLayoutManager(manger);

        view_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        getData();

        return view;
    }

    public void getData() {
        try {
            Call<ClientsModel> call = APIClient.getInstance().getApiInterface()
                    .getOurClientsData();
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ClientsModel clients = new ClientsModel();

    @Override
    public void onApiResponse(Object response) {

        try {
            if (response != null) {
                if (response instanceof ClientsModel) {
                    clients = (ClientsModel) response;
                    Log.d("", "");
                    adapter = new RecyclerClients(getContext(), clients, this);
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

    @Override
    public void itemClick() {

    }
}
