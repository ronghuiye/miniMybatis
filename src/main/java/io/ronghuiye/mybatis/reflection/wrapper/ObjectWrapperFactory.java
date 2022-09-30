package io.ronghuiye.mybatis.reflection.wrapper;

import io.ronghuiye.mybatis.reflection.MetaObject;

public interface ObjectWrapperFactory {

    boolean hasWrapperFor(Object object);

    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
