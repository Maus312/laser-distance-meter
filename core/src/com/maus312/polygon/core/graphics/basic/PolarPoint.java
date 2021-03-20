package com.maus312.polygon.core.graphics.basic;

public class PolarPoint {
    double radius;
    double angle;
    int rawRadius;
    int rawAngle;

    public PolarPoint() {
        radius = 0;
        angle = 0;
    }

    public PolarPoint (int rawRadius, int rawAngle) {
        this.rawAngle = rawAngle;
        this.rawRadius = rawRadius;
        angle = Math.toRadians(rawAngle);
        radius = rawRadius;
    }

    public PolarPoint(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
        rawRadius = (int) radius;
        rawAngle = (int) Math.round(Math.toDegrees(angle));
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Point getCartesianPoint(){
        return new Point(radius * Math.cos(angle),radius * Math.sin(angle));
    }
    public Point getCustomCartesianPoint(){
        return new Point(radius * Math.cos(angle + (1f/2f * Math.PI)),radius * Math.sin(angle + (1f/2f * Math.PI)));
    }

    public int getRawRadius() {
        return rawRadius;
    }

    public int getRawAngle() {
        return rawAngle;
    }
}
