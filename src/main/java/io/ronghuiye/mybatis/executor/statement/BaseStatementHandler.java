package io.ronghuiye.mybatis.executor.statement;

import io.ronghuiye.mybatis.executor.Executor;
import io.ronghuiye.mybatis.executor.parameter.ParameterHandler;
import io.ronghuiye.mybatis.executor.resultset.ResultSetHandler;
import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.ResultHandler;
import io.ronghuiye.mybatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseStatementHandler implements StatementHandler{

    protected final Configuration configuration;
    protected final Executor executor;
    protected final MappedStatement mappedStatement;

    protected final Object parameterObject;
    protected final ResultSetHandler resultSetHandler;
    protected final ParameterHandler parameterHandler;

    protected final RowBounds rowBounds;
    protected BoundSql boundSql;

    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.rowBounds = rowBounds;
        this.parameterObject = parameterObject;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement,rowBounds, resultHandler, boundSql);
        this.boundSql = boundSql;
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject, boundSql);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;

        try {
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(1000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error");
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
