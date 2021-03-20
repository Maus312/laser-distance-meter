package com.maus312.polygon.core.executable.commands.service;

import com.maus312.polygon.core.CommandInterface;
import com.maus312.polygon.core.Constants;
import com.maus312.polygon.core.MainSingle;
import com.maus312.polygon.core.UICallback;
import com.maus312.polygon.core.communication.MessageSource;
import com.maus312.polygon.core.executable.commands.angle.SetScanAngleCI;
import com.maus312.polygon.core.locale.LocaleManager;
import com.maus312.polygon.core.locale.ResourceTable;
import com.maus312.polygon.core.ui.UITable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PServiceOnInit implements CommandInterface {
    private static final String name = "CommandServiceOnInit";

    @Override
    public String getCommandName() {
        return name;
    }
    @Override
    public void onAction() {
        MainSingle single = MainSingle.getInstance();
        MessageSource messageSource = single.getTable().getMessageSource();
        single.setMessageSource(messageSource);
        UITable table = single.getUiTable();

        initTexts(table);
        initActionOfButtons(table, messageSource);

        single.getMessageSource().onTransmit(name + " OK");
    }

    private void initTexts(UITable table) {
        LocaleManager local = MainSingle.getInstance().getLocaleManager();
        table.getMainStatusTVText().setText(local.getStringFromId(ResourceTable.MAIN_STATUS_TV_TEXT_STRING));
        table.getMainStatusTVValue().setText(local.getStringFromId(ResourceTable.MAIN_STATUS_OFF));
        table.getScanTVText().setText(local.getStringFromId(ResourceTable.SCAN_TV_TEXT_STRING));
        table.getScanTVValue().setText(local.getStringFromId(ResourceTable.DISABLED));
        table.getLaserTVText().setText(local.getStringFromId(ResourceTable.LASER_TV_TEXT_STRING));
        table.getLaserTVValue().setText(local.getStringFromId(ResourceTable.DISABLE));
        table.getModeTVText().setText(local.getStringFromId(ResourceTable.MODE_TV_TEXT_STRING));
        table.getModeTVValue().setText(local.getStringFromId(ResourceTable.SERIAL));
        table.getAngleTVText().setText(local.getStringFromId(ResourceTable.ANGLE_TV_TEXT_STRING));
        table.getAngleTVValue().setText(local.getStringFromId(ResourceTable.ANGLE_TV_VALUE_STRING));
        table.getPrecisionTVText().setText(local.getStringFromId(ResourceTable.PRECISION_TV_TEXT_STRING));
        table.getPrecisionTVValue().setText(local.getStringFromId(ResourceTable.DISABLED));
        table.getScanButton().setText(local.getStringFromId(ResourceTable.START) + " " +
                local.getStringFromId(ResourceTable.SCAN_BUTTON_STRING));
        table.getLaserButton().setText(local.getStringFromId(ResourceTable.ENABLE) + " " +
                local.getStringFromId(ResourceTable.LASER_BUTTON_STRING));
        table.getModeButton().setText(local.getStringFromId(ResourceTable.ACROSS) + " " +
                local.getStringFromId(ResourceTable.MODE_BUTTON_STRING));
        table.getAngleTVTextEdit().setText(local.getStringFromId(ResourceTable.ANGLE_TV_TEXTEdit_STRING));
        table.getAngleETVInValue().setText(local.getStringFromId(ResourceTable.ANGLE_E_TV_IN_VALUE_STRING));
        table.getAngleSetButton().setText(local.getStringFromId(ResourceTable.ANGLE_SET_BUTTON_STRING));
        table.getPrecisionButton().setText(local.getStringFromId(ResourceTable.ENABLE) + " " +
                local.getStringFromId(ResourceTable.PRECISION_BUTTON_STRING));
        table.getResetPositionButton().setText(local.getStringFromId(ResourceTable.RESET_POSITION_BUTTON_STRING));
        table.getStartRecordButton().setText(local.getStringFromId(ResourceTable.START) + " " +
                local.getStringFromId(ResourceTable.RECORD));
        table.getStatusBarTV().setText(local.getStringFromId(ResourceTable.STATUS_BAR_TV));

    }

    private void initActionOfButtons(UITable table, MessageSource messageSource) {
        table.getScanButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                messageSource.onReceive(Constants.START_SCAN_TEXT);
            }
        });

        table.getLaserButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                messageSource.onReceive(Constants.START_LASER_TEXT);
            }
        });

        table.getModeButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                messageSource.onReceive(Constants.START_ACROSS_TEXT);
            }
        });

        table.getAngleSetButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                String text = table.getAngleETVInValue().getString();

                Pattern pattern = Pattern.compile("(\\d{1,2})");
                Matcher matcher = pattern.matcher(text);

                while(matcher.find())
                {
                    String value = matcher.group();
                    int angle = Integer.parseInt(value);
                    if (angle < 90) {
                        messageSource.onReceive(new SetScanAngleCI(angle).getCommandName());
                    }
                    break;
                }
            }
        });

        table.getPrecisionButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                messageSource.onReceive(Constants.START_PRECISION_TEXT);
            }
        });

        table.getResetPositionButton().setCallback(new UICallback() {
            @Override
            public void onAction() {
                messageSource.onReceive(Constants.RESET_POSITION_TEXT);
            }
        });
    }

    @Override
    public boolean needConfirmation() {
        return false;
    }
}