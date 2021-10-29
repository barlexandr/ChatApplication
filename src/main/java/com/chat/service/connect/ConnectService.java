package com.chat.service.connect;

import com.chat.model.Connect;

import java.util.Map;

public interface ConnectService {
    Connect addConnect(Map<String, Integer> connectMap);
    boolean deleteConnect(Map<String, Integer> connectMap);
}
