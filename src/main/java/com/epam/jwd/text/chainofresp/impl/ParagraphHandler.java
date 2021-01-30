package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;

import java.util.List;

public class ParagraphHandler implements BaseTextHandler{
    private static ParagraphHandler paragraphHandler;

    private ParagraphHandler(){
    }

    public static ParagraphHandler getParagraphHandler(){
        if (paragraphHandler == null){
            paragraphHandler = new ParagraphHandler();
        }
        return paragraphHandler;
    }

    @Override
    public List<Unit> handleRequest(){
        return null;
    }
}
