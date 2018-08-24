package com.example.hnd6kor.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.circle_progress)CircleProgress circle;
  @BindView(R.id.donut_progress)DonutProgress donutProgress;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    animateDonut(99);
    animateCircle(100);

    donutProgress.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if(donutProgress.getProgress() >= 70)
          animateDonut(50);
        else animateDonut(99);
      }
    });
    circle.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if(circle.getProgress() >= 70)
        animateCircle(50);
        else animateCircle(100);
      }
    });
    IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    getApplicationContext().registerReceiver(broadcastReceiver,iFilter);

  }

  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
    @Override public void onReceive(Context context, Intent intent) {
      int scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
      int health = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
     // Log.e("info_temp",health+"");
      animateDonut(scale);
      animateCircle(health/10);
    }
  };
  private void animateDonut(int progress){
    ProgressAnimation animation = new ProgressAnimation(donutProgress, progress);
    animation.setDuration(1000);
    donutProgress.startAnimation(animation);
  }
  private void  animateCircle(int progress) {
    CircleProgressAnimation circleAnimation = new CircleProgressAnimation(circle, progress);
    circleAnimation.setDuration(1000);
    circle.startAnimation(circleAnimation);
  }
}
