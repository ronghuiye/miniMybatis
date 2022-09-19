package io.ronghuiye.mybatis.transaction.jdbc;

import io.ronghuiye.mybatis.session.TransactionIsolationLevel;
import io.ronghuiye.mybatis.transaction.Transaction;
import io.ronghuiye.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
