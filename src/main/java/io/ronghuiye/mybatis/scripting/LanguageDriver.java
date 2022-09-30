package io.ronghuiye.mybatis.scripting;

import io.ronghuiye.mybatis.mapping.SqlSource;
import io.ronghuiye.mybatis.session.Configuration;
import org.dom4j.Element;

public interface LanguageDriver {
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);
}
