package com.safetyhuge.chanlian.safetyhuge.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：王海宾 on 2017/4/24 0024 11:00
 * 邮箱：995696826@qq.com
 */

public class HtmlService {

    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url
                .openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取html数据
        InputStream inStream = conn.getInputStream();
        //得到html的二进制数据
        byte[] data = readInputStream(inStream);
        String html = new String(data, "gb2312");
        return html;
    }

    public static byte[] readInputStream(InputStream inStream)
            throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}