package com.api.logParse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.logParse.LogParseApplication;
import com.api.logParse.model.Game;

@RestController
@RequestMapping("/api")
public class LogParserController {

    @GetMapping("/game/{id}")
    public Game getGamePorId(@PathVariable(value = "id") final long id) {
        return LogParseApplication.buscaGamePorId(id);
    }

}
