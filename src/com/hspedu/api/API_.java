package com.hspedu.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        //1. 获取本机的 InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //2. 根据制定主机名 获取InetAddress 对象
        InetAddress host1 = InetAddress.getByName("DESKTOP-PIKVTQP");
        System.out.println("host1=" + host1);

        //3. 根据域名返回 InetAddress 对象，比如 www.baidu.com 对应的
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2=" + host2);

        //4. 通过 InetAddress 对象，获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println("host2 对应的ip =" + hostAddress);//14.215.177.39

        //5. 通过 InetAddress 对象，获取对应的主机名/域名
        String hostName = host2.getHostName();
        System.out.println("host2对应的主机名/域名=" + hostName); //www.baidu.com
    }
}
