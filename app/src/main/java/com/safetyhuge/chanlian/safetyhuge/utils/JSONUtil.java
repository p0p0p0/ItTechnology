package com.safetyhuge.chanlian.safetyhuge.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jc on 2015/11/24 0024.
 */
public class JSONUtil {

    public static boolean isPrintException = true;
    /**
     * Json 转成 Map<>
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> getMapForJson(String jsonStr){
        JSONObject jsonObject ;

        if (jsonStr == null) {
            return null;
        }
        if (jsonStr.length() == 0) {
            return new HashMap<String, Object>();
        }
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter= jsonObject.keys();
            String key;
            Object value ;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.e("JSONUtil","json转换map错误");
        }
        return null;
    }
    /**
     * Json 转成 List<Map<>>
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> getlistForJson(String jsonStr){
        List<Map<String, Object>> list = null;

        if (jsonStr == null) {
            return null;
        }
        if (jsonStr.length() == 0) {
            return new ArrayList<Map<String,Object>>();
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            JSONObject jsonObj ;
            list = new ArrayList<Map<String,Object>>();
            for(int i = 0 ; i < jsonArray.length() ; i ++){
                jsonObj = (JSONObject)jsonArray.get(i);
                list.add(getMapForJson(jsonObj.toString()));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取List<String>
     * @param jsonStr
     * @return
     */
    public static List<String> getliststrForJson(String jsonStr){
        List<String> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            String jsonObj ;
            list = new ArrayList<String>();
            for(int i = 0 ; i < jsonArray.length() ; i ++){
                jsonObj = jsonArray.get(i).toString();
                list.add(jsonObj);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }


}
