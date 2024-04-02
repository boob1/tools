package org.hongda.tree.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Cat
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/30 10:46
 **/
@Data
@TableName("cat")
public class Cat implements Serializable {
    @TableId("cat_id")
    private Long catId;

    @TableField("name")
    private String name;

    @TableField("parent_cid")
    private Long parentId;

    @TableField("cat_level")
    private Integer catLevel;

    @TableField("sort")
    private Integer sort;

    @TableField("show_status")
    private Integer showStatus;

    @TableField("icon")
    private String icon;

    @TableField("product_unit")
    private String productUnit;

    @TableField("product_count")
    private Integer productCount;

}
