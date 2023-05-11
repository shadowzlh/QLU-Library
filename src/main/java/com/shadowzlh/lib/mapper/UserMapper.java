package com.shadowzlh.lib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.shadowzlh.lib.pojo.po.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * (User)表数据库访问层
 *
 * @author zlh
 * @since 2022-11-11 09:23:59
 */
public interface UserMapper extends MppBaseMapper<User> {

}

