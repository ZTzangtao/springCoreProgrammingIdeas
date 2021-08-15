package com.zt.dependency.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 *
 * @Author: Tommy
 * @DATE: 2021/8/15
 *
 * @see PropertyEditor
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        // 模拟 Spring Framework 操作
        // 有一段文本 name = "Tommy"

        String text = "name = Tommy";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 传递 String 类型的内容
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

    }

}
