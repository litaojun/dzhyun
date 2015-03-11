/**
 * 本类用于做各种验证。
 * @author Administrator
 *
 */
public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("测试String作为参数的传递是传值还是传引用（地址）");
		String test1 = "old";		
		String test2 = inc(test1,100);	
		System.out.println("test1 = " + test1);
		System.out.println("test2 = " + test2);
		//#测试结果说明String作为参数是传值
		
		//########################
		System.out.println("测试对象作为参数的传递是传值还是传引用（地址）");
		Sub s1 = new Sub(1,1);
		Sub s2 = incSub(s1,10,20);
		System.out.println("s1.x=" + s1.x + ",s1.y=" + s1.y);
		System.out.println("s2.x=" + s2.x + ",s2.y=" + s2.y);
		//#测试结果说明对象是传引用
		
	}

	public static String inc(String str1, int step)
	{
		System.out.println("before inc, str1 = " + str1);
		str1 = str1 + step;
		System.out.println("after inc, str1 = " + str1);
		return str1;
	}
	
	/**
	 * 修改Sub的x，y值并返回。以判断对象在函数中是传值还是传引用。 
	 * @param sub1
	 * @param stepx
	 * @param stepy
	 * @return
	 */
	public static Sub incSub(Sub sub1, int stepx, int stepy)
	{
		sub1.x +=stepx;
		sub1.y += stepy;
		return sub1;
	}
	
	

	
}


class Sub
{
	int x;
	int y;
	
	public Sub(int x1,int y1)
	{
		this.x = x1;
		this.y = y1;
	}
}