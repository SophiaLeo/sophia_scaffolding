package com.scaffolding.sophia.common.util;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author LHL
 */
public class ComUtil {

    public static boolean isEmpty(Object aObj) {
        if (aObj instanceof String) {
            return isEmpty((String) aObj);
        } else if (aObj instanceof Long) {
            return isEmpty((Long) aObj);
        } else if (aObj instanceof Date) {
            return isEmpty((Date) aObj);
        } else if (aObj instanceof Collection) {
            return isEmpty((Collection) aObj);
        } else if (aObj instanceof Map) {
            return isEmpty((Map) aObj);
        } else if (aObj != null && aObj.getClass().isArray()) {
            return isEmptyArray(aObj);
        } else {
            return isNull(aObj);
        }
    }

    private static boolean isEmptyArray(Object array) {
        int length = 0;
        if (array instanceof int[]) {
            length = ((int[]) array).length;
        } else if (array instanceof byte[]) {
            length = ((byte[]) array).length;
        } else if (array instanceof short[]) {
            length = ((short[]) array).length;
        } else if (array instanceof char[]) {
            length = ((char[]) array).length;
        } else if (array instanceof float[]) {
            length = ((float[]) array).length;
        } else if (array instanceof double[]) {
            length = ((double[]) array).length;
        } else if (array instanceof long[]) {
            length = ((long[]) array).length;
        } else if (array instanceof boolean[]) {
            length = ((boolean[]) array).length;
        } else {
            length = ((Object[]) array).length;
        }
        if (length == 0) {
            return true;
        }
        return false;
    }


    public static boolean isEmpty(Date aDate) {
        if (aDate == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Long aLong) {
        if (aLong == null) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isEmpty(Map m) {
        if (m == null || m.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection c) {
        if (c == null || c.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String aStr) {
        if (aStr == null || aStr.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }



    public static String trim(String aStr) {
        if (aStr == null) {
            return "";
        } else {
            return aStr.trim();
        }
    }

    public static boolean isNull(Object oStr) {
        if (oStr == null) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean equals(String str1, String str2) {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }

    public static boolean equals(Long L1, Long L2) {
        return L1 != null ? L1.equals(L2) : L2 == null;
    }

    public static boolean equals(Object obj1, Object obj2) {
        boolean result;
        if (obj1 != null) {
            result = obj1.equals(obj2);
        } else {
            result = (obj2 == null);
        }
        return result;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
    }

}
