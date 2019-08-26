package com.quicktutorialz.nio.AsyncMVC.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    private final String UUID;
    private final Float value;
    private final String committer;
    private final String committee;
    private final LocalDateTime timestamp;

    public Payment(){
        this.UUID = null;
        this.value = null;
        this.committer = null;
        this.committee = null;
        this.timestamp = null;
    }

    public Payment(Float value, String committer, String committee) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.value = value;
        this.committer = committer;
        this.committee = committee;
        this.timestamp = LocalDateTime.now();
    }

    public String getUUID() {
        return UUID;
    }

    public Float getValue() {
        return value;
    }

    public String getCommitter() {
        return committer;
    }

    public String getCommittee() {
        return committee;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
