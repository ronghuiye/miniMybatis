package io.ronghuiye.mybatis.mapping;

import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.type.JdbcType;
import io.ronghuiye.mybatis.type.TypeHandler;

public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }
}
