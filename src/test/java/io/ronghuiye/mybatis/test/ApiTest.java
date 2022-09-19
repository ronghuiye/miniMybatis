package io.ronghuiye.mybatis.test;

import com.alibaba.fastjson.JSON;
import io.ronghuiye.mybatis.binding.MapperRegistry;
import io.ronghuiye.mybatis.builder.xml.XMLConfigBuilder;
import io.ronghuiye.mybatis.io.Resources;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;
import io.ronghuiye.mybatis.session.SqlSessionFactoryBuilder;
import io.ronghuiye.mybatis.session.defaults.DefaultSqlSession;
import io.ronghuiye.mybatis.session.defaults.DefaultSqlSessionFactory;
import io.ronghuiye.mybatis.test.dao.IUserDao;
import io.ronghuiye.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = userDao.queryUserInfoById(1L);
        logger.info("result：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_selectOne() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        SqlSession sqlSession = new DefaultSqlSession(configuration);

        Object[] req = {1L};
        Object res = sqlSession.selectOne("io.ronghuiye.mybatis.test.dao.IUserDao.queryUserInfoById", req);
        logger.info("result：{}", JSON.toJSONString(res));
    }

}
