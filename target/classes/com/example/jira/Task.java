package com.example.HW55;

public class Task {
    private String key;
    private String summary;
    private String description;
    private String issueType;
    private String id;
    private String self;


    public Task(String key, String id, String self) {
        this.key = key;
        this.id = id;
        this.self = self;
    }

    public Task(String projectKey, String summary, String description, String issueType) {

    }

    public static void Issue() {
    }

    public void Issue(String key, String summary, String description, String issueType) {
        this.key = key;
        this.summary = summary;
        this.description = description;
        this.issueType = issueType;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getId() {
        return id;
    }

    public String getSelf() {
        return self;
    }

     public void setKey(String key) {
        this.key = key;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public  void setId(String id) {
        this.id = id;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "key='" + key + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", issueType='" + issueType + '\'' +
                ", id='" + id + '\'' +
                ", self='" + self + '\'' +
                '}';
    }
}
