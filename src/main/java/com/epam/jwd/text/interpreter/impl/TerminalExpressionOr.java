package com.epam.jwd.text.interpreter.impl;

import com.epam.jwd.text.interpreter.Expression;
import com.epam.jwd.text.interpreter.api.AbstractExpression;

public class TerminalExpressionOr implements AbstractExpression{
    @Override
    public void interpret(Expression c){
        c.pushValue(c.popValue() | c.popValue());
    }
}
