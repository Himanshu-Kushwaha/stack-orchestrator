package com.stack.operation.stackorchestrator.entity;

import org.springframework.stereotype.Component;

@Component
public class StackData {

    private static final long serialVersionUID = 1L;

    private String stackElements;
    
    private Long id;
    
    private String pushData;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    

    public String getStackElements() {
        return stackElements;
    }

    public void setStackElements(String stackElements) {
        this.stackElements = stackElements;
    }
    
    public String getPushData() {
        return pushData;
    }

    public void setPushData(String pushData) {
        this.pushData = pushData;
    }

    
}


