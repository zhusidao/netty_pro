package com.example.rpc.demo2;

/**
 * @author ZhuSiDao
 * @date 2020/9/2
 */
public class RemoteObj {
    private String serverName;
    private String methodName;
    private Class<?>[] argumentsTypes;
    private Object[] argumentsValues;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getArgumentsTypes() {
        return argumentsTypes;
    }

    public void setArgumentsTypes(Class[] argumentsTypes) {
        this.argumentsTypes = argumentsTypes;
    }

    public Object[] getArgumentsValues() {
        return argumentsValues;
    }

    public void setArgumentsValues(Object[] argumentsValues) {
        this.argumentsValues = argumentsValues;
    }

    public RemoteObj(String serverName, String methodName, Class<?>[] argumentsTypes, Object[] argumentsValues) {
        this.serverName = serverName;
        this.methodName = methodName;
        this.argumentsTypes = argumentsTypes;
        this.argumentsValues = argumentsValues;
    }
}
