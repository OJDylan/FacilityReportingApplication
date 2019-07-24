package com.example.reportingapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.reportingapplication.RewardItem.RewardItem;
import com.example.reportingapplication.RewardItem.RewardItemAdapter;

import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {
    private RecyclerView rRecyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManager;
    ArrayList<RewardItem> rewardList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rRecyclerView = findViewById(R.id.reward_recycler_view);
        rRecyclerView.setHasFixedSize(true);
        rLayoutManager = new LinearLayoutManager(this);
        rRecyclerView.setLayoutManager(rLayoutManager);

        rewardList.add(new RewardItem
                (R.drawable.starbuccs_reward, "RM10 Voucher", "100 Points"));
        rewardList.add(new RewardItem
                (R.drawable.starbuccs_reward_20, "RM20 Voucher", "200 Points"));
        rewardList.add(new RewardItem
                (R.drawable.starbuccs_reward_30, "RM30 Voucher", "300 Points"));

        rAdapter = new RewardItemAdapter(rewardList);
        rRecyclerView.setAdapter(rAdapter);
    }
}
