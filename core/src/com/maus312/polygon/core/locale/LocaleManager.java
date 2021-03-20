package com.maus312.polygon.core.locale;

public class LocaleManager {
    ResourceValues values;
    LanguageValue locale;

    public LocaleManager(LanguageValue locale) {
        values = new ResourceValues();
        this.locale = locale;
    }

    public LanguageValue getLocale() {
        return locale;
    }

    public void setLocale(LanguageValue locale) {
        this.locale = locale;
    }

    public String getStringFromId(int id) {
        return values.getResourceFromId(id);
    }
}
