package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SymbolHandler implements BaseTextHandler{
    private static SymbolHandler symbolHandler;
    private static final Logger LOGGER = LoggerFactory.getLogger(SymbolHandler.class);

    private SymbolHandler(){
    }

    public static SymbolHandler getSentenceHandler(){
        if (symbolHandler == null){
            symbolHandler = new SymbolHandler();
        }
        return symbolHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        LOGGER.info("Handle symbol request");
        List<Unit> symbols = new ArrayList<>();
        if (checkSequence(sequence)){
            for (String symbol: sequence){
                symbols.add(new Symbol(symbol.charAt(0)));
            }
        } else {
            symbols.addAll(calculateExpression());
        }
        return symbols;
    }

    private boolean checkSequence(List<String> sequence){
        for (String symbol: sequence){
            if (Symbol.isOperator(symbol.charAt(0))){
                return false;
            }
        }
        return true;
    }

    private List<Unit> calculateExpression(){
        // TODO: 31.01.2021
        return null;
    }
}
