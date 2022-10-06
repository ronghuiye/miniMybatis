package io.ronghuiye.mybatis.test.dao;

import io.ronghuiye.mybatis.test.po.User;

import java.util.List;

public interface IUserDao {

    User queryUserInfoById(Long uId);

    User queryUserInfo(User req);

    List<User> queryUserInfoList();

    int updateUserInfo(User req);

    void insertUserInfo(User req);

    int deleteUserInfoByUserId(String userId);

}
