package com.gw.dzhyun.httptest;
/**
 * 本类用于做各种验证。
 * @author Administrator
 *
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
		
public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("测试String作为参数的传递是传值还是传引用（地址）");
//		String test1 = "old";		
//		String test2 = inc(test1,100);	
//		System.out.println("test1 = " + test1);
//		System.out.println("test2 = " + test2);
//		//#测试结果说明String作为参数是传值
//		
//		//########################
//		System.out.println("测试对象作为参数的传递是传值还是传引用（地址）");
//		Sub s1 = new Sub(1,1);
//		Sub s2 = incSub(s1,10,20);
//		System.out.println("s1.x=" + s1.x + ",s1.y=" + s1.y);
//		System.out.println("s2.x=" + s2.x + ",s2.y=" + s2.y);
//		//#测试结果说明对象是传引用
		
		//###########################3
		//测试队列和栈
		System.out.println("测试队列和栈");
		testQueue();
		testStack();
		
	}


		    
    /**
     * 测试队列
     * <pre>
     * 队列特点，先进先出，后进后出，火车过山洞例子
     * </pre>
     */
    static void testQueue(){
        Queue<String> queue=new LinkedList<String>();
        //添加几个元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        System.out.println("队列中的元素是:"+queue);
        //弹出元素
        queue.poll();
        System.out.println("队列中的元素是:"+queue);
        //查看队列中首个元素，并不移除
        String peek=queue.peek();
        System.out.println("查看队列中首个元素，并不移除:"+peek);
        System.out.println("队列中的元素是:"+queue);
    }
    
    
    /**
     * 测试栈
     * <pre>
     * 先进后出，后进先出，水桶倒水
     * </pre>
     */
    static void testStack(){
        Stack<String> stack=new Stack<String>();
        //添加几个元素
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");
        stack.add("5");
        System.out.println("栈中的元素是:"+stack);
        //弹出元素
        stack.pop();
        System.out.println("栈中的元素是:"+stack);
        //查看栈中首个元素，并不移除
        String peek=stack.peek();
        System.out.println("查看栈中首个元素，并不移除:"+peek);
        System.out.println("栈中的元素是:"+stack);
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