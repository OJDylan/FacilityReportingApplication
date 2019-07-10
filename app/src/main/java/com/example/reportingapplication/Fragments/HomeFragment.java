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
import android.widget.Toast;

import com.example.reportingapplication.Database.DatabaseHelper;
import com.example.reportingapplication.LoginActivity;
import com.example.reportingapplication.NewsItem.NewsItem;
import com.example.reportingapplication.NewsItem.NewsItemAdapter;
import com.example.reportingapplication.R;
import com.example.reportingapplication.ReportActivity;
import com.example.reportingapplication.RewardActivity;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper myDb;
    private ArrayList<NewsItem> newsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myDb = new DatabaseHelper(getActivity());

        Intent intent = getActivity().getIntent();
        TextView text = view.findViewById(R.id.greetings);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        setGreetingsMessage(text, intent);

        boolean res = myDb.checkEmpty();
        if (res) {
            populateView();
        } else {
            Toast.makeText(getActivity(), "No content to view", Toast.LENGTH_SHORT).show();
        }

//        Button refresh = view.findViewById(R.id.btn_refresh);
//        refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean res = myDb.checkEmpty();
//                if (res) {
//                    populateView();
//                } else {
//                    Toast.makeText(getActivity(), "No content to view", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

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

    //sets top greetings message
    public static void setGreetingsMessage(TextView text, Intent intent){
        String name = intent.getStringExtra(LoginActivity.EXTRA_TEXT);
        String message = "Greetings " + name;

        text.setText(message);
    }

    //populates recyclerView
    public void populateView(){
        Cursor data = myDb.getData();
        while(data.moveToNext()){
            String title = data.getString(1);
            String desc = data.getString(2);
            //add if statement for different icons
            newsList.add(new NewsItem(R.drawable.ic_home_black_24dp, title, desc));
        }

        mAdapter = new NewsItemAdapter(newsList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
