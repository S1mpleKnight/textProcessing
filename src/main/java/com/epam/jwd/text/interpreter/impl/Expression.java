package com.epam.jwd.text.interpreter.impl;

import com.epam.jwd.text.interpreter.api.AbstractExpression;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Expression{
    private ArrayList<AbstractExpression> listExpression;
    private ArrayDeque<Integer> contextValues;
    private List<String> symbols;
    private static Expression expression;

    private Expression(List<String> symbols){
        this.listExpression = new ArrayList<>();
        this.contextValues = new ArrayDeque<>();
        this.symbols = symbols;
    }

    public static Expression getExpression(List<String> symbols){
        if (expression == null){
            expression = new Expression(symbols);
        } else {
            expression.symbols = symbols;
            expression.contextValues = new ArrayDeque<>();
            expression.listExpression = new ArrayList<>();
        }
        return expression;
    }

    Integer popValue(){
        return contextValues.pop();
    }

    void pushValue(Integer value){
        this.contextValues.push(value);
    }

    public Number calculate(){
        parse(symbols);
        for (AbstractExpression terminal : listExpression){
            terminal.interpret(this);
        }
        return this.popValue();
    }

    private void parse(List<String> symbols){
        for (String lexeme : symbols){
            if (lexeme.isEmpty()){
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp){
                case '^':
                    listExpression.add(new TerminalExpressionXor());
                    break;
                case '~':
                    listExpression.add(new TerminalExpressionNot());
                    break;
                case '&':
                    listExpression.add(new TerminalExpressionAnd());
                    break;
                case '|':
                    listExpression.add(new TerminalExpressionOr());
                    break;
                case '>':
                    if (lexeme.length() == 2 && lexeme.charAt(1) == '>'){
                        listExpression.add(new TerminalExpressionRightShift());
                        break;
                    } else {
                        continue;
                    }
                case '<':
                    if (lexeme.length() == 2 && lexeme.charAt(1) == '<'){
                        listExpression.add(new TerminalExpressionLeftShift());
                        break;
                    } else {
                        continue;
                    }
                default:
                    listExpression.add(new NotTerminalExpression(Integer.parseInt(lexeme)));
                    break;
            }
        }
    }
}
