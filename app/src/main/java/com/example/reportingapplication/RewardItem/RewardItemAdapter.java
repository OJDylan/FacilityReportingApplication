package com.example.reportingapplication.RewardItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reportingapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RewardItemAdapter extends RecyclerView.Adapter<RewardItemAdapter.RewardViewHolder> {
    private ArrayList<RewardItem> rRewardList;

    public static class RewardViewHolder extends RecyclerView.ViewHolder{
        public TextView rTitle;
        public TextView rPoints;
        public ImageView rImage;

        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);
            rTitle = itemView.findViewById(R.id.reward_name);
            rPoints = itemView.findViewById(R.id.reward_points);
            rImage = itemView.findViewById(R.id.reward_image);
        }
    }

    public RewardItemAdapter(ArrayList<RewardItem> rewardList){
        rRewardList = rewardList;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.layout_rewards, parent, false);
        RewardViewHolder rvh = new RewardViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        RewardItem currentItem = rRewardList.get(position);

        holder.rImage.setImageResource(currentItem.getrImageResource());
        holder.rTitle.setText(currentItem.getrTitle());
        holder.rPoints.setText(currentItem.getrPoints());
    }

    @Override
    public int getItemCount() {
        return rRewardList.size();
    }


}
