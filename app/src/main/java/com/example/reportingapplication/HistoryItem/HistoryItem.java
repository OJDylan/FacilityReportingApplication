package com.example.reportingapplication.HistoryItem;

public class HistoryItem {
    private int hImageResource;
    private String hTitle;
    private String hSubtitle;
    private String hDate;

    public HistoryItem(int imageResource, String title, String subtitle, String date){
        hImageResource = imageResource;
        hTitle = title;
        hSubtitle = subtitle;
        hDate = date;
    }

    public int getImageResource(){
        return hImageResource;
    }

    public String getTitle(){
        return hTitle;
    }

    public String getSubtitle(){
        return hSubtitle;
    }

    public String getDate(){
        return hDate;
    }
}
