package io.ronghuiye.mybatis.session;

import java.util.List;

public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <E> List<E> selectList(String statement, Object parameter);

    int insert(String statement, Object parameter);

    int update(String statement, Object parameter);

    Object delete(String statement, Object parameter);

    void commit();

    void close();

    void clearCache();

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
