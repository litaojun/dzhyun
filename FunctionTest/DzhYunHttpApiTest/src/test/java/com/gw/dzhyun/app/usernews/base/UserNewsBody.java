/**
 * @classnmae UserNewsBody.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.usernews.base;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Litaojun
 * @date   2016年2月24日
 */
public class UserNewsBody {
	private String id                     ; 
	private String summary                ;
	private String title                  ;   
	private String otime                  ;
	private String source                 ;
	private String img                    ;
	private String type                   ;
	private String url                    ;
	private int countid                   ;
	private String views                  ;
	private String resType                ;
	private String isTop                  ;
	private String uComments              ;
	private String eComments              ;
	private ArrayList<String[]> stockName;     
	private String zjun                   ;
	private String topName                ;
	private String topUrl                 ;
	private String topType                ;
	private String topColor               ;
	private String fColor                 ;
	public boolean equals(Object o)
	{
		boolean sign = false;
		if(o!=null)
		{
			if(o instanceof UserNewsBody)
			{
				UserNewsBody src = (UserNewsBody) o;
				if(this.id.equals(src.getId())&&
						this.summary.equals(src.getSummary())&&
						this.title.equals(src.getTitle())&&
						this.otime.equals(src.getOtime())&&
						this.source.equals(src.getSource())&&
					    this.img.equals(src.getImg())&&
						this.type.equals(src.getType())&&
						this.url.equals(src.getUrl())&&
						this.countid == src.getCountid()&&
						this.views.equals(src.getViews())&&
						this.resType.equals(src.getResType())&&
						//this.isTop.equals(src.getIsTop())&&
						this.uComments.equals(src.getUComments())&&
						this.eComments.equals(src.getEComments())&&
						//this.stockName.equals(src.getStockName())&&
						//this.zjun.equals(src.getZjun())&&
						this.topName.equals(src.getTopName())&&
						this.topUrl.equals(src.getTopUrl())&&
						this.topType.equals(src.getTopType())&&
						this.topColor.equals(src.getTopColor())//&&
						//this.fColor.equals(src.getFColor())
						)
					sign = true;
			}
		}
		return sign;
	}
    public int compareTo(UserNewsBody o)
    {
    	int ret = 0;
    	ret = this.isTop.compareTo(o.getIsTop()) == 0?this.otime.compareTo(o.getIsTop()):this.isTop.compareTo(o.getIsTop());
    	return ret;
    }
	public String               getId()     
	{
		return this.id;
	}
	public String               getSummary()            
	{
		return this.summary;
	}
	public String               getTitle()     
	{
		return this.title;
	}
	public String               getOtime()    
	{
		return this.otime;
	}
	public String               getSource()  
	{
		return this.source;
	}
	public String               getImg()       
	{
		return this.img;
	}
	public String               getType()    
	{
		return this.type;
	}
	public String               getUrl()      
	{
		return this.url;
	}
	public int               getCountid() 
	{
		return this.countid;
	}
	public String               getViews()         
	{
		return this.views;
	}
	public String               getResType()   
	{
		return this.resType;
	}
	public String               getIsTop()     
	{
		return this.isTop;
	}
	public String               getUComments() 
	{
		return this.uComments;
	}
	public String               getEComments()       
	{
		return this.eComments;
	}
	public ArrayList<String[]> getStockName()
	{
		return this.stockName;
	}
	public String               getTopName()    
	{
		return this.topName;
	}
	public String               getTopUrl()       
	{
		return this.topUrl;
	}
	public String               getTopType()    
	{
		return this.topType;
	}
	public String               getTopColor()      
	{
		return this.topColor;
	}
	public String               getFColor()       
	{
		return this.fColor;
	}
	public String               getZjun()   
	{
		return this.zjun;
	}
	public void setId         (String               id) 
	{
		this.id = id;
	}
	public void setSummary    (String               summary)
	{
		this.summary = summary;
	}
	public void setTitle      (String               title)
	{
		this.title = title;
	}
	public void setOtime      (String               otime)
	{
		this.otime = otime;
	}
	public void setSource     (String               source)
	{
		this.source = source;
	}
	public void setImg        (String               img) 
	{
		this.img = img;
	}
	public void setType       (String               type) 
	{
		this.type = type;
	}
	public void setUrl        (String               url)
	{
		this.url = url;
	}
	public void setCountid    (int               countid)
	{
		this.countid = countid;
	}
	public void setViews      (String               views)
	{
		this.views = views;
	}
	public void setResType    (String               resType)
	{
		this.resType = resType;
	}
	public void setIsTop      (String               isTop)
	{
		this.isTop = isTop;
	}
	public void setUComments  (String               uComments)
	{
		this.uComments = uComments;
	}
	public void setEComments  (String               eComments)
	{
		this.eComments = eComments;
	}
	public void setStockName  (ArrayList<String[]> stockName)
	{
		this.stockName = stockName;
	}
	public void setTopName    (String               topName)
	{
		this.topName = topName;
	}
	public void setTopUrl     (String               topUrl)
	{
		this.topUrl = topUrl;
	}
	public void setTopType    (String               topType)
	{
		this.topType = topType;
	}
	public void setTopColor   (String               topColor)
	{
		this.topColor = topColor;
	}
	public void setFColor     (String               fColor)
	{
		this.fColor = fColor;
	}
	public void               setZjun(String               zjun)   
	{
		this.zjun = zjun;
	}
	public void paserFromJsonObj(JSONObject jsonobj)
	{
		this.id         =   jsonobj.getString("id")     ; 
		this.summary    =   jsonobj.getString("summary")         ;
		this.title      =   jsonobj.getString("title")         ;   
		this.otime      =   jsonobj.getString("otime")         ;
		this.source     =   jsonobj.getString("source")         ;
		this.img        =   jsonobj.getString("img")         ;
		this.type       =   jsonobj.getString("type")         ;
		this.url        =   jsonobj.getString("url")         ;
		this.countid       =   jsonobj.getIntValue("countid")         ;
		this.views      =   jsonobj.getString("views")         ;
		this.resType    =   jsonobj.getString("resType")         ;
		this.isTop      =   jsonobj.getString("isTop")         ;
		this.uComments  =   jsonobj.getString("uComments")         ;
		this.eComments  =   jsonobj.getString("eComments")         ;
		
		//this.stockName  =   jsonobj.getString("stockName")         ;
		ArrayList<String[]> stock = new ArrayList<String[]>();
		JSONArray sname = jsonobj.getJSONArray("stockName");
		if(sname!=null)
		{
			for(int i=0;i<sname.size();i++)
			{
				JSONObject json = sname.getJSONObject(i);
				String[] dataarr = new String[2];
				dataarr[0] = json.getString("stockcode");
				dataarr[1] = json.getString("stockname");
				stock.add(dataarr);
			}
		}
		this.stockName = stock;
		this.zjun       =   jsonobj.getString("zjun")         ;
		this.topName    =   jsonobj.getString("topName")         ;
		this.topUrl     =   jsonobj.getString("topUrl")         ;
		this.topType    =   jsonobj.getString("topType")         ;
		this.topColor   =   jsonobj.getString("topColor")         ;
		this.fColor     =   jsonobj.getString("fColor")         ;
	}
	public void paserFromJson(JSONObject jsonobj)
	{
		this.id         =   jsonobj.getString("id")     ; 
		this.summary    =   jsonobj.getString("summary")         ;
		this.title      =   jsonobj.getString("title")         ;   
		this.otime      =   jsonobj.getString("otime")         ;
		this.source     =   jsonobj.getString("source")         ;
		this.img        =   jsonobj.getString("img")         ;
		this.type       =   jsonobj.getString("type")         ;
		this.url        =   jsonobj.getString("url")         ;
		this.countid       =   jsonobj.getIntValue("countid")         ;
		this.views      =   jsonobj.getString("views")         ;
		this.resType    =   jsonobj.getString("resType")         ;
		this.isTop      =   jsonobj.getString("isTop")         ;
		this.uComments  =   jsonobj.getString("uComments")         ;
		this.eComments  =   jsonobj.getString("eComments")         ;
		
		//this.stockName  =   jsonobj.getString("stockName")         ;
		ArrayList<String[]> stock = new ArrayList<String[]>();
		//JSONArray sname = jsonobj  getJSONArray("stockName");
		JSONObject sname = jsonobj.getJSONObject("stockName");
		if(sname!=null)
		{
			//sname.e
//			for(int i=0;i<sname.size();i++)
//			{
//				JSONObject json = sname.getJSONObject(i);
//				String[] dataarr = new String[2];
//				dataarr[0] = json.getString("stockcode");
//				dataarr[1] = json.getString("stockname");
//				stock.add(dataarr);
//			}
		}
		this.stockName = stock;
		this.zjun       =   jsonobj.getString("zjun")         ;
		this.topName    =   jsonobj.getString("topName")         ;
		this.topUrl     =   jsonobj.getString("topUrl")         ;
		this.topType    =   jsonobj.getString("topType")         ;
		this.topColor   =   jsonobj.getString("topColor")         ;
		this.fColor     =   jsonobj.getString("fColor")         ;
	}
}
