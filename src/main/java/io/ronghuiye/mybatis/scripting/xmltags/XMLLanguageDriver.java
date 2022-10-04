package io.ronghuiye.mybatis.scripting.xmltags;

import io.ronghuiye.mybatis.executor.parameter.ParameterHandler;
import io.ronghuiye.mybatis.mapping.BoundSql;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.mapping.SqlSource;
import io.ronghuiye.mybatis.scripting.LanguageDriver;
import io.ronghuiye.mybatis.scripting.defaults.DefaultParameterHandler;
import io.ronghuiye.mybatis.session.Configuration;
import org.dom4j.Element;

public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object obj, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, obj, boundSql);
    }
}