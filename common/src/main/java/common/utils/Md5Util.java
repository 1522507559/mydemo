package common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
//    private static final String SALT = "isoft_201905011111";

    /**
     * Md5加密
     * @param str 加密字符串
     * @return
     */
    public static String md5(String str) {
        return new Md5Hash(str).toString();
    }

}
