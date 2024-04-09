package org.hongda.page.page1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hongda.page.page1.PagePara;
import org.hongda.page.page1.PageResultS;
import org.hongda.page.page1.User;
import org.hongda.page.page1.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/4/9 19:30
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDataMapper userMapper;

    @Override
    public PageResultS<User> findAll(PagePara pagePara) {
        //  当前页
        Long nowPage = pagePara.getNowPage() == null ? 1 : pagePara.getNowPage();
        Long pageCount = pagePara.getPageCount() == null ? 10 : pagePara.getPageCount();

        Page<PagePara> page = new Page<>(nowPage, pageCount);

        IPage<User> queryResult = userMapper.findAll(page, pagePara);
        // 封装分页信息
        PagePara pageParaData = new PagePara(queryResult.getCurrent(), queryResult.getSize(), queryResult.getTotal(), queryResult.getPages());
        PageResultS<User> resultS = new PageResultS<>();
        resultS.setPagePara(pageParaData);
        resultS.setList(queryResult.getRecords());
        return resultS;
    }
}
