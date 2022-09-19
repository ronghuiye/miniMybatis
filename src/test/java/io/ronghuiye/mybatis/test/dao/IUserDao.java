package io.ronghuiye.mybatis.test.dao;

import io.ronghuiye.mybatis.test.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}
