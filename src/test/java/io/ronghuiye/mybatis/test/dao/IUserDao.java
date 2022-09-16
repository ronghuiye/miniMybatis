package io.ronghuiye.mybatis.test.dao;

public interface IUserDao {

    String queryUserName(String uid);

    Integer queryUserAge(String uid);
}
