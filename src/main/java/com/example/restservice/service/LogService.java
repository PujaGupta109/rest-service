package com.example.restservice.service;

import org.json.JSONException;

import java.sql.Timestamp;

public interface LogService {
    void loadLogs(String str) throws JSONException;
    Long count(String value, Timestamp ts1, Timestamp ts2);
    Long countMatchAny(String value1, String value2, Timestamp ts1, Timestamp ts2);
    Long countMatchAll(String value1, String value2,Timestamp ts1, Timestamp ts2);
}
