package com.stack.operation.stackorchestrator.entity;

import org.springframework.stereotype.Component;

@Component
public class StackData {

    private static final long serialVersionUID = 1L;

    private Integer stackElements;
    
    private Long id;
    
    private Integer pushData;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    

    public Integer getStackElements() {
        return stackElements;
    }

    public void setStackElements(Integer stackElements) {
        this.stackElements = stackElements;
    }
    
    public Integer getPushData() {
        return pushData;
    }

    public void setPushData(Integer pushData) {
        this.pushData = pushData;
    }

    
}

