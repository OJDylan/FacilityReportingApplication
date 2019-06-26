package com.example.reportingapplication.HistoryItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reportingapplication.R;

import java.util.ArrayList;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.HistoryViewHolder> {
    private ArrayList<HistoryItem> hHistoryList;

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public ImageView hIcon;
        public TextView hTitle;
        public TextView hSubtitle;
        public TextView hDate;

        public HistoryViewHolder(@NonNull View itemView){
            super(itemView);
            hIcon = itemView.findViewById(R.id.history_icon);
            hTitle = itemView.findViewById(R.id.history_title);
            hSubtitle = itemView.findViewById(R.id.history_subtitle);
            hDate = itemView.findViewById(R.id.history_date);
        }
    }

    public HistoryItemAdapter(ArrayList<HistoryItem> historyList){
        hHistoryList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.layout_history, parent, false);
        HistoryViewHolder hvh = new HistoryViewHolder(v);
        return hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem currentItem = hHistoryList.get(position);

        holder.hIcon.setImageResource(currentItem.getImageResource());
        holder.hTitle.setText(currentItem.getTitle());
        holder.hSubtitle.setText(currentItem.getSubtitle());
        holder.hDate.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return hHistoryList.size();
    }
}
