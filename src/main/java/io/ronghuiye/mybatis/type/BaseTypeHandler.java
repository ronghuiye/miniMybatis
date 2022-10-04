package io.ronghuiye.mybatis.type;

import io.ronghuiye.mybatis.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseTypeHandler<T> implements TypeHandler<T> {
    protected Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        setNonNullParameter(ps, i, parameter, jdbcType);
    }

    protected abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
}
