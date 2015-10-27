package com.gw.dzhyun.fjjj;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;




public class DzhTfdDao extends BaseDao {
	public TreeSet<MigrationStruct> tranListToTreeSet(List<MigrationStruct> mls)
	{
		TreeSet<MigrationStruct> tst = new TreeSet<MigrationStruct>();
		for(int i=0;i<mls.size();i++)
		{
			tst.add(mls.get(i));
		}
		return tst;
	}
	
	public List<MigrationStruct> getListByUIdAndType() {
		List<MigrationStruct> list = new ArrayList<MigrationStruct>();
		try {
			String sql = "select * from tFD0011";
			
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				MigrationStruct category = assembleUser(result);
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 组装User对象
		private MigrationStruct assembleUser(ResultSet result) {
			try {
				if (null != result) {
					MigrationStruct MST =  new MigrationStruct();
					//MST.setCV(result.getLong("CV"));        
					//MST.setC1(result.getInt("C1"));         
					MST.setDsymbol(result.getString("Dsymbol")); 
					MST.setC5(result.getDouble("C5"));     
					MST.setC7(result.getDouble("C7"));     
					//MST.setC9(result.getString("C9"));      
					MST.setC10(result.getDouble("C10"));    
					//MST.setC10(1.2442225); 
					MST.setC11(result.getDouble("C11"));    
					MST.setC12(result.getDouble("C12"));    
					MST.setC35(result.getString("C35"));     
					MST.setC15(result.getString("C15"));     
					MST.setC19(result.getDouble("C19"));    
					MST.setC20(result.getDouble("C20"));    
					MST.setC22(result.getString("C22"));     
					MST.setC27(result.getDouble("C27"));    
					MST.setC29(result.getDouble("C29"));    
					MST.setC30(result.getDouble("C30"));    
					MST.setC31(result.getDouble("C31"));    
					MST.setC37(result.getDouble("C37")); 
					return MST;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public static void main(String[] args)
		{
			DzhTfdDao dtd = new DzhTfdDao();
			List<MigrationStruct> gt = dtd.getListByUIdAndType();
			for(MigrationStruct tmp : gt)
			{
				String sta = tmp.toString();
				//System.out.println(sta);
			}
		}
}
