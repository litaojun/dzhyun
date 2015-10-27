package com.gw.dzhyun.fjjj;

import java.util.List;
import java.util.TreeSet;

import com.google.protobuf.InvalidProtocolBufferException;

public class Test {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		MigrationOperator a = new MigrationOperator();
		a.operatorMigration();
		MigrationStruct[] mssz = a.getMigrationStructSZ();
		DzhTfdDao dtd = new DzhTfdDao();
		//mysql数据库数据 ,获取LIST数据 
		List<MigrationStruct> gt = dtd.getListByUIdAndType();
		//redis数据库 ,获取LIST数据 
		MigrationStruct[] gtt = a.getMigrationStructSZ();
		System.out.println("mysql数据库数据    gt.size="+gt.size());
		System.out.println("redis数据库 ----gtt.size="+gtt.length);
		//将redis中的LIST数据转换为TreeSet
		TreeSet srcset = a.getTreeSet();
		//将mysql中的LIST数据转换为TreeSet
		TreeSet dstset = dtd.tranListToTreeSet(gt);
		//比较redis和mysql中的TreeSet
		int sign = MigrationOperator.compareTreeMapSZ(srcset, dstset);
		System.out.println("如果输出sign=1则表示mysql与redis中数据一致，否则说明数据不一致");
		System.out.println("sign="+sign);
		 

	}

}
