package com.maus312.polygon.core.ui;

public class StyleWrapper {
    ColorWrapper color;
    boolean filled;
    double width;
    double textSize;

    public StyleWrapper(ColorWrapper color, boolean filled, double width) {
        this.color = color;
        this.filled = filled;
        this.width = width;
    }

    public StyleWrapper(ColorWrapper color, boolean filled, double width, double textSize) {
        this.color = color;
        this.filled = filled;
        this.width = width;
        this.textSize = textSize;
    }

    public ColorWrapper getColor() {
        return color;
    }

    public void setColor(ColorWrapper color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getTextSize() {
        return textSize;
    }

    public void setTextSize(double textSize) {
        this.textSize = textSize;
    }
}
