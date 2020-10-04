package com.stack.operation.stackorchestrator.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stack.operation.stackorchestrator.entity.PopResponseData;
import com.stack.operation.stackorchestrator.entity.PushResponseData;
import com.stack.operation.stackorchestrator.entity.StackData;
import com.stack.operation.stackorchestrator.service.DownstreamCall;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrchestratorControllerTest {

    @Mock
    private DownstreamCall downstreamCall;
    
    @InjectMocks
    private OrchestratorController orchestratorController;
    
    @Test
    public void testSuccessScenarioPush() throws Exception {
        StackData stackdata = new StackData();
        stackdata.setId(1L);
        stackdata.setPushData("12");
        stackdata.setStackElements("13");

        List<String> stackElement = Arrays.asList(new String[] { "12", "13" });

        PushResponseData responseData = new PushResponseData();
        responseData.setDatasource("mysql");
        responseData.setElements(stackElement);
        ResponseEntity<PushResponseData> pushRes = new ResponseEntity<PushResponseData>(responseData, HttpStatus.OK);

        Mockito.when(downstreamCall.pushCall(Mockito.any(StackData.class), Mockito.anyString())).thenReturn(pushRes);
        ResponseEntity<PushResponseData> responseEntity = orchestratorController.pushElement(stackdata, "mysql");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFailurePush() throws Exception {
        assertEquals(orchestratorController.pushElement(null, null).getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Test
    public void testSuccessScenarioPop() throws Exception {
        StackData stackdata = new StackData();
        stackdata.setId(1L);
        stackdata.setPushData("12");
        stackdata.setStackElements("13");
        List<StackData> stackDataList = new ArrayList<StackData>();
        List<String> listNew = Arrays.asList(new String[] { "12", "13"});
        List<String> listOld = Arrays.asList(new String[] { "12", "13", "14"});
        stackDataList.add(stackdata);

        PopResponseData responseData = new PopResponseData();
        responseData.setDatasource("mysql");
        responseData.setNewState(listNew);
        responseData.setOldState(listOld);
        responseData.setPopedElement("14");
        
        ResponseEntity<PopResponseData> popRes = new ResponseEntity<PopResponseData>(responseData, HttpStatus.OK);
        
        Mockito.when(downstreamCall.popCall("mysql")).thenReturn(popRes);
        ResponseEntity<PopResponseData> responseEntity = orchestratorController.popElement("mysql");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testFailurePop() throws Exception {
        assertEquals(orchestratorController.popElement(null).getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
