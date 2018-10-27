package com.yuanye.gameapplication.bean;

import com.yuanye.gameapplication.utils.ColorUtil;
import com.yuanye.gameapplication.utils.CommUtil;

/**
 * Created by YuanYe on 2017/6/16.
 * 小方框的bean
 */

public class BoxBean {

    private String showText = "";//方框上面显示的文字
    private int num;//方框代表的数字(用于计算)
    private int boxAngle = 14;//小方块圆角弧度
    private float box_height = 0; //小方块的高度
    private float box_width = 0; //小方块的宽度
    private float box_now_Y = 0;//小方块距离y轴
    private float box_now_X = 0;//小方块距离x轴
    private String font_color = "#ffffff";//字体颜色
    private int text_size = 90;
    private String bg_color = "#CCC0B2"; //背景颜色
    private boolean empty = true;//小方块是否有内容

    public BoxBean() {

    }

    public int getText_size() {
        //根据文字自动改变字体大小
        int alllength = (int) (getBox_width() > getBox_height() ? box_width : box_height);
        int fontNum = getShowText().toCharArray().length;
        if (fontNum < 3) {
            return (int) (alllength * 0.66);
        } else {
            return (int) ((box_width/fontNum)*2*0.80);
        }
//        return text_size;
    }

    public void setText_size(int text_size) {
        this.text_size = text_size;
    }

    public String getFont_color() {
        if (num < 8) {
            return "#776D61";
        }
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public String getShowText() {
        if (num == 0)
            return showText;
        else {
            if (CommUtil.isEmpty(showText))
                return "" + num;
            return showText;
        }
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getBoxAngle() {
        return boxAngle;
    }

    public void setBoxAngle(int boxAngle) {
        this.boxAngle = boxAngle;
    }

    public float getBox_height() {
        return box_height;
    }

    public void setBox_height(float box_height) {
        this.box_height = box_height;
    }

    public float getBox_width() {
        return box_width;
    }

    public void setBox_width(float box_width) {
        this.box_width = box_width;
    }

    public String getBg_color() {
        if (!empty) {
            return ColorUtil.getColor(num);
        } else {
            //默认颜色（没有填充的时候）
            return bg_color;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public float getBox_now_Y() {
        return box_now_Y;
    }

    public void setBox_now_Y(float box_now_Y) {
        this.box_now_Y = box_now_Y;
    }

    public float getBox_now_X() {
        return box_now_X;
    }

    public void setBox_now_X(float box_now_X) {
        this.box_now_X = box_now_X;
    }

}
