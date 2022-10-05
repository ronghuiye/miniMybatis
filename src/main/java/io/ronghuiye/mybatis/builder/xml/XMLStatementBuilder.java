package io.ronghuiye.mybatis.builder.xml;

import io.ronghuiye.mybatis.binding.MapperBuilderAssistant;
import io.ronghuiye.mybatis.builder.BaseBuilder;
import io.ronghuiye.mybatis.io.Resources;
import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.mapping.SqlCommandType;
import io.ronghuiye.mybatis.mapping.SqlSource;
import io.ronghuiye.mybatis.scripting.LanguageDriver;
import io.ronghuiye.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class XMLStatementBuilder extends BaseBuilder {
    private Element element;
    private MapperBuilderAssistant builderAssistant;

    public XMLStatementBuilder(Configuration configuration,MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    public void parseStatementNode() {
        String id = element.attributeValue("id");

        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);

        String resultMap = element.attributeValue("resultMap");
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        builderAssistant.addMappedStatement(id,
                sqlSource, sqlCommandType, parameterTypeClass, resultMap, resultTypeClass, langDriver);

    }
}
