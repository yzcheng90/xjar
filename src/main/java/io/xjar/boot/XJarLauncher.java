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
        this.xLauncher = new XLauncher(args);
    }

    public static void main(String[] args) throws Exception {
        String address = AddressUtil.getHostAddress();
        System.out.println("--------------本机地址："+ address);
        new XJarLauncher(args).launch();
    }

    public void launch() throws Exception {
        launch(xLauncher.args);
    }

    @Override
    protected ClassLoader createClassLoader(URL[] urls) throws Exception {
        return new XBootClassLoader(urls, this.getClass().getClassLoader(), xLauncher.xDecryptor, xLauncher.xEncryptor, xLauncher.xKey);
    }

}
