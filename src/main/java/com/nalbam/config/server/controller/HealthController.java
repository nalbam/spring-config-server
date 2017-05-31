package com.nalbam.config.server.controller;

import com.nalbam.common.util.PackageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public Map<String, String> health() {
        return PackageUtil.getData(getClass());
    }

}
