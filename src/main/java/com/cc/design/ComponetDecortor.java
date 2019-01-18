package com.cc.design;

/**
 * 构件装饰类
 */
public class ComponetDecortor extends Componet {
    private Componet componet;

    public ComponetDecortor(Componet componet) {
        this.componet = componet;
    }
    @Override
    void display() {
        componet.display();
    }
}
