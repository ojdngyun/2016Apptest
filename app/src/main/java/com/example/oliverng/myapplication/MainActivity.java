package com.example.oliverng.myapplication;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mContainerLayout;
    RelativeLayout mContainerLayout2;
    TextView mTextView;
    TextView mTextView2;
    static int green;
    static int grey;
    Toolbar toolbar;
    boolean isSelected = true;
    ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        green = ContextCompat.getColor(this, android.R.color.holo_green_light);
        grey = ContextCompat.getColor(this, android.R.color.darker_gray);
        mContainerLayout = (RelativeLayout) findViewById(R.id.ContainerLayout);
        mTextView = (TextView) findViewById(R.id.textView);
        mContainerLayout2 = (RelativeLayout) findViewById(R.id.ContainerLayout2);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mContainerLayout.setOnClickListener(createClickListener(mTextView));
        mContainerLayout2.setOnClickListener(createClickListener(mTextView2));

        mImageButton = (ImageButton) findViewById(R.id.fab);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private View.OnClickListener createClickListener(final TextView view){
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition((ViewGroup) v);
                int finalRadius = (int) Math.hypot(v.getWidth()/ 2, v.getHeight()/ 2);
                if(isSelected){
                    view.setText("InActive");
                    Animator anim = ViewAnimationUtils.createCircularReveal(
                            v, (int) v.getWidth() / 2, (int) v.getHeight() / 2, 0, finalRadius
                    );
                    anim.setDuration(500);
                    anim.setInterpolator(new AnticipateOvershootInterpolator());
                    v.setBackground(ContextCompat.getDrawable(MainActivity.this, R.mipmap.eclairs));
                    anim.start();
                    isSelected = false;
                }else{
                    view.setText("Active");
                    Animator anim = ViewAnimationUtils.createCircularReveal(
                            v, (int) v.getWidth() / 2, (int) v.getHeight() / 2, 0, finalRadius
                    );
                    anim.setDuration(500);
                    anim.setInterpolator(new AnticipateOvershootInterpolator());
                    v.setBackgroundColor(green);
                    anim.start();
                    isSelected = true;
                }
            }
        };
        return clickListener;
    }
}
