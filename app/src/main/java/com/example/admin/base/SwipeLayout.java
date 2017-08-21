package com.example.admin.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.example.admin.screen.R;


public class SwipeLayout extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {


    final static int LEFT_RANGE = 100;//0~LEFT_RANGE 区间范围内拦截事件
    private Scroller mScroller;
    private Context context;

    private Drawable mShadowLeft;//层次阴影


    private int width;
    private static final int DURATION_TIME = 300;

    private final static int FULL_ALPHA = 255;

    private float mScrimOpacity;

    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;

    private boolean isFinish = false;

    private Paint p = new Paint();

    public SwipeLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());
        getViewTreeObserver().addOnGlobalLayoutListener(this);

        mShadowLeft = context.getResources().getDrawable(R.drawable.shadow_left);
        setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShadow(canvas);
        drawScrim(canvas);
    }

    private void drawShadow(Canvas canvas) {
        mShadowLeft.setBounds(getLeft() - mShadowLeft.getIntrinsicWidth(), getTop(), getLeft(), getBottom());
        mShadowLeft.setAlpha((int) (mScrimOpacity * FULL_ALPHA));
        mShadowLeft.draw(canvas);

    }


    private void drawScrim(Canvas canvas) {

        final int baseAlpha = (DEFAULT_SCRIM_COLOR & 0xff000000) >>> 24;
        final int alpha = (int) (baseAlpha * mScrimOpacity);
        final int color = alpha << 24;

        canvas.drawRect(0, getTop(), getLeft(), getBottom(), p);
        canvas.drawColor(color);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mScrimOpacity = 1 + (float) mScroller.getCurrX() / (float) width;

            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //更新界面
            postInvalidate();
        } else {
            if (isFinish) {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        }
        super.computeScroll();
    }

    float lastX;
    private boolean isInRange = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        float x = ev.getX();
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isInRange = intercept = x >= 0 && x <= LEFT_RANGE;
//                System.out.println("1_ACTION_DOWN    " + x + "  intercept=" + intercept);
                break;
            case MotionEvent.ACTION_MOVE:
                intercept = isInRange && x > lastX;
//                System.out.println("1_ACTION_MOVE  " + lastX);
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            default:
                break;
        }
        lastX = x;
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float offX = x - lastX;
        int left = getLeft();
//        System.out.println("x=" + x);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                System.out.println("2_ACTION_DOWN" + "   " + lastX);
                lastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
//                System.out.println("2_ACTION_MOVE  left=" + left + "  offX=" + offX);
                if (offX > 0) {
                    scrollTo((int) (left - offX), 0);
                    mScrimOpacity = 1 + (float) getScrollX() / (float) width;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (left + offX < width / 4) {
//                    System.out.println("2_ACTION_UP  x=" + x);
                    if (offX > 0) {
                        mScroller.startScroll((int) (-left - offX), 0, (int) (left + offX), 0, DURATION_TIME);
                        invalidate();
                    }
                } else {
//                    System.out.println("2_ACTION_UP  2");
                    mScroller.startScroll((int) (-left - offX), 0, (int) (-width + left + offX), 0, DURATION_TIME);
                    invalidate();
                    isFinish = true;
                }
                break;
            default:
                break;
        }
        return isInRange;
    }


    @Override
    public void onGlobalLayout() {
        width = getWidth();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
