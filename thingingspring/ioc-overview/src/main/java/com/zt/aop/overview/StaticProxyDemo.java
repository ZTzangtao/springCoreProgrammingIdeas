package com.zt.aop.overview;

/**
 * 静态代理示例
 *
 * @Author Tommy
 * @Date 2021/11/26 9:02 PM
 * @Version 1.0
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }
}
