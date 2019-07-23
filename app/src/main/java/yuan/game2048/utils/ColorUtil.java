package yuan.game2048.utils;

/**
 * Created by YuanYe on 2017/6/16.
 */

public class ColorUtil {

    private static String[] box_color = {"#ECE4D9", "#EEDFCC", "#F1B07B",
            "#F59266", "#F47C5F", "#E9593A", "#F4D86C", "#EED04B",
            "#E1B717", "#E8C202", "#5CD98F", "#22BA61", "#FC1D19"}; //方块的颜色

    /**
     * 根据当前的数字获取颜色
     */
    public static String getColor(int num) {
        if ((num & (num - 1)) == 0 ? false : true) {
            return "#FC1D19";
        }
        int index = 0;
        while (true) {
            num = num / 2;
            if (num <= 1)
                break;
            else
                index++;
        }
        return box_color[index];
    }
}
