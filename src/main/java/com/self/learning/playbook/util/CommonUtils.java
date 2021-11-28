package com.self.learning.playbook.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.util.UUID;

public class CommonUtils {

    public static final String hostIp;

    static {
        hostIp = getHostIpWithoutPort();
    }

    private static String getHostIpWithoutPort() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            return "UNKNOWN";
        }
    }

    public static String generateRequestId() {
        return UUID.randomUUID().toString();
    }

}
