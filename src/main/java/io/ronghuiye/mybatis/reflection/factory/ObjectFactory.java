package io.ronghuiye.mybatis.reflection.factory;

import java.util.List;
import java.util.Properties;

public interface ObjectFactory {
    void setProperties(Properties properties);

    <T> T create(Class<T> tClass);

    <T> T create(Class<T> tClass, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

    <T> boolean isCollection(Class<T> type);
}
