package io.ronghuiye.mybatis.test;

import com.alibaba.fastjson.JSON;
import io.ronghuiye.mybatis.builder.xml.XMLConfigBuilder;
import io.ronghuiye.mybatis.executor.Executor;
import io.ronghuiye.mybatis.io.Resources;
import io.ronghuiye.mybatis.mapping.Environment;
import io.ronghuiye.mybatis.session.*;
import io.ronghuiye.mybatis.session.defaults.DefaultSqlSession;
import io.ronghuiye.mybatis.test.dao.IActivityDao;
import io.ronghuiye.mybatis.test.dao.IUserDao;
import io.ronghuiye.mybatis.test.po.Activity;
import io.ronghuiye.mybatis.test.po.User;
import io.ronghuiye.mybatis.transaction.Transaction;
import io.ronghuiye.mybatis.transaction.TransactionFactory;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_queryActivityById() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        Activity req = new Activity();
        req.setActivityId(100001L);
        Activity res = dao.queryActivityById(req);
        logger.info("result：{}", JSON.toJSONString(res));
    }

//    @Test
//    public void test_ognl() throws OgnlException {
//        Activity req = new Activity();
//        req.setActivityId(1L);
//        req.setActivityName("test");
//        req.setActivityDesc("test desc");
//
//        OgnlContext context = new OgnlContext();
//        context.setRoot(req);
//        Object root = context.getRoot();
//
//        Object activityName = Ognl.getValue("activityName", context, root);
//        Object activityDesc = Ognl.getValue("activityDesc", context, root);
//        Object value = Ognl.getValue("activityDesc.length()", context, root);
//
//        System.out.println(activityName + "\t" + activityDesc + " length：" + value);
//    }

//    private SqlSession sqlSession;
//
//    @Before
//    public void init() throws IOException {
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
//        sqlSession = sqlSessionFactory.openSession();
//    }

//    @Test
//    public void test_insertUserInfo() {
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        User user = new User();
//        user.setUserId("10002");
//        user.setUserName("white");
//        user.setUserHead("1_05");
//        userDao.insertUserInfo(user);
//        logger.info("result：{}", "Insert OK");
//
//        sqlSession.commit();
//    }
//
//    @Test
//    public void test_deleteUserInfoByUserId() {
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        int count = userDao.deleteUserInfoByUserId("10002");
//        logger.info("result：{}", count == 1);
//
//        sqlSession.commit();
//    }
//
//
//    @Test
//    public void test_updateUserInfo() {
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        int count = userDao.updateUserInfo(new User(1L, "3", "cat"));
//        logger.info("result：{}", count);
//
//        sqlSession.commit();
//    }
//
//    @Test
//    public void test_queryUserInfoById() {
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        User user = userDao.queryUserInfoById(1L);
//        logger.info("result：{}", JSON.toJSONString(user));
//    }
//
//    @Test
//    public void test_queryUserInfo() throws IOException {
//
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        User user = userDao.queryUserInfo(new User(1L));
//        logger.info("result：{}", JSON.toJSONString(user));
//
//    }
//
//    @Test
//    public void test_queryUserInfoList() {
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        List<User> users = userDao.queryUserInfoList();
//        logger.info("result：{}", JSON.toJSONString(users));
//    }

//    @Test
//    public void test_queryActivityById(){
//        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
//        Activity res = dao.queryActivityById(100001L);
//        logger.info("result：{}", JSON.toJSONString(res));
//    }
//
//    @Test
//    public void test_insert() {
//        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
//
//        Activity activity = new Activity();
//        activity.setActivityId(10004L);
//        activity.setActivityName("test");
//        activity.setActivityDesc("test insert");
//        activity.setCreator("xiaofuge");
//
//        Integer res = dao.insert(activity);
//        sqlSession.commit();
//
//        logger.info("result：count：{} idx：{}", res, JSON.toJSONString(activity.getId()));
//    }
//
//    @Test
//    public void test_insert_select() throws IOException {
//        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
//        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
//        Configuration configuration = xmlConfigBuilder.parse();
//
//        final Environment environment = configuration.getEnvironment();
//        TransactionFactory transactionFactory = environment.getTransactionFactory();
//        Transaction tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
//
//        final Executor executor = configuration.newExecutor(tx);
//        SqlSession sqlSession = new DefaultSqlSession(configuration, executor);
//
//        Activity activity = new Activity();
//        activity.setActivityId(10005L);
//        activity.setActivityName("test");
//        activity.setActivityDesc("test insert");
//        activity.setCreator("xiaofuge");
//        int res = sqlSession.insert("io.ronghuiye.mybatis.test.dao.IActivityDao.insert", activity);
//
//        Object obj = sqlSession.selectOne("io.ronghuiye.mybatis.test.dao.IActivityDao.insert!selectKey");
//        logger.info("result：count：{} idx：{}", res, JSON.toJSONString(obj));
//
//        sqlSession.commit();
//    }

}
