package com.gw.dzhyun.fjjj;

public class MigrationStruct  implements Comparable{
//	private  long          CV       ;
//	private  int           C1       ;
	private  String  Dsymbol    ;    
	private  double C5         ;     
	private  double C7         ;     
	//private  String  C9         ;    
	private  double C10        ;     
	private  double C11        ;     
	private  double C12        ;     
	private  String  C35        ;    
	private  String  C15        ;    
	private  double C19        ;     
	private  double C20        ;     
	private  String  C22        ;    
	private  double C27        ;     
	private  double C29        ;     
	private  double C30        ;     
	private  double C31        ;    
	private  double C37        ;
//	public long  getCV()
//	{
//		return this.CV;
//	}
	public MigrationStruct()
	{
		
	}
	public double  getC37()
	{
		//return 1.1111;
		return this.C37;
	}
//	public int  getC1()
//	{
//		return this.C1;
//	}
	public String getDsymbol()
	{
		return this.Dsymbol;
	}
	public double getC5()
	{
		return this.C5;
	}
	public double getC7()
	{
		return this.C7;
	}
//	public String getC9()
//	{
//		return this.C9;
//	}
	public double getC10()
	{
		return this.C10;
	}
	public double getC11()
	{
		return this.C11;
	}
	public double getC12()
	{
		return this.C12;
	}
	public String getC35()
	{
		return this.C35;
	}
	public String getC15()
	{
		return this.C15;
	}
	public double getC19()
	{
		return this.C19;
	}
	public double getC20()
	{
		return this.C20;
	}
	public String getC22()
	{
		return this.C22;
	}
	public double getC27()
	{
		return this.C27;
	}
	public double getC29()
	{
		return this.C29;
	}
	public double getC30()
	{
		return this.C30;
	}
	public double getC31()
	{
		return this.C31;
	}
//	public void setCV(long    CV)    
//	{
//		this.CV=CV;
//	}
//	public void setC1(int     C1)
//	{
//		this.C1 = C1;
//	}
	public void setDsymbol(String  Dsymbol)
	{
		this.Dsymbol = Dsymbol;
	}
	public void setC5(double  C5)
	{
		this.C5 = C5;
	}
	public void setC7(double  C7)      
	{
		this.C7 = C7;
	}
//	public void setC9(String  C9)    
//	{
//		this.C9 = C9;
//	}
	public void setC10(double  C10)  
	{
		this.C10 = C10;
	}
	public void setC11(double  C11)    
	{
		this.C11 = C11;
	}
	public void setC12(double  C12)    
	{
		this.C12 = C12;
	}
	public void setC35(String  C35)   
	{
		this.C35 = C35;
	}
	public void setC15(String  C15)     
	{
		this.C15 = C15;
	}
	public void setC19(double  C19)     
	{
		this.C19 = C19;
	}
	public void setC20(double  C20)     
	{
		this.C20 = C20;
	}
	public void setC22(String  C22)
	{
		this.C22 = C22;
	}
	public void setC27(double  C27)    
	{
		this.C27 = C27;
	}
	public void setC29(double  C29)    
	{
		this.C27 = C27;
	}
	public void setC30(double  C30)   
	{
		this.C30 = C30;
	}
	public void setC31(double  C31)    
	{
		this.C31 = C31;
	}
	public void setC37(double  C37)    
	{
		this.C37 = C37;
	}
	public int compareTo(Object o)
	{
		int retcode = 0;
		if(o instanceof MigrationStruct)
		{
			MigrationStruct ms = (MigrationStruct)o;
			String dstDsymbol =  ms.getDsymbol();
			int sign = this.Dsymbol.compareTo(dstDsymbol);
			if(sign>0)
			{
				retcode =  1;
			}
			else
				if(sign == 0 )
					retcode = 0;
				else
					retcode = -1;
			//System.out.println("retcode=" + retcode+"----Dsymbol="+this.Dsymbol+"----"+"dstDsymbol="+dstDsymbol);
			return retcode;
			
		}
		System.out.println("retcode=" + -1);
		return -1;
	}
	 @Override
	public boolean equals(Object ms) 
	 {
		 MigrationStruct obj = (MigrationStruct)ms;
	      return this.Dsymbol.equals(obj.getDsymbol())    &&    
	    		   this.C5 ==  obj.C5         &&     
	    		   this.C7  ==   obj.C7     &&     
	    		   this.C10 ==   obj.C10    &&     
	    		   this.C11 ==   obj.C11    &&     
	    		   this.C12 ==   obj.C12    &&     
	    		   this.C35 .equals(obj.getC35())    &&    
	    		   this.C15 .equals(obj.getC15())    &&   
	    		   this.C19 ==   obj.C19    &&     
	    		   this.C20 ==   obj.C20    &&     
	    		   this.C22 .equals(obj.getC22())    &&    
	    		   this.C27 ==   obj.C27    &&     
	    		   this.C29 ==   obj.C29    &&     
	    		   this.C30 ==   obj.C30    &&     
	    		   this.C31 ==   obj.C31    &&    
	    		   this.C37 ==   obj.C37;
	    }
	public String toString()
	{
		String retstr = /*"CV="+CV+"C1="+C1+*/"Dsymbol="+Dsymbol+"C5="+C5+"C7="+C7+/*"C9="+C9+*/"C10="+C10+"C11="+C11+"C12="+C12+"C35="+C35+"C15="+C15+"C19="+C19+"C20="+C20+"C22="+C22+"C27="+C27+"C29="+C29+"C30="+C30+"C31="+C31+"C37="+C37;
		//System.out.println(retstr);
		return retstr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
