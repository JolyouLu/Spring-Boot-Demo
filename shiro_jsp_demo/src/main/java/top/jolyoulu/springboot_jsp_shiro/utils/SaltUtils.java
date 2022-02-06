package top.jolyoulu.springboot_jsp_shiro.utils;

import java.util.Random;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 23:46
 * @Version 1.0
 */
public class SaltUtils {

    /**
     * 生成随机盐
     *
     * @param n
     * @return
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRETUVWHYZabcdefghijklmnopqretuvwhyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(8));
    }

}
