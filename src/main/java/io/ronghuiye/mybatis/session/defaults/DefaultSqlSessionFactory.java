package io.ronghuiye.mybatis.session.defaults;

import io.ronghuiye.mybatis.binding.MapperRegistry;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
