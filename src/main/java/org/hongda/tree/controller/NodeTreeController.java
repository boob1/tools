package org.hongda.tree.controller;

import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.hongda.tree.mapper.CatMapper;
import org.hongda.tree.pojo.Cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hongda.vo.Result;

/**
 * @ClassName NodeTreeController
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/30 10:37
 **/
@RestController
@RequestMapping("/nodeTree")
@Slf4j
public class NodeTreeController {
    @Autowired
    CatMapper catMapper;

    @GetMapping("/getNodeTree")
    public Result getNodeTree() {
        List<Cat> cats = catMapper.selectList(new LambdaQueryWrapper<>());
        List<TreeNode<Long>> nodeList = cats.stream().map(NodeTreeController::getLongTeeNode)
                .collect(Collectors.toList());
        return Result.success(TreeUtil.build(nodeList, 0L));
    }


    public static TreeNode<Long> getLongTeeNode(Cat cat) {
        TreeNode<Long> treeNode = new TreeNode();
        treeNode.setId(cat.getCatId());
        treeNode.setParentId(cat.getParentId());
        treeNode.setName(cat.getName());
        treeNode.setWeight(cat.getSort());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("showStatus", cat.getShowStatus());
        map.put("catLeval", cat.getCatLevel());
        map.put("icon", cat.getIcon());
        map.put("productCount", cat.getProductCount());
        map.put("productUnit", cat.getProductUnit());
        treeNode.setExtra(map);
        return treeNode;
    }
}
