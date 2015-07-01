package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.Collections;

public class DataCompare {
	public static int compareArrayList(ArrayList<String> srclist,ArrayList<String> dstlist)
	{
		if(srclist.size() != dstlist.size())
			return 0;
		Collections.sort(srclist);
		Collections.sort(dstlist);
		for(int i=0;i<srclist.size();i++)
		{
			if(!srclist.get(i).equals(dstlist.get(i)))
			{
				return 0;
			}
		}
		return 1;
	}

}
