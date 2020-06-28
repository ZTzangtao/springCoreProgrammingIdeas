package zt.com.main;

import zt.com.javaBean.Student;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        //去掉Object类，所有类的父类
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
            .forEach(propertyDescriptor -> {
                System.out.println(propertyDescriptor);

                //PropertyDescriptor 允许添加属性编辑器 - PropertyEditor
                //GUI -> texy(String) -> PropertyType
                Class<?> propertyType = propertyDescriptor.getPropertyType();
                String propertyName = propertyDescriptor.getName();
                if ("age".equals(propertyName)){
                    propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
//                    propertyDescriptor.createPropertyEditor()
                }

            });

    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {

            public void setAsText(String text) throws java.lang.IllegalArgumentException {
                Integer value = Integer.valueOf(text);

                setValue(value);
            }
        }

}
