package com.maus312.laserMeter;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
    static float getDistance(Point a) {
        return (float) (Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)));
    }

    static Point getVector(Point a, Point b) {
        return new Point(b.getX() - a.getX(), b.getY() - a.getY());
    }

    static Point getPointFromPolar(double distance, double horzAngle) {
        float x = (float) (distance * Math.cos(horzAngle));
        float y = (float) (distance * Math.sin(horzAngle));
        return new Point(x,y);
    }

    static Point rotate(Point a, double angle) {
        float x = (float) (a.getX() * Math.cos(Math.toRadians(angle)) - a.getY() * Math.sin(Math.toRadians(angle)));
        float y = (float) (a.getY() * Math.cos(Math.toRadians(angle)) + a.getX() * Math.sin(Math.toRadians(angle)));
        return new Point(x, y);
    }

    static Point rotateAroundPoint(Point a, double angle, Point b) {
        float x = (float) (b.getX() + (a.getX() - b.getX()) * Math.cos(Math.toRadians(angle)) - (a.getY() - b.getY()) * Math.sin(Math.toRadians(angle)));
        float y = (float) (b.getY() + (a.getY() - b.getY()) * Math.cos(Math.toRadians(angle)) + (a.getX() - b.getX()) * Math.sin(Math.toRadians(angle)));
        return new Point(x, y);
    }

    static float getCorrectedLenght(float maxDistance, float maxDistanceAngle, float newAngle) {
        //TODO fix me
        if (Math.abs(newAngle - maxDistanceAngle) > 45) {
            return 0;
        }
        return (float) Math.cos(Math.toDegrees(maxDistanceAngle - newAngle));
    }


    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
