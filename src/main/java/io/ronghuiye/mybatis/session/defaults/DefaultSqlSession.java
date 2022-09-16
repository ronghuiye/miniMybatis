package io.ronghuiye.mybatis.session.defaults;

import io.ronghuiye.mybatis.binding.MapperResistry;
import io.ronghuiye.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private MapperResistry mapperResistry;

    public DefaultSqlSession(MapperResistry mapperResistry) {
        this.mapperResistry = mapperResistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("Proxyed with statement: " + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("Proxyed with statement: " + statement + " params: " + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperResistry.getMapper(type, this);
    }
}
