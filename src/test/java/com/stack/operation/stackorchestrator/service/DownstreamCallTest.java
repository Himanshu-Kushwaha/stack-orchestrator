package com.stack.operation.stackorchestrator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.stack.operation.stackorchestrator.entity.PopResponseData;
import com.stack.operation.stackorchestrator.entity.PushResponseData;
import com.stack.operation.stackorchestrator.entity.StackData;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DownstreamCallTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    DownstreamCall downStreamCall;
    
    @Test
    public void popCallTest() throws Exception {
        ReflectionTestUtils.setField(downStreamCall,"popHost","http://localhost:8070/");
        PopResponseData popResponseData = new PopResponseData();
        popResponseData.setDatasource("mysql");
        ResponseEntity<PopResponseData> responseEntity = new ResponseEntity<>(popResponseData, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<?>>any(), ArgumentMatchers.<Class<PopResponseData>>any()))
                .thenReturn(responseEntity);
        downStreamCall.popCall("mysql");
        
    }
    
    @Test
    public void pushCallTest() throws Exception {
        ReflectionTestUtils.setField(downStreamCall,"pushHost","http://localhost:8070/");
        PushResponseData pushResponseData = new PushResponseData();
        pushResponseData.setDatasource("mysql");
        StackData stackData = new StackData();
        stackData.setPushData("343");
        ResponseEntity<PushResponseData> responseEntity = new ResponseEntity<>(pushResponseData, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<?>>any(), ArgumentMatchers.<Class<PushResponseData>>any()))
                .thenReturn(responseEntity);
        downStreamCall.pushCall(stackData, "mysql");
        
    }
}
