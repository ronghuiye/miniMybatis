package io.ronghuiye.mybatis.session.defaults;

import io.ronghuiye.mybatis.binding.MapperResistry;
import io.ronghuiye.mybatis.session.SqlSession;
import io.ronghuiye.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperResistry mapperResistry;

    public DefaultSqlSessionFactory(MapperResistry mapperResistry) {
        this.mapperResistry = mapperResistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperResistry);
    }
}
