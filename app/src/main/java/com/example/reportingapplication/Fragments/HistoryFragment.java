package com.example.reportingapplication.Fragments;

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
import android.widget.Toast;

import com.example.reportingapplication.Database.DatabaseHelper;
import com.example.reportingapplication.HistoryItem.HistoryItem;
import com.example.reportingapplication.HistoryItem.HistoryItemAdapter;
import com.example.reportingapplication.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    DatabaseHelper myDb;
    private RecyclerView hRecyclerView;
    private RecyclerView.Adapter hAdapter;
    private RecyclerView.LayoutManager hLayoutManager;
    ArrayList<HistoryItem> historyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        myDb = new DatabaseHelper(getActivity());

        hRecyclerView = view.findViewById(R.id.history_recycler_view);
        hRecyclerView.setHasFixedSize(true);
        hLayoutManager = new LinearLayoutManager(getActivity());
        hRecyclerView.setLayoutManager(hLayoutManager);

        boolean res = myDb.checkEmpty();
        if (res) {
            populateView();
        } else {
            Toast.makeText(getActivity(), "There's no data to view", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void populateView(){
        Cursor data = myDb.getData();
        while (data.moveToNext()){
            String title = data.getString(1);
            String desc = data.getString(2);
            String date = data.getString(5);
            switch (title) {
                case "Traffic":
                    historyList.add(new HistoryItem
                            (R.drawable.ic_traffic_light_24dp, title, desc, date));
                    break;
                case "Roads":
                    historyList.add(new HistoryItem
                            (R.drawable.ic_road_24dp, title, desc, date));
                    break;
                case "Signs":
                    historyList.add(new HistoryItem
                            (R.drawable.ic_sign_24dp, title, desc, date));
                    break;
            }
            hAdapter = new HistoryItemAdapter(historyList);
            hRecyclerView.setAdapter(hAdapter);
        }
    }
}
