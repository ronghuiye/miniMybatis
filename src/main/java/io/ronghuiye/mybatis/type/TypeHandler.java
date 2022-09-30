package io.ronghuiye.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TypeHandler<T> {

    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

}
