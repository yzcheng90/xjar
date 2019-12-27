package io.xjar.boot;

import io.xjar.XLauncher;
import io.xjar.util.AddressUtil;
import org.springframework.boot.loader.JarLauncher;

import java.net.URL;
import java.util.Arrays;

/**
 * Spring-Boot Jar 启动器
 *
 * @author Payne 646742615@qq.com
 * 2018/11/23 23:06
 */
public class XJarLauncher extends JarLauncher {
    private final XLauncher xLauncher;

    public XJarLauncher(String... args) throws Exception {
        System.out.println("--------------XJarLauncher---"+Arrays.toString(args));
        this.xLauncher = new XLauncher(args);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--------------main---"+ Arrays.toString(args));
        new XJarLauncher(args).launch();
    }

    public void launch() throws Exception {
        launch(xLauncher.args);
    }

    @Override
    protected ClassLoader createClassLoader(URL[] urls) throws Exception {
        System.out.println("--------------createClassLoader---"+xLauncher.xKey.getPassword());
        System.out.println("--------------createClassLoader---"+xLauncher.xKey.getDecryptKey());
        System.out.println("--------------createClassLoader---"+xLauncher.xKey.getEncryptKey());
//        String password = xLauncher.xKey.getPassword();
//        String address = AddressUtil.getHostAddress();
//        String md5 = AddressUtil.encode( password + address).substring(0,16);
//        System.out.println("======password:"+md5);
//        xLauncher.xKey.setPassword(md5);
        return new XBootClassLoader(urls, this.getClass().getClassLoader(), xLauncher.xDecryptor, xLauncher.xEncryptor, xLauncher.xKey);
    }

}
