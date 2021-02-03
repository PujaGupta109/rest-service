package com.example.restservice.service;

import com.example.restservice.model.LogEntry;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;

@Component
public class LogServiceImpl implements LogService {
    public static ArrayList<LogEntry> CENTRAL_LOGS = new ArrayList<>();

    @Override
    public void loadLogs(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        try {

            JSONArray arr = obj.getJSONArray("str");
            for (int i = 0; i < arr.length(); i++) {
                LogEntry logEntry = new LogEntry();
                JSONObject obj1 = (JSONObject) arr.get(i);
                logEntry.setPfm((String) obj1.get("pfm"));
                logEntry.setLevel((String) obj1.get("level"));
                logEntry.setStep((String) obj1.get("step"));
                logEntry.setTimestamp(Timestamp.valueOf( obj1.get("timestamp").toString().substring(0,23)));
                CENTRAL_LOGS.add(logEntry);

            }
            System.out.println(CENTRAL_LOGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Long count(String value, Timestamp ts1, Timestamp ts2) {

        if (ts1 == null || ts2 == null) {
            return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value)).count();
        }
        return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value)).filter(logEntry -> logEntry.getTimestamp().after(ts1) && logEntry.getTimestamp().before(ts2)).count();

    }

    @Override
    public Long countMatchAny(String value1, String value2, Timestamp ts1, Timestamp ts2) {
        if (ts1 == null || ts2 == null) {
            return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value1) || logEntry.getLevel().equalsIgnoreCase(value2)).count();
        }
        return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value1) || logEntry.getLevel().equalsIgnoreCase(value2)).filter(logEntry -> logEntry.getTimestamp().after(ts1) && logEntry.getTimestamp().before(ts2)).count();

    }

    @Override
    public Long countMatchAll(String value1, String value2, Timestamp ts1, Timestamp ts2) {
        if (ts1 == null || ts2 == null) {
            return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value1) && logEntry.getLevel().equalsIgnoreCase(value2)).count();
        }
        return CENTRAL_LOGS.stream().filter(logEntry -> logEntry.getPfm().equalsIgnoreCase(value1) && logEntry.getLevel().equalsIgnoreCase(value2)).filter(logEntry -> logEntry.getTimestamp().after(ts1) && logEntry.getTimestamp().before(ts2)).count();

    }
}
