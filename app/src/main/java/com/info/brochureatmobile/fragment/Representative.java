package com.info.brochureatmobile.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Model.RepresentativeModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.RepresentativeAdapter;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class Representative extends Fragment implements OnResponseInterface, RepresentativeAdapter.RecyclerItemClick {

    ImageView view_photos;
    RecyclerView recycl_list;
    RepresentativeAdapter adapter;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.representative, container, false);
        init(v);
        getData();
        view_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            // dial.setEnabled(true);
        } else {
            //dial.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }


        return v;
    }

    private void getData() {
        try {
            Call<RepresentativeModel> call = APIClient.getInstance().getApiInterface()
                    .getRepresentativeData();
            call.request().url();
            Log.e("MyUrl", call.request().url() + "");
            new ResponseListner(this).getResponse(call);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(View v) {
        view_photos = v.findViewById(R.id.view_photos);
        recycl_list = v.findViewById(R.id.recycl_list);
    }

    RepresentativeModel representativeModel = new RepresentativeModel();

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                representativeModel = (RepresentativeModel) response;

                adapter = new RepresentativeAdapter(getContext(), representativeModel, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recycl_list.setLayoutManager(mLayoutManager);
                recycl_list.setAdapter(adapter);

                Log.d("", "");

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onApiFailure(String message) {
        try {

        } catch (Exception e) {
            e.getMessage();
        }
    }


    @Override
    public void emailClick(int pos) {
        try {

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void mobileClick(int pos) {
        try {

            if (checkPermission(Manifest.permission.CALL_PHONE)) {
                String[] ob = representativeModel.data.ourrepresentative.get(pos).mobile.split("-");
                String dial = "tel:" + ob[0] + ob[1];
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(getContext(), "Please grant call permission", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void phn1Click(int pos) {
        try {

            if (checkPermission(Manifest.permission.CALL_PHONE)) {
                String[] ob = representativeModel.data.ourrepresentative.get(pos).office_phone1.split("-");
                String dial = "tel:" + ob[0] + ob[1];
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(getContext(), "Please grant call permission", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void phn2Click(int pos) {
        try {

            if (checkPermission(Manifest.permission.CALL_PHONE)) {
                String[] ob = representativeModel.data.ourrepresentative.get(pos).office_phone2.split("-");
                String dial = "tel:" + ob[0] + ob[1];
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(getContext(), "Please grant call permission", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //dial.setEnabled(true);
                    //Toast.makeText(getContext(), "You can call the number by clicking on the button", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


}
