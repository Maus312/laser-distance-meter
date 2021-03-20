package com.maus312.polygon.core.locale;

import com.maus312.polygon.core.MainSingle;

import java.util.ArrayList;
import java.util.List;

public class ResourceValues {
    private List<String> drawPanelView          = new ArrayList() {{add("");                add("");}};
    private List<String> mainStatusTVText       = new ArrayList() {{add("Status:");         add("Стан:");}};
    private List<String> mainStatusTVValue      = new ArrayList() {{add("");                add("");}};
    private List<String> scanTVText             = new ArrayList() {{add("Scan:");           add("Сканування:");}};
    private List<String> scanTVValue            = new ArrayList() {{add("");                add("");}};
    private List<String> laserTVText            = new ArrayList() {{add("Laser:");          add("Лазер");}};
    private List<String> laserTVValue           = new ArrayList() {{add("");                add("");}};
    private List<String> modeTVText             = new ArrayList() {{add("Mode:");           add("Режим");}};
    private List<String> modeTVValue            = new ArrayList() {{add("");                add("");}};
    private List<String> angleTVText            = new ArrayList() {{add("Angle:");          add("Кут");}};
    private List<String> angleTVValue           = new ArrayList() {{add("30");              add("");}};
    private List<String> precisionTVText        = new ArrayList() {{add("Precision");       add("Точність");}};
    private List<String> precisionTVValue       = new ArrayList() {{add("");                add("");}};
    private List<String> scanButton             = new ArrayList() {{add("Scan");            add("Сканування");}};
    private List<String> laserButton            = new ArrayList() {{add("Laser");           add("Лазер");}};
    private List<String> modeButton             = new ArrayList() {{add("Mode");            add("Режим");}};
    private List<String> angleTVTextEdit        = new ArrayList() {{add("Angle");           add("Кут");}};
    private List<String> angleETVInValue        = new ArrayList() {{add("30");              add("30");}};
    private List<String> angleSetButton         = new ArrayList() {{add("Set");             add("Встановити");}};
    private List<String> precisionButton        = new ArrayList() {{add("Precision");       add("Точність");}};
    private List<String> resetPositionButton    = new ArrayList() {{add("Reset Position");  add("Скинути позицію");}};
    private List<String> record                 = new ArrayList() {{add("Records");         add("Запис");}};
    private List<String> statusBarTV            = new ArrayList() {{add("");                add("");}};

    private List<String> mainStatusOn           = new ArrayList() {{add("Working");      add("Працює");}};
    private List<String> mainStatusOff          = new ArrayList() {{add("Not Working");  add("Непрацює");}};
    private List<String> enabled                = new ArrayList() {{add("Enabled");      add("Ввімкнено");}};
    private List<String> disabled               = new ArrayList() {{add("Disabled");     add("Вимкнуто");}};
    private List<String> serial                 = new ArrayList() {{add("Serial");       add("Рядковий");}};
    private List<String> across                 = new ArrayList() {{add("Across");       add("Черезрядковий");}};
    private List<String> mode                   = new ArrayList() {{add("Mode");         add("Режим");}};
    private List<String> start                  = new ArrayList() {{add("Start");        add("Почати");}};
    private List<String> stop                   = new ArrayList() {{add("Stop");         add("Зупинити");}};
    private List<String> enable                 = new ArrayList() {{add("Enable");       add("Увімкнути");}};
    private List<String> disable                = new ArrayList() {{add("Disable");      add("Вимкнути");}};

    private List<List<String>> resourceValues = new ArrayList(){{
        add(drawPanelView);
        add(mainStatusTVText);
        add(mainStatusTVValue);
        add(scanTVText);
        add(scanTVValue);
        add(laserTVText);
        add(laserTVValue);
        add(modeTVText);
        add(modeTVValue);
        add(angleTVText);
        add(angleTVValue);
        add(precisionTVText);
        add(precisionTVValue);
        add(scanButton);
        add(laserButton);
        add(modeButton);
        add(angleTVTextEdit);
        add(angleETVInValue);
        add(angleSetButton);
        add(precisionButton);
        add(resetPositionButton);
        add(record);
        add(statusBarTV);
        add(mainStatusOn);
        add(mainStatusOff);
        add(enabled);
        add(disabled);
        add(serial);
        add(across);
        add(mode);
        add(start);
        add(stop);
        add(enable);
        add(disable);
    }};

    public String getResourceFromId(int id) {
        try {
            return resourceValues.get(id).get(MainSingle.getInstance().getLocaleManager().getLocale().getCode());
        } catch (IndexOutOfBoundsException e) {
            return "error";
        }
    }
}
