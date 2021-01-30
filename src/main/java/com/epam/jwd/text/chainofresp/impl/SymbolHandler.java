package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;

import java.util.List;

public class SymbolHandler implements BaseTextHandler{
    private static SymbolHandler symbolHandler;

    private SymbolHandler(){
    }

    public static SymbolHandler getSentenceHandler(){
        if (symbolHandler == null){
            symbolHandler = new SymbolHandler();
        }
        return symbolHandler;
    }

    @Override
    public List<Unit> handleRequest(){
        return null;
    }
}
