package com.example.restservice.controller;

import com.example.restservice.service.LogService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class LogController {
    @Autowired
    LogService logServiceImpl;

    @RequestMapping(value = "/loadLogs", method = RequestMethod.POST)
    public ResponseEntity setLogData(@RequestBody(required = true) String logs) throws JSONException {

            logServiceImpl.loadLogs(logs);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "application/text")
    public ResponseEntity<String> occurrances(@RequestParam(value = "value", required = true) String value,@RequestParam(value ="ts1", required = false) String ts1, @RequestParam(value ="ts2", required = false) String ts2) {
        if(ts1!=null &&ts2!=null) {
            Long count = logServiceImpl.count(value, Timestamp.valueOf(ts1.substring(0,23)), Timestamp.valueOf(ts2.substring(0,23)));
            return new ResponseEntity(count.toString(), HttpStatus.OK);
        }
        else {
            Long count = logServiceImpl.count(value, null,null);
            return new ResponseEntity(count.toString(),HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/countMatchAny/{value1}/{value2}/{ts1}/{ts2}", method = RequestMethod.GET, produces = "application/text")
    public ResponseEntity<String> countMatchAny(@RequestParam(value = "value1") String value1, @RequestParam(value = "value2") String value2 ,@RequestParam(value ="ts1", required = false) String ts1, @RequestParam(value ="ts2", required = false) String ts2) {
        if(ts1!=null &&ts2!=null) {
            return new ResponseEntity(logServiceImpl.countMatchAny(value1, value2, Timestamp.valueOf(ts1.substring(0, 23)), Timestamp.valueOf(ts2.substring(0, 23))).toString(), HttpStatus.OK);

        } else{
            return new ResponseEntity(logServiceImpl.countMatchAny(value1, value2,null, null).toString(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/countMatchAll/{value1}/{value2}/{ts1}/{ts2}", method = RequestMethod.GET, produces = "application/text")
    public ResponseEntity<String> countMatchAll(@RequestParam(value = "value1") String value1, @RequestParam(value = "value2") String value2,@RequestParam(value ="ts1", required = false) String ts1, @RequestParam(value ="ts2", required = false) String ts2) {
        if(ts1!=null &&ts2!=null) {
            return new ResponseEntity(logServiceImpl.countMatchAll(value1, value2, Timestamp.valueOf(ts1), Timestamp.valueOf(ts2)).toString(),HttpStatus.OK);
        }else {
            return new ResponseEntity(logServiceImpl.countMatchAll(value1, value2, null, null).toString(),HttpStatus.OK);
        }
    }
}
