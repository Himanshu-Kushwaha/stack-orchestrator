package com.stack.operation.stackorchestrator.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class PushResponseData {
    
    private List<Integer> elements;

    public List<Integer> getElements() {
        return elements;
    }

    public void setElements(List<Integer> elements) {
        this.elements = elements;
    }
    

}

