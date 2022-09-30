package io.ronghuiye.mybatis.reflection.wrapper;

import io.ronghuiye.mybatis.reflection.MetaObject;
import io.ronghuiye.mybatis.reflection.factory.ObjectFactory;
import io.ronghuiye.mybatis.reflection.property.PropertyTokenizer;

import java.util.Collection;
import java.util.List;

public class CollectionWrapper implements ObjectWrapper{

    private Collection<Object> objects;

    public CollectionWrapper(MetaObject metaObject, Collection<Object> objects) {
        this.objects = objects;
    }

    @Override
    public Object get(PropertyTokenizer prop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getSetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getSetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getGetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasSetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasGetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public void add(Object element) {
        objects.add(element);
    }

    @Override
    public <E> void addAll(List<E> element) {
        objects.addAll(element);
    }

}
