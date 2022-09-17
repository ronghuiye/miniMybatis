package io.ronghuiye.mybatis.builder;

import io.ronghuiye.mybatis.session.Configuration;

public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
