package cn.zmy.recursive;

public class Hanoi {
//	有三根杆子A，B，C。A杆上有N个(N>1)穿孔圆盘，盘的尺寸由下到上依次变小。要求按下列规则将所有圆盘移至C杆：
//	每次只能移动一个圆盘；
//	大盘不能叠在小盘上面。
//	提示：可将圆盘临时置于B杆，也可将从A杆移出的圆盘重新移回A杆，但都必须遵循上述两条规则。
//	问：如何移？最少要移动多少次？
	private final static String from = "盘子A";  
    private final static String to = "盘子C";  
    private final static String mid = "盘子B";
    
    public static long step = 0;
    
    public void move(int n, String from, String to, String mid) {
    	
    	if (n == 1) {
    		System.out.println("移动盘子1 从" + from + "到" + to);
    		step ++;
    	} else {
    		move(n-1, from, mid, to);
    		System.out.println("移动盘子" +  n + " 从" + from + "到" + to);
    		step ++;
    		move(n-1, mid, to, from);
    	}
    		
    }
    
    public static void main(String[] args) {
		Hanoi han = new Hanoi();
		han.move(3, from, to, mid);
		System.out.println(step);
	}
}
