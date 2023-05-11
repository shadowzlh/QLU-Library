package com.shadowzlh.lib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.shadowzlh.lib.mapper.UserSeatstrMapper;
import com.shadowzlh.lib.pojo.po.UserSeatstr;
import com.shadowzlh.lib.service.UserSeatstrService;
import org.springframework.stereotype.Service;


/**
 * (UserSeatstr)表服务实现类
 *
 * @author zlh
 * @since 2022-11-19 23:42:39
 */
@Service("userSeatstrService")
public class UserSeatstrServiceImpl extends MppServiceImpl<UserSeatstrMapper, UserSeatstr> implements UserSeatstrService {

}

