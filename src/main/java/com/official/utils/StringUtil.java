package com.official.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具 (JedisUtil用到)
 * 
 */
public class StringUtil {
    /**
     * 定义下划线
     */
    private static final char UNDERLINE = '_';

    /**
     * String为空判断(不允许空格)
     * @param str
     * @return boolean
     * @author Wang926454
     * @date 2018/9/4 14:49
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * String不为空判断(不允许空格)
     * @param str
     * @return boolean
     * @author Wang926454
     * @date 2018/9/4 14:51
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Byte数组为空判断
     * @param bytes
     * @return boolean
     * @author Wang926454
     * @date 2018/9/4 15:39
     */
    public static boolean isNull(byte[] bytes){
        // 根据byte数组长度为0判断
        return bytes.length == 0 || bytes == null;
    }

    /**
     * Byte数组不为空判断
     * @param bytes
     * @return boolean
     * @author Wang926454
     * @date 2018/9/4 15:41
     */
    public static boolean isNotNull(byte[] bytes) {
        return !isNull(bytes);
    }

    /**
     * 驼峰转下划线工具
     * @param param
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/4 14:52
     */
    public static String camelToUnderline(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     * @param param
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/4 14:52
     */
    public static String underlineToCamel(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (c == 95) {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 在字符串两周添加''
     * @param param
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/4 14:53
     */
    public static String addSingleQuotes(String param) {
        return "\'" + param + "\'";
    }
    
    /**
     * 
     * 根据文件名获取工号
     * @param fileName
     * @return
     */
    public static String getWorkNumberFromFileName(String fileName) {

    			//String a = "2133fdjsl发动机老司机";
    			Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(fileName);
                if(m.find()){
                    return m.group();
                }
		return "";
    }
    
    public static void main(String[] args) {
    	System.out.println(getWorkNumberFromFileName("2133fdjsl发动机老司机16"));
	}



}
