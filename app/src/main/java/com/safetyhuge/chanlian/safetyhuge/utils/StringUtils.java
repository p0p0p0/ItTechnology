package com.safetyhuge.chanlian.safetyhuge.utils;

/**
 * 作者：王海宾 on 2017/3/31 0031 16:06
 * 邮箱：995696826@qq.com
 */

public class StringUtils {
    public static  String Transition(String string){
        String str1= string.replaceAll("\\&lt;","");
        String str2 = str1.replaceAll("\\&gt;","");
        String str3 = str2.replaceAll("\\/p","\\\n");
        String str4 = str3.replaceAll("p","");
        String str5 = str4.replaceAll("br/","");
        String str6 = str5.replaceAll("\\&nbs;","");
        String str7 = str6.replaceAll("\\/","");
        String str8 = str7.replaceAll("br","\\\n");
        String str9 = str8.replaceAll("strong","");
        String str10 = str9.replaceAll("div class=\"mod-to\"div class=\"card-summary nslog-area\" data-nslog-tye=\"72\"div class=\"card-summary-content\"","");
        String str11 = str10.replaceAll("div","");
        String str12 = str11.trim();
        return str12;
    }
}
