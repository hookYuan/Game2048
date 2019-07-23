package yuan.game2048.control;

import yuan.game2048.bean.BoxBean;
import yuan.game2048.manager.BoxManager;

import java.util.Random;

/**
 * Created by YuanYe on 2017/6/17.
 * 滑动控制器
 */

public class TouchControl {

    BoxManager boxManager;

    public TouchControl(BoxManager boxManager) {
        this.boxManager = boxManager;
    }

    /**
     * 随机生成一个小方块的方法
     */
    public void autoProduce() {
        Random random = new Random();
        //随机方块的位置
        int postion = random.nextInt(boxManager.getBoxList().size());
        //限定循环次数，防止死循环
        int index = boxManager.getBackgroudBean().getNum_h() * boxManager.getBackgroudBean().getNum_w();
        while (index > 0) {
            BoxBean boxBean = boxManager.getBoxList().get(postion);
            if (boxBean.isEmpty()) {
                boxBean.setEmpty(false);
                //随机生成2-8的点数
                boxBean.setNum((int) Math.pow(2, random.nextInt(3) + 1));
                break;
            }
            if (postion < boxManager.getBoxList().size() - 1) {
                postion = postion + 1;
            } else {
                postion = 0;
                //TODO 这里代表游戏已经结束
            }
            index--;
        }
    }

    /**
     * 向下移动小方块的方法
     */
    public void moveDownBox() {
        for (int j = 0; j < boxManager.getBackgroudBean().getNum_w(); j++) {
            int emptyNum = 0;//为空的个数
            int startIndex = j + boxManager.getBoxList().size() - boxManager.getBackgroudBean().getNum_w();//没列开始的下标
            boolean isMerge = false;//判断是否已经合并（只合并一次）
            BoxBean front = null;//前一个空方快
            for (int i = boxManager.getBackgroudBean().getNum_h() - 1; i >= 0; i--) {
                //当前的方块
                int currenPos = startIndex - (boxManager.getBackgroudBean().getNum_h() - 1 - i) * boxManager.getBackgroudBean().getNum_w();
                BoxBean current = boxManager.getBoxList().get(currenPos);
                if (current.isEmpty()) {
                    emptyNum = emptyNum + 1;
                } else if (front != null) { //有前一个方块
                    if (emptyNum > 0) { //前面有空方块
                        if (current.getNum() == front.getNum() && !isMerge) {
                            moveBox(currenPos, currenPos + boxManager.getBackgroudBean().getNum_w() * (1 + emptyNum), 1);
                            emptyNum = emptyNum + 1;
                            isMerge = true;
                        } else {
                            moveBox(currenPos, currenPos + emptyNum * boxManager.getBackgroudBean().getNum_w(), 0);
                            front = boxManager.getBoxList().get(currenPos + emptyNum * boxManager.getBackgroudBean().getNum_w());
                        }
                    } else if (current.getNum() == front.getNum() && !isMerge) {
                        moveBox(currenPos, currenPos + boxManager.getBackgroudBean().getNum_w(), 1);
                        emptyNum = emptyNum + 1;
                        isMerge = true;
                    } else {
                        front = current;
                    }
                } else { //没有前一个方块
                    moveBox(currenPos, startIndex, 0);
                    front = boxManager.getBoxList().get(startIndex);
                }
            }
        }
    }

    /**
     * 向上移动小方块的方法
     */
    public void moveUpBox() {
        for (int j = 0; j < boxManager.getBackgroudBean().getNum_w(); j++) {
            int emptyNum = 0;//为空的个数
            int startIndex = j;//没列开始的下标
            boolean isMerge = false;//判断是否已经合并（只合并一次）
            BoxBean front = null;//前一个空方快
            for (int i = 0; i < boxManager.getBackgroudBean().getNum_h(); i++) {
                //当前的方块
                int currenPos = i * boxManager.getBackgroudBean().getNum_w() + startIndex;
                BoxBean current = boxManager.getBoxList().get(currenPos);
                if (current.isEmpty()) {
                    emptyNum = emptyNum + 1;
                } else if (front != null) { //有前一个方块
                    if (emptyNum > 0) { //前面有空方块
                        if (current.getNum() == front.getNum()&& !isMerge) {
                            moveBox(currenPos, currenPos - boxManager.getBackgroudBean().getNum_w() * (1 + emptyNum), 1);
                            emptyNum = emptyNum + 1;
                            isMerge = true;
                        } else {
                            moveBox(currenPos, currenPos - emptyNum * boxManager.getBackgroudBean().getNum_w(), 0);
                            front = boxManager.getBoxList().get(currenPos - emptyNum * boxManager.getBackgroudBean().getNum_w());
                        }
                    } else if (current.getNum() == front.getNum() && !isMerge) {
                        moveBox(currenPos, currenPos - boxManager.getBackgroudBean().getNum_w(), 1);
                        emptyNum = emptyNum + 1;
                        isMerge = true;
                    } else {
                        front = current;
                    }
                } else { //没有前一个方块
                    moveBox(currenPos, startIndex, 0);
                    front = boxManager.getBoxList().get(startIndex);
                }
            }
        }
    }

