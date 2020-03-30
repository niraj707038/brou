package com.info.brochureatmobile.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.info.brochureatmobile.Model.CategoryModel;
import com.info.brochureatmobile.Model.SubscriptionModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Utility;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import java.util.ArrayList;

import retrofit2.Call;

public class EnquiryOption extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {


    EditText et_email, et_name, et_mobile;
    TextView et_product;
    String validemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String email = "";
    String name, pid;
    ImageView img_back;
    ArrayList<String> list = new ArrayList<>();
    // ArrayAdapter<String> adapter = null;
    Button btn_send;
    TextView tv_categery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.enquiry_option);

            name = getIntent().getStringExtra("name");
            pid = getIntent().getStringExtra("pid");
           // et_product.setText(getIntent().getStringExtra("pid"));

            init();
            et_product.setText(name);

            setListener();

        } catch (Exception e) {
            e.printStackTrace();
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

    private void setListener() {
        try {
            img_back.setOnClickListener(this);
            btn_send.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init() {
        try {
            img_back = findViewById(R.id.img_back);
            // spinner = findViewById(R.id.spinner);
            et_email = findViewById(R.id.et_email);
            et_name = findViewById(R.id.et_name);
            et_mobile = findViewById(R.id.et_mobile);
            btn_send = findViewById(R.id.btn_send);
            tv_categery = findViewById(R.id.tv_categery);
            et_product = findViewById(R.id.et_product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.img_back:
                    finish();
                    break;
                case R.id.btn_send:
                    if (!et_name.getText().toString().equalsIgnoreCase("")) {
                        if (!et_email.getText().toString().equalsIgnoreCase("") && et_email.getText().toString().trim().matches(validemail)) {
                            if (!et_mobile.getText().toString().equalsIgnoreCase("")) {

                                //  sendData(et_name.getText().toString(), et_email.getText().toString(), et_mobile.getText().toString(), category_id);

                            } else {
                                Toast.makeText(EnquiryOption.this, "Please Enter phone", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(EnquiryOption.this, "Please Enter email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(EnquiryOption.this, "Please Enter name", Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendData(String name, String mail, String phone, String category_id) {
        try {
            if (Utility.checkNetwork(this)) {
                Call<SubscriptionModel> call = APIClient.getInstance().getApiInterface()
                        .getSubscription(name, mail, phone, "", "2", category_id, "0");
                call.request().url();
                Log.e("MyUrl", call.request().url() + "");
                new ResponseListner(this).getResponse(call);
            } else {
                Toast.makeText(EnquiryOption.this, "No connection", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    CategoryModel category = new CategoryModel();
    SubscriptionModel subscriptionModel = new SubscriptionModel();

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                if (response instanceof CategoryModel) {
                    category = (CategoryModel) response;
                    for (CategoryModel.Data cat : category.data) {
                        list.add(cat.categoryname);
                    }


                } else if (response instanceof SubscriptionModel) {
                    subscriptionModel = (SubscriptionModel) response;
                    if (subscriptionModel.statu.equalsIgnoreCase("1")) {
                        Toast.makeText(EnquiryOption.this, "Request submitted successfully", Toast.LENGTH_LONG).show();
                        this.finish();
                    }
                    Log.d("", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiFailure(String message) {
        try {
            Log.d("msg:", message);
        } catch (Exception e) {

        }
    }
}





