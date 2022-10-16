package io.ronghuiye.mybatis.plugin;

public @interface Signature {

    Class<?> type();
    String method();
    Class<?>[] args();
}
