package cn.zmy.base;

public class InnerClassTest {
	public static void main(String[] args) {
		OuterClass.main(null);
	}

}

class OuterClass {  
    
    static int k = printI();  
      
    static int printI(){  
        System.out.println("Inner Class is loading before creating OuterClass instance");
        System.out.println(InnerClass.i);
        return InnerClass.i ;  
    }  
      
    OuterClass(){  
        System.out.println("OuterClass constructor");  
    }  
      
    class InnerClass{  
        private static final int i = 1;  
          
    }  
    public static void main(String[] args) {  
        OuterClass o;  
    }  
}
