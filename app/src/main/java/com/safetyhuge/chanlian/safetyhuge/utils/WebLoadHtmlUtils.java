package com.safetyhuge.chanlian.safetyhuge.utils;

/**
 * Created by Chinalink on 2017/3/28 0028.
 */

public class WebLoadHtmlUtils {

    public static String loadHtml(String title, String content) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        html.append("<head>");
        html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        html.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        html.append("<meta http-equiv=\"Cache-Control\" content=\"no-transform\">");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">");
        html.append("<meta name=\"keywords\" content=\"\">");
        html.append("<title>" + title + "</title>");
        html.append("<style> img { width: 100%; }</style>");
        html.append("</head>");
        html.append("<body>" + content + "</body>");
        html.append("</html>");
        return html.toString();
    }
}