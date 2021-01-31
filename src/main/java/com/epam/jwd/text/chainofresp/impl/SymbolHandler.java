package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.interpreter.impl.Expression;
import com.epam.jwd.text.rpn.PolishParser;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
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
            symbols.addAll(calculateExpression(sequence));
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

    private List<Unit> calculateExpression(List<String> sequence){
        List<String> rpn = PolishParser.RPN(Arrays.asList(makeValidExpressionString(sequence).split(" ")));
        List<Unit> symbols = new ArrayList<>();
        for (String symbol: Expression.getExpression(rpn).calculate().toString().split("")){
            symbols.add(new Symbol(symbol.charAt(0)));
        }
        return symbols;
    }

    private String makeValidExpressionString(List<String> sequence){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sequence.size(); i++){
            if (sequence.get(i).equals(">")){
                if ((i + 1) < sequence.size() && sequence.get(i + 1).equals(">")){
                    sb.append(">>").append(" ");
                }
            } else if (sequence.get(i).equals("<")){
                if ((i + 1) < sequence.size() && sequence.get(i + 1).equals("<")){
                    sb.append("<<").append(" ");
                }
            } else if (i == sequence.size()-1){
                sb.append(sequence.get(i));
            } else {
                sb.append(sequence.get(i)).append(" ");
            }
        }
        return sb.toString();
    }
}
