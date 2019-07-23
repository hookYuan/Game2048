package yuan.game2048.bean;

/**
 * Created by YuanYe on 2017/6/17.
 */

public class BackgroudBean {

    private float bg_width = 0;//游戏背景的宽
    private float bg_height = 0;//游戏背景的高

    private int num_w = 5;//横向方块的个数
    private int num_h = 5;//竖向方块的个数

    private int padding = 20;//方块之间的间隔

    private String color = "#BBAB9E"; //默认背景的颜色
    private String bg_img = "";//背景图片
    private int bgAngle = 20;//画布背景圆角弧度

    public int getBgAngle() {
        return bgAngle;
    }

    public void setBgAngle(int bgAngle) {
        this.bgAngle = bgAngle;
    }

    public float getBg_width() {
        return bg_width;
    }

    public void setBg_width(float bg_width) {
        this.bg_width = bg_width;
    }

    public float getBg_height() {
        return bg_height;
    }

    public void setBg_height(float bg_height) {
        this.bg_height = bg_height;
    }

    public int getNum_w() {
        return num_w;
    }

    public void setNum_w(int num_w) {
        this.num_w = num_w;
    }

    public int getNum_h() {
        return num_h;
    }

    public void setNum_h(int num_h) {
        this.num_h = num_h;
    }

    public int getPadding() {
        int h = (int) (bg_width>bg_height?bg_height:bg_width);
        return (int) (h/num_h*0.12);
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBg_img() {
        return bg_img;
    }

    public void setBg_img(String bg_img) {
        this.bg_img = bg_img;
    }
}
