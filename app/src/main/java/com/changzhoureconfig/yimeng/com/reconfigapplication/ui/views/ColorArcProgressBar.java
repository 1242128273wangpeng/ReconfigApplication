package com.changzhoureconfig.yimeng.com.reconfigapplication.ui.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.changzhoureconfig.yimeng.com.reconfigapplication.R;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.BaseUtils;
import com.changzhoureconfig.yimeng.com.reconfigapplication.utils.DayOperateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * colorful arc progress bar
 * Created by shinelw on 12/4/15.
 */
public class ColorArcProgressBar extends View {
    //直径
    private int diameter = 500;

    //圆心
    private float centerX;
    private float centerY;

    private Paint allArcPaint;
    private Paint progressPaint;
    private Paint imagePaint;
    private Paint hintPaint;
    private Paint degreePaint;
    private Paint curSpeedPaint;
    private RectF bgRect;
    // 添加
    private float x1, x2;
    private float y1, y2;
    private Paint mTextPaint;
    private Paint redCirclePaint;
    private Paint wholeArcPaint;
    private String wholebgArcColor = "#ffffff";
    private String redCircleColor = "#ff0000";
    // 结束
    private ValueAnimator progressAnimator;
    private float startAngle = 180;
    private float sweepAngle = 180;
    private float currentAngle = 0;
    private float lastAngle = 0;
    private int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};
    private float maxValues = 100;
    private float curValues = -1;
    private float bgArcWidth = dipToPx(20);
    private float progressWidth = dipToPx(10);
    private float textSize = dipToPx(60);
    private float hintSize = dipToPx(16);
    private float curSpeedSize = dipToPx(13);
    private int aniSpeed = 600;
    private float longdegree = dipToPx(6);
    private float positionX;
    private float positionY;
    private float positionR;
    private final int DEGREE_PROGRESS_DISTANCE = dipToPx(8);
    private String hintColor = "#ffffff";
    private String longDegreeColor = "#ffffff";
    private String bgArcColor = "#3ed7df";
    private boolean isShowCurrentSpeed = true;
    private String hintString = "Km/h";
    private boolean isNeedTitle;
    private boolean isNeedUnit;
    private boolean isNeedDial;
    private boolean isNeedContent;
    private String titleString;
    private AnimateEndListener animateEndListener;
    // sweepAngle / maxValues 的值
    private float k;
    private boolean begin = false;
    private boolean showbrith;
    private int chooseWeek;

    public ColorArcProgressBar(Context context) {
        super(context, null);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initCofig(context, attrs);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCofig(context, attrs);
        initView();
    }
    /**
     */
    public int calculate(Date d1, Date d2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(d1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(d2);
        int diff = DayOperateUtils.daysBetween(calendar1, calendar2);
        return diff;
    }
    /**
     * 初始化布局配置
     *
     * @param context
     * @param attrs
     */
    private void initCofig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorArcProgressBar);
        int color1 = a.getColor(R.styleable.ColorArcProgressBar_front_color1, Color.GREEN);
        int color2 = a.getColor(R.styleable.ColorArcProgressBar_front_color2, color1);
        int color3 = a.getColor(R.styleable.ColorArcProgressBar_front_color3, color1);
        colors = new int[]{color1, color2, color3, color3};
        sweepAngle = a.getInteger(R.styleable.ColorArcProgressBar_total_engle, 180);
        bgArcWidth = a.getDimension(R.styleable.ColorArcProgressBar_back_width, dipToPx(2));
        progressWidth = a.getDimension(R.styleable.ColorArcProgressBar_front_width, dipToPx(10));
        isNeedTitle = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_title, false);
        isNeedContent = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_content, false);
        isNeedUnit = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_unit, false);
        isNeedDial = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_dial, false);
        hintString = a.getString(R.styleable.ColorArcProgressBar_string_unit);
        titleString = a.getString(R.styleable.ColorArcProgressBar_string_title);
        curValues = a.getFloat(R.styleable.ColorArcProgressBar_current_value, 0);
        maxValues = a.getFloat(R.styleable.ColorArcProgressBar_max_value, 60);
        setCurrentValues(curValues);
        setMaxValues(maxValues);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) + dipToPx(26);
        int height = (int) (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2 + dipToPx(56);
        setMeasuredDimension(width, height);
    }

    private void initView() {
        diameter = 3 * BaseUtils.getScreenWidth(getContext()) / 4;
        //弧形的矩阵区域
        bgRect = new RectF();
        bgRect.top = longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE + dipToPx(1);
        bgRect.left = longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE + dipToPx(14.2f);
        bgRect.right = diameter + (longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE) + dipToPx(14);
        bgRect.bottom = diameter + (longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE);
        //圆心
        centerX = (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;
        centerY = (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;
        //外部刻度线
        degreePaint = new Paint();
        degreePaint.setColor(Color.parseColor(longDegreeColor));
        //整个弧形
        allArcPaint = new Paint();
        allArcPaint.setAntiAlias(true);
        allArcPaint.setStyle(Paint.Style.STROKE);
        allArcPaint.setStrokeWidth(bgArcWidth);
        allArcPaint.setColor(Color.parseColor(bgArcColor));
        allArcPaint.setStrokeCap(Paint.Cap.ROUND);
        //整个内外环弧形
        wholeArcPaint = new Paint();
        wholeArcPaint.setAntiAlias(true);
        wholeArcPaint.setStyle(Paint.Style.STROKE);
        wholeArcPaint.setStrokeWidth(4 + bgArcWidth);
        wholeArcPaint.setColor(Color.parseColor(wholebgArcColor));
        wholeArcPaint.setStrokeCap(Paint.Cap.ROUND);
        redCirclePaint = new Paint();
        redCirclePaint.setAntiAlias(true);
        redCirclePaint.setStyle(Paint.Style.FILL);
        redCirclePaint.setColor(Color.parseColor(redCircleColor));
        redCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        //当前进度的弧形
        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setColor(Color.GREEN);
        //内容显示文字
        imagePaint = new Paint();
        //显示单位文字
        hintPaint = new Paint();
        hintPaint.setTextSize(hintSize);
        hintPaint.setTextAlign(Paint.Align.CENTER);
        hintPaint.setColor(Color.WHITE);
        //显示标题文字
        curSpeedPaint = new Paint();
        curSpeedPaint.setTextSize(curSpeedSize);
        curSpeedPaint.setColor(Color.parseColor(hintColor));
        curSpeedPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //抗锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        Paint bgpaint = new Paint();
        bgpaint.setTextAlign(Paint.Align.CENTER);
        //整个背景弧
        canvas.drawArc(bgRect, startAngle, sweepAngle, false, wholeArcPaint);
        //整个弧
        canvas.drawArc(bgRect, startAngle, sweepAngle, false, allArcPaint);
        //设置渐变色
        SweepGradient sweepGradient = new SweepGradient(centerX, centerY, colors, null);
        Matrix matrix = new Matrix();
        matrix.setRotate(180, centerX, centerY);
        sweepGradient.setLocalMatrix(matrix);
        progressPaint.setShader(sweepGradient);
        redCirclePaint.setColor(Color.WHITE);
        canvas.drawCircle(bgRect.left, (bgRect.top + diameter / 2), progressWidth / 2 + 2, redCirclePaint);
        redCirclePaint.setColor(Color.RED);
        canvas.drawCircle(bgRect.left, (bgRect.top + diameter / 2 - dipToPx(0.5f)), progressWidth / 2, redCirclePaint);
        // 当前进度
        canvas.drawArc(bgRect, startAngle, currentAngle, false, progressPaint);
        // 画圆
        canvas.drawCircle(centerX + dipToPx(14f) - (float) (diameter / 2 * Math.cos(currentAngle * Math.PI / 180)),
                centerY + dipToPx(1.2f) - (float) (diameter / 2 * Math.sin(currentAngle * Math.PI / 180)), progressWidth / 2 - dipToPx(1f), redCirclePaint);
        positionX = centerX + dipToPx(14f) - (float) (diameter / 2 * Math.cos(currentAngle * Math.PI / 180));
        positionY = centerY + dipToPx(1.2f) - (float) (diameter / 2 * Math.sin(currentAngle * Math.PI / 180));
        positionR = progressWidth / 2 - dipToPx(1f);
        if (isNeedContent) {
            Bitmap bitmap = null;
            switch (chooseWeek) {
                case 0:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week1);
                    break;
                case 1:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week2);
                    break;
                case 2:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week3);
                    break;
                case 3:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week4);
                    break;
                case 4:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week5);
                    break;
                case 5:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week6);
                    break;
                case 6:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week7);
                    break;
                case 7:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week8);
                    break;
                case 8:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week9);
                    break;
                case 9:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week10);
                    break;
                case 10:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week11);
                    break;
                case 11:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week12);
                    break;
                case 12:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week13);
                    break;
                case 13:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week14);
                    break;
                case 14:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week15);
                    break;
                case 15:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week16);
                    break;
                case 16:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week17);
                    break;
                case 17:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week18);
                    break;
                case 18:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week19);
                    break;
                case 19:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week20);
                    break;
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week20);
                    break;
                default:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.week20);
                    break;
            }
            Bitmap centerbg = BitmapFactory.decodeResource(getResources(), R.drawable.centerbg);
            canvas.drawBitmap(centerbg, centerX - (centerbg.getWidth() / 2) + dipToPx(13), dipToPx(150) - centerbg.getHeight() / 2, imagePaint);
            float scaleWidth = 1, scaleHeight = 1;
            float scale = 0.48f;
            scaleWidth = scaleWidth * scale;
            scaleHeight = scaleHeight * scale;
            Matrix matrix1 = new Matrix();
            matrix1.postScale(scaleWidth, scaleHeight);
            imagePaint.setColor(Color.WHITE);
            Bitmap bitmapbak = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix1, true);
            canvas.drawBitmap(bitmapbak, centerX - (bitmapbak.getWidth() / 2) + dipToPx(13), dipToPx(150) - bitmapbak.getHeight() / 2, imagePaint);
            Paint mainPaint = new Paint();
            mainPaint.setColor(Color.parseColor("#3ed7df"));
            mainPaint.setStrokeWidth(70);
            mainPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getMeasuredWidth() / 2, dipToPx(150), dipToPx(61.5f), mainPaint);// 蓝色圆
            Paint containP = new Paint();
            containP.setColor(Color.WHITE);
            containP.setStyle(Paint.Style.STROKE);
            containP.setAlpha(250);
            containP.setStrokeWidth(6);
            canvas.drawCircle(getMeasuredWidth() / 2, dipToPx(150), dipToPx(48), containP);// 小圆
            containP.setAlpha(120);
            containP.setStrokeWidth(24);
            canvas.drawCircle(getMeasuredWidth() / 2, dipToPx(150), dipToPx(53), containP);// 中圆
            containP.setAlpha(60);
            containP.setStrokeWidth(24);
            canvas.drawCircle(getMeasuredWidth() / 2, dipToPx(150), dipToPx(61.5f), containP);// 大圆
            if (showbrith) {
                Bitmap birthbitmap = BaseUtils.decodeResource(getResources(), R.drawable.babybirth);
                canvas.drawBitmap(birthbitmap, centerX - (birthbitmap.getWidth() / 2) + dipToPx(13), dipToPx(150) - birthbitmap.getHeight() / 2, imagePaint);
            }
        }
        if (isNeedUnit) {
            canvas.drawText(hintString, centerX, centerY + 2 * textSize / 3, hintPaint);
        }
        if (isNeedTitle) {
            canvas.drawText(titleString, centerX, centerY - 2 * textSize / 3, curSpeedPaint);
        }
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(dipToPx(15));
        mTextPaint.setColor(Color.WHITE);
        canvas.drawText("怀孕", bgRect.left - dipToPx(16), centerY + dipToPx(42), mTextPaint);
        canvas.drawText("出生", bgRect.right - dipToPx(12), centerY + dipToPx(42), mTextPaint);
        invalidate();
    }

    public void setShowbrith(boolean showbrith) {
        this.showbrith = showbrith;
        postInvalidate();
    }

    public void getWholeDay(){

    }

    public void setChooseWeek(int chooseWeek) {
        this.chooseWeek = chooseWeek;
        postInvalidate();
    }

    /**
     * 设置最大值
     *
     * @param maxValues
     */
    public void setMaxValues(float maxValues) {
        this.maxValues = maxValues;
        k = sweepAngle / maxValues;
    }

    /**
     * 设置当前值
     *
     * @param currentValues
     */
    public float setCurrentValues(float currentValues) {
        if (currentValues > maxValues) {
            currentValues = maxValues;
        }
        lastAngle = currentAngle;
        float order =  lastAngle + currentValues;
        if(order<=0){
            order = 0;
        }
        if(order>180){
            order = 180;
        }
        setAnimation(lastAngle, order, aniSpeed);
        return order;
    }

    /**
     * 设置当天当前值
     *
     * @param currentValues
     */
    public float setCurrentDayValues(float lastAngle, float currentValues) {
        if (currentValues > maxValues) {
            currentValues = maxValues;
        }
        setAnimation(lastAngle, currentValues, aniSpeed);
        return currentValues;
    }

    public float setCurrentDayValues(float lastAngle, float currentValues, int maniSpeed) {
        if (currentValues > maxValues) {
            currentValues = maxValues;
        }
        setAnimation(lastAngle, currentValues, maniSpeed);
        return currentValues;
    }

    public float getCurrentAngle() {
        return currentAngle;
    }

    /**
     * 设置整个圆弧宽度
     *
     * @param bgArcWidth
     */
    public void setBgArcWidth(int bgArcWidth) {
        this.bgArcWidth = bgArcWidth;
    }

    /**
     * 设置进度宽度
     *
     * @param progressWidth
     */
    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
    }

    /**
     * 设置速度文字大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * 设置单位文字大小
     *
     * @param hintSize
     */
    public void setHintSize(int hintSize) {
        this.hintSize = hintSize;
    }

    /**
     * 设置单位文字
     *
     * @param hintString
     */
    public void setUnit(String hintString) {
        this.hintString = hintString;
        invalidate();
    }

    /**
     * 设置直径大小
     *
     * @param diameter
     */
    public void setDiameter(int diameter) {
        this.diameter = dipToPx(diameter);
    }

    /**
     * 为进度设置动画
     *
     * @param last
     * @param current
     */
    private void setAnimation(float last, float current, int length) {
        progressAnimator = ValueAnimator.ofFloat(last, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(currentAngle);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                if (currentAngle >= 180) {
                    currentAngle = 180;
                }
                if (currentAngle < 0) {
                    currentAngle = 0;
                }
                if (animateEndListener != null) {
                    animateEndListener.animEnd(currentAngle);
                }
            }
        });
        progressAnimator.start();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int pxToDip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public void setIsShowCurrentSpeed(boolean isShowCurrentSpeed) {
        this.isShowCurrentSpeed = isShowCurrentSpeed;
    }

    public void setAnimateEndListener(AnimateEndListener animateEndListener) {
        this.animateEndListener = animateEndListener;
    }

    public void removeAnimateEndListener(AnimateEndListener animateEndListener) {
        this.animateEndListener = null;
    }


    public interface AnimateEndListener {
        public void animEnd(float currentAngle);
    }

}
