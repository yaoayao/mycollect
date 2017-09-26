package com.cc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by wanchao on 2017/9/23.
 */
public class NamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new BeanDefinitionParser());
    }
}
