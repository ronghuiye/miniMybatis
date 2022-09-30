package io.ronghuiye.mybatis.reflection.wrapper;

import io.ronghuiye.mybatis.reflection.MetaObject;
import io.ronghuiye.mybatis.reflection.factory.ObjectFactory;
import io.ronghuiye.mybatis.reflection.property.PropertyTokenizer;

import java.util.List;

public interface ObjectWrapper {
    Object get(PropertyTokenizer prop);

    void set(PropertyTokenizer prop, Object value);

    String findProperty(String name, boolean useCamelCaseMapping);

    String[] getGetterNames();

    String[] getSetterNames();

    Class<?> getSetterType(String name);

    Class<?> getGetterType(String name);

    boolean hasSetter(String name);

    boolean hasGetter(String name);

    MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

    boolean isCollection();

    void add(Object element);

    <E> void addAll(List<E> element);
}
