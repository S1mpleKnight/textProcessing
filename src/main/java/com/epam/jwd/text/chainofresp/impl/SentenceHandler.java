package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;

import java.util.List;

public class SentenceHandler implements BaseTextHandler{
    private static SentenceHandler sentenceHandler;

    private SentenceHandler(){
    }

    public static SentenceHandler getSentenceHandler(){
        if (sentenceHandler == null){
            sentenceHandler = new SentenceHandler();
        }
        return sentenceHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        return null;
    }
}
