package com.example.oliverng.myapplication;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by oliverng on 2016-05-21.
 */
public class ButtonContainer extends RelativeLayout {


    boolean shown = false;

    Handler handler;

    public ButtonContainer(Context context) {
        super(context);
    }

    public ButtonContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init() {
        handler = new Handler();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(!shown);
                shown = !shown;
            }
        });
    }

    private void startAnimation(final boolean show) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View view = getChildAt(i);
            if (LinearLayout.class.isInstance(view)) {
                view.setVisibility(View.VISIBLE);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("qwerqwer", "onClick: ");
                    }
                });
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ButtonContainer.this.getChildCount(); i++) {
                    View view = getChildAt(i);
                    if (LinearLayout.class.isInstance(view)) {
                        customAnimation(view, i, show);
                    }
                }
            }
        });
    }

    private void customAnimation(final View view, final int index, boolean show) {
        view.setPivotX(0f);
        view.setPivotY(0f);
//
//
        ObjectAnimator animator = new ObjectAnimator();
        animator.setInterpolator(new BounceInterpolator());
        ObjectAnimator.ofFloat(
                view,
                "translationY",
                0,
                -view.getHeight() * index * 0.8f - view.getHeight())
                .setDuration(700)
                .start();
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(700)
                .start();


//        if(show) {
//            ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f).setDuration(1000)
//                    .start();
//            ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f).setDuration(1000)
//                    .start();
//        }else{
//            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f).setDuration(1000)
//                    .start();
//            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f).setDuration(1000)
//                    .start();
//        }

//        ObjectAnimator.ofFloat(
//                ((ViewGroup) view).getChildAt(1),
//                "translationX",
//                -((ViewGroup) view).getChildAt(1).getWidth(),
//                0)
//                .setDuration(700)
//                .start();

        ObjectAnimator.ofObject(
                ((ViewGroup) view).getChildAt(1),
                "textColor",
                new ArgbEvaluator(),
                ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .setDuration(700)
                .start();

        ObjectAnimator.ofObject(
                ((ViewGroup) view).getChildAt(1),
                "textSize",
                new FloatEvaluator(),
                10, 50)
                .setDuration(700)
                .start();
    }
}
