package com.zt.dependency.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * String - Properties {@link PropertyEditor}
 *
 * @Author: Tommy
 * @DATE: 2021/8/15
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    //1. 实现 setAsText(String) 方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
//        super.setAsText(text);

        //2. 将 String 类型转换成 Properties 类型
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        //3. 临时存储 Properties 对象
        setValue(properties);

        // next 获取临时 Properties 对象 #getValue
    }
}
