package com.example.restservice.model;

import java.sql.Timestamp;
public class LogEntry {
    private String pfm;
    private String level;
    private String step;
    private Timestamp timestamp;

    public void setPfm(String pfm) {
        this.pfm = pfm;
    }

    public void setLevel(String level) {
        this.level= level;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPfm() {
        return this.pfm;
    }
    public String getLevel() {
        return this.level;
    }
    public String getStep() {
        return this.step;
    }
    public Timestamp getTimestamp() {
        return this.timestamp;
    }
    public String toString(){
        return (this.getLevel() + "  " + this.getPfm() + "  " + this.step);
    }
}
