package com.example.hnd6kor.timer;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.github.lzyzsd.circleprogress.CircleProgress;

/**
 * Created by HND6KOR on 03/03/2017.
 */

public class CircleProgressAnimation extends Animation {
  private CircleProgress circleProgress;

  private float oldProgress;
  private float newProgress;

  public CircleProgressAnimation(CircleProgress circleProgress, int newProgress) {
    this.oldProgress = circleProgress.getProgress();
    this.newProgress = newProgress;
    this.circleProgress = circleProgress;
  }

  @Override
  protected void applyTransformation(float interpolatedTime, Transformation transformation) {
    int progress = (int) (oldProgress + ((newProgress - oldProgress) * interpolatedTime));
    circleProgress.setProgress(progress);
    //circleProgress.requestLayout();
    circleProgress.postInvalidate();
  }
}
