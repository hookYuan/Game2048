package yuan.game2048.manager;

import yuan.game2048.bean.BackgroudBean;
import yuan.game2048.bean.BoxBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuanYe on 2017/6/17.
 */

public class BoxManager {

    private List<BoxBean> boxBeanList; //box的集合
    private BackgroudBean backgroudBean;

    public BoxManager(BackgroudBean backgroudBean) {
        boxBeanList = new ArrayList<>();
        this.backgroudBean = backgroudBean;
        initBoxBeanList(backgroudBean);
    }

    public BackgroudBean getBackgroudBean() {
        return backgroudBean;
    }

    public void setBackgroudBean(BackgroudBean backgroudBean) {
        this.backgroudBean = backgroudBean;
    }

    /**
     * 根据预设背景生成box集合
     */
    private void initBoxBeanList(BackgroudBean backgroudBean) {
        //计算每个小方块的宽高
        float box_width = (backgroudBean.getBg_width() - backgroudBean.getPadding() * (backgroudBean.getNum_w() + 1)) / backgroudBean.getNum_w();
        float box_height = (backgroudBean.getBg_height() - backgroudBean.getPadding() * (backgroudBean.getNum_h() + 1)) / backgroudBean.getNum_h();
        float box_w_now = 0;//小方块现在的宽度
        float box_h_now = 0;//小方块现在的高度

        for (int i = 0; i < backgroudBean.getNum_w(); i++) {
            box_h_now = (box_height + backgroudBean.getPadding()) * i + backgroudBean.getPadding();
            box_w_now = 0;
            for (int j = 0; j < backgroudBean.getNum_h(); j++) {
                box_w_now = (box_width + backgroudBean.getPadding()) * j + backgroudBean.getPadding();
                BoxBean boxBean = new BoxBean();
                boxBean.setBox_width(box_width);
                boxBean.setBox_height(box_height);
                boxBean.setBox_now_X(box_w_now);
                boxBean.setBox_now_Y(box_h_now);
                boxBeanList.add(boxBean);
            }
        }
    }

    public List<BoxBean> getBoxList() {
        if (boxBeanList == null) {
            boxBeanList = new ArrayList<>();
            return boxBeanList;
        }
        return boxBeanList;
    }

    /**
     * 获取最新分数
     */
    public int getScore() {
        int sum = 0;
        for (BoxBean box : boxBeanList) {
            int score = box.getNum();
            sum += score;
        }
        return sum;
    }
}
