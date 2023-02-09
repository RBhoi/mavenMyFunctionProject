package com.example;

import java.util.List;

public class FunctionInput {
    private List<String> entityList;
    private String status;
    private String childFilter;

    private String smtp;
    private String from;
    private String to;
    private String user;
    private String password;

    private String graphClientId;
    private String graphUserName;
    private String graphUserPassword;
    private String sharepointFolder;

    public FunctionInput(List<String> entityList, String status, String childFilter) {
        this.entityList = entityList;
        this.status = status;
        this.childFilter = childFilter;
    }

    public List<String> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<String> entityList) {
        this.entityList = entityList;
    }

   
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChildFilter() {
        return childFilter;
    }

    public void setChildFilter(String childFilter) {
        this.childFilter = childFilter;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getGraphClientId() {
        return graphClientId;
    }

    public void setGraphClientId(String graphClientId) {
        this.graphClientId = graphClientId;
    }

    public String getGraphUserName() {
        return graphUserName;
    }

    public void setGraphUserName(String graphUserName) {
        this.graphUserName = graphUserName;
    }

    public String getGraphUserPassword() {
        return graphUserPassword;
    }

    public void setGraphUserPassword(String graphUserPassword) {
        this.graphUserPassword = graphUserPassword;
    }

    public String getSharepointFolder() {
        return sharepointFolder;
    }

    public void setSharepointFolder(String sharepointFolder) {
        this.sharepointFolder = sharepointFolder;
    }
}
