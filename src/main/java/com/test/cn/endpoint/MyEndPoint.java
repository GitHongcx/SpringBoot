package com.test.cn.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "serverTime")
public class MyEndPoint {

    @ReadOperation
    public Map<String, Object> hello(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("author", "star");
        result.put("server_time", LocalDateTime.now());
        result.put("ms_format", LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond());
        return result;
    }
}
