package com.cc.design;

import org.springframework.security.access.method.P;

/**
 * Created by wanchao on 2018/2/7.
 */
public class ChartDisplay {
    private AbstractChart abstractChart;

    public void setChart(AbstractChart abstractChart) {
        this.abstractChart = abstractChart;
    }
    public void display(){
        abstractChart.display();
        System.out.println("display");
    }

    public static void main(String[] args) {
        ChartDisplay chartDisplay = new ChartDisplay();
//        PipeChart pipeChart = new PipeChart();
//        chartDisplay.setChart(pipeChart);
        BarChart barChart = new BarChart();
        chartDisplay.setChart(barChart);
        chartDisplay.display();
    }
}
