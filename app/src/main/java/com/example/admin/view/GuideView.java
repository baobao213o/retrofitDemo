package com.example.admin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.admin.screen.R;
import com.example.admin.util.ViewUtils;

import java.util.ArrayList;
import java.util.Collection;


public class GuideView extends RelativeLayout {

    public interface IAnimatorEnd {
        void amanitorEnd();
    }

    private IAnimatorEnd listener;

    public void setListener(IAnimatorEnd listener) {
        this.listener = listener;
    }

    private int mHeight;
    private int mWidth;
    private int dp80 = ViewUtils.dp2px(getContext(), 80);
    private boolean mStart = false;

    ImageView pink, purple, yellow, blue, icon;

    public GuideView(Context context) {
        super(context);
        init();
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL, TRUE);
        lp.addRule(CENTER_VERTICAL, TRUE);
        lp.setMargins(0, 0, 0, dp80);


        pink = new ImageView(getContext());
        pink.setLayoutParams(lp);
        pink.setImageResource(R.drawable.pink);
        addView(pink);
        purple = new ImageView(getContext());
        purple.setLayoutParams(lp);
        purple.setImageResource(R.drawable.purple);
        addView(purple);
        yellow = new ImageView(getContext());
        yellow.setLayoutParams(lp);
        yellow.setImageResource(R.drawable.yellow);
        addView(yellow);
        blue = new ImageView(getContext());
        blue.setLayoutParams(lp);
        blue.setImageResource(R.drawable.blue);
        addView(blue);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && !mStart) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    mStart = true;
                }
            }, 500);
        }
    }

    public void start() {
        ViewPath redPath1 = new ViewPath(); //偏移坐标
        redPath1.moveTo(0, 0);
        redPath1.lineTo(mWidth / 5 - mWidth / 2, 0);
        ViewPath redPath2 = new ViewPath();
        redPath2.moveTo(mWidth / 5 - mWidth / 2, 0);
        redPath2.curveTo(-700, -mHeight / 2, mWidth / 3 * 2, -mHeight / 3 * 2, 0, -dp80);
        setAnimation(pink, redPath1, redPath2);

        ViewPath purplePath1 = new ViewPath(); //偏移坐标
        purplePath1.moveTo(0, 0);
        purplePath1.lineTo(mWidth / 5 * 2 - mWidth / 2, 0);
        ViewPath purplePath2 = new ViewPath(); //偏移坐标
        purplePath2.moveTo(mWidth / 5 * 2 - mWidth / 2, 0);
        purplePath2.curveTo(-300, -mHeight / 2, mWidth, -mHeight / 9 * 5, 0, -dp80);
        setAnimation(purple, purplePath1, purplePath2);

        ViewPath yellowPath1 = new ViewPath(); //偏移坐标
        yellowPath1.moveTo(0, 0);
        yellowPath1.lineTo(mWidth / 5 * 3 - mWidth / 2, 0);
        ViewPath yellowPath2 = new ViewPath(); //偏移坐标
        yellowPath2.moveTo(mWidth / 5 * 3 - mWidth / 2, 0);
        yellowPath2.curveTo(300, mHeight, -mWidth, -mHeight / 9 * 5, 0, -dp80);
        setAnimation(yellow, yellowPath1, yellowPath2);

        ViewPath bluePath1 = new ViewPath(); //偏移坐标
        bluePath1.moveTo(0, 0);
        bluePath1.lineTo(mWidth / 5 * 4 - mWidth / 2, 0);
        ViewPath bluePath2 = new ViewPath(); //偏移坐标
        bluePath2.moveTo(mWidth / 5 * 4 - mWidth / 2, 0);
        bluePath2.curveTo(700, mHeight / 3 * 2, -mWidth / 2, mHeight / 2, 0, -dp80);
        setAnimation(blue, bluePath1, bluePath2);
    }

    private void setAnimation(final ImageView target, ViewPath path1, ViewPath path2) {
        //左右平移
        ObjectAnimator Anim1 = ObjectAnimator.ofObject(new ImgView(target), "position", new ViewPathEvaluator(), path1.getPoints().toArray());
        Anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        Anim1.setDuration(10);
        //贝塞尔曲线
        ObjectAnimator Anim2 = ObjectAnimator.ofObject(new ImgView(target), "position", new ViewPathEvaluator(), path2.getPoints().toArray());
        Anim2.setInterpolator(new AccelerateDecelerateInterpolator());

        addAnimation(Anim1, Anim2, target);
    }

    private void addAnimation(ObjectAnimator animator1, ObjectAnimator animator2, ImageView target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0.5f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1, getScale(target), 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1, getScale(target), 1.0f);

        AnimatorSet all2 = new AnimatorSet();
        all2.setDuration(1800);
        all2.playTogether(alpha, scaleX, scaleY, animator2);
        all2.addListener(new AnimEndListener(all2));
        AnimatorSet all = new AnimatorSet();
        all.playSequentially(animator1, all2);
        all.start();
    }

    private float getScale(ImageView target) {
        if (target == pink) {
            return 3.0f;
        } else if (target == purple) {
            return 2.0f;
        } else if (target == yellow) {
            return 4.5f;
        } else if (target == blue) {
            return 3.5f;
        }
        return 2f;
    }

    private boolean isExit=true;
    private class AnimEndListener extends AnimatorListenerAdapter {
        private AnimatorSet target;

        public AnimEndListener(AnimatorSet target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeAllViews();
            if(isExit){
                listener.amanitorEnd();
                isExit=false;
            }
        }

    }

    public class ImgView {
        private final ImageView pink;

        public ImgView(ImageView pink) {
            this.pink = pink;
        }

        public void setPosition(ViewPoint newLoc) {
            pink.setTranslationX(newLoc.x);
            pink.setTranslationY(newLoc.y);
        }
    }


    class ViewPath {

        // 四种路径操作
        public static final int MOVE = 0;
        public static final int LINE = 1;
        public static final int QUAD = 2;
        public static final int CURVE = 3;

        private ArrayList<ViewPoint> mPoints;


        public ViewPath() {
            mPoints = new ArrayList<>();
        }

        // 添加重置起点的路径
        public void moveTo(float x, float y) {
            mPoints.add(ViewPoint.moveTo(x, y, MOVE));
        }

        // 添加直线移动的路径
        public void lineTo(float x, float y) {
            mPoints.add(ViewPoint.lineTo(x, y, LINE));
        }

        // 添加三阶贝塞尔移动的路径
        public void curveTo(float x, float y, float x1, float y1, float x2, float y2) {
            mPoints.add(ViewPoint.curveTo(x, y, x1, y1, x2, y2, CURVE));
        }

        // 添加二阶贝塞尔移动的路径
        public void quadTo(float x, float y, float x1, float y1) {
            mPoints.add(ViewPoint.quadTo(x, y, x1, y1, QUAD));
        }

        // 获取路径集合
        public Collection<ViewPoint> getPoints() {
            return mPoints;
        }


    }


}
