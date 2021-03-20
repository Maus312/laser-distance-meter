package com.maus312.polygon.core.graphics.basic;

public class Point {
    private double x;
    private double y;

    public Point() {
        this(0,0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getIntX() {
        return (int) x;
    }

    public float getFloatX() {
        return (float) x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getIntY() {
        return (int) y;
    }

    public float getFloatY() {
        return (float) y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
