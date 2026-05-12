package rvt;

import java.util.List;

class A implements IA {
    public void methodA() {
        System.out.println("Method A");
    }
    public void methodIA() {
        System.out.println("Method IA");
    }
}

class B extends A implements IB {
    public void methodB() {
        System.out.println("Method B");
    }
    public void methodIB() {
        System.out.println("Method IB");
    }
}

class C extends B implements IC {
    public void methodC() {
        System.out.println("Method C");
    }
    public void methodIC() {
        System.out.println("Method IC");
    }
    private List<E> list;
}
class D implements IA {
    public void methodIA() {
        System.out.println("Method IA");
    }
}

class E {
    private List<C> list;
}

interface IA {
    void methodIA();
}
interface IB {
    void methodIB();
}
interface IC {
    void methodIC();
}

