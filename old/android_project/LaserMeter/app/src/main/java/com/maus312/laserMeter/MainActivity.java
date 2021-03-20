package com.maus312.laserMeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SectorView sectorView = findViewById(R.id.sectorView);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int size = width < height ? width : height;
        //int margin = (int) Utils.convertDpToPixel(5, this);
        //size -= 2 * margin;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        //layoutParams.setMargins(margin, margin, margin, margin);
        sectorView.setLayoutParams(layoutParams);


        ViewTreeObserver viewTreeObserver = sectorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int viewHeight = sectorView.getHeight();
                    if (viewHeight != 0)
                        sectorView.initSizeParam();
                    sectorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}