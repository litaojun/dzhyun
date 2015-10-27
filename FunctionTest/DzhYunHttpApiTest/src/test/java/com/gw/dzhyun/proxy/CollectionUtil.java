package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

	//Set是包含独一无二元素的Collection，HashSet把它的元素存储在哈希表中，而TreeSet把它的元素存储在树中
	private Set<String> HashSet = new HashSet<String>();  
	private ArrayList<String> arraylist=new ArrayList<String>();
	private List linklist = new LinkedList();
	  //构造HashSet删除Collection中多余的元素
	private HashSet set=new HashSet(this.arraylist);
    //　将coolection放入HashSet后即会消除重复元素          
   // System.out.println("set："+set);
    
	//对 HashSet 的遍历-----------------------------------------------------------
	//1.迭代遍历：  
	public void setIterator()
	{
		Iterator<String> it = HashSet.iterator();  
		while(it.hasNext()) 
		{  
		  String str = it.next();
		}  
	}
	//		2.for循环遍历：  
	public void setFor()
	{
		for (String str : HashSet) {  
		      System.out.println(str);  
		}  
	}
	
	//对ArrayList遍历
	//1 for遍历 
   public void arrayListFor()
   {
	   for(int i=0;i<this.arraylist.size();i++)
	   {
		   System.out.println(arraylist.get( i));
	   }
   }
   //2 :遍历
   public void arrayList()
   {
	   for(String a:this.arraylist)
	   {
		   System.out.println(a);
	   }
   }
   //3 迭代遍历
   public void iteratorArrayList()
   {
	   Iterator itrator=this.arraylist.iterator();
	   while(itrator.hasNext())
       {
            //调用itrator的next方法获得下一个元素的引用
            if( itrator.next() instanceof String ) // instanceof　判断是否是String　的实例
                  itrator.remove();    //如果是的　则删除
       }
   }
   
   //linklist遍历
   //1 linklist基本操作
  public void linkListopr()
  {
	  linklist.add("a");//向列表的尾部追加"a"
	  linklist.add(0,"b");//在指定位置插入"b"
	  String[] strArray = new String[] {"z", "a", "c","C"};
	  
  }
   
   
   
   
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
