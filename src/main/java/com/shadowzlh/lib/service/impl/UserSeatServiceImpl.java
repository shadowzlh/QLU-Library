package com.shadowzlh.lib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.shadowzlh.lib.mapper.UserSeatMapper;
import com.shadowzlh.lib.pojo.po.UserSeat;
import com.shadowzlh.lib.service.UserSeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * (UserSeat)表服务实现类
 *
 * @author zlh
 * @since 2022-11-11 09:31:41
 */
@Service("userSeatService")
@Transactional
public class UserSeatServiceImpl extends MppServiceImpl<UserSeatMapper, UserSeat> implements UserSeatService {

}

