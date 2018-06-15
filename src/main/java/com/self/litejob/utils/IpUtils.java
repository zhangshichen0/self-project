package com.self.litejob.utils;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author shichen
 * @create 2018/6/15
 * @desc
 */
@Slf4j
public final class IpUtils {

    private static volatile String cachedIpAddress;

    /**
     * 获取ip 如果能获取到外网ip 则返回外网ip 否则返回内网ip
     * @return
     */
    public static String getIp() {
        if (!Strings.isNullOrEmpty(cachedIpAddress)) {
            return cachedIpAddress;
        }
        String localIpAddress = null;
        Enumeration<NetworkInterface> networkInterfaceEnumeration = null;
        try {
            networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            log.error("【获取本机ip】get ip error, e", e);
            throw new RuntimeException(e);
        }
        while (networkInterfaceEnumeration.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
            Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
            while (inetAddressEnumeration.hasMoreElements()) {
                InetAddress inetAddress = inetAddressEnumeration.nextElement();
                if (isPublicIpAddress(inetAddress)) {
                    String pubIp = inetAddress.getHostAddress();
                    cachedIpAddress = pubIp;
                    return pubIp;
                }
                if (isLocalIpAddress(inetAddress)) {
                    localIpAddress = inetAddress.getHostAddress();
                }
            }
        }

        return localIpAddress;
    }

    /**
     * 获取本机Host名称.
     *
     * @return 本机Host名称
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (final UnknownHostException ex) {
            throw new RuntimeException();
        }
    }

    private static boolean isPublicIpAddress(final InetAddress ipAddress) {
        return !ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
    }

    private static boolean isLocalIpAddress(final InetAddress ipAddress) {
        return ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
    }

    private static boolean isV6IpAddress(final InetAddress ipAddress) {
        return ipAddress.getHostAddress().contains(":");
    }
}
