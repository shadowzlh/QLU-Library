package com.shadowzlh.lib.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.shadowzlh.lib.mapper.UserAreaMapper;
import com.shadowzlh.lib.pojo.po.UserArea;
import com.shadowzlh.lib.service.UserAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (UserArea)表服务实现类
 *
 * @author zlh
 * @since 2022-11-11 09:31:44
 */
@Service("userAreaService")
@Transactional
public class UserAreaServiceImpl extends MppServiceImpl<UserAreaMapper, UserArea> implements UserAreaService {

}

