package io.ronghuiye.mybatis.test;

import com.alibaba.fastjson.JSON;
import io.ronghuiye.mybatis.io.Resources;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;
import io.ronghuiye.mybatis.session.SqlSessionFactoryBuilder;
import io.ronghuiye.mybatis.test.dao.IUserDao;
import io.ronghuiye.mybatis.test.po.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_insertUserInfo() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = new User();
        user.setUserId("10002");
        user.setUserName("white");
        user.setUserHead("1_05");
        userDao.insertUserInfo(user);
        logger.info("result：{}", "Insert OK");

        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        int count = userDao.deleteUserInfoByUserId("10002");
        logger.info("result：{}", count == 1);

        sqlSession.commit();
    }


    @Test
    public void test_updateUserInfo() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        int count = userDao.updateUserInfo(new User(1L, "3", "cat"));
        logger.info("result：{}", count);

        sqlSession.commit();
    }

    @Test
    public void test_queryUserInfoById() {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = userDao.queryUserInfoById(1L);
        logger.info("result：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo() throws IOException {

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = userDao.queryUserInfo(new User(1L));
        logger.info("result：{}", JSON.toJSONString(user));

    }

    @Test
    public void test_queryUserInfoList() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        logger.info("result：{}", JSON.toJSONString(users));
    }

}
