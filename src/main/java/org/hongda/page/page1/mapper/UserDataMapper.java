package org.hongda.page.page1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hongda.page.page1.PagePara;
import org.hongda.page.page1.User;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/9 19:26
 **/
@Mapper
public interface UserDataMapper extends BaseMapper<User> {
    /**
     * 查询全部用户信息
     * @return
     */
    IPage<User> findAll(Page<PagePara> page, @Param("par")PagePara pagePara);
}
