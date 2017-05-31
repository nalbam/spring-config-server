package com.nalbam.config.server.controller;

import com.nalbam.common.util.AddressUtil;
import com.nalbam.common.util.PackageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Map<String, String> index() {
        Map<String, String> data = new ConcurrentHashMap<>();
        data.put("title", PackageUtil.getTitle(getClass()));
        data.put("version", PackageUtil.getVersion(getClass()));
        data.put("address", AddressUtil.getAddress());
        return data;
    }

}
