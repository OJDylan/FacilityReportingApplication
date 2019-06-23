package com.example.reportingapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder> {
    private ArrayList<NewsItem> mNewsList;

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIcon;
        public TextView mTitle;
        public TextView mSubtitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.icon);
            mTitle = itemView.findViewById(R.id.title);
            mSubtitle = itemView.findViewById(R.id.subtitle);
        }
    }

    public NewsItemAdapter(ArrayList<NewsItem> newsList){
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.layout_news, parent, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem currentItem = mNewsList.get(position);

        holder.mIcon.setImageResource(currentItem.getImageResource());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mSubtitle.setText(currentItem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
