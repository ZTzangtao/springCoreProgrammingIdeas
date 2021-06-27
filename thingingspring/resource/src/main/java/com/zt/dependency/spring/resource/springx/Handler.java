package com.zt.dependency.spring.resource.springx;


import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 简单的继承 {@link sun.net.www.protocol.x.Handler}类
 *
 * @Author: Tommy
 * @DATE: 2021/6/27
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    //-Djava.protocol.handler.pkgs=com/zt/dependency/spring/resource
    public static void main(String[] args) throws IOException {
        //springx 协议
        URL url = new URL("springx:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("utf-8")));
    }
}
