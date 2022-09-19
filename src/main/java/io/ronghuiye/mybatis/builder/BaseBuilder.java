package io.ronghuiye.mybatis.builder;

import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.type.TypeAliasRegistry;

public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
