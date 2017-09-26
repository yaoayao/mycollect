package com.cc.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by wanchao on 2017/9/23.
 */
public class BeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element,BeanDefinitionBuilder bean) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        int age = Integer.valueOf(element.getAttribute("age"));
        bean.addPropertyValue("id", id);
        bean.addPropertyValue("name", name);
        bean.addPropertyValue("age", age);
    }
}
