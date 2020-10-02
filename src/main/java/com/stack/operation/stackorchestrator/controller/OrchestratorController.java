package com.stack.operation.stackorchestrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stack.operation.stackorchestrator.entity.PopResponseData;
import com.stack.operation.stackorchestrator.entity.StackData;
import com.stack.operation.stackorchestrator.service.DownstreamCall;


@Controller
public class OrchestratorController {

    @Autowired
    private DownstreamCall downstreamCall;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/pop", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity pushElement() throws Exception {
        logger.info("HottestRepoController:getHottestRepo():: RepoCount request for hottest repos {}");
        return new ResponseEntity<>(downstreamCall.popCall().getBody(), HttpStatus.OK);

    }

    @RequestMapping(value = "/push", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity pushElement(
            @RequestBody StackData pushData) throws Exception {
        logger.info("HottestRepoController:getHottestRepo():: RepoCount request for hottest repos {}");
        return new ResponseEntity<>(downstreamCall.pushCall(pushData).getBody(), HttpStatus.OK);

    }
}

