package com.project.healthstatus;

public class summery_item {

    String name;
    String color;
    String points;
    String background_color;
    public summery_item(String name , String color,String points,String background_color)
    {
        this.color = color;
        this.name = name;
        this.points = points;
        this.background_color = background_color;
    }

    public String getColor() {
        return color;
    }

    public String getname() {
        return name;
    }

    public String getPoints() {
        return points;
    }

    public String getBackground_color() {
        return background_color;
    }
}
