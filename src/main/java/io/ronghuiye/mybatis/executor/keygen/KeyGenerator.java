package io.ronghuiye.mybatis.executor.keygen;

import io.ronghuiye.mybatis.executor.Executor;
import io.ronghuiye.mybatis.mapping.MappedStatement;

import java.sql.Statement;

public interface KeyGenerator {

    void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

    void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);
}
