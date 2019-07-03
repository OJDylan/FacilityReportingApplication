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

import com.example.reportingapplication.HistoryItem.HistoryItem;
import com.example.reportingapplication.HistoryItem.HistoryItemAdapter;
import com.example.reportingapplication.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private RecyclerView hRecyclerView;
    private RecyclerView.Adapter hAdapter;
    private RecyclerView.LayoutManager hLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ArrayList<HistoryItem> historyList = new ArrayList<>();

        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));
        historyList.add(new HistoryItem(
                R.drawable.ic_history_black_24dp, "Title", "Description", "12/05/19"));

        hRecyclerView = view.findViewById(R.id.history_recycler_view);
        hRecyclerView.setHasFixedSize(true);
        hLayoutManager = new LinearLayoutManager(getActivity());
        hAdapter = new HistoryItemAdapter(historyList);

        hRecyclerView.setLayoutManager(hLayoutManager);
        hRecyclerView.setAdapter(hAdapter);


        return view;
    }
}
