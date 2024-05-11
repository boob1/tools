package stringTools;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/22 15:04
 **/
public class StringTest {
    public static void main(String[] args) {

        System.out.println(StringUtils.leftPad("123", 5));

        System.out.println(StringUtils.format("123", "456","789"));
    }
}
