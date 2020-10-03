package com.stack.operation.stackorchestrator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.stack.operation.stackorchestrator.entity.PopResponseData;
import com.stack.operation.stackorchestrator.entity.PushResponseData;
import com.stack.operation.stackorchestrator.entity.StackData;

@Service
public class DownstreamCall {

    @Value("${stack.push.host}")
    private String pushHost;

    @Value("${stack.pop.host}")
    private String popHost;
    
    @Autowired
    private RestTemplate restTemplateApi;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<PopResponseData> popCall(String datasource) throws Exception {
        UriComponents popUrl = UriComponentsBuilder.fromHttpUrl(popHost + datasource + "/pop").build();
        logger.info("POP api call : downstreamCall() ::  downstreamURI {}", popUrl.toUriString());
        return restTemplateApi.exchange(popUrl.toUriString(), HttpMethod.GET, null, PopResponseData.class);
    }

    public ResponseEntity<PushResponseData> pushCall(StackData pushData, String datasource) throws Exception {
        UriComponents pushUrl = UriComponentsBuilder.fromHttpUrl(pushHost + datasource + "/push").build();
        logger.info("PUSH api call : downstreamCall() ::  downstreamURI {}", pushUrl.toUriString());
        ResponseEntity<StackData> postData = new ResponseEntity<StackData>(pushData, HttpStatus.OK);
        return restTemplateApi.exchange(pushUrl.toUriString(), HttpMethod.POST, postData,
                PushResponseData.class);
    }
}

