package io.ronghuiye.mybatis.test;

import io.ronghuiye.mybatis.binding.MapperProxyFactory;
import io.ronghuiye.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("io.ronghuiye.mybatis.test.dao.IUserDao.queryUserName", "excuted sql queryUserName");
        IUserDao userDao = factory.newInstance(sqlSession);
        String result = userDao.queryUserName("id");
        logger.info(result);
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao)Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                ((proxy, method, args) -> "proxy excuted!"));
        String result = userDao.queryUserName("id");
        logger.info(result);
    }

}
