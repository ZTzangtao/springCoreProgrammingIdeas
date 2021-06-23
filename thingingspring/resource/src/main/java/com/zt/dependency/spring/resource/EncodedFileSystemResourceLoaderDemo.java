package com.zt.dependency.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;


/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @Author: Tommy
 * @DATE: 2021/6/22
 *
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 *
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/"+System.getProperty("user.dir") + "\\thingingspring\\resource\\src/main/java/com/zt/dependency/spring/resource/EncodedFileSystemResourceLoaderDemo.java";

        //新建一个对象
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();

        //FileSystemResource ==> WritableResource ==> Resource
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        //字符输入流
        try(Reader reader = encodedResource.getReader();) {
            System.out.println(IOUtils.toString(reader));
        }



    }
}
