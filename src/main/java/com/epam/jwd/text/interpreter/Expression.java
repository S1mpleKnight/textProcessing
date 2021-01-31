package com.epam.jwd.text.interpreter;

import com.epam.jwd.text.interpreter.api.AbstractExpression;
import com.epam.jwd.text.interpreter.impl.NotTerminalExpression;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionAnd;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionLeftShift;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionNot;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionOr;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionRightShift;
import com.epam.jwd.text.interpreter.impl.TerminalExpressionXor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Expression{
    private final ArrayList<AbstractExpression> listExpression;
    private final ArrayDeque<Integer> contextValues;
    private final List<String> symbols;

    public Expression(List<String> symbols){
        this.listExpression = new ArrayList<>();
        this.contextValues = new ArrayDeque<>();
        this.symbols = symbols;
    }

    public Integer popValue(){
        return contextValues.pop();
    }

    public void pushValue(Integer value){
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
