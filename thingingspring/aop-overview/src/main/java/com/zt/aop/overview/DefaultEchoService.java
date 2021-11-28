package com.zt.aop.overview;

/**
 * @Author Tommy
 * @Date 2021/11/26 9:20 PM
 * @Version 1.0
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO]" + message;
    }
}
