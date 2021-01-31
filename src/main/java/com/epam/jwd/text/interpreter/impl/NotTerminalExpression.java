package com.epam.jwd.text.interpreter.impl;

import com.epam.jwd.text.interpreter.Expression;
import com.epam.jwd.text.interpreter.api.AbstractExpression;

public class NotTerminalExpression implements AbstractExpression{
    private final int number;

    public NotTerminalExpression(int number){
        this.number = number;
    }

    @Override
    public void interpret(Expression c){
        c.pushValue(number);
    }
}
