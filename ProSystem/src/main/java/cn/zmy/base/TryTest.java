package cn.zmy.base;

public class TryTest {
	// 可以没有catch模块
	public static void main(String[] args) {
		System.out.println("NoException the method value is " + NoException());
		System.out.println("NoExceptionReturnTry the method value is " + NoExceptionReturnTry());
		System.out.println("WithException the method value is " + WithException());
		System.out.println("CatchExceptionReturn the method value is " + CatchExceptionReturn());
		System.out.println("CatchException the method value is " + CatchException());
	}

	/**
	 * 执行try块，执行到return语句时，先执行return的语句，--i，但是不返回到main 方法，执行finally块，
	 * 遇到finally块中的return语句，执行--i,并将值返回到main方法，这里就不会再回去返回try块中计算得到的值
	 */
	@SuppressWarnings("finally")
	public static int NoException() {
		int i = 10;
		try {
			System.out.println("i in try block is " + i);
			return --i;
		} catch (Exception e) {
			--i;
			System.out.println("i in catch - form try block is " + i);
			return --i;
		} finally {
			System.out.println("i in finally - from try or catch block is " + i);
			return --i;
		}
	}

	/**
	 * try中执行完return的语句后，不返回，执行finally块，finally块执行结束后，返回到try块中，返回i在try块中最后的值
	 * 
	 * @return
	 */
	public static int NoExceptionReturnTry() {
		int i = 10;
		try {
			System.out.println("i in try block is-- " + i);
			return --i;
		} catch (Exception e) {
			--i;
			System.out.println("i in catch - form try block is-- " + i);
			return --i;
		} finally {

			System.out.println("i in finally - from try or catch block is-- " + i);
			--i;
			System.out.println("i in finally block is-- " + i);
			// return --i;
		}
	}

	/**
	 * 顺序，抛出异常后，执行catch块，在catch块的return的--i执行完后，
	 * 并不直接返回而是执行finally，因finally中有return语句，所以，执行，返回结果6
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int WithException() {
		int i = 10;
		try {
			System.out.println("i in try block is-- " + i);
			i = i / 0;
			return --i;
		} catch (Exception e) {
			System.out.println("i in catch - form try block is-- " + i);
			--i;
			System.out.println("i in catch block is-- " + i);
			return --i;
		} finally {
			System.out.println("i in finally - from try or catch block is-- " + i);
			--i;
			System.out.println("i in finally block is-- " + i);
			return --i;
		}
	}

	/**
	 * 在try块中出现异常，到catch中，执行到异常，到finally中执行，finally执行结束后判断发现异常，抛出
	 * 
	 * @return
	 */
	public static int CatchException() {
		int i = 10;
		try {
			System.out.println("i in try block is-- " + i);
			i = i / 0;
			return --i;
		} catch (Exception e) {
			System.out.println("i in catch - form try block is-- " + i);
			int j = i / 0;
			return --i;
		} finally {

			System.out.println("i in finally - from try or catch block is-- " + i);
			--i;
			System.out.println("i in finally block is-- " + i);
			// return --i;
		}
	}

	/**
	 * try块中出现异常到catch，catch中出现异常到finally，finally中执行到return语句返回，不检查异常
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int CatchExceptionReturn() {
		int i = 10;
		try {
			System.out.println("i in try block is-- " + i);
			i = i / 0;
			return --i;
		} catch (Exception e) {
			System.out.println("i in catch - form try block is-- " + i);
			int j = i / 0;
			return --i;
		} finally {

			System.out.println("i in finally - from try or catch block is-- " + i);
			--i;
			System.out.println("i in finally block is-- " + i);
			return --i;
		}
	}

}
