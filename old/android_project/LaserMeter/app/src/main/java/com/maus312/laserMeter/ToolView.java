package com.maus312.laserMeter;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ToolView extends LinearLayout implements View.OnClickListener {
    View ledViewWork;
    View ledViewLaser;
    Button workButton;
    Button laserButton;
    Button resetScan;

    public ToolView(Context context) {
        super(context);
        init();
    }

    public ToolView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ToolView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.tool_view, this);
        ledViewWork = findViewById(R.id.viewLedWork);
        ledViewLaser = findViewById(R.id.viewLedLaser);

        workButton = findViewById(R.id.buttonWork);
        laserButton = findViewById(R.id.buttonLaser);
        resetScan = findViewById(R.id.buttonResetScan);

        laserButton.setEnabled(false);
        resetScan.setEnabled(false);

        workButton.setOnClickListener(this);
        laserButton.setOnClickListener(this);
        resetScan.setOnClickListener(this);

        laserButton.setActivated(false);
        resetScan.setActivated(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonWork :
                boolean workStatus = ((LaserMeterApplication) getContext().getApplicationContext()).getWorkStatus();
                if(!workStatus) {
                    ledViewWork.setBackground(getResources().getDrawable(R.drawable.circle_activate));
                } else {
                    ledViewWork.setBackground(getResources().getDrawable(R.drawable.circle_inactivate));
                    ledViewLaser.setBackground(getResources().getDrawable(R.drawable.circle_inactivate));
                    ((LaserMeterApplication) getContext().getApplicationContext()).setLaserStatus(false);

                }
                laserButton.setEnabled(!workStatus);
                resetScan.setEnabled(!workStatus);
                ((LaserMeterApplication) getContext().getApplicationContext()).setWorkStatus(!workStatus);
                break;
            case R.id.buttonLaser :
                boolean laserStatus = ((LaserMeterApplication) getContext().getApplicationContext()).getLaserStatus();
                    if(!laserStatus) {
                        ledViewLaser.setBackground(getResources().getDrawable(R.drawable.circle_activate));
                    } else {
                        ledViewLaser.setBackground(getResources().getDrawable(R.drawable.circle_inactivate));
                    }

                ((LaserMeterApplication) getContext().getApplicationContext()).setLaserStatus(!laserStatus);
                break;
            case R.id.buttonResetScan:

                break;
            default:
                break;
        }
    }


}
