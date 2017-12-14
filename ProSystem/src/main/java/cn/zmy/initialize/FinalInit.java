package cn.zmy.initialize;

public class FinalInit {
	// 必须在定义处或是构造器内对其进行初始化
	// 不同的对象有不用的k,值不可以修改
	final int k;
	// 在装载是已初始化（此例默认是0），只有一份(不同对象共享), 值可以修改
	static int sk;
	// static 在装载（为实例化）已被初始化， final要求在定义或构造器内（实例化后）初始化 因此只能在定义时初始化
	static final int fk=5;
	
	FinalInit() {
		k = 1;
		sk = 1;
	}
	
	public int getK() {
		return k;
	}
	

	FinalInit(int i) {
		k =2;
		sk = 2;
	}
	
	FinalInit(String s) {
		k = 4;
		sk ++ ;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(FinalInit.sk);// 尚未创建对象 sk是初始值3
		FinalInit f1 = new FinalInit();
		System.out.println(FinalInit.sk);//--1
		System.out.println(f1.getK());
		FinalInit f2 = new FinalInit(1);
		System.out.println(FinalInit.sk);// --2
		System.out.println(f2.getK());
		FinalInit f3 = new FinalInit("");
		System.out.println(FinalInit.sk);//--3
		System.out.println(f3.getK());
	}
}

