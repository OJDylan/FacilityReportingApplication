package com.example.reportingapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reportingapplication.NewsItem.NewsItem;
import com.example.reportingapplication.NewsItem.NewsItemAdapter;
import com.example.reportingapplication.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem(R.drawable.ic_history_black_24dp, "Line 1", "Line 2"));
        newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, "Line 3", "Line 4"));
        newsList.add(new NewsItem(R.drawable.ic_person_black_24dp, "Line 5", "Line 6"));
        newsList.add(new NewsItem(R.drawable.ic_history_black_24dp, "Line 1", "Line 2"));
        newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, "Line 3", "Line 4"));
        newsList.add(new NewsItem(R.drawable.ic_person_black_24dp, "Line 5", "Line 6"));
        newsList.add(new NewsItem(R.drawable.ic_history_black_24dp, "Line 1", "Line 2"));
        newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, "Line 3", "Line 4"));
        newsList.add(new NewsItem(R.drawable.ic_person_black_24dp, "Line 5", "Line 6"));

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NewsItemAdapter(newsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
