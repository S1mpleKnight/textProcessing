package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Symbol;

import java.util.List;

public class LexemeHandler implements BaseTextHandler{
    private static LexemeHandler lexemeHandler;
    private final SymbolHandler symbolHandler;

    private LexemeHandler(SymbolHandler symbolHandler){
        this.symbolHandler = symbolHandler;
    }

    public static LexemeHandler getLexemeHandler(){
        if (lexemeHandler == null){
            lexemeHandler = new LexemeHandler(SymbolHandler.getSentenceHandler());
        }
        return lexemeHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        return null;
    }
}
