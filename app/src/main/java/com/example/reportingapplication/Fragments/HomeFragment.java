package com.example.reportingapplication.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reportingapplication.Database.DatabaseHelper;
import com.example.reportingapplication.LoginActivity;
import com.example.reportingapplication.NewsItem.NewsItem;
import com.example.reportingapplication.NewsItem.NewsItemAdapter;
import com.example.reportingapplication.R;
import com.example.reportingapplication.ReportActivity;
import com.example.reportingapplication.RewardActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //set greeting message
        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra(LoginActivity.EXTRA_TEXT);
        String message = "Greetings " + name;

        TextView text = view.findViewById(R.id.greetings);
        text.setText(message);

        //Populate RecycledView
        ArrayList<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem(R.drawable.ic_history_black_24dp, "Title", "Description"));
        newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, "Title", "Description"));
        newsList.add(new NewsItem(R.drawable.ic_person_black_24dp, "Title", "Description"));
        newsList.add(new NewsItem(R.drawable.ic_history_black_24dp, "Title", "Description"));
        newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, "Title", "Description"));

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NewsItemAdapter(newsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Reports button onClick listener
        Button reportButton = view.findViewById(R.id.btn_report);
        reportButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportActivity.class));
            }
        });

        //Rewards button onClick listener
        Button rewardButton = view.findViewById(R.id.btn_rewards);
        rewardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RewardActivity.class));
            }
        });

        return view;
    }
}
