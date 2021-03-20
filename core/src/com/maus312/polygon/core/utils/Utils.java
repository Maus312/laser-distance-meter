package com.maus312.polygon.core.utils;

import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.graphics.basic.Point;
import com.maus312.polygon.core.graphics.basic.PolarPoint;

public class Utils {
    public static String getDoubleString(double value, int countOfSymbols) {
        if (value == 0) {
            return "0";
        }

        String stringValue = Double.toString(value);
        int positionOfDot = stringValue.indexOf('.');
        if (positionOfDot == -1 || (stringValue.length() < (positionOfDot + countOfSymbols))) {
            return stringValue;
        } else {
            return stringValue.substring(0, positionOfDot + countOfSymbols);
        }
    }

    public static double getLengthFromAngleAndPolar(double angle, PolarPoint polar, boolean CCW) {
        Point point = Utils.getPointSquareRayFromCenter(new Point(),1,angle,CCW);
        return Utils.getLengthFromPoint(point) - polar.getRadius();
    }

    public static boolean checkLengthThresholdFromAngleAndPolar(double angle, PolarPoint polar, boolean CCW) {
        Point point = Utils.getPointSquareRayFromCenter(new Point(),1,angle,CCW);
        double normalizedLength = Utils.getLengthFromPoint(point) * Constants.REAL_X;
        return ((normalizedLength - polar.getRadius()) > (normalizedLength * Constants.THRESHOLD)) ;
    }

    public static double getLengthFromPoints(Point a, Point b) {
        return Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
    }

    public static double getLengthFromPoint(Point a) {
        return Math.sqrt(Math.pow((a.getX()), 2) + Math.pow((a.getY()), 2));
    }

    public static Point getPointSquareRayFromCenter(Point center, double side, double inAngle, boolean CCW) {
        double customAngle;
        Calculation xCalculation;
        Calculation yCalculation;

        if (CCW) {
            inAngle = (2f * Math.PI) - inAngle;
        }

        inAngle = ((inAngle + (3f / 2f * Math.PI)) % (2f * Math.PI));

        if (inAngle <= (Math.PI / 4)) {
            customAngle = inAngle;
            xCalculation = angle -> (side / Math.cos(angle)) * Math.cos(angle);
            yCalculation = angle -> (side / Math.cos(angle)) * Math.sin(angle);
        } else if (inAngle > Math.PI / 4 && inAngle <= (3 * Math.PI / 4)) {
            customAngle = inAngle - (Math.PI / 2);
            xCalculation = angle -> (side / Math.cos(angle)) * -Math.sin(angle);
            yCalculation = angle -> (side / Math.cos(angle)) * Math.cos(angle);
        } else if (inAngle > (3 * Math.PI / 4) && inAngle <= (5 * Math.PI / 4)) {
            customAngle = inAngle - Math.PI;
            xCalculation = angle -> (side / Math.cos(angle)) * -Math.cos(angle);
            yCalculation = angle -> (side / Math.cos(angle)) * -Math.sin(angle);
        } else if (inAngle > (5 * Math.PI / 4) && inAngle <= (7 * Math.PI / 4)) {
            customAngle = inAngle - ((3 * Math.PI) / 2);
            xCalculation = angle -> (side / Math.cos(angle)) * Math.sin(angle);
            yCalculation = angle -> (side / Math.cos(angle)) * -Math.cos(angle);
        } else if (inAngle > (7 * Math.PI / 4) && inAngle < (2 * Math.PI)) {
            customAngle = inAngle - (2 * Math.PI);
            xCalculation = angle -> (side / Math.cos(angle)) * Math.cos(angle);
            yCalculation = angle -> (side / Math.cos(angle)) * Math.sin(angle);
        } else {
            return new Point();
        }

        Point bPoint = new Point(
                center.getX() + xCalculation.calculate(customAngle),
                center.getY() + yCalculation.calculate(customAngle));
        return bPoint;
    }

    interface Calculation {
        double calculate(double angle);
    }
}
