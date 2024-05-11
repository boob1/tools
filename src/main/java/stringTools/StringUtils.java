package stringTools;

import cn.hutool.core.util.StrUtil;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/22 15:03
 **/
public class StringUtils {

    /**
     *
     * @param str 原字符窜
     * @param size：要求长度
     * @return 长度不足为size,前面补0
     */
    public static String leftPad(String str, int size) {
        return StrUtil.padPre(str, size, '0');
    }

    /**
     *
     * @param strs 拼接字符窜1
     * @param strs2 拼接字符窜2
     * @param strs3 拼接字符窜3
     * @return 三个字符窜拼接一起
     */
    public static String format(String strs,String strs2 ,String strs3) {
        return StrUtil.format("{}{}{}", strs, strs2, strs3);
    }
}
