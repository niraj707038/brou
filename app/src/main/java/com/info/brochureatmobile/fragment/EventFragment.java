package com.info.brochureatmobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.info.brochureatmobile.Activity.ZoomEventActivity;
import com.info.brochureatmobile.Model.NewsModel;
import com.info.brochureatmobile.R;
import com.info.brochureatmobile.adapter.Event_Adapter;

import java.util.ArrayList;

public class EventFragment extends Fragment implements Event_Adapter.EvantItemClick {

    ArrayList<NewsModel.Data.Event> eventModel=new ArrayList<>();
    private Event_Adapter adapter;
    RecyclerView recycl_list;


    public EventFragment() {
        // Required empty public constructor
    }

    public EventFragment(ArrayList<NewsModel.Data.Event> eventModel) {
        this.eventModel = eventModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_event, container, false);
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        recycl_list=v.findViewById(R.id.recycl_list);

        adapter = new Event_Adapter(getContext(), eventModel,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycl_list.setLayoutManager(mLayoutManager);
        recycl_list.setAdapter(adapter);
        return v;
    }

    public void itemClick(int pos) {

        String a= eventModel.get(pos).getImage();
        String name= eventModel.get(pos).getName();
        String discription= eventModel.get(pos).getDescription();
        String from_date= eventModel.get(pos).getFrom_date();
        String to_date = eventModel.get(pos).getTo_date();


        Intent aa=new Intent(getContext(), ZoomEventActivity.class);
        aa.putExtra("img",a);
        aa.putExtra("name",name);
        aa.putExtra("discription",discription);
        aa.putExtra("from_date",from_date);
        aa.putExtra("to_date",to_date);
        startActivity(aa);

    }


}