package yuan.game2048.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import yuan.game2048.bean.BackgroudBean;
import yuan.game2048.bean.BoxBean;
import yuan.game2048.control.TouchControl;
import yuan.game2048.manager.BoxManager;
import yuan.game2048.utils.CommUtil;

/**
 * Created by YuanYe on 2017/6/16.
 */

public class GameView extends View {

    private Paint mPaint;//默认画笔
    private Paint fontPaint;//字体画笔
    private BackgroudBean backgroudBean;
    private BoxManager boxManager;
    private TouchControl control;

    private float mPosY; //按下时的Y
    private float mPosX;//触控时的x点
    private float mCurrentPosX; //横行滑动距离
    private float mCurrentPosY; //纵向滑动距离


    private boolean startGame = true;

    private OnStepListener listener;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        fontPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startGame == true) {
            backgroudBean = new BackgroudBean();
            backgroudBean.setBg_height(getHeight());
            backgroudBean.setBg_width(getWidth());
            boxManager = new BoxManager(backgroudBean);
            control = new TouchControl(boxManager);
            startGame = false;
        }
        drawBackground(canvas);
        control.autoProduce();
        drawBoxs(canvas);
    }


    /**
     * 绘制背景
     */
    private void drawBackground(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);//充满
        mPaint.setColor(Color.parseColor(backgroudBean.getColor()));
        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        RectF oval1 = new RectF(0, 0, backgroudBean.getBg_width(), backgroudBean.getBg_height());// 设置个新的长方形
        canvas.drawRoundRect(oval1, backgroudBean.getBgAngle() / 2, backgroudBean.getBgAngle() / 2, mPaint);//第二个参数是x半径，第三个参数是y半径
    }

    /**
     * 绘制一个小方框
     *
     * @param canvas
     */
    private void drawBox(Canvas canvas, BoxBean boxBean) {
        mPaint.setStyle(Paint.Style.FILL);//充满
        mPaint.setColor(Color.parseColor(boxBean.getBg_color()));
        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        RectF oval3 = new RectF(boxBean.getBox_now_X(), boxBean.getBox_now_Y(),
                boxBean.getBox_width() + boxBean.getBox_now_X(),
                boxBean.getBox_height() + boxBean.getBox_now_Y());// 设置个新的长方形
        canvas.drawRoundRect(oval3, boxBean.getBoxAngle() / 2, boxBean.getBoxAngle() / 2, mPaint);//第二个参数是x半径，第三个参数是y半径
        //绘制文字
        if (!CommUtil.isEmpty(boxBean.getShowText())) {
            fontPaint.setStrokeWidth(4);
            fontPaint.setTextSize(boxBean.getText_size());
            fontPaint.setStyle(Paint.Style.FILL);
            fontPaint.setColor(Color.parseColor(boxBean.getFont_color()));
            fontPaint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fontMetrics = fontPaint.getFontMetrics();
            float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
            int baseLineY = (int) (oval3.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
            canvas.drawText(boxBean.getShowText() + "", oval3.centerX(),
                    baseLineY, fontPaint);
        }

        Log.i("", "------h----------" + boxBean.getBox_height());
    }

    /**
     * 绘制所有小方框
     *
     * @param canvas
     */
    private void drawBoxs(Canvas canvas) {
        for (int i = 0; i < boxManager.getBoxList().size(); i++) {
            drawBox(canvas, boxManager.getBoxList().get(i));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            mPosX = (int) event.getX();
            mPosY = (int) event.getY();
        }
        if (MotionEvent.ACTION_UP == event.getAction()) {
            mCurrentPosX = (int) event.getX() - mPosX;
            mCurrentPosY = (int) event.getY() - mPosY;
            mPosX = (int) event.getX();
            mPosY = (int) event.getY();
            if (mCurrentPosX > 0 && Math.abs(mCurrentPosX) > Math.abs(mCurrentPosY)) {
                Log.i("game", "向右的按下位置" + mPosX + "移动位置" + mCurrentPosX);
                control.moveRightBox();
            } else if (mCurrentPosX < 0 && Math.abs(mCurrentPosX) > Math.abs(mCurrentPosY)) {
                Log.i("game", "向左的按下位置" + mPosX + "移动位置" + mCurrentPosX);
                control.moveLeftBox();
            } else if (mCurrentPosY > 0 && Math.abs(mCurrentPosX) < Math.abs(mCurrentPosY)) {
                Log.i("game", "向下的按下位置" + mPosX + "移动位置" + mCurrentPosX);
                control.moveDownBox();
            } else if (mCurrentPosY < 0 && Math.abs(mCurrentPosX) < Math.abs(mCurrentPosY)) {
                Log.i("game", "向上的按下位置" + mPosX + "移动位置" + mCurrentPosX);
                control.moveUpBox();
            }
            invalidate();
            if (listener != null) listener.onStep();
        }
        return true;
    }

    /**
     * 重新开始游戏
     */
    public void startGame() {
        startGame = true;
        invalidate();
    }

    /**
     * 获取游戏分数
     */
    public int getScore() {
        if (boxManager == null) return 0;
        return boxManager.getScore();
    }

    /**
     * 设置游戏滑动监听
     *
     * @param listener
     */
    public void setOnStepListener(OnStepListener listener) {
        this.listener = listener;
    }

    public interface OnStepListener {
        void onStep();
    }

}
