package io.ronghuiye.mybatis.executor;

import io.ronghuiye.mybatis.executor.statement.StatementHandler;
import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.ResultHandler;
import io.ronghuiye.mybatis.session.RowBounds;
import io.ronghuiye.mybatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor extends BaseExecutor{

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement statement = null;
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
            statement = prepareStatement(handler);
            return handler.update(statement);
        } finally {
          closeStatement(statement);
        }
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement statement = null;
        try {
            Configuration configuration = ms.getConfiguration();
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            statement = prepareStatement(handler);
            return handler.query(statement, resultHandler);
        } finally {
            closeStatement(statement);
        }
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Statement statement;
        Connection connection = transaction.getConnection();
        statement = handler.prepare(connection);
        handler.parameterize(statement);
        return statement;
    }
}
