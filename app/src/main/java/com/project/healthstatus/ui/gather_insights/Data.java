package com.project.healthstatus.ui.gather_insights;

import com.jjoe64.graphview.series.DataPointInterface;

public class Data implements DataPointInterface {
    double x=0,y=0;
    public Data(int x , int y)
    {
        this.x = x;
        this.y = y;
        
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
