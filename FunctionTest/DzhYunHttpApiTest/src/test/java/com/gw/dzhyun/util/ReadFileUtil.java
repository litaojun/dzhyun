package com.gw.dzhyun.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import com.gw.dzhyun.httptest.StorageProxyTest;
public class ReadFileUtil {
	
	public static ArrayList readFileToArrayList() throws FileNotFoundException,IOException
	{
		ArrayList sb = new ArrayList();
		FileReader reader;
		reader = new FileReader("c://litaojun.dat");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
		while((str = br.readLine()) != null)
		{
			      sb.add(str);
			      //System.out.println(str);
		}
        br.close();
        reader.close();
        return sb;
	}
	public static void addArrayListToFile(ArrayList<String> al) throws IOException
	{
		  FileWriter writer;
		 writer = new FileWriter("c://test2.txt");
          BufferedWriter bw = new BufferedWriter(writer);
          for(String mystr:al)
          {
              bw.write(mystr+"\n");
          }
          bw.close();
          writer.close();
	}
	public static void main(String[] args) throws FileNotFoundException ,IOException
	{
		ArrayList<String> all = new ArrayList();
		ArrayList testall = new ArrayList(); 
		all = readFileToArrayList();
		StorageProxyTest spt = new StorageProxyTest();
		int count = 0;
		for(String x:all)
		{
			if(count>=2000)
				break;
			try {
				int i = spt.testOutputJsonxing(x);
				if(i == 0)
				{
					testall.add(x);
					count++;
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		addArrayListToFile(testall);
		//addArrayListToFile(all);

	}
		
}
