package io.ronghuiye.mybatis.scripting.xmltags;

import io.ronghuiye.mybatis.mapping.SqlSource;
import io.ronghuiye.mybatis.scripting.LanguageDriver;
import io.ronghuiye.mybatis.session.Configuration;
import org.dom4j.Element;

public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

}