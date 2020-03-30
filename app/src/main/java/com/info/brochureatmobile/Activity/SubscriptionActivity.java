package com.info.brochureatmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.info.brochureatmobile.MainActivity;
import com.info.brochureatmobile.Model.SubscriptionModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.util.Utility;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import retrofit2.Call;

public class SubscriptionActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    TextView tv_skip, tv_subscribe;
    EditText et_name, et_email, et_phone, et_company;
    String validemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_subscription);
            init();
            setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            tv_subscribe = findViewById(R.id.tv_subscribe);
            tv_skip = findViewById(R.id.tv_skip);
            et_name = findViewById(R.id.et_name);
            et_email = findViewById(R.id.et_email);
            et_phone = findViewById(R.id.et_phone);
            et_company = findViewById(R.id.et_company);
        } catch (Exception e) {

        }
    }

    private void setListener() {
        try {
            tv_skip.setOnClickListener(this);
            tv_subscribe.setOnClickListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.tv_skip:
                    Intent nit = new Intent(SubscriptionActivity.this, MainActivity.class);
                    startActivity(nit);
                    break;
                case R.id.tv_subscribe:
                    if (!et_name.getText().toString().equalsIgnoreCase("")) {
                        if (!et_email.getText().toString().equalsIgnoreCase("") && et_email.getText().toString().trim().matches(validemail)) {
                            if (!et_phone.getText().toString().equalsIgnoreCase("")) {
                                if (!et_company.getText().toString().equalsIgnoreCase("")) {
                                    sendData(et_name.getText().toString(), et_email.getText().toString(), et_phone.getText().toString(), et_company.getText().toString());
                                } else {
                                    Toast.makeText(SubscriptionActivity.this, "Please Enter company", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(SubscriptionActivity.this, "Please Enter phone", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(SubscriptionActivity.this, "Please Enter email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SubscriptionActivity.this, "Please Enter name", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    private void sendData(String name, String mail, String phone, String company) {
        try {
            if (Utility.checkNetwork(this)) {
                Call<SubscriptionModel> call = APIClient.getInstance().getApiInterface()
                        .getSubscription(name, mail, phone, company, "1", "0", "0");
                call.request().url();
                Log.e("MyUrl", call.request().url() + "");
                new ResponseListner(this).getResponse(call);
            } else {
                Toast.makeText(SubscriptionActivity.this, "No connection", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    SubscriptionModel sm;

    @Override
    public void onApiResponse(Object response) {
        try {
            if (response != null) {
                sm = (SubscriptionModel) response;
                Log.d("", "");
                if (sm.statu.equalsIgnoreCase("1")) {
                    SharedPreferences sharedPreferences
                            = getSharedPreferences("Subscription",
                            MODE_PRIVATE);
                    SharedPreferences.Editor myEdit
                            = sharedPreferences.edit();
                    myEdit.putString(
                            "status",
                            sm.statu);
                    myEdit.commit();

                    Intent intent = new Intent(SubscriptionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onApiFailure(String message) {
        try {

        } catch (Exception e) {

        }
    }
}
