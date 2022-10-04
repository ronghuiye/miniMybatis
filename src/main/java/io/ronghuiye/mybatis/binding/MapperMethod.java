package io.ronghuiye.mybatis.binding;

import io.ronghuiye.mybatis.mapping.MappedStatement;
import io.ronghuiye.mybatis.mapping.SqlCommandType;
import io.ronghuiye.mybatis.session.Configuration;
import io.ronghuiye.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.*;

public class MapperMethod {

    private final SqlCommand command;
    private final MethodSignature method;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        command = new SqlCommand(configuration, mapperInterface, method);
        this.method = new MethodSignature(configuration, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.type) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                Object param = method.convertArgsToSqlCommandParam(args);
                result = sqlSession.selectOne(command.getName(), param);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    public static class SqlCommand {
        private String name;
        private SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }

    public static class MethodSignature {
        private final SortedMap<Integer, String> params;

        public MethodSignature(Configuration configuration, Method method) {
            this.params = Collections.unmodifiableSortedMap(getParams(method));
        }

        public Object convertArgsToSqlCommandParam(Object[] args) {
            final int paramCount = params.size();
            if (args == null || paramCount == 0) {
                return null;
            } else if (paramCount == 1) {
                return args[params.keySet().iterator().next().intValue()];
            } else {
                final Map<String, Object> param = new ParamMap<Object>();
                int i =0;
                for (Map.Entry<Integer, String> entry : params.entrySet()) {
                    param.put(entry.getValue(), args[entry.getKey().intValue()]);
                    final String genericParamName = "param" + (i + 1);
                    if (!param.containsKey(genericParamName)) {
                        param.put(genericParamName, args[entry.getKey()]);
                    }
                    i++;
                }
                return param;
            }
        }

        private SortedMap<Integer, String> getParams(Method method) {
            final SortedMap<Integer, String> params = new TreeMap<>();
            final Class<?>[] argTypes = method.getParameterTypes();
            for (int i = 0; i < argTypes.length; i++) {
                String paramName = String.valueOf(params.size());
                params.put(i, paramName);
            }
            return params;
        }
    }

    public static class ParamMap<V> extends HashMap<String, V> {
        private static final long serialVersionUID = -2212268410512043526L;

        @Override
        public V get(Object key) {
            if (!super.containsKey(key)) {
                throw new RuntimeException("Parameter '" + key + "' not found. Available parameters are " + keySet());
            }
            return super.get(key);
        }
    }

}
