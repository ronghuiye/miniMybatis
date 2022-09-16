package io.ronghuiye.mybatis.test;

import io.ronghuiye.mybatis.binding.MapperProxyFactory;
import io.ronghuiye.mybatis.binding.MapperResistry;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;
import io.ronghuiye.mybatis.session.defaults.DefaultSqlSessionFactory;
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

        MapperResistry resistry = new MapperResistry();
        resistry.addMappers("io.ronghuiye.mybatis.test.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(resistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String result = userDao.queryUserName("id");
        logger.info(result);
    }

}
