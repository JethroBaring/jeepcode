package com.jethro.jeepcode.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.jethro.jeepcode.models.Route;
import com.jethro.jeepcode.services.RouteService;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class RouteController {
    @Autowired
    RouteService service;

    @GetMapping("/code")
    public Route getRouteByJeepCode(@RequestParam String code) {
        return service.getJeepRoute(code);
    }

    @GetMapping("/codes")
    public Map<String, Route> getMethodName(@RequestParam String codes) {
        return service.getJeepsRoutes(codes);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnexpectedErrors(Exception e) {
        return "{\"error\": \"" + e.getMessage() + "\"}";    }
}
