package com.info.brochureatmobile.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.info.brochureatmobile.Activity.ZoomNewsActivity;
import com.info.brochureatmobile.Model.NewsModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.NewsAdapter;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements NewsAdapter.NewsItemClick {

    ArrayList<NewsModel.Data.News> newsModel = new ArrayList<>();
    private NewsAdapter adapter;
    RecyclerView recycl_list;


    public NewsFragment() {
        // Required empty public constructor
    }

    public NewsFragment(ArrayList<NewsModel.Data.News> newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recycl_list=v.findViewById(R.id.recycl_list);
        adapter = new NewsAdapter(getContext(), newsModel,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycl_list.setLayoutManager(mLayoutManager);
        recycl_list.setAdapter(adapter);

        return v;

    }

    @Override
    public void itemClick(int pos) {
        String a= newsModel.get(pos).image;
        String name= newsModel.get(pos).getName();
        String discription= newsModel.get(pos).getDescription();

        Intent aa=new Intent(getContext(), ZoomNewsActivity.class);
        aa.putExtra("img",a);
        aa.putExtra("name",name);
        aa.putExtra("discription",discription);
        startActivity(aa);

    }
}