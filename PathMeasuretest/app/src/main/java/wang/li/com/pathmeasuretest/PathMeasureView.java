package wang.li.com.pathmeasuretest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者 ： Long  Create： 2016/9/30   08：30
 * 邮箱 ：984192873@qq.com
 *
 *      此类就是为了 练习 PathMeasure
 *        ---- > 作用 对 path 的截取
 *        重要方发 ：　有参构造　 mPathMeasure.setPath(path, true);　
 *        mPathMeasure.getSegment(）// 最重要 截取path
 *
 *        ------->  详情看源码
 */
public class PathMeasureView extends View {

    private PathMeasure mPathMeasure;
    private Paint mPaint;
    private Path mPath;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        post(new Runnable() {  // 为了获取控件的快和高
            @Override
            public void run() {
                mPathMeasure = new PathMeasure();
                Path path = new Path();
                float finalR = Math.min(getWidth(), getHeight()) / 2;
                path.addCircle(getWidth() / 2, getHeight() / 2, finalR - 5, Path.Direction.CW);

                mPaint = new Paint();
                mPaint.setColor(Color.BLACK);
                mPaint.setAntiAlias(true);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(8);

                mPathMeasure.setPath(path, true);
                final float length = mPathMeasure.getLength();

                mPath = new Path();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.0f);
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();

                        float v = (float) (fraction - ((0.5 - Math.abs(fraction - 0.5))));
                        mPathMeasure.getSegment(length *v, length * fraction, mPath, true);
                        invalidate();
                    }
                });
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.start();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
    }


}
