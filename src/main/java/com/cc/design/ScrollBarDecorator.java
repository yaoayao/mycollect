package com.cc.design;

public class ScrollBarDecorator extends ComponetDecortor {
    public ScrollBarDecorator(Componet componet) {
        super(componet);
    }

    @Override
    void display() {
        add();
        super.display();
    }
    private void add(){
        System.out.println("增加滚动条");
    }
}
