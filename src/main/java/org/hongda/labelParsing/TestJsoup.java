package org.hongda.labelParsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @ClassName TestJsoup
 * @Description 解析标签的内容进行修改
 * @Author liuyibo
 * @Date 2024/3/29 13:52
 **/
public class TestJsoup {
    public static void main(String[] args) throws IOException {

        String html1 = "<p><strong>来来来我是加粗的字体</strong></p><p><em>斜体哈哈哈哈哈哈</em></p><p><u>下横线的字体哈哈哈</u></p><p><s>删除线的字体</s></p><h1>H1字体-开发和时空房间</h1><h2>H2字体-哈哈哈哈</h2><ol><li>我是列表1</li><li>我是列表2</li><li>我是列表3</li><li>我是列表4</li></ol><ul><li>我前面是点1</li><li>我前面是点2</li><li>我前面是点3</li></ul><p>角标-CO<sub>2</sub></p><p>上标-9<sup>3</sup></p><p><span class=\"ql-size-large\">大号字体-尽快发货</span></p><p><span class=\"ql-size-small\">小号字体-活动哈哈</span></p><h2>二级标题-哈哈哈</h2><h4>四级标题-呼呼呼呼</h4><p><span style=\"color: rgb(230, 0, 0);\">字体加颜色-红色</span></p><p><span style=\"background-color: rgb(178, 178, 0);\">字体填充色-哈哈</span></p><p class=\"ql-align-center\">我是居中</p><p class=\"ql-align-right\">我说居右</p><p><img src=\"/tlmp-content/temp-imgs/portal/viewImg?imgContent=052598519bab46f4a244decc912e2f68.png\"></p><p><img src=\"/tlmp-content/temp-imgs/portal/viewImg?imgContent=914d7ab2c2834157b3d546bd7d2c557c.png\"></p>";

        updateContentImgToNas(html1);


    }

    private static void updateContentImgToNas(String wordText) throws IOException {
        // 如果未存在图片，不用修改
        if (StringUtils.isEmpty(wordText) || !wordText.contains("img")) {
            System.out.println(wordText);
        }

        //  转换成一个可操作的文档对象
        Document doc = Jsoup.parse(wordText);
        // 获取所有img的标签
        Elements paragraphs = doc.select("p > img");
        // 遍历所有img标签
        if (!paragraphs.isEmpty()) {
            for (Element paragraph : paragraphs) {
                // 获取图片的路径
                String imagePath = paragraph.attr("src");
                String nasPath = "/mms/2024-03-12/7.jpg";
                // 修改图片的路径
                paragraph.attr("src", nasPath);
            }
        }
        // 输出修改后的内容
        System.out.println(doc.select("p").toString()); ;

    }
}
