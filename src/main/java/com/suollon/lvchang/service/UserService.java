package com.suollon.lvchang.service;

import com.suollon.lvchang.domain.entity.User;

/**
 * @author wangwl
 * @date 2020/4/11 23:48
 */
public interface UserService {

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
