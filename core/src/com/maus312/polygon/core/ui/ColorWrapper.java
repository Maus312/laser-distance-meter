package com.maus312.polygon.core.ui;

public class ColorWrapper {
    int R;
    int G;
    int B;
    int A;

    public ColorWrapper(int r, int g, int b, int a) {
        R = r;
        G = g;
        B = b;
        A = a;
    }

    public ColorWrapper(int r, int g, int b) {
        this(r,g,b, 255);
    }

    public static ColorWrapper getBLACKColor() {
        return new ColorWrapper(0,0,0);
    }

    public static ColorWrapper getBLUEColor() {
        return new ColorWrapper(0,0,0xAA);
    }

    public static ColorWrapper getGREENColor() {
        return new ColorWrapper(0,0xAA,0);
    }

    public static ColorWrapper getCYANColor() {
        return new ColorWrapper(0,0xAA,0xAA);
    }

    public static ColorWrapper getREDColor() {
        return new ColorWrapper(0xAA,0,0);
    }

    public static ColorWrapper getMAGENTAColor() {
        return new ColorWrapper(0xAA,0,0xAA);
    }

    public static ColorWrapper getBROWNColor() {
        return new ColorWrapper(0xAA,0x55,0);
    }

    public static ColorWrapper getLIGHTGRAYColor() {
        return new ColorWrapper(0xAA,0xAA,0xAA);
    }

    public static ColorWrapper getDARKGRAYColor() {
        return new ColorWrapper(0x55,0x55,0x55);
    }

    public static ColorWrapper getBRIGHTBLUEColor() {
        return new ColorWrapper(0x55,0x55,0xFF);
    }

    public static ColorWrapper getBRIGHTGREENColor() {
        return new ColorWrapper(0x55,0xFF,0x55);
    }

    public static ColorWrapper getBRIGHTCYANColor() {
        return new ColorWrapper(0x55,0xFF,0xFF);
    }

    public static ColorWrapper getBRIGHTREDColor() {
        return new ColorWrapper(0xFF,0x55,0x55);
    }

    public static ColorWrapper getBRIGHTMAGENTAColor() {
        return new ColorWrapper(0xFF,0x55,0xFF);
    }

    public static ColorWrapper getBRIGHTYELLOWColor() {
        return new ColorWrapper(0xFF,0xFF,0x55);
    }

    public static ColorWrapper getBRIGHTWHITEColor() {
        return new ColorWrapper(0xFF,0xFF,0xFF);
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getARGB() {
        return((A << 24)|(R << 16)|(G << 8)|(B));
    }

    public int getRGB() {
        return((R << 16)|(G << 8)|(B));
    }
}