    /**
     * 向左移动小方块的方法
     */
    public void moveLeftBox() {
        for (int j = 0; j < boxManager.getBackgroudBean().getNum_h(); j++) {
            int emptyNum = 0;//为空的个数
            int startIndex = j * boxManager.getBackgroudBean().getNum_h();//没列开始的下标
            boolean isMerge = false;//判断是否已经合并（只合并一次）
            BoxBean front = null;//前一个空方快
            for (int i = 0; i < boxManager.getBackgroudBean().getNum_w(); i++) {
                //当前的方块
                BoxBean current = boxManager.getBoxList().get(i + startIndex);
                if (current.isEmpty()) {
                    emptyNum = emptyNum + 1;
                } else if (front != null) { //有前一个方块
                    if (emptyNum > 0) { //前面有空方块
                        if (current.getNum() == front.getNum() && !isMerge) {
                            moveBox(i + startIndex, i + startIndex - 1 - emptyNum, 1);
                            emptyNum = emptyNum + 1;
                            isMerge = true;
                        } else {
                            moveBox(i + startIndex, i + startIndex - emptyNum, 0);
                            front = boxManager.getBoxList().get(i + startIndex - emptyNum);
                        }
                    } else if (current.getNum() == front.getNum() && !isMerge) {
                        moveBox(i + startIndex, i + startIndex - 1, 1);
                        emptyNum = emptyNum + 1;
                        isMerge = true;
                    } else {
                        front = current;
                    }
                } else { //没有前一个方块
                    moveBox(i + startIndex, startIndex, 0);
                    front = boxManager.getBoxList().get(startIndex);
                }
            }
        }
    }

    /**
     * 向右移动小方块的方法
     */
    public void moveRightBox() {
        for (int j = 0; j < boxManager.getBackgroudBean().getNum_h(); j++) {
            int emptyNum = 0;//为空的个数
            int startIndex = j * boxManager.getBackgroudBean().getNum_h();//每列开始的下标
            boolean isMerge = false;//判断是否已经合并（只合并一次）
            BoxBean front = null;//前一个空方快
            for (int i = boxManager.getBackgroudBean().getNum_w() - 1; i >= 0; i--) {
                //当前的方块
                BoxBean current = boxManager.getBoxList().get(i + startIndex);
                if (current.isEmpty()) {
                    emptyNum = emptyNum + 1;
                } else if (front != null) { //有前一个方块
                    if (emptyNum > 0) { //前面有空方块
                        if (current.getNum() == front.getNum() && !isMerge) {
                            moveBox(i + startIndex, i + startIndex + 1 + emptyNum, 1);
                            emptyNum = emptyNum + 1;
                            isMerge = true;
                        } else {
                            moveBox(i + startIndex, i + startIndex + emptyNum, 0);
                            front = boxManager.getBoxList().get(i + startIndex + emptyNum);
                        }
                    } else if (current.getNum() == front.getNum() && !isMerge) {
                        moveBox(i + startIndex, i + startIndex + 1, 1);
                        emptyNum = emptyNum + 1;
                        isMerge = true;
                    } else {
                        front = current;
                    }
                } else { //没有前一个方块
                    moveBox(i + startIndex, startIndex + boxManager.getBackgroudBean().getNum_w() - 1, 0);
                    front = boxManager.getBoxList().get(startIndex + boxManager.getBackgroudBean().getNum_w() - 1);
                }
            }
        }
    }

    /**
     * 移动当前个方块的方法
     *
     * @param current 当前坐标
     * @param aims    目标位置坐标
     * @param mode    0--移动  1--合并
     */
    private void moveBox(int current, int aims, int mode) {
        if (current == aims)
            return;
        switch (mode) {
            case 0:
                boxManager.getBoxList().get(aims).setNum(boxManager.getBoxList().get(current).getNum());
                break;
            case 1:
                boxManager.getBoxList().get(aims).setNum(boxManager.getBoxList().get(current).getNum() * 2);
                break;
        }
        boxManager.getBoxList().get(aims).setEmpty(false);
        boxManager.getBoxList().get(current).setNum(0);
        boxManager.getBoxList().get(current).setEmpty(true);
        boxManager.getBoxList().get(current).setShowText("");
    }
}
