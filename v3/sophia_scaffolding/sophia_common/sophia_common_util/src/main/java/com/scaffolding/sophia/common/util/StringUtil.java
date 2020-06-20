package com.scaffolding.sophia.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LHL
 */
public class StringUtil {


    /**
     * 校验手机号
     *
     * @param phone 手机号
     * @return boolean
     */
    public static boolean checkPhone(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        if (phone == null || phone.length() != 11) {
            return Boolean.FALSE;
        }

        Matcher m = p.matcher(phone);
        return m.matches();
    }


    /**
     * 校验邮箱
     *
     * @param email 邮箱
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        if (ComUtils.isEmpty(email)) {
            return false;
        }
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 校验url
     *
     * @param url 路径
     * @return boolean
     */
    public static boolean isValidUrlAddress(String url) {
        String pattern = "([h]|[H])([t]|[T])([t]|[T])([p]|[P])([s]|[S]){0,1}://([^:/]+)(:([0-9]+))?(/\\S*)*";
        return url.matches(pattern);
    }

    /**
     * 获取方法中指定注解的value值返回
     *
     * @param method               方法名
     * @param validationParamValue 注解的类名
     * @return String
     */
    public static String getMethodAnnotationOne(Method method, String validationParamValue) {
        String retParam = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                String str = parameterAnnotations[i][j].toString();
                if (str.indexOf(validationParamValue) > 0) {
                    retParam = str.substring(str.indexOf("=") + 1, str.indexOf(")"));
                }
            }
        }
        return retParam;
    }

    /**
     * 将utf-8编码的汉字转为中文
     *
     * @param str 汉字
     * @return String
     * @author LHL
     */
    public static String utf8Decoding(String str) {
        String result = str;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }



	/**
	 * 截取文件排除后缀名
	 *
	 * @param fileName 文件名
	 * @return String
	 */
	public static String getFileNameSuffix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

}
