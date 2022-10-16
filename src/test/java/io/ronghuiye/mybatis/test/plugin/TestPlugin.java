package io.ronghuiye.mybatis.test.plugin;


import io.ronghuiye.mybatis.executor.statement.StatementHandler;
import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.plugin.Interceptor;
import io.ronghuiye.mybatis.plugin.Intercepts;
import io.ronghuiye.mybatis.plugin.Invocation;
import io.ronghuiye.mybatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class TestPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        System.out.println("intercept SQL：" + sql);
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("param：" + properties.getProperty("test00"));
    }

}
