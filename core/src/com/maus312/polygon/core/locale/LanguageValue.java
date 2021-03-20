package com.maus312.polygon.core.locale;

public enum LanguageValue {
    Eng(0),
    Ukr(1);

    private int code;
    LanguageValue(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
