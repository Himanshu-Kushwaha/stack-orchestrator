package com.stack.operation.stackorchestrator.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class PopResponseData {
    
    private List<String> oldState;
    private List<String> newState;
    private String popedElement;
    private String datasource;
    public List<String> getOldState() {
        return oldState;
    }
    public void setOldState(List<String> oldState) {
        this.oldState = oldState;
    }
    public List<String> getNewState() {
        return newState;
    }
    public void setNewState(List<String> newState) {
        this.newState = newState;
    }
    public String getPopedElement() {
        return popedElement;
    }
    public void setPopedElement(String popedElement) {
        this.popedElement = popedElement;
    }
    public String getDatasource() {
        return datasource;
    }
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
}


