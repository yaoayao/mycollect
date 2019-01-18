package com.cc.design;

/**
 * 为窗口增加滚动条
 * 装饰者模式实现
 *
 */
public class Client {
    public static void main(String[] args) {
        Componet widow = new Windows();
        ScrollBarDecorator scrollBarDecorator = new ScrollBarDecorator(widow);
        scrollBarDecorator.display();
    }
}
