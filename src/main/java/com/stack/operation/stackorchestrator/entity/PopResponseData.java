package com.stack.operation.stackorchestrator.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class PopResponseData {
    
    private List<Integer> oldState;
    private List<Integer> newState;
    private Integer popedElement;
    public List<Integer> getOldState() {
        return oldState;
    }
    public void setOldState(List<Integer> oldState) {
        this.oldState = oldState;
    }
    public List<Integer> getNewState() {
        return newState;
    }
    public void setNewState(List<Integer> newState) {
        this.newState = newState;
    }
    public Integer getPopedElement() {
        return popedElement;
    }
    public void setPopedElement(Integer popedElement) {
        this.popedElement = popedElement;
    }
    
}

