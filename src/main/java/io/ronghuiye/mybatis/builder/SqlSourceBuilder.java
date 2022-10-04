package io.ronghuiye.mybatis.builder;

import io.ronghuiye.mybatis.mapping.ParameterMapping;
import io.ronghuiye.mybatis.mapping.SqlSource;
import io.ronghuiye.mybatis.parsing.GenericTokenParser;
import io.ronghuiye.mybatis.parsing.TokenHandler;
import io.ronghuiye.mybatis.reflection.MetaClass;
import io.ronghuiye.mybatis.reflection.MetaObject;
import io.ronghuiye.mybatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlSourceBuilder extends BaseBuilder {

    private static Logger logger = LoggerFactory.getLogger(SqlSourceBuilder.class);

    private static final String parameterProperties = "javaType,jdbcType,mode,numericScale,resultMap,typeHandler,jdbcTypeName";

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    public SqlSource parse(String originalSql, Class<?> parameterType, Map<String, Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        return new StaticSqlSource(configuration, sql, handler.getParameterMappings());
    }

    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {

        private List<ParameterMapping> parameterMappings = new ArrayList<>();
        private Class<?> parameterType;
        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
            Map<String, String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType;
            if (typeHandlerRegistry.hasTypeHandler(parameterType)) {
                propertyType = parameterType;
            } else if (property != null) {
                MetaClass metaClass = MetaClass.forClass(parameterType);
                if (metaClass.hasGetter(property)) {
                    propertyType = metaClass.getGetterType(property);
                } else {
                    propertyType = Object.class;
                }
            } else {
                propertyType = Object.class;
            }

            logger.info("property: {} propertyType: {}", property, propertyType);
            ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, property, propertyType);
            return builder.build();
        }

    }

}