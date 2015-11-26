package com.atopcloud.util;

import java.io.*;
import java.util.Vector;

/**
 * Created by Hihiri on 2015/11/10.
 */
public class FileUtil {
    public static Vector<String> getVectorFromFile(String filePath, String contentEncoding) {
        Vector<String> urlList = new Vector<String>();
        File file = new File(filePath);
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, contentEncoding));
            String tempString;
            while ((tempString = br.readLine()) != null) {
                urlList.add(tempString);
            }
            fis.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }
}
