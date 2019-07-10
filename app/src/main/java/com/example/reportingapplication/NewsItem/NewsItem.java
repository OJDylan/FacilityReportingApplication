package com.example.reportingapplication.NewsItem;

public class NewsItem {
    private int mImageResource;
    private String mTitle;
    private String mDescription;

    public NewsItem(int imageResource, String title, String desc){
        mImageResource = imageResource;
        mTitle = title;
        mDescription = desc;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getSubtitle(){
        return mDescription;
    }
}
