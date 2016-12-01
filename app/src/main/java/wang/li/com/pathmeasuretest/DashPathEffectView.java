package wang.li.com.pathmeasuretest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者 ： Long  Create： 2016/9/30   10：02
 * 邮箱 ：984192873@qq.com
 */
public class DashPathEffectView extends View {

    private Paint p;

    public DashPathEffectView(Context context) {
        this(context, null);
    }

    public DashPathEffectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashPathEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
//        p.set
        p.setStrokeWidth(3);
        PathEffect effects = new DashPathEffect(new float[]{10, 10, 10, 10}, 0);
        p.setPathEffect(effects);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 40, getWidth(), 40, p);
    }
}
