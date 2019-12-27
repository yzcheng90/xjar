package io.xjar.util;

import io.xjar.boot.XBoot;
import io.xjar.jar.XJarAntEntryFilter;

/**
 * @author czx
 * @title: MainUtil
 * @projectName xjar
 * @description: TODO
 * @date 2019/12/2715:16
 */
public class MainUtil {

    public static void main(String[] args) throws Exception {
//        String address = AddressUtil.getHostAddress();
        String address = "095312603d514460379c9b8dea9193f4";
        System.out.println("======"+address);
        System.out.println("======开始");
        String password = "czx";
        password = AddressUtil.encode( password + address);
        System.out.println("======password:"+password);
        String plaintext = "E:\\github\\study-http\\target\\study-http-1.0.0.jar";
        String encrypted = "E:\\github\\study-http\\target\\encrypted.jar";
        String decrypted = "E:\\github\\study-http\\target\\decrypted.jar";
        XBoot.encrypt(plaintext, encrypted, password, new XJarAntEntryFilter("com/study/**"));
        password = "czx";
        System.out.println("======password:"+password);
        XBoot.decrypt(encrypted, decrypted, password, new XJarAntEntryFilter("com/study/**"));
        System.out.println("======结束");
    }
}
