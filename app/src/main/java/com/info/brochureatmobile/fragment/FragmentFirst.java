package com.info.brochureatmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.info.brochureatmobile.Activity.AboutUs;
import com.info.brochureatmobile.Model.HomeModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.Widget.AutoScrollViewPager;
import com.info.brochureatmobile.adapter.RecyclerAdapter;
import com.info.brochureatmobile.adapter.ViewPagerAdapter;
import com.info.brochureatmobile.util.Utility;
import com.info.brochureatmobile.webservice.APIClient;
import com.info.brochureatmobile.webservice.OnResponseInterface;
import com.info.brochureatmobile.webservice.ResponseListner;

import java.util.ArrayList;

import retrofit2.Call;

public class FragmentFirst extends Fragment implements RecyclerAdapter.RecyclerItemClick, OnResponseInterface {

    ImageView menu_view, view_notification;
    TextView tv_title;

    AutoScrollViewPager viewPager;
    LinearLayout sliderdots;
    private int discount;
    private ImageView[] dots;
    private RecyclerView recycler_list;
    private RecyclerAdapter adapter;
    private ViewPagerAdapter viewPagerAdapter;
    private StaggeredGridLayoutManager manger;
    HomeModel home = new HomeModel();
    ArrayList<HomeModel.Details> homeModels = null;
    ArrayList<HomeModel.Details> homeModelsPager = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.fragmentfirst, container, false);

            viewPager = view.findViewById(R.id.viewpage);
            sliderdots = view.findViewById(R.id.sliderdots);

            menu_view = view.findViewById(R.id.menu_view);
            tv_title = view.findViewById(R.id.tv_title);
            view_notification = view.findViewById(R.id.view_notification);

            setViewPager();
            getData();


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    for (int i = 0; i < discount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));

                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            recycler_list = view.findViewById(R.id.recycler_list);
            manger = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recycler_list.setLayoutManager(manger);


            menu_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout mDrawer = getActivity().findViewById(R.id.drawer_layout);
                    mDrawer.openDrawer(GravityCompat.START);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void setViewPager() {
        try {
            viewPager.startAutoScroll();
            viewPager.setInterval(4000);
            viewPager.setCycle(true);
            viewPager.setStopScrollWhenTouch(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        try {
            if (Utility.checkNetwork(getContext())) {
                Call<HomeModel> call = APIClient.getInstance().getApiInterface()
                        .getHomeData();
                call.request().url();
                Log.e("MyUrl", call.request().url() + "");
                new ResponseListner(this).getResponse(call);
            } else {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onApiResponse(Object response) {

        try {
            if (response != null) {
                if (response instanceof HomeModel) {
                    if (response instanceof HomeModel) {
                        home = (HomeModel) response;
                        homeModels = new ArrayList<>();
                        homeModelsPager = new ArrayList<>();
                        for (HomeModel.Details hm : home.details) {
                            if (hm.id.equalsIgnoreCase("6"))
                                homeModels.add(hm);
                            if (hm.id.equalsIgnoreCase("4")) {
                                homeModelsPager.add(hm);
                            }
                        }
                        Log.d("", "");
                        adapter = new RecyclerAdapter(getContext(), homeModels, this);
                        //recycler_list.setAdapter(adapter);
                        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        recycler_list.setLayoutManager(lm);
                        recycler_list.setAdapter(adapter);

                        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), homeModelsPager);
                        viewPager.setAdapter(viewPagerAdapter);
                        discount = viewPagerAdapter.getCount();
                        dots = new ImageView[discount];

                        for (int i = 0; i < discount; i++) {
                            dots[i] = new ImageView(getContext());
                            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(5, 0, 5, 0);

                            sliderdots.addView(dots[i], params);
                        }

                        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiFailure(String message) {
        try {

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void itemClick(String typeid) {
        if (typeid.equalsIgnoreCase("56")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new ProductFragment(), "product").addToBackStack(null).commit();

        } else if (typeid.equalsIgnoreCase("51")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new Service(), "service").addToBackStack(null).commit();

        } else if (typeid.equalsIgnoreCase("53")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new Certification(), "certification").addToBackStack(null).commit();

        } else if (typeid.equalsIgnoreCase("54")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new OurClients(), "clients").addToBackStack(null).commit();
        } else if (typeid.equalsIgnoreCase("50")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new AboutUs(), "aboutus").addToBackStack(null).commit();
        } else if (typeid.equalsIgnoreCase("57")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new NewsEvent(), "aboutus").addToBackStack(null).commit();
        } else if (typeid.equalsIgnoreCase("52")) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_main, new Representative(), "aboutus").addToBackStack(null).commit();
        }
    }
}


