package com.gw.dzhyun.app.topicinvest.base;

import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyHttpUtil;
import com.gw.dzhyun.util.TranYfloatMain;

public class TopicInvestInfo {
	
    private String obj;
    private float kaiPanJia;
    private float zuiXinJia;
    private float huanShou;
    private float liangBi;
    private long shiJian;
    private float zhangFu;
    public int sznum,xdnum,ppnum;
    public TopicInvestInfo()
    {
    	this.liangBi = 0;
    	this.huanShou = 0;
    	this.zhangFu = 0;
    }
    public void setObj(String obj)
    {
    	this.obj = obj;
    }
    public void setZhangFu(float zhangFu)
    {
    	this.zhangFu = zhangFu;
    }
    public void setKaiPanJia(float kpj)
    {
    	this.kaiPanJia = kpj;
    }
    public void setZuiXinJia(float zxj)
    {
    	this.zuiXinJia = zxj;
    }
    public void setHuanShou(float huans)
    {
    	this.huanShou = huans;
    }
    public void setLiangBi(float liangBi)
    {
    	this.liangBi = liangBi;
    }
    public void setShiJian(long shijian)
    {
    	this.shiJian = shijian;
    }
    public String getObj()
    {
    	return this.obj ;
    }
    public float getZhangFu()
    {
    	return this.zhangFu;
    }
    public float getKaiPanJia()
    {
    	return this.kaiPanJia;
    }
    public float getZuiXinJia()
    {
    	return this.zuiXinJia;
    }
    public float getHuanShou()
    {
    	return this.huanShou;
    }
    public float getLiangBi()
    {
    	return this.liangBi;
    }
    public long getShiJian()
    {
    	return this.shiJian;
    }
    public void printAvgData()
    {
    	System.out.println("换手huanShou:"+huanShou + "\r\n量比liangBi:"+liangBi + "\r\n涨幅zhangFu:"+zhangFu + "\r\n上涨家数sznum:"+sznum+"\r\n下跌家数xdnum:"+xdnum+"\r\n平盘家数ppnum:"+ppnum);
    }
    public boolean equals(TopicInvestInfo o)
    {
    	boolean ret = false;
//    	this.printAvgData();
//    	System.out.println("--------------------------------------");
//    	o.printAvgData();
        float tmlb = this.liangBi - o.getLiangBi();
        float tmzf = this.zhangFu - o.getZhangFu();
        float tmhs = this.huanShou - o.getHuanShou();
    	if(tmlb>-0.01 && tmlb<=0.01 && tmzf>-0.01 && tmzf<=0.01 && tmhs>-0.01 && tmhs<=0.01 && this.sznum == o.sznum && this.ppnum == o.ppnum && this.xdnum == o.xdnum)
    	{
    		ret = true;
    	}
    	return ret;
    }
	public static void main(String[] args) throws SAXException, Exception {
		// TODO Auto-generated method stub
		String[] urlstr = {"http://10.15.144.80/quote/dyna?obj=SZ300171&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SZ300311&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SZ300315&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SZ300312&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SH600289&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SH600770&token=df1afda4ead649ff8dd52f3b770495e9",
        		"http://10.15.144.80/quote/dyna?obj=SH600198&token=df1afda4ead649ff8dd52f3b770495e9"};
        for(int i=0;i<urlstr.length;i++)
        {
        	String retstr = MyHttpUtil.getQuoteDyna(urlstr[i],"json");
        	//System.out.println(urlstr[i]+"\n");
        	//System.out.println(retstr+"\n");
        	//System.out.println("retstr="+retstr+"\n");
    		JSONObject data = JSON.parseObject(retstr);
    		TranYfloatMain tym = new TranYfloatMain(data,"RepDataQuoteDynaSingle");
    		JSONObject tranjson = tym.dealJsonArray();
    		System.out.println(tranjson+"\n");
        }
	}

}
