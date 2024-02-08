package com.ramanora.stona.mapsection.hallsection.activity;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramanora.stona.R;
import com.ramanora.stona.photoview.OnPhotoTapListener;
import com.ramanora.stona.photoview.PhotoView;

public class ActivityHall3MiddleLevel extends AppCompatActivity {
    static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d";
    static final String SCALE_TOAST_STRING = "Scaled to: %.2ff";
    static final String FLING_LOG_STRING = "Fling velocityX: %.2f, velocityY: %.2f";

    private PhotoView mPhotoView;
    private TextView mCurrMatrixTv;

    private Toast mCurrentToast;

    private Matrix mCurrentDisplayMatrix = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall3_middle_level);

        mPhotoView = (PhotoView) findViewById(R.id.iv_photo);
        //  mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);

        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.hall4);
        mPhotoView.setImageDrawable(bitmap);

        // Lets attach some listeners, not required though!
        //  mPhotoView.setOnMatrixChangeListener(new MatrixChangeListener());
        mPhotoView.setOnPhotoTapListener(new ActivityHall3MiddleLevel.PhotoTapListener());
        //    mPhotoView.setOnSingleFlingListener(new SingleFlingListener());
    }

    private class PhotoTapListener implements OnPhotoTapListener {

        @Override
        public void onPhotoTap(ImageView view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;

        }
    }


}
