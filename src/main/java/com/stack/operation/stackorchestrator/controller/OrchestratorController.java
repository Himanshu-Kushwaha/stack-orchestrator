package com.stack.operation.stackorchestrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stack.operation.stackorchestrator.entity.StackData;
import com.stack.operation.stackorchestrator.service.DownstreamCall;

@Controller
public class OrchestratorController {

    @Autowired
    private DownstreamCall downstreamCall;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/{datasource}/pop", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity popElement(@PathVariable String datasource) {
        try {
            logger.info("Orchestrator() pop data from :: {}", datasource);
            return new ResponseEntity<>(downstreamCall.popCall(datasource).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error While poping data in {}", datasource);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/{datasource}/push", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity pushElement(@RequestBody StackData pushData, @PathVariable String datasource) {
        try {
            logger.info("Orchestrator() push data in :: {}", datasource);
            return new ResponseEntity<>(downstreamCall.pushCall(pushData, datasource).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error While pushing data in {}", datasource);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

