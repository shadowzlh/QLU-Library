package com.shadowzlh.lib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.shadowzlh.lib.mapper.UserMapper;
import com.shadowzlh.lib.pojo.po.User;
import com.shadowzlh.lib.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * (User)表服务实现类
 *
 * @author zlh
 * @since 2022-11-11 09:20:50
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends MppServiceImpl<UserMapper, User> implements UserService {

}

