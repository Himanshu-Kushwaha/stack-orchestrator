package com.stack.operation.stackorchestrator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stack.operation.stackorchestrator.entity.PopResponseData;
import com.stack.operation.stackorchestrator.entity.PushResponseData;
import com.stack.operation.stackorchestrator.entity.StackData;

@Component
public class DownstreamCall {

    @Autowired
    private RestTemplate restTemplateApi;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<PopResponseData> popCall() {
        logger.debug("GitHubApiCall : downstreamCall() ::  downstreamURI {}");
//        try {
            return restTemplateApi.exchange("http://localhost:8078/pop", HttpMethod.GET, null, PopResponseData.class);
//        } catch (HttpStatusCodeException exception) {
//            throw new DownstreamAPIException(exception.getStatusCode(), exception.getMessage());
//        }
    }
    
    public ResponseEntity<PushResponseData> pushCall(StackData pushData) {
        logger.debug("GitHubApiCall : downstreamCall() ::  downstreamURI {}");
            ResponseEntity<StackData> postData = new ResponseEntity<StackData>(pushData, HttpStatus.OK);
            return restTemplateApi.exchange("http://localhost:8077/push", HttpMethod.POST, postData, PushResponseData.class);
    }
}
