package com.maus312.polygon.core.graphics.basic;

import com.maus312.polygon.core.ui.StyleWrapper;

public class GArc {
    private Point min;
    private Point max;
    private double startAngle;
    private double endAngle;
    private StyleWrapper style;

    public GArc(Point min, Point max, double startAngle, double endAngle, StyleWrapper style) {
        this.min = min;
        this.max = max;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.style = style;
    }

    public Point getMin() {
        return min;
    }

    public void setMin(Point min) {
        this.min = min;
    }

    public Point getMax() {
        return max;
    }

    public void setMax(Point max) {
        this.max = max;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public StyleWrapper getStyle() {
        return style;
    }

    public void setStyle(StyleWrapper style) {
        this.style = style;
    }
}
