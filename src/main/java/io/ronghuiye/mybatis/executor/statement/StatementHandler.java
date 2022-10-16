package io.ronghuiye.mybatis.executor.statement;

import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    Statement prepare(Connection connection) throws SQLException;

    void parameterize(Statement statement) throws SQLException;

    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    int update(Statement statement) throws SQLException;

    BoundSql getBoundSql();
}
