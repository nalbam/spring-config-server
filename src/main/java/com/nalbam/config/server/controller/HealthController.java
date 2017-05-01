package com.nalbam.config.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Map<String, String> index() {
        Map<String, String> data = new ConcurrentHashMap<>();
        data.put("version", getVersion());
        data.put("address", getAddress());
        return data;
    }

    private synchronized String getAddress() {
        try {
            InetAddress addr;
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                for (Enumeration<InetAddress> a = e.nextElement().getInetAddresses(); a.hasMoreElements(); ) {
                    addr = a.nextElement();
                    if (!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && addr.isSiteLocalAddress()) {
                        return addr.getHostAddress();
                    }
                }
            }
            addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private synchronized String getVersion() {
        String version = null;
        Package p = getClass().getPackage();
        if (p != null) {
            version = p.getImplementationVersion();
            if (version == null) {
                version = p.getSpecificationVersion();
            }
        }
        if (version == null) {
            version = "";
        }
        return version;
    }

}
