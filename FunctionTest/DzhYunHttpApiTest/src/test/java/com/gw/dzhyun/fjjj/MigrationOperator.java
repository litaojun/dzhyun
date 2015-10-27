package com.gw.dzhyun.fjjj;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.JedisOperator;

public class MigrationOperator
{
	private MigrationStruct[] data;
	private TreeSet<MigrationStruct> tsdata = new TreeSet<MigrationStruct>();
	private JedisOperator jed = new JedisOperator();
	public void operatorMigration() throws InvalidProtocolBufferException
	{
		byte[] b = jed.getByteByKey("A10");
		if(b==null && b.length == 0)
			return ;
		Dzhfenjj.FenJiJiJinJingTai fjjdt = Dzhfenjj.FenJiJiJinJingTai.parseFrom(b);
		int num = fjjdt.getShuJuCount();
		this.data = new MigrationStruct[num];
		for(int i=0;i<num;i++ )
		{
			Dzhfenjj.FenJiJingTaiShuJu tmp =fjjdt.getShuJu(i);
			data[i] = this.tranToMigrationStruct(tmp);
			tsdata.add(data[i]);
		}
	}
	public TreeSet getTreeSet()
	{
		return this.tsdata;
	}
	public MigrationStruct[] getMigrationStructSZ()
	{
		return this.data;
	}
	public MigrationStruct tranToMigrationStruct(Dzhfenjj.FenJiJingTaiShuJu a)
	{
		MigrationStruct curmts = new MigrationStruct();
		curmts.setDsymbol(a.getBObj());
		curmts.setC5(a.getBZuiXinJingZhi());
		curmts.setC7(a.getBChangNeiFenE());
		//curmts.setC7(1.3223);
		curmts.setC10(a.getBChuShiGangGan());
		curmts.setC11(a.getBFenEZhanBi());
		curmts.setC12(a.getBXiaZheFaZhi());
		curmts.setC35(a.getBGenZongObj());
		curmts.setC15(a.getMObj());
		curmts.setC19(a.getMJingZhi());
		curmts.setC20(a.getMShangZheFaZhi());
		curmts.setC22(a.getAObj());
		curmts.setC27(a.getAZuiXinJingZhi());
		curmts.setC29(a.getAChangNeiFenE());
		curmts.setC30(a.getAFenEZhanBi());
		curmts.setC31(a.getAYueDingShouYi());
		curmts.setC37(a.getAYueDingShouYi2());
		return curmts;
	}
	
	//比较函数，比较两个TreeSet中的数据是否相等，如果相等则返回1，否则返回0
	public static int compareTreeMapSZ(TreeSet<MigrationStruct> a,TreeSet<MigrationStruct> b)
	{
		if(a.size() != b.size())
			return 0;
		Iterator<MigrationStruct> msia=a.iterator();
		Iterator<MigrationStruct> msib=b.iterator();
		while(msia.hasNext() && msib.hasNext())
		{
			MigrationStruct curma = msia.next();
			MigrationStruct curmb = msib.next();
			//curma.setC10(1.998);
			//System.out.println(curma.toString());
			//System.out.println(curmb.toString()+"\n");
			if(!curma.equals(curmb))
				return 0;
		}
		return 1;
	}
	

	public static void main(String[] args) throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		MigrationOperator a = new MigrationOperator();
		a.operatorMigration();
		MigrationStruct[] mssz = a.getMigrationStructSZ();
		for(MigrationStruct tmp : mssz)
		{
			String sta = tmp.toString();
			System.out.println(sta);
		}

	}

}
