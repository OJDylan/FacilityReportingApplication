package com.example.reportingapplication.RewardItem;

public class RewardItem {
    private int rImageResource;
    private String rTitle;
    private String rPoints;

    public RewardItem(int imageResource, String title, String points) {
        rImageResource = imageResource;
        rTitle = title;
        rPoints = points;
    }

    public int getrImageResource(){
        return rImageResource;
    }

    public String getrTitle(){
        return rTitle;
    }

    public String getrPoints(){
        return rPoints;
    }
}
