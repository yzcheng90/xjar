package io.xjar.util;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.List;

/**
 * @author czx
 * @title: AddressUtil
 * @projectName xjar
 * @description: TODO
 * @date 2019/12/2710:32
 */
public class AddressUtil {


    public static String getHostAddress() throws SocketException {
        java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        while (en.hasMoreElements()) {
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for (InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if (network == null) {
                    continue;
                }
                byte[] mac = network.getHardwareAddress();
                if (mac == null) {
                    continue;
                }
                sb.delete(0, sb.length());
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                //tmpMacList.add(sb.toString());
            }
        }
        /***去重，别忘了同一个网卡的ipv4,ipv6得到的mac都是一样的，肯定有重复，下面这段代码是。。流式处理***/
        //List<String> unique = tmpMacList.stream().distinct();
        return encode(sb.toString());
    }

    public static String encode(String data){
        String md5Result = null;
        if(null == data){
            return md5Result;
        }

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer sb = new StringBuffer("");
            for(int offset = 0; offset < b.length; offset ++){
                i = b[offset];
                if(i< 0){
                    i += 256;
                }
                if(i < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            md5Result = sb.toString();
        } catch (Exception e) {
            e.getMessage();
        }
        return md5Result;
    }
}
