package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;

import java.util.List;

public class TextHandler implements BaseTextHandler{
    private static TextHandler textHandler;
    private final ParagraphHandler paragraphHandler;
    private List<String> text;

    private TextHandler(ParagraphHandler paragraphHandler){
        this.paragraphHandler = paragraphHandler;
    }

    public static TextHandler getTextHandler(List<String> text){
        if (textHandler == null){
            textHandler = new TextHandler(ParagraphHandler.getParagraphHandler());
        }
        textHandler.setText(text);
        return textHandler;
    }

    @Override
    public List<Unit> handleRequest(){
        return null;
    }

    public List<String> getText(){
        return text;
    }

    public void setText(List<String> text){
        this.text = text;
    }
}
