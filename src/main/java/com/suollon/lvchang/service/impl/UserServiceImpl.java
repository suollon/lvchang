package com.suollon.lvchang.service.impl;

import com.suollon.lvchang.dao.UserDAO;
import com.suollon.lvchang.domain.entity.User;
import com.suollon.lvchang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userDAO.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userDAO.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userDAO.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userDAO.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDAO.updateByPrimaryKey(record);
    }
}
