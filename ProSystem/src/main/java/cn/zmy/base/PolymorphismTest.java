package cn.zmy.base;

public class PolymorphismTest {
	/**
	 * 在继承链中对象方法的调用存在一个优先级：
	 * this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
	 * @param args
	 */
	public static void main(String[] args) {
        A a1 = new A();
        /**
         * 当超类对象引用变量(a2)引用子类对象(new B())时，
         * 被引用对象的类型(B)而不是引用变量的类型(A)决定了调用谁的成员方法，
         * 但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。 
         */
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        
        System.out.println("1--" + a1.show(b)); //a1.show(a) A&A  this.show((super)O)
        System.out.println("2--" + a1.show(c));//a1.show(a) A&A  this.show((super)O)
        System.out.println("3--" + a1.show(d));//a1.show(a) A&A  this.show((super)O)
        /**
         * a2是一个引用变量，类型为A，它引用的是B的一个对象，
         * 由B来决定调用的是哪个方法。因此应该调用B的show(B obj)从而输出"B and B”才对。
         * 但是为什么跟前面的分析得到的结果不相符呢？！问题在于我们不要后半部分，
         * 那里特别指明：这个被调用的方法必须是在超类中定义过的，也就是被子类覆盖的方法。
         * B里面的show(B obj)在超类A中有定义吗？没有！那就更谈不上被覆盖了。
         */
        System.out.println("4--" + a2.show(b));
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));
        
        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));      
    }

}

class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    } 

}

class B extends A{
    public String show(B obj){
        return ("B and B");
    }
    
    public String show(A obj){
        return ("B and A");
    } 
}

class C extends B{

}

class D extends B{

}

