package io.ronghuiye.mybatis.session;

import io.ronghuiye.mybatis.binding.MapperRegistry;
import io.ronghuiye.mybatis.datasource.druid.DruidDataSourceFactory;
import io.ronghuiye.mybatis.datasource.pooled.PooledDataSourceFactory;
import io.ronghuiye.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import io.ronghuiye.mybatis.executor.Executor;
import io.ronghuiye.mybatis.executor.SimpleExecutor;
import io.ronghuiye.mybatis.executor.resultset.DefaultResultSetHandler;
import io.ronghuiye.mybatis.executor.resultset.ResultSetHandler;
import io.ronghuiye.mybatis.executor.statement.PreparedStatementHandler;
import io.ronghuiye.mybatis.executor.statement.StatementHandler;
import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.mapping.Environment;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.transaction.Transaction;
import io.ronghuiye.mybatis.transaction.jdbc.JdbcTransactionFactory;
import io.ronghuiye.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    protected Environment environment;

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement statement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, statement, boundSql);
    }

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }
}
