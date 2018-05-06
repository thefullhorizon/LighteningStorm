package com.nanshan.lighteningstorm.base.data;

/**
 * Created by nanshan on 7/14/2017.
 */

public class AssetInfoBean {

    private String title;
    private double amount;
    private String color;

    public AssetInfoBean(String title, String color, double amount) {
        this.title = title;
        this.amount = amount;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
