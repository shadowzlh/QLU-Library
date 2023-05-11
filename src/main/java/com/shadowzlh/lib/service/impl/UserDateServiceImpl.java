package com.shadowzlh.lib.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.shadowzlh.lib.mapper.UserDateMapper;
import com.shadowzlh.lib.pojo.po.UserDate;
import com.shadowzlh.lib.service.UserDateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * (UserDate)表服务实现类
 *
 * @author zlh
 * @since 2022-11-11 09:31:43
 */
@Service("userDateService")
@Transactional
public class UserDateServiceImpl extends MppServiceImpl<UserDateMapper, UserDate> implements UserDateService {

}

