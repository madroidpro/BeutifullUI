package com.example.hnd6kor.timer;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.github.lzyzsd.circleprogress.DonutProgress;

/**
 * Created by HND6KOR on 03/03/2017.
 */

public class ProgressAnimation extends Animation {

  private DonutProgress donutProgress;

  private float oldProgress;
  private float newProgress;

  public ProgressAnimation(DonutProgress donutProgress, int newProgress) {
    this.oldProgress = donutProgress.getProgress();
    this.newProgress = newProgress;
    this.donutProgress = donutProgress;
  }

  @Override
  protected void applyTransformation(float interpolatedTime, Transformation transformation) {
     int progress = (int) (oldProgress + ((newProgress - oldProgress) * interpolatedTime));
    //Log.e("info_progress",progress+"---"+interpolatedTime);
    donutProgress.setProgress(progress);
    //donutProgress.requestLayout();
    donutProgress.postInvalidate();
  }
}
