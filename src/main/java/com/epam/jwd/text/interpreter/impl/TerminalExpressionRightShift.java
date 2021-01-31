package com.epam.jwd.text.interpreter.impl;

import com.epam.jwd.text.interpreter.api.AbstractExpression;

public class TerminalExpressionRightShift implements AbstractExpression{
    @Override
    public void interpret(Expression c){
        int right = c.popValue();
        int left = c.popValue();
        c.pushValue(left >> right);
    }
}
