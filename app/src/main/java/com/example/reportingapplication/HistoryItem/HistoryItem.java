package com.example.reportingapplication.HistoryItem;

public class HistoryItem {
    private int hImageResource;
    private String hTitle;
    private String hSubtitle;
    private String hDate;
    private String hCity;

    public HistoryItem(int imageResource, String title, String subtitle, String date, String city){
        hImageResource = imageResource;
        hTitle = title;
        hSubtitle = subtitle;
        hDate = date;
        hCity = city;
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

    public String getCity(){
        return hCity;
    }
}
