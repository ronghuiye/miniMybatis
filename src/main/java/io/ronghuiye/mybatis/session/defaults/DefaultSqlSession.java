package io.ronghuiye.mybatis.session.defaults;

import io.ronghuiye.mybatis.binding.MapperRegistry;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("Proxyed with statement: " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        return (T) ("Proxyed with statement: " + statement + " params: " + parameter + " SQL: " + ms.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
