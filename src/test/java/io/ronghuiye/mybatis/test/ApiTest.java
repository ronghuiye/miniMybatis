package io.ronghuiye.mybatis.test;

import io.ronghuiye.mybatis.binding.MapperRegistry;
import io.ronghuiye.mybatis.io.Resources;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;
import io.ronghuiye.mybatis.session.SqlSessionFactoryBuilder;
import io.ronghuiye.mybatis.session.defaults.DefaultSqlSessionFactory;
import io.ronghuiye.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String result = userDao.queryUserInfoById("id");
        logger.info(result);
    }

}
