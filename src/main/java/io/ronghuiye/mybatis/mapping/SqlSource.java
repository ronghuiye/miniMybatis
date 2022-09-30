package io.ronghuiye.mybatis.mapping;

public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
}
