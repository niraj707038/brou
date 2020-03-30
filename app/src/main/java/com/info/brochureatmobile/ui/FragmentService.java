package com.info.brochureatmobile.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.ServiceModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.ServiceAdapter;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class FragmentService extends Fragment implements OnResponseInterface, ServiceAdapter.ServiceItemClick {

    RecyclerView recycler_list;
    private ServiceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment_service, container, false);
            init(view);
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    public void getData() {
        try {
            Call<ServiceModel> call = APIClient.getInstance().getApiInterface()
                    .getServiceData();
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void init(View v) {
        recycler_list = v.findViewById(R.id.recycler_list);
    }

    ServiceModel serviceModel;

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                if (response instanceof ServiceModel) {
                    serviceModel = (ServiceModel) response;
                    Log.d("", "");
                    adapter = new ServiceAdapter(getContext(), serviceModel, this);
                    recycler_list.setAdapter(adapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiFailure(String message) {
        try {
            Log.d("failure message:", message);
        } catch (Exception e) {

        }
    }

    @Override
    public void itemClick(int position) {

    }
}
